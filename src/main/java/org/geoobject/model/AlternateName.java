package org.geoobject.model;

import org.cendra.model.commons.EntityDomain;
import org.cendra.model.commons.EntityErasable;

public class AlternateName extends EntityErasable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4308035564442884704L;

	private String alternateName;
	private Boolean preferredName;
	private Boolean shortName;
	private Boolean colloquial;
	private Boolean historic;
	private Languaje languaje;

	public String getAlternateName() {
		alternateName = this.formatValue(alternateName);
		return alternateName;
	}

	public void setAlternateName(String alternateName) {
		alternateName = this.formatValue(alternateName);
		this.alternateName = alternateName;
	}

	public Boolean getPreferredName() {
		if(preferredName == null){
			preferredName = false;
		}
		return preferredName;
	}

	public void setPreferredName(Boolean preferredName) {
		if(preferredName == null){
			preferredName = false;
		}
		this.preferredName = preferredName;
	}

	public Boolean getShortName() {
		if(shortName == null){
			shortName = false;
		}
		return shortName;
	}

	public void setShortName(Boolean shortName) {
		if(shortName == null){
			shortName = false;
		}
		this.shortName = shortName;
	}

	public Boolean getColloquial() {
		if(colloquial == null){
			colloquial = false;
		}
		return colloquial;
	}

	public void setColloquial(Boolean colloquial) {
		if(colloquial == null){
			colloquial = false;
		}
		this.colloquial = colloquial;
	}

	public Boolean getHistoric() {
		if(historic == null){
			historic = false;
		}
		return historic;
	}

	public void setHistoric(Boolean historic) {
		if(historic == null){
			historic = false;
		}
		this.historic = historic;
	}

	public Languaje getLanguaje() {
		return languaje;
	}

	public void setLanguaje(Languaje languaje) {
		this.languaje = languaje;
	}

	public AlternateName clone() throws CloneNotSupportedException {
		AlternateName other = (AlternateName) super.clone();

		other.setAlternateName(this.getAlternateName());
		other.setPreferredName(this.getPreferredName());
		other.setShortName(this.getShortName());
		other.setColloquial(this.getColloquial());
		other.setHistoric(this.getHistoric());
		if (this.getLanguaje() != null) {
			other.setLanguaje(this.getLanguaje().clone());
		} else {
			other.setLanguaje(null);
		}

		return other;
	}

	@Override
	public int compareTo(Object o) {

		if (o != null && o instanceof EntityDomain == false) {
			throw new IllegalArgumentException("It was expected " + AlternateName.class.getSimpleName() + " and not "
					+ o.getClass().getCanonicalName());
		}

		if (this.getAlternateName() != null && o != null) {
			return this.getAlternateName().compareTo(((AlternateName) o).getAlternateName());
		}

		if (this.getAlternateName() != null && o == null) {
			return this.getAlternateName().compareTo("");
		}

		return "".compareTo(((AlternateName) o).getAlternateName());
	}

	@Override
	public String toString() {

		String s = super.toString();

		if (this.getAlternateName() != null) {
			return (s + " " + " " + this.getLanguaje() + " " + this.getAlternateName()).trim();
		}

		return "";
	}

}
