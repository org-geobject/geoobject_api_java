package org.cendra.commons.util.service;

import java.time.ZonedDateTime;

import org.cendra.commons.util.system.InfoHost;

public class ServiceError {

	private StatusCode statusCode = StatusCode.INTERNAL_SERVER_ERROR;
	private String title = "unknown";
	private String subject = "unknown";
	private String message = "unknown";
	private String timeError = "unknown";
	private InfoHost infoHost = new InfoHost();

	// =================================================================================

	public ServiceError(String title, String subject, String message,
			StatusCode statusCode) {

		timeError = ZonedDateTime.now().toString();

		if (title != null && title.isEmpty() == false) {
			this.title = title;
		}
		if (subject != null && subject.isEmpty() == false) {
			this.title = title;
		}

		if (statusCode != null) {
			this.statusCode = statusCode;
		}
	}

	public StatusCode getStatusCode() {
		return statusCode;
	}

	public String getTitle() {
		return title;
	}

	public String getSubject() {
		return subject;
	}

	public String getMessage() {
		return message;
	}

	public String getTimeError() {
		return timeError;
	}

	public InfoHost getInfoHost() {
		return infoHost;
	}

	@Override
	public String toString() {
		return "ServiceError [statusCode=" + statusCode + ", title=" + title
				+ ", subject=" + subject + ", message=" + message
				+ ", timeError=" + timeError + ", infoHost=" + infoHost + "]";
	}

	// =================================================================================

}
