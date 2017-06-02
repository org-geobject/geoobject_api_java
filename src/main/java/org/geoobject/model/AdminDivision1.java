package org.geoobject.model;

import org.cendra.model.commons.EntityId;

public class AdminDivision1 extends EntityId {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2861512196413931152L;
	
	@Override
	public AdminDivision1 clone() throws CloneNotSupportedException {
		AdminDivision1 other = new AdminDivision1();

		other.setId(this.getId());

		return other;
	}

}
