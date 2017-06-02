package org.cendra.model.person;

import org.cendra.model.commons.EntityErasable;
import org.geoobject.model.Country;

public class Phone extends EntityErasable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1794364099542595786L;

	private Country country;
	private String localCallingCode;
	private String number;
	private String comment;
	private PhoneType phoneType;

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getLocalCallingCode() {
		return localCallingCode;
	}

	public void setLocalCallingCode(String localCallingCode) {
		this.localCallingCode = localCallingCode;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public PhoneType getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(PhoneType phoneType) {
		this.phoneType = phoneType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
