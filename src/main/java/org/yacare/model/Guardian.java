package org.yacare.model;

import java.util.Date;

import org.cendra.model.person.PersonGender;
import org.geoobject.model.AdminDivision1;
import org.geoobject.model.Country;

public class Guardian {

	private String firstName;
	private String otherNames;
	private String surnames;

	private PersonGender gender;
	private Date dateOfBirth;
	private Integer age;
	private String fullAge;
	private String identityNumberDNI;
	private String identityNumberCUIL;
	private Country countryNationality;

	private Country countryAddress;
	private AdminDivision1 adminDivision1Address;
	private String localityAddress;
	private String zipCodeAddress;
	private String neighbourhoodAddress;
	private String streetAddress;
	private String streetNumberAddress;
	private String floorAddress;
	private String roomAddress;
	private String buildingAddress;
	private String commentAddress;

	private GuardianCommunicationOptions communicationOptions;

}
