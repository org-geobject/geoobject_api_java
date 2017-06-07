package org.yacare.model;

import java.util.Date;

import org.cendra.model.person.BloodFactor;
import org.cendra.model.person.BloodGroup;
import org.cendra.model.person.PersonGender;
import org.geoobject.model.AdminDivision1;
import org.geoobject.model.Country;

public class Candidate {

	private String firstName;
	private String otherNames;
	private String surnames;	
	private BloodGroup bloodGroup;
	private BloodFactor bloodFactor;
	private PersonGender gender;
	private Date dateOfBirth;
	private Integer age;
	private String fullAge;
	private String identityNumberDNI;
	private String identityNumberCUIL;
	
	private Country countryBirth;
	private AdminDivision1 adminDivision1Birth;
	private String localityBirth;
	
	private Country countryNationality;

	private String localityAddress;
	private String zipCodeAddress;
	private String neighbourhoodAddress;
	private String streetAddress;
	private String streetNumberAddress;
	private String floorAddress;
	private String roomAddress;
	private String buildingAddress;
	private String commentAddress;

}
