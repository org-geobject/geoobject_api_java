package org.geoobject.model;

import org.cendra.model.commons.EntityDomain;

public class IdentityType extends EntityDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -592028983649425138L;

	private Country issuingCountry;

	public Country getIssuingCountry() {
		return issuingCountry;
	}

	public void setIssuingCountry(Country issuingCountry) {
		this.issuingCountry = issuingCountry;
	}

	@Override
	public IdentityType clone() throws CloneNotSupportedException {
		IdentityType other = (IdentityType) super.clone();

		other.setIssuingCountry(this.getIssuingCountry());

		return other;
	}

}
