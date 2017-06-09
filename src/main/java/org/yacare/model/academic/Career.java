package org.yacare.model.academic;

import java.time.Year;

import org.cendra.model.commons.EntityDomain;

public class Career extends EntityDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -536148691511009784L;

	private Year yearOfImplementation;

	public Year getYearOfImplementation() {
		return yearOfImplementation;
	}

	public void setYearOfImplementation(Year yearOfImplementation) {
		this.yearOfImplementation = yearOfImplementation;
	}

}
