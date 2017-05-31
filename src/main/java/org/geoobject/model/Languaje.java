package org.geoobject.model;

import org.cendra.model.commons.EntityDomain;
import org.cendra.model.commons.EntityId;

public class Languaje extends EntityId {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5235094281658043071L;

	private Boolean macrolanguage = false;
	private String iso639_3;
	private String iso639_2;
	private String iso639_1;
	private String languageName;

	public String getId() {
		iso639_3 = this.formatValue(iso639_3);
		return iso639_3;
	}

	public void setId(String id) {
		id = this.formatValue(id);
		this.iso639_3 = id;
	}

	public Boolean getMacrolanguage() {
		if(macrolanguage == null){
			macrolanguage = false;
		}	
		return macrolanguage;
	}

	public void setMacrolanguage(Boolean macrolanguage) {
		if(macrolanguage == null){
			macrolanguage = false;
		}
		this.macrolanguage = macrolanguage;
	}

	public String getIso639_3() {
		iso639_3 = this.formatValue(iso639_3);
		return iso639_3;
	}

	public void setIso639_3(String iso639_3) {
		iso639_3 = this.formatValue(iso639_3);
		this.iso639_3 = iso639_3;
	}

	public String getIso639_2() {
		iso639_2 = this.formatValue(iso639_2);
		return iso639_2;
	}

	public void setIso639_2(String iso639_2) {
		iso639_2 = this.formatValue(iso639_2);
		this.iso639_2 = iso639_2;
	}

	public String getIso639_1() {
		iso639_1 = this.formatValue(iso639_1);
		return iso639_1;
	}

	public void setIso639_1(String iso639_1) {
		iso639_1 = this.formatValue(iso639_1);
		this.iso639_1 = iso639_1;
	}

	public String getLanguageName() {
		languageName = this.formatValue(languageName);
		return languageName;
	}

	public void setLanguageName(String languageName) {
		languageName = this.formatValue(languageName);
		this.languageName = languageName;
	}

	public Languaje clone() throws CloneNotSupportedException {
		Languaje other = (Languaje) super.clone();

		other.setMacrolanguage(this.getMacrolanguage());
		other.setIso639_3(this.getIso639_3());
		other.setIso639_2(this.getIso639_2());
		other.setIso639_1(this.getIso639_1());
		other.setLanguageName(this.getLanguageName());

		return other;
	}
	
	@Override
	public int compareTo(Object o) {

		if (o != null && o instanceof EntityDomain == false) {
			throw new IllegalArgumentException("It was expected " + Languaje.class.getSimpleName() + " and not "
					+ o.getClass().getCanonicalName());
		}

		if (this.getIso639_3() != null && o != null) {
			return this.getIso639_3().compareTo(((Languaje) o).getIso639_3());
		}

		if (this.getIso639_3() != null && o == null) {
			return this.getIso639_3().compareTo("");
		}

		return "".compareTo(((Languaje) o).getIso639_3());
	}
	
	@Override
	public String toString() {
		
		String s = super.toString();

		if (this.getIso639_3() != null) {
			return (s + " (" + this.getIso639_3() + ")").trim();
		}

		return "";
	}

}
