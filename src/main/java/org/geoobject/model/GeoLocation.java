package org.geoobject.model;

import java.io.Serializable;

@SuppressWarnings("rawtypes")
public class GeoLocation implements Serializable, Comparable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9072924408193625655L;

	private Country country;
	private AdminDivision1 adminDivision1;
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

	public AdminDivision1 getAdminDivision1() {
		return adminDivision1;
	}

	public void setAdminDivision1(AdminDivision1 adminDivision1) {
		this.adminDivision1 = adminDivision1;
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

		if (this.getCountry() != null) {
			other.setCountry(this.getCountry().clone());
		} else {
			other.setCountry(null);
		}
		if (this.getAdminDivision1() != null) {
			other.setAdminDivision1(this.getAdminDivision1().clone());
		} else {
			other.setAdminDivision1(null);
		}
		other.setLocality(this.getLocality());
		other.setZipCode(this.getZipCode());
		if (this.getGeoCoordinates() != null) {
			other.setGeoCoordinates(this.getGeoCoordinates().clone());
		} else {
			other.setGeoCoordinates(null);
		}
		other.setComment(this.getComment());

		return other;
	}

	@Override
	public int compareTo(Object o) {

		if (o != null && o instanceof GeoCoordinates == false) {
			throw new IllegalArgumentException("It was expected " + GeoLocation.class.getSimpleName() + " and not "
					+ o.getClass().getCanonicalName());
		}

		if (o != null) {
			if (this.getCountry() == null) {
				return -1;
			}
			if (((GeoLocation) o).getCountry() == null) {
				return 1;
			}
			int r = this.getCountry().compareTo(((GeoLocation) o).getCountry());
			if (r < 0) {
				return r;
			}
			if (this.getAdminDivision1() == null) {
				return -1;
			}
			if (((GeoLocation) o).getAdminDivision1() == null) {
				return 1;
			}
			r = this.getAdminDivision1().compareTo(((GeoLocation) o).getAdminDivision1());
			if (r < 0) {
				return r;
			}
			if (this.getLocality() == null) {
				return -1;
			}
			if (((GeoLocation) o).getLocality() == null) {
				return 1;
			}
			return this.getLocality().compareTo(((GeoLocation) o).getLocality());
		}

		return 1;

	}

	@Override
	public String toString() {

		return this.getCountry() + ", " + this.getAdminDivision1() + ", " + this.getLocality();
	}

}
