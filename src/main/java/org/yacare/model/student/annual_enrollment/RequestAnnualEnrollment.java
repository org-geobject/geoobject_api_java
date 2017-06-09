package org.yacare.model.student.annual_enrollment;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.yacare.model.student.EmergencyContact;
import org.yacare.model.student.LegalGuardian;
import org.yacare.model.student.StudentEnrollment;
import org.yacare.model.student.request_admission.RequestSchoolShift;

public class RequestAnnualEnrollment {

	private ZonedDateTime dateRequest;
	private StudentEnrollment candidate;
	private LegalGuardian guardian1;
	private LegalGuardian guardian2;
	private RequestSchoolShift schoolShift = new RequestSchoolShift();
	private List<EmergencyContact> emergencyContacts = new ArrayList<EmergencyContact>();
	private ZonedDateTime confirmationDate;

	public ZonedDateTime getDateRequest() {
		return dateRequest;
	}

	public void setDateRequest(ZonedDateTime dateRequest) {
		this.dateRequest = dateRequest;
	}

	public StudentEnrollment getCandidate() {
		return candidate;
	}

	public void setCandidate(StudentEnrollment candidate) {
		this.candidate = candidate;
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

	public List<EmergencyContact> getEmergencyContacts() {
		return emergencyContacts;
	}

	public void setEmergencyContacts(List<EmergencyContact> emergencyContacts) {
		this.emergencyContacts = emergencyContacts;
	}

	public ZonedDateTime getConfirmationDate() {
		return confirmationDate;
	}

	public void setConfirmationDate(ZonedDateTime confirmationDate) {
		this.confirmationDate = confirmationDate;
	}

}
