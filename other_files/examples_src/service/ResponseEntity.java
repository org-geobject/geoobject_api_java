package org.cendra.commons.util.service;

import java.util.List;

import org.cendra.commons.model.Erasable;
import org.cendra.commons.model.Identifiable;
import org.cendra.commons.util.service.validate.Incidents;

public class ResponseEntity {

	private StatusCode statusCode;
	private String msg;
	private String location;
	private Object body;

	public ResponseEntity(Incidents incidents) {
		this.statusCode = incidents.getStatusCode();
		this.body = incidents;
	}

	public ResponseEntity(ServiceError serviceError) {
		this.statusCode = serviceError.getStatusCode();
		this.msg = serviceError.getTitle();
		this.body = serviceError;
	}

	public ResponseEntity(ServiceException serviceException) {
		this.statusCode = serviceException.getStatusCode();
		this.msg = "Error interno e inesperado.";
		this.body = serviceException;
	}

	@SuppressWarnings("rawtypes")
	public ResponseEntity(String verb, boolean isListBody, Object body) {

		if (Verbs.GET.equalsIgnoreCase(verb)) {

			if (isListBody && (body == null || ((List) body).size() == 0)) {
				// Evalua si la lista es nula o esta vacia, si es asi
				// retorna 204 y un cuepo vacio

				this.statusCode = StatusCode.NO_CONTENT;
				this.location = null;
				this.msg = "[Cero elementos] La búsqueda retorno una lista vacia de elementos.";
				this.body = null;

			} else if (isListBody && body != null && ((List) body).size() > 0) {

				// Retorna 200 y un cuerpo con el resultado

				this.statusCode = StatusCode.OK;
				this.location = null;
				this.msg = "[OK] La búsqueda retorno una lista con " + ((List) body).size() + " elementos.";
				this.body = body;

			} else if (body == null) {
				// EVALUA si el resultado es nulo, si es asi retorna 404 y un
				// cuepo vacio

				this.statusCode = StatusCode.NOT_FOUND;
				this.location = null;
				this.msg = "[No encontrado] Recurso solicitado no existente.";
				this.body = null;

			} else if (body != null && body instanceof Erasable
					&& ((Erasable) body).getErased() != null
					&& ((Erasable) body).getErased() == true) {
				// EVALUA si el resultado esta borrado logicamente, si
				// es asi retorna 410 y un cuepo vacio

				this.statusCode = StatusCode.GONE;
				this.location = null;
				this.msg = "[Retirado] Recurso que ya no está disponible.";
				this.body = null;

			} else if (body != null) {
				// Retorna 200 y un cuerpo con el resultado

				this.statusCode = StatusCode.OK;
				this.location = null;
				this.msg = "[OK] La búsqueda retorno el recurso especificado.";
				this.body = body;

			}
		} else if (Verbs.PUT.equalsIgnoreCase(verb) && body != null) {
			// Retorna 200, un cuepo con el resultado

			this.statusCode = StatusCode.OK;
			this.location = null;
			this.msg = "[OK] La actualización (full) se logrón con éxito. Se retorna el recurso actualizado.";
			this.body = body;

		} else if (Verbs.PATCH.equalsIgnoreCase(verb) && body != null) {
			// Retorna 200, un cuepo con el resultado

			this.statusCode = StatusCode.OK;
			this.location = null;
			this.msg = "[OK] La actualización parcial se logrón con éxito. Se retorna el recurso actualizado.";
			this.body = body;

		} else if (Verbs.DELETE.equalsIgnoreCase(verb)) {
			// Retorna 200, un cuepo vacio

			this.statusCode = StatusCode.OK;
			this.location = null;
			this.msg = "[OK] El borrado se logrón con éxito. Se retorna un cuerpo vacio.";
			this.body = null;

		} else if (Verbs.POST.equalsIgnoreCase(verb) && body != null
				&& body instanceof Identifiable
				&& ((Identifiable) body).getId() != null
				&& ((Identifiable) body).getId().isEmpty() == false) {
			// Retorna 200, un cuepo con el resultado

			this.statusCode = StatusCode.CREATED;
			this.location = ((Identifiable) body).getId();
			this.msg = "[OK] La creación se logró con éxito. Se retorna el recurso creado y su id.";
			this.body = body;
		} else {
			throw new IllegalArgumentException(
					"No se puede crear el objeto "
							+ this.getClass().getCanonicalName()
							+ " debido a que se especificaron mal sus argumentos de entrada.\nVarbo:"
							+ verb + "\nEs una lista: " + isListBody
							+ "\nCuerpo:" + body);
		}

	}

	public ResponseEntity(StatusCode statusCode, String msg) {
		init(statusCode, null, msg, null);
	}

	public ResponseEntity(StatusCode statusCode, String msg, Object body) {
		init(statusCode, null, msg, body);
	}

	public ResponseEntity(StatusCode statusCode, String location, String msg,
			Object body) {
		init(statusCode, location, msg, body);
	}

	private void init(StatusCode statusCode, String location, String msg,
			Object body) {
		this.statusCode = statusCode;
		this.location = location;
		this.msg = msg;
		this.body = body;
	}

	public boolean hasBody() {
		return (this.body != null);
	}

	public boolean hasBodyBusiness() {

		return hasBody() && body instanceof ServiceException == false
				&& body instanceof ServiceError == false
				&& body instanceof Incidents == false;
	}

	public boolean hasBodyServiceException() {

		return hasBody() && body instanceof ServiceException;
	}

	public boolean hasBodyServiceError() {

		return hasBody() && body instanceof ServiceError;
	}

	public boolean hasBodyIncidents() {

		return hasBody() && body instanceof Incidents;
	}

	public Object getBody() {
		return this.body;
	}

	public StatusCode getStatusCode() {
		return statusCode;
	}

	public String getMsg() {
		return msg;
	}

	public String getLocation() {
		return location;
	}

	@Override
	public String toString() {
		return "ResponseEntity [body=" + body + ", statusCode=" + statusCode
				+ ", msg=" + msg + ", location=" + location + "]";
	}

}
