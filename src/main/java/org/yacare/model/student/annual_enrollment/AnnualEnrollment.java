package org.yacare.model.student.annual_enrollment;

import java.time.ZonedDateTime;

import org.yacare.model.academic.year.Division;
import org.yacare.model.academic.year.SchoolShift;

public class AnnualEnrollment {

	private RequestAnnualEnrollment request;
	private ZonedDateTime dateAdmission;
	private SchoolShift mainSchoolShift;
	private Division mainDivision;	
	private Short seatNumber;
	private AnnualEnrollmentState state;

}
