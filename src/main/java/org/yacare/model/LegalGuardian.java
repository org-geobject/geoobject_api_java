package org.yacare.model;

import org.cendra.model.person.Person;

public class LegalGuardian {

	private Person personalInformation;
	private EducationLevel educationLevel;
	private String professionOccupation;

	public Person getPersonalInformation() {
		return personalInformation;
	}

	public void setPersonalInformation(Person personalInformation) {
		this.personalInformation = personalInformation;
	}

	public EducationLevel getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(EducationLevel educationLevel) {
		this.educationLevel = educationLevel;
	}

	public String getProfessionOccupation() {
		return professionOccupation;
	}

	public void setProfessionOccupation(String professionOccupation) {
		this.professionOccupation = professionOccupation;
	}

}
