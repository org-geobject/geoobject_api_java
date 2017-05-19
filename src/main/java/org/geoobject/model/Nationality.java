package org.geoobject.model;

public class Nationality {

	@SuppressWarnings("unused")
	private String id;
	private Languaje languaje;
	private String nounSingular;
	private String adjectiveSingular;
	private String adjectiveFeminineSingular;

	public String getId() {
		return languaje.getIso639_3();
	}

//	public void setId(String id) {
//		this.languaje.setId(id);
//	}

	public Languaje getLanguaje() {
		return languaje;
	}

	public void setLanguaje(Languaje languaje) {
		this.languaje = languaje;
	}

	public String getNounSingular() {
		return nounSingular;
	}

	public void setNounSingular(String nounSingular) {
		this.nounSingular = nounSingular;
	}

	public String getAdjectiveSingular() {
		return adjectiveSingular;
	}

	public void setAdjectiveSingular(String adjectiveSingular) {
		this.adjectiveSingular = adjectiveSingular;
	}

	public String getAdjectiveFeminineSingular() {
		return adjectiveFeminineSingular;
	}

	public void setAdjectiveFeminineSingular(String adjectiveFeminineSingular) {
		this.adjectiveFeminineSingular = adjectiveFeminineSingular;
	}

	@Override
	public String toString() {
		return "Nationality [languaje=" + languaje + ", nounSingular=" + nounSingular + "]";
	}

}
