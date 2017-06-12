package org.yacare.model.student;

import java.util.ArrayList;
import java.util.List;

import org.cendra.model.person.Person;
import org.cendra.organization.Org;
import org.yacare.model.academic.Career;
import org.yacare.model.student.annual_enrollment.AnnualEnrollment;

public class StudentEnrollment {

	private Person personalInformation;
	private Org institution;
	private Career career;
	private StudentState state;
	private Admission admission = new Admission();
	private Graduation graduation = new Graduation();
	private List<LegalGuardian> guardians = new ArrayList<LegalGuardian>();
	private List<EmergencyContact> emergencyContacts = new ArrayList<EmergencyContact>();
	private List<AnnualEnrollment> annualEnrollments = new ArrayList<AnnualEnrollment>();

}
