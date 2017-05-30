package org.cendra.model.person;

import java.io.Serializable;

import org.geoobject.model.IdentityType;

@SuppressWarnings("rawtypes")
public class MainIdentityDocument implements Serializable, Comparable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2470580947171442552L;

	private String identityNumber;
	private IdentityType identityType;

	public String getIdentityNumber() {
		identityNumber = formatValueIdentityNumber(identityNumber);
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		identityNumber = formatValueIdentityNumber(identityNumber);
		this.identityNumber = identityNumber;
	}

	public IdentityType getIdentityType() {
		return identityType;
	}

	public void setIdentityType(IdentityType identityType) {
		this.identityType = identityType;
	}

	@Override
	public MainIdentityDocument clone() throws CloneNotSupportedException {
		MainIdentityDocument other = new MainIdentityDocument();

		other.setIdentityNumber(this.getIdentityNumber());
		if (this.getIdentityType() != null) {
			other.setIdentityType(this.getIdentityType().clone());
		} else {
			other.setIdentityType(null);
		}

		return other;
	}

	@Override
	public int compareTo(Object o) {

		if (o != null && o instanceof MainIdentityDocument == false) {
			throw new IllegalArgumentException("It was expected " + MainIdentityDocument.class.getSimpleName() + " and not "
					+ o.getClass().getCanonicalName());
		}

		if (this.getIdentityNumber() != null && o != null) {
			return this.getIdentityNumber().compareTo(((MainIdentityDocument) o).getIdentityNumber());
		}

		if (this.getIdentityNumber() != null && o == null) {
			return this.getIdentityNumber().compareTo("");
		}

		return "".compareTo(((MainIdentityDocument) o).getIdentityNumber());

	}
	
	@Override
	public String toString() {

		String s = "";

		if (this.getIdentityNumber() != null) {
			return (s + " (" + this.getIdentityNumber() + ")").trim();
		}

		return "";
	}

	protected String formatValueIdentityNumber(String value) {

		if (value != null) {
			value = value.trim();
		}

		if (value != null && value.isEmpty()) {
			value = null;
		}

		if (value != null) {
			value = value.toUpperCase();
		}

		return value;
	}

}
