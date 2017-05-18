package org.geoobject.model;

public class Languaje {

	@SuppressWarnings("unused")
	private String id;
	private Boolean macrolanguage = false;
	private String iso639_3;
	private String iso639_2;
	private String iso639_1;
	private String languageName;

	public String getId() {
		return iso639_3;
	}

	public void setId(String id) {
		this.iso639_3 = id;
	}

	public Boolean getMacrolanguage() {
		return macrolanguage;
	}

	public void setMacrolanguage(Boolean macrolanguage) {
		this.macrolanguage = macrolanguage;
	}

	public String getIso639_3() {
		return iso639_3;
	}

	public void setIso639_3(String iso639_3) {
		this.iso639_3 = iso639_3;
	}

	public String getIso639_2() {
		return iso639_2;
	}

	public void setIso639_2(String iso639_2) {
		this.iso639_2 = iso639_2;
	}

	public String getIso639_1() {
		return iso639_1;
	}

	public void setIso639_1(String iso639_1) {
		this.iso639_1 = iso639_1;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	@Override
	public String toString() {
		return "Languaje [id=" + iso639_3 + ", macrolanguage=" + macrolanguage + ", iso639_3=" + iso639_3 + ", iso639_2="
				+ iso639_2 + ", iso639_1=" + iso639_1 + ", languageName=" + languageName + "]";
	}

}
