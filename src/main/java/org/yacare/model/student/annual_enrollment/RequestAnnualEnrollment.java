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
	private List<LegalGuardian> guardians = new ArrayList<LegalGuardian>();
	private RequestSchoolShift schoolShift = new RequestSchoolShift();
	private List<EmergencyContact> emergencyContacts = new ArrayList<EmergencyContact>();
	private ZonedDateTime confirmationDate;

}
