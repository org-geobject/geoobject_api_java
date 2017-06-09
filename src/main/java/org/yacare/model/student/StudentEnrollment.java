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
	private LegalGuardian guardian1;
	private LegalGuardian guardian2;
	private List<EmergencyContact> emergencyContacts = new ArrayList<EmergencyContact>();
	private List<AnnualEnrollment> annualEnrollments = new ArrayList<AnnualEnrollment>();

	public Person getPersonalInformation() {
		return personalInformation;
	}

	public void setPersonalInformation(Person personalInformation) {
		this.personalInformation = personalInformation;
	}

	public Org getInstitution() {
		return institution;
	}

	public void setInstitution(Org institution) {
		this.institution = institution;
	}

	public Career getCareer() {
		return career;
	}

	public void setCareer(Career career) {
		this.career = career;
	}

	public StudentState getState() {
		return state;
	}

	public void setState(StudentState state) {
		this.state = state;
	}

	public Admission getAdmission() {
		return admission;
	}

	public void setAdmission(Admission admission) {
		this.admission = admission;
	}

	public Graduation getGraduation() {
		return graduation;
	}

	public void setGraduation(Graduation graduation) {
		this.graduation = graduation;
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

	public List<EmergencyContact> getEmergencyContacts() {
		return emergencyContacts;
	}

	public void setEmergencyContacts(List<EmergencyContact> emergencyContacts) {
		this.emergencyContacts = emergencyContacts;
	}

	public List<AnnualEnrollment> getAnnualEnrollments() {
		return annualEnrollments;
	}

	public void setAnnualEnrollments(List<AnnualEnrollment> annualEnrollments) {
		this.annualEnrollments = annualEnrollments;
	}

}
