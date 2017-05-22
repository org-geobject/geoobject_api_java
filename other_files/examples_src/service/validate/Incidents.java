package org.cendra.commons.util.service.validate;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.cendra.commons.util.service.StatusCode;
import org.cendra.commons.util.system.InfoHost;

public class Incidents {

	private StatusCode statusCode = StatusCode.INTERNAL_SERVER_ERROR;
	private String title = "unknown";
	private String subject = "unknown";
	private String message = "unknown";
	private String timeError = "unknown";
	private InfoHost infoHost = new InfoHost();
	private List<Incident> incidents = new ArrayList<Incident>();
	private boolean in = true;

	// =================================================================================

	public Incidents(boolean in, List<Incident> incidents) {

		this.in = in;

		timeError = ZonedDateTime.now().toString();

		title = "Resultado de Validación";

		int e = 0;
		int w = 0;

		for (Incident incident : incidents) {
			if (incident.getIsError()) {
				e++;
			} else {
				w++;
			}
		}

		message = "Erres: " + e + ", Advertencias: " + w;

		if (in) {
			this.statusCode = StatusCode.CONFLICT;
			subject = "Validación de entrada";
		} else {
			this.statusCode = StatusCode.UNPROCESSABLE_ENTITY;
			subject = "Validación de salida";
		}
	}

	public boolean containsErrors() {
		for (Incident incident : incidents) {
			if (incident.getIsError()) {
				return true;
			}
		}

		return false;
	}

	public boolean notContainsErrors() {
		for (Incident incident : incidents) {
			if (incident.getIsError()) {
				return false;
			}
		}

		return true;
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

	public List<Incident> getIncidents() {
		return incidents;
	}

	public boolean isIn() {
		return in;
	}

	public void setIn(boolean in) {
		this.in = in;

		if (in) {
			this.statusCode = StatusCode.CONFLICT;
			subject = "Validación de entrada";
		} else {
			this.statusCode = StatusCode.UNPROCESSABLE_ENTITY;
			subject = "Validación de salida";
		}
	}

	// =================================================================================

}
