package org.cendra.commons.util.service;

/**
 * Java 5 enumeration of HTTP status codes.
 *
 * <p>
 * The HTTP status code series can be retrieved via {@link #series()}.
 *
 * 
 * @see StatusCode.Series
 * @see <a href="http://www.iana.org/assignments/http-status-codes">HTTP Status
 *      Code Registry</a>
 */
public enum StatusCode {

	/**
	 * {@code 200 OK}.
	 * 
	 * @see <a
	 *      href="http://tools.ietf.org/html/rfc2616#section-10.2.1">HTTP/1.1</a>
	 * 
	 *      200 OK - Respuesta a un exitoso GET, PUT, PATCH o DELETE. Puede ser
	 *      usado también para un POST que no resulta en una creación.
	 * 
	 */
	OK(200, "OK"),

	/**
	 * {@code 201 Created}.
	 * 
	 * @see <a
	 *      href="http://tools.ietf.org/html/rfc2616#section-10.2.2">HTTP/1.1</a>
	 * 
	 *      201 Created – [Creada] Respuesta a un POST que resulta en una
	 *      creación. Debería ser combinado con un encabezado Location,
	 *      apuntando a la ubicación del nuevo recurso.
	 * 
	 */
	CREATED(201, "Created"),

	/**
	 * {@code 204 No Content}.
	 * 
	 * @see <a
	 *      href="http://tools.ietf.org/html/rfc2616#section-10.2.5">HTTP/1.1</a>
	 * 
	 *      204 No Content – [Sin Contenido] Respuesta a una petición exitosa
	 *      que no devuelve un body (como una petición DELETE). Tambien usado
	 *      cuando se devuelve una lista vacia, ejmplo /continentes/EU/paises
	 * 
	 */
	NO_CONTENT(204, "No Content"),

//	/**
//	 * {@code 304 Not Modified}.
//	 * 
//	 * @see <a
//	 *      href="http://tools.ietf.org/html/rfc2616#section-10.3.5">HTTP/1.1</a>
//	 * 
//	 *      304 Not Modified – [No Modificada] Usado cuando el cacheo de
//	 *      encabezados HTTP está activo
//	 */
//	NOT_MODIFIED(304, "Not Modified"),

	/**
	 * {@code 400 Bad Request}.
	 * 
	 * @see <a
	 *      href="http://tools.ietf.org/html/rfc2616#section-10.4.1">HTTP/1.1</a>
	 * 
	 *      400 Bad Request – [Petición Errónea] La petición está malformada,
	 *      como por ejemplo, si el contenido no fue bien parseado.
	 * 
	 */
	BAD_REQUEST(400, "Bad Request"),

	/**
	 * {@code 401 Unauthorized}.
	 * 
	 * @see <a
	 *      href="http://tools.ietf.org/html/rfc2616#section-10.4.2">HTTP/1.1</a>
	 * 
	 *      401 Unauthorized – [Desautorizada] Cuando los detalles de
	 *      autenticación son inválidos o no son otorgados. También útil para
	 *      disparar un popup de autorización si la API es usada desde un
	 *      navegador.
	 * 
	 */
	UNAUTHORIZED(401, "Unauthorized"),

	/**
	 * {@code 403 Forbidden}.
	 * 
	 * @see <a
	 *      href="http://tools.ietf.org/html/rfc2616#section-10.4.4">HTTP/1.1</a>
	 * 
	 *      403 Forbidden – [Prohibida] Cuando la autenticación es exitosa pero
	 *      el usuario no tiene permiso al recurso en cuestión.
	 * 
	 */
	FORBIDDEN(403, "Forbidden"),

	/**
	 * {@code 404 Not Found}.
	 * 
	 * @see <a
	 *      href="http://tools.ietf.org/html/rfc2616#section-10.4.5">HTTP/1.1</a>
	 * 
	 *      404 Not Found – [No encontrada] Cuando un recurso no existente es
	 *      solicitado. Por ejemplo /continentes/EU/paises/AR32
	 * 
	 */
	NOT_FOUND(404, "Not Found"),

	/**
	 * {@code 405 Method Not Allowed}.
	 * 
	 * @see <a
	 *      href="http://tools.ietf.org/html/rfc2616#section-10.4.6">HTTP/1.1</a>
	 * 
	 *      405 Method Not Allowed – [Método no permitido] Cuando un método HTTP
	 *      que está siendo pedido no está permitido para el usuario
	 *      autenticado.
	 */
	METHOD_NOT_ALLOWED(405, "Method Not Allowed"),

	/**
	 * {@code 409 Conflict}.
	 * 
	 * @see <a
	 *      href="http://tools.ietf.org/html/rfc2616#section-10.4.10">HTTP/1.1</a>
	 * @see <a http://www.restpatterns.org/HTTP_Status_Codes/409_-_Conflict</a>
	 * 
	 *      Usarlo para validacones de entrada.
	 * 
	 */
	CONFLICT(409, "Conflict"),

	/**
	 * {@code 410 Gone}.
	 * 
	 * @see <a
	 *      href="http://tools.ietf.org/html/rfc2616#section-10.4.11">HTTP/1.1</a>
	 * 
	 *      410 Gone – [Retirado] Indica que el recurso en ese endpoint ya no
	 *      está disponible. Útil como una respuesta en blanco para viejas
	 *      versiones de la API, es decir uun endpoint deprecado. También es
	 *      util para identificar un borrado lógico, como por ejemplo el país
	 *      Yugoslavia con la url /continentes/EU/paises/YU
	 */
	GONE(410, "Gone"),

	/**
	 * {@code 415 Unsupported Media Type}.
	 * 
	 * @see <a
	 *      href="http://tools.ietf.org/html/rfc2616#section-10.4.16">HTTP/1.1</a>
	 * 
	 *      415 Unsupported Media Type – [Tipo de contenido no soportado] Si el
	 *      tipo de contenido que solicita la petición es incorrecto
	 * 
	 */
	UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),

	/**
	 * {@code 422 Unprocessable Entity}.
	 * 
	 * @see <a href="http://tools.ietf.org/html/rfc4918#section-11.2">WebDAV</a>
	 * 
	 *      422 Unprocessable Entity – [Entidad improcesable] Utilizada para
	 *      errores de validación. Se aplica a la validación de salida.
	 * 
	 */
	UNPROCESSABLE_ENTITY(422, "Unprocessable Entity"),

//	/**
//	 * {@code 429 Too Many Requests}.
//	 * 
//	 * @see <a href="http://tools.ietf.org/html/rfc2817#section-6">Upgrading to
//	 *      TLS Within HTTP/1.1</a>
//	 * 
//	 *      429 Too Many Requests – [Demasiadas peticiones] Cuando una petición
//	 *      es rechazada debido a la tasa límite .
//	 * 
//	 */
//	MANY_REQUESTS(429, "Many Requests"),

	// --- 5xx Server Error ---

	/**
	 * {@code 500 Internal Server Error}.
	 * 
	 * @see <a
	 *      href="http://tools.ietf.org/html/rfc2616#section-10.5.1">HTTP/1.1</a>
	 */
	INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

	/**
	 * {@code 501 Not Implemented}.
	 * 
	 * @see <a
	 *      href="http://tools.ietf.org/html/rfc2616#section-10.5.2">HTTP/1.1</a>
	 * 
	 *      Similar a los carteles de "sitio en construcción" o por ejemplo a
	 * 
	 */
	NOT_IMPLEMENTED(501, "Not Implemented"),

	/**
	 * {@code 503 Service Unavailable}.
	 * 
	 * @see <a
	 *      href="http://tools.ietf.org/html/rfc2616#section-10.5.4">HTTP/1.1</a>
	 * 
	 *      Por ejemplo si nuestra api tiene o esta en un servicio de
	 *      mantenimiento. Claro esta que usted debería devolver un mensaje
	 *      amigable comentando por ejemplo el por que no esta disponible el
	 *      servicio, y/o por cuanto tiempo lo estará.
	 * 
	 * 
	 */
	SERVICE_UNAVAILABLE(503, "Service Unavailable");

	private final int value;

	private final String reasonPhrase;

	private StatusCode(int value, String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
	}

	/**
	 * Return the integer value of this status code.
	 */
	public int value() {
		return this.value;
	}

	/**
	 * Return the reason phrase of this status code.
	 */
	public String getReasonPhrase() {
		return reasonPhrase;
	}

	/**
	 * Returns the HTTP status series of this status code.
	 * 
	 * @see StatusCode.Series
	 */
	public Series series() {
		return Series.valueOf(this);
	}

	/**
	 * Return a string representation of this status code.
	 */
	@Override
	public String toString() {
		return Integer.toString(value);
	}

	/**
	 * Return the enum constant of this type with the specified numeric value.
	 * 
	 * @param statusCode
	 *            the numeric value of the enum to be returned
	 * @return the enum constant with the specified numeric value
	 * @throws IllegalArgumentException
	 *             if this enum has no constant for the specified numeric value
	 */
	public static StatusCode valueOf(int statusCode) {
		for (StatusCode status : values()) {
			if (status.value == statusCode) {
				return status;
			}
		}
		throw new IllegalArgumentException("No matching constant for ["
				+ statusCode + "]");
	}

	/**
	 * Java 5 enumeration of HTTP status series.
	 * <p>
	 * Retrievable via {@link StatusCode#series()}.
	 */
	public static enum Series {

		INFORMATIONAL(1), SUCCESSFUL(2), REDIRECTION(3), CLIENT_ERROR(4), SERVER_ERROR(
				5);

		private final int value;

		private Series(int value) {
			this.value = value;
		}

		/**
		 * Return the integer value of this status series. Ranges from 1 to 5.
		 */
		public int value() {
			return this.value;
		}

		private static Series valueOf(StatusCode status) {
			int seriesCode = status.value() / 100;
			for (Series series : values()) {
				if (series.value == seriesCode) {
					return series;
				}
			}
			throw new IllegalArgumentException("No matching constant for ["
					+ status + "]");
		}

	}
	
	

}
