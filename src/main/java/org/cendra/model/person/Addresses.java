package org.cendra.model.person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.geoobject.model.Address;

public class Addresses implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1409809665951661688L;

	private MainAddress mainAddress;
	private List<Address> addresses = new ArrayList<Address>();

	public MainAddress getMainAddress() {
		return mainAddress;
	}

	public void setMainAddress(MainAddress mainAddress) {
		this.mainAddress = mainAddress;
	}

	public List<Address> getAddresses() {
		addresses = formatValueNationalities(addresses);
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		addresses = formatValueNationalities(addresses);
		this.addresses = addresses;
	}

	public void addAddress(Address address) {
		addresses = formatValueNationalities(addresses);
		this.addresses.add(address);
	}

	protected List<Address> formatValueNationalities(List<Address> addresses) {
		List<Address> r = new ArrayList<Address>();

		if (addresses != null) {
			for (Address address : addresses) {
				if (address != null) {
					r.add(address);
				}
			}
		}

		return r;
	}

	@Override
	public Addresses clone() throws CloneNotSupportedException {
		Addresses other = new Addresses();

		if (this.getMainAddress() != null) {
			other.setMainAddress(this.getMainAddress().clone());
		} else {
			other.setMainAddress(null);
		}
		if (this.getAddresses() != null) {
			for (Address address : this.getAddresses()) {
				if (address != null) {
					this.addAddress(address.clone());
				}
			}
		} else {
			other.setAddresses(null);
		}

		return other;
	}

}
