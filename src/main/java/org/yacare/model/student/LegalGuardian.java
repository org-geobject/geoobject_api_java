package org.yacare.model.student;

import org.cendra.model.person.Person;

public class LegalGuardian {

	private FamilyRelationshipType familyRelationshipType;
	private Person personalInformation;

	public FamilyRelationshipType getFamilyRelationshipType() {
		return familyRelationshipType;
	}

	public void setFamilyRelationshipType(FamilyRelationshipType familyRelationshipType) {
		this.familyRelationshipType = familyRelationshipType;
	}

	public Person getPersonalInformation() {
		return personalInformation;
	}

	public void setPersonalInformation(Person personalInformation) {
		this.personalInformation = personalInformation;
	}

}
