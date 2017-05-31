package org.geoobject.model;

import java.io.Serializable;

@SuppressWarnings("rawtypes")
public class GeoLocation implements Serializable, Comparable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9072924408193625655L;
	
	private Country country;
	private AdminAreaLevel1 adminAreaLevel1;
	private String locality;
	private String zipCode;
	private GeoCoordinates geoCoordinates;
	private String comment;

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public AdminAreaLevel1 getAdminAreaLevel1() {
		return adminAreaLevel1;
	}

	public void setAdminAreaLevel1(AdminAreaLevel1 adminAreaLevel1) {
		this.adminAreaLevel1 = adminAreaLevel1;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public GeoCoordinates getGeoCoordinates() {
		return geoCoordinates;
	}

	public void setGeoCoordinates(GeoCoordinates geoCoordinates) {
		this.geoCoordinates = geoCoordinates;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public GeoLocation clone() throws CloneNotSupportedException {
		GeoLocation other = new GeoLocation();

		if(this.getCountry() != null){
			other.setCountry(this.getCountry()clone);
		}
		
		
		

		return other;
	}

	@Override
	public int compareTo(Object o) {

		if (o != null && o instanceof GeoCoordinates == false) {
			throw new IllegalArgumentException("It was expected " + GeoCoordinates.class.getSimpleName() + " and not "
					+ o.getClass().getCanonicalName());
		}

		if (this.getLat() != null && o != null) {
			return this.getLat().compareTo(((GeoCoordinates) o).getLat());
		}

		if (this.getLat() != null && o == null) {
			return this.getLat().compareTo(0.0);
		}

		return new Double("0.0").compareTo(((GeoCoordinates) o).getLat());

	}

	@Override
	public String toString() {

		return this.getLat() + ", " + this.getLng();
	}

}
