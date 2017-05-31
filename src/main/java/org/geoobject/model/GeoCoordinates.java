package org.geoobject.model;

import java.io.Serializable;

@SuppressWarnings("rawtypes")
public class GeoCoordinates implements Serializable, Comparable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6355835406066689975L;

	private Double lat = null;
	private Double lng = null;

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	@Override
	public GeoCoordinates clone() throws CloneNotSupportedException {
		GeoCoordinates other = new GeoCoordinates();

		other.setLat(this.getLat());
		other.setLng(this.getLng());

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
