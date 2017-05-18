package org.geoobject.model;

public class AlternateName {

	private String id;
	private String alternateName;
	private Boolean preferredName;
	private Boolean shortName;
	private Boolean colloquial;
	private Boolean historic;
	private Languaje languaje;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAlternateName() {
		return alternateName;
	}

	public void setAlternateName(String alternateName) {
		this.alternateName = alternateName;
	}

	public Boolean getPreferredName() {
		return preferredName;
	}

	public void setPreferredName(Boolean preferredName) {
		this.preferredName = preferredName;
	}

	public Boolean getShortName() {
		return shortName;
	}

	public void setShortName(Boolean shortName) {
		this.shortName = shortName;
	}

	public Boolean getColloquial() {
		return colloquial;
	}

	public void setColloquial(Boolean colloquial) {
		this.colloquial = colloquial;
	}

	public Boolean getHistoric() {
		return historic;
	}

	public void setHistoric(Boolean historic) {
		this.historic = historic;
	}

	public Languaje getLanguaje() {
		return languaje;
	}

	public void setLanguaje(Languaje languaje) {
		this.languaje = languaje;
	}

	@Override
	public String toString() {
		return "AlternateName [id=" + id + ", alternateName=" + alternateName + "]";
	}

}
