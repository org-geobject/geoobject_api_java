package org.cendra.model.person;

import java.io.Serializable;

import org.geoobject.model.AdminDivision1;
import org.geoobject.model.Country;
import org.geoobject.model.GeoCoordinates;

@SuppressWarnings("rawtypes")
public class MainAddress implements Serializable, Comparable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8964655401928041457L;

	private Country country;
	private AdminDivision1 adminDivision1;
	private String locality;
	private String zipCode;
	private String neighbourhood;
	private String street;
	private String streetNumber;
	private String floor;
	private String room;
	private String building;
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

	public String getNeighbourhood() {
		return neighbourhood;
	}

	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
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
	public MainAddress clone() throws CloneNotSupportedException {
		MainAddress other = new MainAddress();

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
		other.setNeighbourhood(this.getNeighbourhood());
		other.setStreet(this.getStreet());
		other.setStreetNumber(this.getStreetNumber());
		other.setFloor(this.getFloor());
		other.setRoom(this.getRoom());
		other.setBuilding(this.getRoom());
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
			throw new IllegalArgumentException("It was expected " + MainAddress.class.getSimpleName() + " and not "
					+ o.getClass().getCanonicalName());
		}

		if (o != null) {
			if (this.getCountry() == null) {
				return -1;
			}
			if (((MainAddress) o).getCountry() == null) {
				return 1;
			}
			int r = this.getCountry().compareTo(((MainAddress) o).getCountry());
			if (r < 0) {
				return r;
			}
			if (this.getAdminDivision1() == null) {
				return -1;
			}
			if (((MainAddress) o).getAdminDivision1() == null) {
				return 1;
			}
			r = this.getAdminDivision1().compareTo(((MainAddress) o).getAdminDivision1());
			if (r < 0) {
				return r;
			}
			if (this.getLocality() == null) {
				return -1;
			}
			if (((MainAddress) o).getLocality() == null) {
				return 1;
			}
			return this.getLocality().compareTo(((MainAddress) o).getLocality());
		}

		return 1;

	}

	@Override
	public String toString() {

		return this.getCountry() + ", " + this.getAdminDivision1() + ", " + this.getLocality() + ", "
				+ this.getNeighbourhood() + ", " + this.getStreet();
	}

}
