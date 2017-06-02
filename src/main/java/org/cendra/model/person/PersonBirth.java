package org.cendra.model.person;

import java.io.Serializable;
import java.util.Date;

import org.geoobject.model.GeoLocation;

public class PersonBirth implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4277067472638712848L;

	private Date dateOfBirth;
	private Integer age;
	private String fullAge;
	private GeoLocation placeOfBirth;

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getFullAge() {
		return fullAge;
	}

	public void setFullAge(String fullAge) {
		this.fullAge = fullAge;
	}

	public GeoLocation getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(GeoLocation placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	@Override
	public PersonBirth clone() throws CloneNotSupportedException {
		PersonBirth other = new PersonBirth();

		other.setDateOfBirth(this.getDateOfBirth());
		other.setAge(this.getAge());
		other.setFullAge(this.getFullAge());
		if (this.getPlaceOfBirth() != null) {
			other.setPlaceOfBirth(this.getPlaceOfBirth());
		} else {
			other.setPlaceOfBirth(null);
		}

		return other;
	}

	@Override
	public String toString() {

		return dateOfBirth + ", " + this.getAge() + ", " + this.getPlaceOfBirth();
	}

}
