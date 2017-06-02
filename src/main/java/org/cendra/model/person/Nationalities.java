package org.cendra.model.person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
public class Nationalities implements Serializable, Comparable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5975531749662873125L;

	private Nationality mainNationality;
	private List<Nationality> nationalities = new ArrayList<Nationality>();

	public Nationality getMainNationality() {
		return mainNationality;
	}

	public void setMainNationality(Nationality mainNationality) {
		this.mainNationality = mainNationality;
	}

	public List<Nationality> getNationalities() {
		nationalities = formatValueNationalities(nationalities);
		return nationalities;
	}

	public void setNationalities(List<Nationality> nationalities) {
		nationalities = formatValueNationalities(nationalities);
		this.nationalities = nationalities;
	}

	public void addNationality(Nationality nationality) {
		nationalities = formatValueNationalities(nationalities);
		this.nationalities.add(nationality);
	}

	protected List<Nationality> formatValueNationalities(List<Nationality> nationalities) {
		List<Nationality> r = new ArrayList<Nationality>();

		if (nationalities != null) {
			for (Nationality nationality : nationalities) {
				if (nationality != null) {
					r.add(nationality);
				}
			}
		}

		return r;
	}

	@Override
	public Nationalities clone() throws CloneNotSupportedException {
		Nationalities other = new Nationalities();

		if (this.getMainNationality() != null) {
			other.setMainNationality(this.getMainNationality().clone());
		} else {
			other.setMainNationality(null);
		}
		if (this.getNationalities() != null) {
			for (Nationality nationality : this.getNationalities()) {
				if (nationality != null) {
					this.addNationality(nationality.clone());
				}
			}
		} else {
			other.setNationalities(null);
		}

		return other;
	}

	@Override
	public int compareTo(Object o) {

		if (o != null && o instanceof Nationalities == false) {
			throw new IllegalArgumentException("It was expected " + Nationalities.class.getSimpleName() + " and not "
					+ o.getClass().getCanonicalName());
		}

		return this.getMainNationality().compareTo(((Nationalities) o).getMainNationality());
	}

	@Override
	public String toString() {
		if (mainNationality != null) {
			return mainNationality.toString();
		}
		return null;

	}

}
