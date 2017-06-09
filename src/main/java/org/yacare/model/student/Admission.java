package org.yacare.model.student;

import java.time.ZonedDateTime;

import org.yacare.model.student.request_admission.RequestAdmission;

/* tiene que tener toda la info de la admision, notas, orden de merito etc. */
public class Admission {

	private RequestAdmission requestAdmission;
	private ZonedDateTime dateAdmission;

	public RequestAdmission getRequestAdmission() {
		return requestAdmission;
	}

	public void setRequestAdmission(RequestAdmission requestAdmission) {
		this.requestAdmission = requestAdmission;
	}

	public ZonedDateTime getDateAdmission() {
		return dateAdmission;
	}

	public void setDateAdmission(ZonedDateTime dateAdmission) {
		this.dateAdmission = dateAdmission;
	}

}
