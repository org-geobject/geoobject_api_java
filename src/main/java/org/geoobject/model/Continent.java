package org.geoobject.model;

import java.util.ArrayList;
import java.util.List;

import org.cendra.model.commons.EntityId;
import org.cendra.model.commons.File;

public class Continent extends EntityId {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6321979075132565850L;

	private String code;
	private String shortName;
	private String description;
	private List<AlternateName> alternateNames = new ArrayList<AlternateName>();
	private List<String> abbreviations = new ArrayList<String>();
	private File orthographicProjectionFile;
	private String urlWikipedia;
	private String geonameId;

	public String getId() {
		code = this.formatValue(code);
		return code;
	}

	public void setId(String id) {
		id = this.formatValue(id);
		this.code = id;
	}

	public String getCode() {
		code = this.formatValue(code);
		return code;
	}

	public void setCode(String code) {
		code = this.formatValue(code);
		this.code = code;
	}

	public String getShortName() {
		shortName = this.formatValue(shortName);
		return shortName;
	}

	public void setShortName(String shortName) {
		shortName = this.formatValue(shortName);
		this.shortName = shortName;
	}

	public String getDescription() {
		description = this.formatValue(description);
		return description;
	}

	public void setDescription(String description) {
		description = this.formatValue(description);
		this.description = description;
	}

	public List<AlternateName> getAlternateNames() {
		alternateNames = formatValueAlternateNames(alternateNames);
		return alternateNames;
	}

	public void setAlternateNames(List<AlternateName> alternateNames) {
		alternateNames = formatValueAlternateNames(alternateNames);
		this.alternateNames = alternateNames;
	}

	public void addAlternateName(AlternateName alternateName) {
		alternateNames = formatValueAlternateNames(alternateNames);
		this.alternateNames.add(alternateName);
	}

	public List<String> getAbbreviations() {
		abbreviations = formatValueAbbreviations(abbreviations);
		return abbreviations;
	}

	public void setAbbreviations(List<String> abbreviations) {
		abbreviations = formatValueAbbreviations(abbreviations);
		this.abbreviations = abbreviations;
	}

	public void addAbbreviation(String abbreviation) {
		abbreviations = formatValueAbbreviations(abbreviations);
		this.abbreviations.add(abbreviation);
	}

	public File getOrthographicProjectionFile() {
		return orthographicProjectionFile;
	}

	public void setOrthographicProjectionFile(File orthographicProjectionFile) {
		this.orthographicProjectionFile = orthographicProjectionFile;
	}

	public String getUrlWikipedia() {
		urlWikipedia = this.formatValue(urlWikipedia);
		return urlWikipedia;
	}

	public void setUrlWikipedia(String urlWikipedia) {
		urlWikipedia = this.formatValue(urlWikipedia);
		this.urlWikipedia = urlWikipedia;
	}

	public String getGeonameId() {
		geonameId = this.formatValue(geonameId);
		return geonameId;
	}

	public void setGeonameId(String geonameId) {
		geonameId = this.formatValue(geonameId);
		this.geonameId = geonameId;
	}

	public String allNames() {
		StringBuilder sb = new StringBuilder();

		sb.append(this.getShortName() + "\n");
		sb.append(this.getDescription() + "\n");
		if (this.getAbbreviations() != null) {
			for (String item : this.getAbbreviations()) {
				sb.append(item + "\n");
			}
		}

		if (this.getAlternateNames() != null) {
			for (AlternateName item : this.getAlternateNames()) {
				sb.append(item.getAlternateName() + "\n");
			}
		}

		return sb.toString();
	}

	@Override
	public Continent clone() throws CloneNotSupportedException {
		Continent other = (Continent) super.clone();

		other.setCode(this.getCode());
		other.setShortName(this.getShortName());
		other.setDescription(this.getDescription());

		if (this.getAlternateNames() != null) {
			for (AlternateName alternateName : this.getAlternateNames()) {
				if (alternateName != null) {
					this.addAlternateName(alternateName.clone());
				}

			}
		} else {
			other.setAlternateNames(null);
		}

		if (this.getAbbreviations() != null) {
			for (String abbreviation : this.getAbbreviations()) {
				if (abbreviation != null) {
					this.addAbbreviation(abbreviation);
				}

			}
		} else {
			other.setAbbreviations(null);
		}

		if (other.getOrthographicProjectionFile() != null) {
			other.setOrthographicProjectionFile(this.getOrthographicProjectionFile().clone());
		} else {
			other.setOrthographicProjectionFile(null);
		}
		other.setUrlWikipedia(this.getUrlWikipedia());
		other.setGeonameId(this.getGeonameId());

		return other;
	}

	@Override
	public int compareTo(Object o) {

		if (o != null && o instanceof Continent == false) {
			throw new IllegalArgumentException("It was expected " + Continent.class.getSimpleName() + " and not "
					+ o.getClass().getCanonicalName());
		}

		if (this.getCode() != null && o != null) {
			return this.getCode().compareTo(((Continent) o).getCode());
		}

		if (this.getCode() != null && o == null) {
			return this.getCode().compareTo("");
		}

		return "".compareTo(((Continent) o).getCode());
	}

	protected List<AlternateName> formatValueAlternateNames(List<AlternateName> alternateNames) {
		List<AlternateName> r = new ArrayList<AlternateName>();

		if (alternateNames != null) {
			for (AlternateName alternateName : alternateNames) {
				if (alternateName != null) {
					r.add(alternateName);
				}
			}
		}

		return r;
	}

	protected List<String> formatValueAbbreviations(List<String> abbreviations) {
		List<String> r = new ArrayList<String>();

		if (abbreviations != null) {
			for (String abbreviation : abbreviations) {
				if (abbreviation != null) {
					r.add(abbreviation);
				}
			}
		}

		return r;
	}

}
