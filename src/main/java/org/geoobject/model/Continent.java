package org.geoobject.model;

import java.util.ArrayList;
import java.util.List;

import org.cendra.model.commons.File;

public class Continent implements Comparable<Continent> {

	@SuppressWarnings("unused")
	private String id;
	private String code;
	private String shortName;
	private String description;
	private List<AlternateName> alternateNames = new ArrayList<AlternateName>();
	private List<String> abbreviations = new ArrayList<String>();
	private File orthographicProjectionFile;
	private String urlWikipedia;
	private String geonameId;

	public String getId() {
		return code;
	}

	public void setId(String id) {
		this.code = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<AlternateName> getAlternateNames() {
		return alternateNames;
	}

	public void setAlternateNames(List<AlternateName> alternateNames) {
		this.alternateNames = alternateNames;
	}

	public List<String> getAbbreviations() {
		return abbreviations;
	}

	public void setAbbreviations(List<String> abbreviations) {
		this.abbreviations = abbreviations;
	}

	public File getOrthographicProjectionFile() {
		return orthographicProjectionFile;
	}

	public void setOrthographicProjectionFile(File orthographicProjectionFile) {
		this.orthographicProjectionFile = orthographicProjectionFile;
	}

	public String getUrlWikipedia() {
		return urlWikipedia;
	}

	public void setUrlWikipedia(String urlWikipedia) {
		this.urlWikipedia = urlWikipedia;
	}

	public String getGeonameId() {
		return geonameId;
	}

	public void setGeonameId(String geonameId) {
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
	public String toString() {
		return "Continent [id=" + code + ", code=" + code + ", shortName=" + shortName + "]";
	}

	@Override
	public int compareTo(Continent obj) {
		
		return this.getCode().compareTo(obj.getCode());
	}

}
