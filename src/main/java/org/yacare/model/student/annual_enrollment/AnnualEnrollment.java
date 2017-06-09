package org.yacare.model.student.annual_enrollment;

import java.time.ZonedDateTime;

import org.yacare.model.academic.year.SchoolShift;

public class AnnualEnrollment {

	private RequestAnnualEnrollment request;
	private ZonedDateTime dateAdmission;
	private SchoolShift mainSchoolShift;
	private String mainCourse;
	private String mainDivision;
	private Short seatNumber;
	private AnnualEnrollmentState state;

	public RequestAnnualEnrollment getRequest() {
		return request;
	}

	public void setRequest(RequestAnnualEnrollment request) {
		this.request = request;
	}

	public ZonedDateTime getDateAdmission() {
		return dateAdmission;
	}

	public void setDateAdmission(ZonedDateTime dateAdmission) {
		this.dateAdmission = dateAdmission;
	}

	public SchoolShift getMainSchoolShift() {
		return mainSchoolShift;
	}

	public void setMainSchoolShift(SchoolShift mainSchoolShift) {
		this.mainSchoolShift = mainSchoolShift;
	}

	public String getMainCourse() {
		return mainCourse;
	}

	public void setMainCourse(String mainCourse) {
		this.mainCourse = mainCourse;
	}

	public String getMainDivision() {
		return mainDivision;
	}

	public void setMainDivision(String mainDivision) {
		this.mainDivision = mainDivision;
	}

	public Short getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(Short seatNumber) {
		this.seatNumber = seatNumber;
	}

	public AnnualEnrollmentState getState() {
		return state;
	}

	public void setState(AnnualEnrollmentState state) {
		this.state = state;
	}

}
