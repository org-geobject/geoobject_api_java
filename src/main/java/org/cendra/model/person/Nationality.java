package org.cendra.model.person;

import java.io.Serializable;

import org.geoobject.model.Country;

@SuppressWarnings("rawtypes")
public class Nationality implements Serializable, Comparable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 406798922561440466L;

	private Country country;

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public Nationality clone() throws CloneNotSupportedException {
		Nationality other = new Nationality();

		if (this.getCountry() != null) {
			other.setCountry(this.getCountry().clone());
		} else {
			other.setCountry(null);
		}

		return other;
	}

	@Override
	public int compareTo(Object o) {

		if (o != null && o instanceof Nationality == false) {
			throw new IllegalArgumentException("It was expected " + Nationality.class.getSimpleName() + " and not "
					+ o.getClass().getCanonicalName());
		}

		return this.getCountry().compareTo(((Nationality) o).getCountry());

	}

}
