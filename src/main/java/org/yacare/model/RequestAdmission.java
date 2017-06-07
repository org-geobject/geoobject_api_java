package org.yacare.model;

import org.cendra.model.person.Person;

public class RequestAdmission {
	
	public Long number;
	private Person candidate;
	private LegalGuardian guardian1;
	private LegalGuardian guardian2;
	private SchoolShift schoolShift;
	private Boolean brotherStudent = false;
	private String firstNameBrotherStudent;
	private String otherNamesBrotherStudent;
	private String surnamesBrotherStudent;
	private String dniBrotherStudent;
	private SchoolShift schoolShiftCourse;
	

}
