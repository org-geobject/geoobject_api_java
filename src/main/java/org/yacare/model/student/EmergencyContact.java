package org.yacare.model.student;

import org.cendra.model.person.MainPhone;

public class EmergencyContact {

	private String names;
	private String surnames;
	private String relationship;
	private MainPhone phone;
	private String comment;

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getSurnames() {
		return surnames;
	}

	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public MainPhone getPhone() {
		return phone;
	}

	public void setPhone(MainPhone phone) {
		this.phone = phone;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
