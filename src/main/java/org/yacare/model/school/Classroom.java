package org.yacare.model.school;

import org.cendra.model.commons.EntityDomain;

public class Classroom extends EntityDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2711400816787525916L;

	private Short numberSeats;
	private ClassroomType type;

	public Short getNumberSeats() {
		return numberSeats;
	}

	public void setNumberSeats(Short numberSeats) {
		this.numberSeats = numberSeats;
	}

	public ClassroomType getType() {
		return type;
	}

	public void setType(ClassroomType type) {
		this.type = type;
	}

}
