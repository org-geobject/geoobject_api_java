package org.cendra.model.person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
public class IdentityDocuments implements Serializable, Comparable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6615452104089963703L;

	private MainIdentityDocument mainIdentity;
	private List<IdentityDocument> identities = new ArrayList<IdentityDocument>();

	public MainIdentityDocument getMainIdentity() {
		return mainIdentity;
	}

	public void setMainIdentity(MainIdentityDocument mainIdentity) {
		this.mainIdentity = mainIdentity;
	}

	public List<IdentityDocument> getIdentities() {
		identities = formatValueIdentities(identities);
		return identities;
	}

	public void setIdentities(List<IdentityDocument> identities) {
		identities = formatValueIdentities(identities);
		this.identities = identities;
	}

	public void addIdentityDocument(IdentityDocument identityDocument) {
		identities = formatValueIdentities(identities);
		this.identities.add(identityDocument);
	}

	@Override
	public IdentityDocuments clone() throws CloneNotSupportedException {
		IdentityDocuments other = new IdentityDocuments();

		if (this.getMainIdentity() != null) {
			other.setMainIdentity(this.getMainIdentity().clone());
		} else {
			other.setMainIdentity(null);
		}

		if (this.getIdentities() != null) {
			for (IdentityDocument identityDocument : this.getIdentities()) {
				if (identityDocument != null) {
					this.addIdentityDocument(identityDocument.clone());
				}

			}
		} else {
			other.setIdentities(null);
		}

		return other;
	}

	@Override
	public int compareTo(Object o) {

		if (o != null && o instanceof MainIdentityDocument == false) {
			throw new IllegalArgumentException("It was expected " + MainIdentityDocument.class.getSimpleName()
					+ " and not " + o.getClass().getCanonicalName());
		}

		if (this.getMainIdentity() != null && o != null) {
			return this.getMainIdentity().compareTo(((IdentityDocuments) o).getMainIdentity());
		}

		if (this.getMainIdentity() != null && o == null) {
			return this.getMainIdentity().compareTo(null);
		}

		return ((IdentityDocuments) o).getMainIdentity().compareTo(null);

	}

	@Override
	public String toString() {

		if (this.getMainIdentity() != null) {
			return this.getMainIdentity().toString();
		}

		return "";
	}

	protected List<IdentityDocument> formatValueIdentities(List<IdentityDocument> identities) {
		List<IdentityDocument> r = new ArrayList<IdentityDocument>();

		if (identities != null) {
			for (IdentityDocument identityDocument : identities) {
				if (identityDocument != null) {
					r.add(identityDocument);
				}
			}
		}

		return r;
	}

}
