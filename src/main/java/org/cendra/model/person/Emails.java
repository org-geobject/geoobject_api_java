package org.cendra.model.person;

import java.util.ArrayList;
import java.util.List;

public class Emails {

	private String mainEmail;
	private List<Email> alternativeEmails = new ArrayList<Email>();

	public String getMainEmail() {
		return mainEmail;
	}

	public void setMainEmail(String mainEmail) {
		this.mainEmail = mainEmail;
	}

	public List<Email> getAlternativeEmails() {
		return alternativeEmails;
	}

	public void setAlternativeEmails(List<Email> alternativeEmails) {
		this.alternativeEmails = alternativeEmails;
	}
	
	public void addAlternativeEmail(Email alternativeEmail) {
		this.alternativeEmails.add(alternativeEmail);
	}
	
	@Override
	public Emails clone() throws CloneNotSupportedException {
		Emails other = new Emails();

		other.setMainEmail(this.getMainEmail());
		for (Email email : this.getAlternativeEmails()) {
			this.addAlternativeEmail((Email) email.clone());
		}

		return other;
	}

}
