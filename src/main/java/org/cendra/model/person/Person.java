package org.cendra.model.person;

import org.cendra.model.commons.File;
import org.yacare.model.student.EducationLevel;

public class Person {

	private File photo;
	private String firstName;
	private String otherNames;
	private String surnames;
	private PersonGender gender;
	private IdentityDocuments identityDocuments = new IdentityDocuments();
	private PersonBirth birth = new PersonBirth();
	private Nationalities nationalities = new Nationalities();
	private CommunicationOptions communicationOptions = new CommunicationOptions();
	private PersonHealthData healthData = new PersonHealthData();
	private EducationLevel educationLevel;
	private String professionOccupation;

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getOtherNames() {
		return otherNames;
	}

	public void setOtherNames(String otherNames) {
		this.otherNames = otherNames;
	}

	public String getSurnames() {
		return surnames;
	}

	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}

	public PersonGender getGender() {
		return gender;
	}

	public void setGender(PersonGender gender) {
		this.gender = gender;
	}

	public IdentityDocuments getIdentityDocuments() {
		if (identityDocuments == null) {
			identityDocuments = new IdentityDocuments();
		}
		return identityDocuments;
	}

	public void setIdentityDocuments(IdentityDocuments identityDocuments) {
		if (identityDocuments == null) {
			identityDocuments = new IdentityDocuments();
		}
		this.identityDocuments = identityDocuments;
	}

	public PersonBirth getBirth() {
		if (birth == null) {
			birth = new PersonBirth();
		}
		return birth;
	}

	public void setBirth(PersonBirth birth) {
		if (birth == null) {
			birth = new PersonBirth();
		}
		this.birth = birth;
	}

	public Nationalities getNationalities() {
		if (nationalities == null) {
			nationalities = new Nationalities();
		}
		return nationalities;
	}

	public void setNationalities(Nationalities nationalities) {
		if (nationalities == null) {
			nationalities = new Nationalities();
		}
		this.nationalities = nationalities;
	}

	public CommunicationOptions getCommunicationOptions() {
		if (communicationOptions == null) {
			communicationOptions = new CommunicationOptions();
		}
		return communicationOptions;
	}

	public void setCommunicationOptions(CommunicationOptions communicationOptions) {
		if (communicationOptions == null) {
			communicationOptions = new CommunicationOptions();
		}
		this.communicationOptions = communicationOptions;
	}

	public PersonHealthData getHealthData() {
		if (healthData == null) {
			healthData = new PersonHealthData();
		}
		return healthData;
	}

	public void setHealthData(PersonHealthData healthData) {
		if (healthData == null) {
			healthData = new PersonHealthData();
		}
		this.healthData = healthData;
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
