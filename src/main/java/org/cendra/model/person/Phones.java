package org.cendra.model.person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Phones implements Serializable, Cloneable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 4430031184112259496L;
	
	private MainPhone mainPhone;
	private List<Phone> alternativePhones = new ArrayList<Phone>();

	public MainPhone getMainPhone() {
		return mainPhone;
	}

	public void setMainPhone(MainPhone mainPhone) {
		this.mainPhone = mainPhone;
	}

	public List<Phone> getAlternativePhones() {
		return alternativePhones;
	}

	public void setAlternativePhones(List<Phone> alternativePhones) {
		this.alternativePhones = alternativePhones;
	}
	
	public void addAlternativePhone(Phone alternativePhone) {
		if(alternativePhone == null){
			return;
		}
		this.alternativePhones.add(alternativePhone);
	}
	
	protected List<Phone> formatValuePhones(List<Phone> phones) {
		List<Phone> r = new ArrayList<Phone>();

		if (phones != null) {
			for (Phone phone : phones) {
				if (phone != null) {
					r.add(phone);
				}
			}
		}

		return r;
	}

}
