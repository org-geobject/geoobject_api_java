package org.yacare.model.student.request_admission;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.cendra.model.person.Person;
import org.yacare.model.academic.Career;
import org.yacare.model.academic.year.admission_period.AdmissionPeriod;
import org.yacare.model.student.LegalGuardian;

public class RequestAdmission {

	private AdmissionPeriod admissionPeriod;
	private Career career;
	private ZonedDateTime dateRequest;
	private Person candidate;
	private String fromInstitution;
	private List<LegalGuardian> guardians = new ArrayList<LegalGuardian>();
	private RequestSchoolShift schoolShift = new RequestSchoolShift();
	private RequestAdmissionCourse admissionCourse = new RequestAdmissionCourse();
	private ZonedDateTime confirmationDate;

}
