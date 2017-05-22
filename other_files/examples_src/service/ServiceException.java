package org.cendra.commons.util.service;

import java.time.ZonedDateTime;

import org.cendra.commons.util.dao.jdbc.SQLExceptionWrapper;
import org.cendra.commons.util.error.InfoException;
import org.cendra.commons.util.system.InfoHost;

public class ServiceException {

	private StatusCode statusCode = StatusCode.INTERNAL_SERVER_ERROR;
	private String title = "unknown";
	private String subject = "unknown";
	private String message = "unknown";
	private String timeError = "unknown";
	private InfoException infoException;
	private InfoHost infoHost = new InfoHost();

	// =================================================================================

	public ServiceException(Exception exception, String title, String subject) {
		init(exception, title, subject);
	}

	public ServiceException(Exception exception) {
		init(exception, null, null);
	}

	private void init(Exception exception, String title, String subject) {

		timeError = ZonedDateTime.now().toString();
		this.infoException = new InfoException(exception);

		if (exception instanceof SQLExceptionWrapper) {
			title = ((SQLExceptionWrapper) exception).getTitle();
			subject = ((SQLExceptionWrapper) exception).getSubject();
		}

		if (title != null && title.isEmpty() == false) {
			this.title = title;
		}
		if (subject != null && subject.isEmpty() == false) {
			this.title = title;
		}

		message = exception.getMessage();

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

	public InfoException getInfoException() {
		return infoException;
	}

	public InfoHost getInfoHost() {
		return infoHost;
	}

	@Override
	public String toString() {
		return "ServiceException [statusCode=" + statusCode + ", title="
				+ title + ", subject=" + subject + ", message=" + message
				+ ", timeError=" + timeError + ", infoException="
				+ infoException + ", infoHost=" + infoHost + "]";
	}

	// =================================================================================

}
