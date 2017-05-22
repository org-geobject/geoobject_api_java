package org.cendra.commons.util.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cendra.commons.AbstractContext;
import org.cendra.commons.util.error.LogPrinter;
import org.cendra.commons.util.model.Identifiable;

public abstract class AbstractServicesServerContext extends AbstractContext {

	protected Map<String, ServiceMetaData> servicesMetaData = new HashMap<String, ServiceMetaData>();

	public AbstractServicesServerContext() {
		super();
		try {
			initServicesMetaData();

		} catch (Exception e) {
			e.printStackTrace();
			new LogPrinter().print(AbstractContext.class.getName(),
					LogPrinter.LEVEL_FATAL, e);

		}
	}

	abstract protected void initServicesMetaData();

	public synchronized ResponseEntity execute(String verb, String url,
			String user, String password) {
		return execute(verb, url, new HashMap<String, Object>(),
				"application/json", null, user, password);
	}

	public synchronized ResponseEntity execute(String verb, String url,
			Map<String, Object> requestParams, String requestContentType,
			Object requestBody, String user, String password) {

		try {

			// -------------------------------------------------------------
			saveRequestToDebugLog(verb, url, requestParams, requestContentType,
					requestBody);
			// -------------------------------------------------------------
			boolean b = checkUrlAndRequestParams(verb, url, requestParams);
			if (b == false) { // Ill-formed ?
				// return 400 Bad Request
				ResponseEntity responseEntity = new ResponseEntity(
						StatusCode.BAD_REQUEST,
						"URL y/o Request Params mal formados.");
				saveCheckUrlAndRequestParamsToWaningLog(
						responseEntity.getStatusCode(),
						responseEntity.getMsg(), verb, url, requestParams);
				saveResponseToDebugLog(responseEntity.getStatusCode(),
						responseEntity.getMsg(), verb, url, requestParams,
						requestContentType, requestBody, responseEntity);
				return responseEntity;

			}
			// -------------------------------------------------------------
			b = checkContentType(requestContentType);
			if (b == false) { // not supported ?
				// return 415 Unsupported Media Type
				ResponseEntity responseEntity = new ResponseEntity(
						StatusCode.UNSUPPORTED_MEDIA_TYPE,
						"Content Type no soportado.");
				saveCheckContentTypeToWaningLog(responseEntity.getStatusCode(),
						responseEntity.getMsg(), verb, url, requestParams,
						requestContentType);
				saveResponseToDebugLog(responseEntity.getStatusCode(),
						responseEntity.getMsg(), verb, url, requestParams,
						requestContentType, requestBody, responseEntity);
				return responseEntity;
			}
			// -------------------------------------------------------------
			b = checkRequestBodyWellFormed(requestBody);
			if (b == false) { // Ill-formed ?
				// return 400 Bad Request
				ResponseEntity responseEntity = new ResponseEntity(
						StatusCode.BAD_REQUEST,
						"Request Body mal formado, no corresponde con su content type.");
				saveCheckRequestBodyWellFormedToWaningLog(
						responseEntity.getStatusCode(),
						responseEntity.getMsg(), verb, url, requestParams,
						requestContentType, requestBody);
				saveResponseToDebugLog(responseEntity.getStatusCode(),
						responseEntity.getMsg(), verb, url, requestParams,
						requestContentType, requestBody, responseEntity);
				return responseEntity;
			}
			// -------------------------------------------------------------
			if (verb.equalsIgnoreCase(Verbs.POST)
					|| verb.equalsIgnoreCase(Verbs.PUT)
					|| verb.equalsIgnoreCase(Verbs.PATCH)) {
				// -------------------------------------------------------------
				b = checkRequestBodyIsNotNull(requestBody);
				if (b == false) { // is null ?
					// return 409 Conflict
					ResponseEntity responseEntity = new ResponseEntity(
							StatusCode.CONFLICT,
							"Request Body nulo (vacio), no está permitido para verbos POST, PUT y PATCH.");
					saveCheckRequestBodyIsNotNullWellFormedToWaningLog(
							responseEntity.getStatusCode(),
							responseEntity.getMsg(), verb, url, requestParams,
							requestContentType);
					saveResponseToDebugLog(responseEntity.getStatusCode(),
							responseEntity.getMsg(), verb, url, requestParams,
							requestContentType, requestBody, responseEntity);
					return responseEntity;
				}
				// -------------------------------------------------------------
			}
			// -------------------------------------------------------------
			b = checkCredential(user, password);
			if (b == false) { // not valid ?
				// return 401 Unauthorized
				ResponseEntity responseEntity = new ResponseEntity(
						StatusCode.UNAUTHORIZED,
						"Usuario y/o clave incorrecto.");
				saveCheckCredentialToSecurityWaningLog(
						responseEntity.getStatusCode(),
						responseEntity.getMsg(), verb, url, requestParams,
						requestContentType, requestBody, user, password);
				saveResponseToDebugLog(responseEntity.getStatusCode(),
						responseEntity.getMsg(), verb, url, requestParams,
						requestContentType, requestBody, responseEntity);
				return responseEntity;
			}
			// -------------------------------------------------------------
			b = checkPermissions(user);
			if (b == false) { // not valid ?
				// return 403 Forbidden
				ResponseEntity responseEntity = new ResponseEntity(
						StatusCode.FORBIDDEN, "El usuario no tiene permiso.");
				saveCheckPermissionsToSecurityWaningLog(
						responseEntity.getStatusCode(),
						responseEntity.getMsg(), verb, url, requestParams,
						requestContentType, requestBody, user, password);
				saveResponseToDebugLog(responseEntity.getStatusCode(),
						responseEntity.getMsg(), verb, url, requestParams,
						requestContentType, requestBody, responseEntity);
				return responseEntity;
			}
			// -------------------------------------------------------------
			b = checkEnable(verb, url);
			if (b == false) { // disable ?
				// return 503 Service Unavailable
				ResponseEntity responseEntity = new ResponseEntity(
						StatusCode.SERVICE_UNAVAILABLE,
						"El servicio no está disponible en estos momentos, el mismo ha sido deshabilitado por el administrador.");
				saveCheckEnableToDisabledWarningLog(
						responseEntity.getStatusCode(),
						responseEntity.getMsg(), verb, url);
				saveResponseToDebugLog(responseEntity.getStatusCode(),
						responseEntity.getMsg(), verb, url, requestParams,
						requestContentType, requestBody, responseEntity);
				return responseEntity;
			}
			// -------------------------------------------------------------
			b = checkOperable(verb, url);
			if (b == false) { // disable ?
				// return 503 Service Unavailable
				ResponseEntity responseEntity = new ResponseEntity(
						StatusCode.SERVICE_UNAVAILABLE,
						"El servicio no está disponible en estos momentos, el mismo se ha caído o no está en condiciones de brindar el servicio.");
				saveCheckOperableToOperableFatalLog(
						responseEntity.getStatusCode(),
						responseEntity.getMsg(), verb, url);
				saveResponseToDebugLog(responseEntity.getStatusCode(),
						responseEntity.getMsg(), verb, url, requestParams,
						requestContentType, requestBody, responseEntity);
				return responseEntity;
			}
			// -------------------------------------------------------------
			b = checkNotDeprecated(verb, url);
			if (b == false) { // deprecated ?
				// return 410 Gone
				ResponseEntity responseEntity = new ResponseEntity(
						StatusCode.GONE,
						"El servicio no está disponible, el mismo ha sido deprecado.");
				saveCheckNotDeprecatedToDeprecatedWarningLog(
						responseEntity.getStatusCode(),
						responseEntity.getMsg(), verb, url);
				saveResponseToDebugLog(responseEntity.getStatusCode(),
						responseEntity.getMsg(), verb, url, requestParams,
						requestContentType, requestBody, responseEntity);
				return responseEntity;
			}
			// -------------------------------------------------------------
			Object responseBody = null;
			try {
				responseBody = execute(verb, url, requestParams, requestBody);
			} catch (Exception e) {
				// return 500 Internal Server Error

				ServiceException serviceException = new ServiceException(e);

				ResponseEntity responseEntity = new ResponseEntity(
						StatusCode.INTERNAL_SERVER_ERROR,
						"El servicio arrojo un error interno e inesperado.",
						serviceException);
				saveExecuteToInternalErrorLog(responseEntity.getStatusCode(),
						responseEntity.getMsg(), verb, url, requestParams,
						requestContentType, requestBody, serviceException);
				saveResponseToDebugLog(responseEntity.getStatusCode(),
						responseEntity.getMsg(), verb, url, requestParams,
						requestContentType, requestBody, responseEntity);
				return responseEntity;
			}
			// -------------------------------------------------------------
			if (verb.equalsIgnoreCase(Verbs.GET)) {

				if (responseBodyIsList(verb, url)) {
					// -------------------------------------------------------------
					b = checkResponseBodyIsListAndNotNullAndNotEmpty(verb, url,
							responseBody);
					if (b == false) { // is list and is null or is empty
						// return 204 No Content
						ResponseEntity responseEntity = new ResponseEntity(
								StatusCode.NO_CONTENT,
								"[Cero elementos] La búsqueda retorno una lista vacia de elementos.");
						saveResponseToDebugLog(responseEntity.getStatusCode(),
								responseEntity.getMsg(), verb, url,
								requestParams, requestContentType, requestBody,
								responseEntity);
						return responseEntity;
					}

					// -------------------------------------------------------------
					// return 200 OK
					@SuppressWarnings("rawtypes")
					ResponseEntity responseEntity = new ResponseEntity(
							StatusCode.OK,
							"[OK] La búsqueda retorno una lista con "
									+ ((List) responseBody).size()
									+ " elementos.", responseBody);
					saveResponseToDebugLog(responseEntity.getStatusCode(),
							responseEntity.getMsg(), verb, url, requestParams,
							requestContentType, requestBody, responseEntity);
					return responseEntity;
					// -------------------------------------------------------------
				} else {
					// -------------------------------------------------------------
					b = checkResponseBodyIsNotListAndNotNull(verb, url,
							responseBody);
					if (b == false) { // is not list and is null
						// return 404 Not Found
						ResponseEntity responseEntity = new ResponseEntity(
								StatusCode.NOT_FOUND,
								"[No encontrado] Recurso solicitado no existente.");
						saveResponseToDebugLog(responseEntity.getStatusCode(),
								responseEntity.getMsg(), verb, url,
								requestParams, requestContentType, requestBody,
								responseEntity);
						return responseEntity;
					}
					// -------------------------------------------------------------
					b = checkResponseBodyNotErased(verb, url, responseBody);
					if (b == false) { // is not list and erased
						// return 410 Gone
						ResponseEntity responseEntity = new ResponseEntity(
								StatusCode.GONE,
								"[Retirado] Recurso que ya no está disponible.");
						saveResponseToDebugLog(responseEntity.getStatusCode(),
								responseEntity.getMsg(), verb, url,
								requestParams, requestContentType, requestBody,
								responseEntity);
						return responseEntity;
					}
					// -------------------------------------------------------------
					// return 200 OK
					ResponseEntity responseEntity = new ResponseEntity(
							StatusCode.OK,
							"[OK] La búsqueda retorno el recurso solicitado.",
							responseBody);
					saveResponseToDebugLog(responseEntity.getStatusCode(),
							responseEntity.getMsg(), verb, url, requestParams,
							requestContentType, requestBody, responseEntity);
					return responseEntity;
					// -------------------------------------------------------------
				}

			} else if (verb.equalsIgnoreCase(Verbs.DELETE)) {
				// -------------------------------------------------------------
				// return 200 OK
				ResponseEntity responseEntity = new ResponseEntity(
						StatusCode.OK,
						"[OK] El borrado se logró con éxito. Se retorna un cuerpo vacio.");
				saveResponseToDebugLog(responseEntity.getStatusCode(),
						responseEntity.getMsg(), verb, url, requestParams,
						requestContentType, requestBody, responseEntity);
				return responseEntity;
				// -------------------------------------------------------------
			} else if (verb.equalsIgnoreCase(Verbs.PUT)) {
				// -------------------------------------------------------------
				b = checkResponseBodyIsNotNull(verb, url, responseBody);
				if (b == false) { // is null
					// return 422 Unprocessable Entity
					ResponseEntity responseEntity = new ResponseEntity(
							StatusCode.UNPROCESSABLE_ENTITY,
							"[Error] El response body es nulo.");
					saveCheckResponseBodyIsNotNullToResponseBodyErrorLog(
							responseEntity.getStatusCode(),
							responseEntity.getMsg(), verb, url, requestParams,
							requestContentType, requestBody, responseEntity);
					saveResponseToDebugLog(responseEntity.getStatusCode(),
							responseEntity.getMsg(), verb, url, requestParams,
							requestContentType, requestBody, responseEntity);
					return responseEntity;
				}
				// -------------------------------------------------------------
				// return 200 OK
				ResponseEntity responseEntity = new ResponseEntity(
						StatusCode.OK,
						"[OK] La actualización (full) se logrón con éxito. Se retorna el recurso actualizado.",
						responseBody);
				saveResponseToDebugLog(responseEntity.getStatusCode(),
						responseEntity.getMsg(), verb, url, requestParams,
						requestContentType, requestBody, responseEntity);
				return responseEntity;
				// -------------------------------------------------------------
			} else if (verb.equalsIgnoreCase(Verbs.PATCH)) {
				// -------------------------------------------------------------
				b = checkResponseBodyIsNotNull(verb, url, responseBody);
				if (b == false) { // is null
					// return 422 Unprocessable Entity
					ResponseEntity responseEntity = new ResponseEntity(
							StatusCode.UNPROCESSABLE_ENTITY,
							"[Error] El response body es nulo.");
					saveCheckResponseBodyIsNotNullToResponseBodyErrorLog(
							responseEntity.getStatusCode(),
							responseEntity.getMsg(), verb, url, requestParams,
							requestContentType, requestBody, responseEntity);
					saveResponseToDebugLog(responseEntity.getStatusCode(),
							responseEntity.getMsg(), verb, url, requestParams,
							requestContentType, requestBody, responseEntity);
					return responseEntity;
				}
				// -------------------------------------------------------------
				// return 200 OK
				ResponseEntity responseEntity = new ResponseEntity(
						StatusCode.OK,
						"[OK] La actualización parcial se logrón con éxito. Se retorna el recurso actualizado.",
						responseBody);
				saveResponseToDebugLog(responseEntity.getStatusCode(),
						responseEntity.getMsg(), verb, url, requestParams,
						requestContentType, requestBody, responseEntity);
				return responseEntity;
				// -------------------------------------------------------------
			} else if (verb.equalsIgnoreCase(Verbs.POST)) {
				// -------------------------------------------------------------
				b = checkResponseBodyIsNotNull(verb, url, responseBody);
				if (b == false) { // is null
					// return 422 Unprocessable Entity
					ResponseEntity responseEntity = new ResponseEntity(
							StatusCode.UNPROCESSABLE_ENTITY,
							"[Error] El response body es nulo.");
					saveCheckResponseBodyIsNotNullToResponseBodyErrorLog(
							responseEntity.getStatusCode(),
							responseEntity.getMsg(), verb, url, requestParams,
							requestContentType, requestBody, responseEntity);
					saveResponseToDebugLog(responseEntity.getStatusCode(),
							responseEntity.getMsg(), verb, url, requestParams,
							requestContentType, requestBody, responseEntity);
					return responseEntity;
				}
				// -------------------------------------------------------------
				// return 201 Created
				ResponseEntity responseEntity = new ResponseEntity(
						StatusCode.CREATED,
						((Identifiable) responseBody).getId(),
						"[OK] La creación se logró con éxito. Se retorna el recurso creado y su id.",
						responseBody);
				saveResponseToDebugLog(responseEntity.getStatusCode(),
						responseEntity.getMsg(), verb, url, requestParams,
						requestContentType, requestBody, responseEntity);
				return responseEntity;
				// -------------------------------------------------------------
			} else {
				// return 405 Method Not Allowed
				ResponseEntity responseEntity = new ResponseEntity(
						StatusCode.METHOD_NOT_ALLOWED,
						"[Error] Se ha empleado un verbo no aceptado por el recurso.");
				saveCheckVerbToVerbWarningLog(responseEntity.getStatusCode(),
						responseEntity.getMsg(), verb, url, requestParams,
						requestContentType, requestBody, responseEntity);
				saveResponseToDebugLog(responseEntity.getStatusCode(),
						responseEntity.getMsg(), verb, url, requestParams,
						requestContentType, requestBody, responseEntity);
				return responseEntity;
			}

			// -------------------------------------------------------------

		} catch (Exception e) {
			new LogPrinter().print(AbstractContext.class.getName(),
					LogPrinter.LEVEL_FATAL, e);
		}
		return null;

	}

	// -------------------------------------------------------------

	private void saveRequestToDebugLog(String verb, String url,
			Map<String, Object> requestParams, String requestContentType,
			Object requestBody) {
	}

	// -------------------------------------------------------------

	private boolean checkUrlAndRequestParams(String verb, String url,
			Map<String, Object> requestParams) {
		return true;
	}

	private void saveCheckUrlAndRequestParamsToWaningLog(StatusCode statusCode,
			String msg, String verb, String url,
			Map<String, Object> requestParams) {
	}

	// -------------------------------------------------------------

	private boolean checkContentType(String requestContentType) {
		return true;
	}

	private void saveCheckContentTypeToWaningLog(StatusCode statusCode,
			String msg, String verb, String url,
			Map<String, Object> requestParams, String requestContentType) {
	}

	// -------------------------------------------------------------

	private boolean checkRequestBodyWellFormed(Object requestBody) {
		return true;
	}

	private void saveCheckRequestBodyWellFormedToWaningLog(
			StatusCode statusCode, String msg, String verb, String url,
			Map<String, Object> requestParams, String requestContentType,
			Object requestBody) {
	}

	// -------------------------------------------------------------

	private boolean checkRequestBodyIsNotNull(Object requestBody) {
		return true;
	}

	private void saveCheckRequestBodyIsNotNullWellFormedToWaningLog(
			StatusCode statusCode, String msg, String verb, String url,
			Map<String, Object> requestParams, String requestContentType) {
	}

	// -------------------------------------------------------------

	private boolean checkCredential(String user, String password) {
		return true;
	}

	private void saveCheckCredentialToSecurityWaningLog(StatusCode statusCode,
			String msg, String verb, String url,
			Map<String, Object> requestParams, String requestContentType,
			Object requestBody, String user, String password) {
	}

	// -------------------------------------------------------------

	private boolean checkPermissions(String user) {
		return true;
	}

	private void saveCheckPermissionsToSecurityWaningLog(StatusCode statusCode,
			String msg, String verb, String url,
			Map<String, Object> requestParams, String requestContentType,
			Object requestBody, String user, String password) {
	}

	// -------------------------------------------------------------

	private boolean checkEnable(String verb, String url) {
		return true;
	}

	private void saveCheckEnableToDisabledWarningLog(StatusCode statusCode,
			String msg, String verb, String url) {
	}

	// -------------------------------------------------------------

	private boolean checkOperable(String verb, String url) {
		return true;
	}

	private void saveCheckOperableToOperableFatalLog(StatusCode statusCode,
			String msg, String verb, String url) {
	}

	// -------------------------------------------------------------

	private boolean checkNotDeprecated(String verb, String url) {
		return true;
	}

	private void saveCheckNotDeprecatedToDeprecatedWarningLog(
			StatusCode statusCode, String msg, String verb, String url) {
	}

	// -------------------------------------------------------------

	protected abstract Object execute(String verb, String url,
			Map<String, Object> requestParams, Object requestBody)
			throws Exception;

	private void saveExecuteToInternalErrorLog(StatusCode statusCode,
			String msg, String verb, String url,
			Map<String, Object> requestParams, String requestContentType,
			Object requestBody, ServiceException serviceException) {
	}

	// -------------------------------------------------------------

	private boolean responseBodyIsList(String verb, String url) {

		return true;
	}

	// -------------------------------------------------------------

	private boolean checkResponseBodyIsListAndNotNullAndNotEmpty(String verb,
			String url, Object responseBody) {
		return true;
	}

	// -------------------------------------------------------------

	private boolean checkResponseBodyIsNotListAndNotNull(String verb,
			String url, Object responseBody) {
		return true;
	}

	// -------------------------------------------------------------

	private boolean checkResponseBodyNotErased(String verb, String url,
			Object responseBody) {
		return true;
	}

	// -------------------------------------------------------------

	private boolean checkResponseBodyIsNotNull(String verb, String url,
			Object responseBody) {
		return true;
	}

	private void saveCheckResponseBodyIsNotNullToResponseBodyErrorLog(
			StatusCode statusCode, String msg, String verb, String url,
			Map<String, Object> requestParams, String requestContentType,
			Object requestBody, ResponseEntity responseEntity) {
	}

	// -------------------------------------------------------------

	private void saveCheckVerbToVerbWarningLog(StatusCode statusCode,
			String msg, String verb, String url,
			Map<String, Object> requestParams, String requestContentType,
			Object requestBody, ResponseEntity responseEntity) {
	}

	// -------------------------------------------------------------

	private void saveResponseToDebugLog(StatusCode statusCode, String msg,
			String verb, String url, Map<String, Object> requestParams,
			String requestContentType, Object requestBody,
			ResponseEntity responseEntity) {
	}

	// -------------------------------------------------------------

}
