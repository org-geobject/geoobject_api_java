package org.cendra.model.person;

import org.cendra.model.commons.EntityErasable;
import org.geoobject.model.IdentityType;

public class IdentityDocument extends EntityErasable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5049462282819173794L;

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
	public IdentityDocument clone() throws CloneNotSupportedException {
		IdentityDocument other = (IdentityDocument) super.clone();

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

		if (o != null && o instanceof IdentityDocument == false) {
			throw new IllegalArgumentException("It was expected " + IdentityDocument.class.getSimpleName() + " and not "
					+ o.getClass().getCanonicalName());
		}

		if (this.getIdentityNumber() != null && o != null) {
			return this.getIdentityNumber().compareTo(((IdentityDocument) o).getIdentityNumber());
		}

		if (this.getIdentityNumber() != null && o == null) {
			return this.getIdentityNumber().compareTo("");
		}

		return "".compareTo(((IdentityDocument) o).getIdentityNumber());

	}
	
	@Override
	public String toString() {

		String s = super.toString();

		if (this.getIdentityNumber() != null) {
			return (s + " (" + this.getIdentityNumber() + ")").trim();
		}

		return "";
	}

	protected String formatValueIdentityNumber(String value) {

		value = super.formatValue(value);

		if (value != null) {
			value = value.toUpperCase();
		}

		return value;
	}

}
