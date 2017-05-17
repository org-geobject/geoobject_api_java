package org.cendra.util.service.swagger;

class InfoJson {

	private String description;
	private String version;
	private String title;
	private ContactJson contact;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ContactJson getContact() {
		return contact;
	}

	public void setContact(ContactJson contact) {
		this.contact = contact;
	}

}
