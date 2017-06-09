package org.yacare.model.student.request_admission;

import java.time.ZonedDateTime;

import org.cendra.model.person.Person;
import org.yacare.model.academic.year.admission_period.AdmissionPeriod;
import org.yacare.model.student.LegalGuardian;

public class RequestAdmission {

	private AdmissionPeriod admissionPeriod;
	private ZonedDateTime dateRequest;
	private Person candidate;
	private String fromInstitution;
	private LegalGuardian guardian1;
	private LegalGuardian guardian2;
	private RequestSchoolShift schoolShift = new RequestSchoolShift();
	private RequestAdmissionCourse admissionCourse = new RequestAdmissionCourse();
	private ZonedDateTime confirmationDate;

	public AdmissionPeriod getAdmissionPeriod() {
		return admissionPeriod;
	}

	public void setAdmissionPeriod(AdmissionPeriod admissionPeriod) {
		this.admissionPeriod = admissionPeriod;
	}

	public ZonedDateTime getDateRequest() {
		return dateRequest;
	}

	public void setDateRequest(ZonedDateTime dateRequest) {
		this.dateRequest = dateRequest;
	}

	public Person getCandidate() {
		return candidate;
	}

	public void setCandidate(Person candidate) {
		this.candidate = candidate;
	}

	public String getFromInstitution() {
		return fromInstitution;
	}

	public void setFromInstitution(String fromInstitution) {
		this.fromInstitution = fromInstitution;
	}

	public LegalGuardian getGuardian1() {
		return guardian1;
	}

	public void setGuardian1(LegalGuardian guardian1) {
		this.guardian1 = guardian1;
	}

	public LegalGuardian getGuardian2() {
		return guardian2;
	}

	public void setGuardian2(LegalGuardian guardian2) {
		this.guardian2 = guardian2;
	}

	public RequestSchoolShift getSchoolShift() {
		return schoolShift;
	}

	public void setSchoolShift(RequestSchoolShift schoolShift) {
		this.schoolShift = schoolShift;
	}

	public RequestAdmissionCourse getAdmissionCourse() {
		return admissionCourse;
	}

	public void setAdmissionCourse(RequestAdmissionCourse admissionCourse) {
		this.admissionCourse = admissionCourse;
	}

	public ZonedDateTime getConfirmationDate() {
		return confirmationDate;
	}

	public void setConfirmationDate(ZonedDateTime confirmationDate) {
		this.confirmationDate = confirmationDate;
	}

}
