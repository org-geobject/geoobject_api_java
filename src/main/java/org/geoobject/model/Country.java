package org.geoobject.model;

import java.util.ArrayList;
import java.util.List;

public class Country {

	/**
	 * Assigned to a country, territory, or area of geographical interest.
	 */
	public static final String ISO2_STATE_OFfICIALLY = "Officially assigned";

	/**
	 * Free for assignment at the disposal of users.
	 */
	public static final String ISO2_STATE_USER_ASSIGNED = "User-assigned";

	/**
	 * Reserved on request for restricted use.
	 */
	public static final String ISO2_STATE_EXCEPTIONALLY = "Exceptionally reserved";

	/**
	 * Deleted from ISO 3166-1 but reserved transitionally.
	 */
	public static final String ISO2_STATE_TRANSITIONALLY = "Transitionally reserved";

	/**
	 * Used in coding systems associated with ISO 3166-1.
	 */
	public static final String ISO2_STATE_INDETERMINATELY = "Indeterminately reserved";

	/**
	 * Free for assignment by the ISO 3166/MA only.
	 */
	public static final String UNASSIGNED = "Unassigned";

	@SuppressWarnings("unused")
	private String id;
	
	private Boolean independent;
	private Boolean recognisedStates;
	private String iso2State;

	private Continent continent;
	private String iso2;
	private String iso3;
	private String isoNumeric;
	private String fips;
	private String equivalentFipsCode;
	private String shortName;
	private String description;
	private List<AlternateName> alternateNames = new ArrayList<AlternateName>();
	private List<String> abbreviations = new ArrayList<String>();
	private List<String> iataCodes = new ArrayList<String>();

	private String capital;
	private String tld;
	private String phone;
	private String postalCodeFormat;
	private String postalCodeRegex;
	private List<TimeZone> timeZones = new ArrayList<TimeZone>();
	private List<Currency> currencyList = new ArrayList<Currency>();
	private List<Languaje> languajes = new ArrayList<Languaje>();

	private String geonameId;

	private File flagAFile;
	private File flagBFile;
	private File coatOfArms;
	private File orthographicProjectionFile;
	private File regionFile;
	private File mapFile;

	private String urlWikipedia;
	private String urlWikipedia2;

	private String urlGeonames;
	private String urlGeonamesMap;
	private String urlGeonamesOtherCountryNames;
	private String urlGeonamesAdministrativeDivision;
	
	public String getId() {
		return iso2;
	}

	public void setId(String id) {
		this.iso2 = id;
	}

	public Boolean getIndependent() {
		return independent;
	}

	public void setIndependent(Boolean independent) {
		this.independent = independent;
	}

	public Boolean getRecognisedStates() {
		return recognisedStates;
	}

	public void setRecognisedStates(Boolean recognisedStates) {
		this.recognisedStates = recognisedStates;
	}

	public String getIso2State() {
		return iso2State;
	}

	public void setIso2State(String iso2State) {
		this.iso2State = iso2State;
	}

	public Continent getContinent() {
		return continent;
	}

	public void setContinent(Continent continent) {
		this.continent = continent;
	}

	public String getIso2() {
		return iso2;
	}

	public void setIso2(String iso2) {
		this.iso2 = iso2;
	}

	public String getIso3() {
		return iso3;
	}

	public void setIso3(String iso3) {
		this.iso3 = iso3;
	}

	public String getIsoNumeric() {
		return isoNumeric;
	}

	public void setIsoNumeric(String isoNumeric) {
		this.isoNumeric = isoNumeric;
	}

	public String getFips() {
		return fips;
	}

	public void setFips(String fips) {
		this.fips = fips;
	}

	public String getEquivalentFipsCode() {
		return equivalentFipsCode;
	}

	public void setEquivalentFipsCode(String equivalentFipsCode) {
		this.equivalentFipsCode = equivalentFipsCode;
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

	public List<String> getIataCodes() {
		return iataCodes;
	}

	public void setIataCodes(List<String> iataCodes) {
		this.iataCodes = iataCodes;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getTld() {
		return tld;
	}

	public void setTld(String tld) {
		this.tld = tld;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostalCodeFormat() {
		return postalCodeFormat;
	}

	public void setPostalCodeFormat(String postalCodeFormat) {
		this.postalCodeFormat = postalCodeFormat;
	}

	public String getPostalCodeRegex() {
		return postalCodeRegex;
	}

	public void setPostalCodeRegex(String postalCodeRegex) {
		this.postalCodeRegex = postalCodeRegex;
	}

	public List<TimeZone> getTimeZones() {
		return timeZones;
	}

	public void setTimeZones(List<TimeZone> timeZones) {
		this.timeZones = timeZones;
	}

	public List<Currency> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(List<Currency> currencyList) {
		this.currencyList = currencyList;
	}

	public List<Languaje> getLanguajes() {
		return languajes;
	}

	public void setLanguajes(List<Languaje> languajes) {
		this.languajes = languajes;
	}

	public String getGeonameId() {
		return geonameId;
	}

	public void setGeonameId(String geonameId) {
		this.geonameId = geonameId;
	}

	public File getFlagAFile() {
		return flagAFile;
	}

	public void setFlagAFile(File flagAFile) {
		this.flagAFile = flagAFile;
	}

	public File getFlagBFile() {
		return flagBFile;
	}

	public void setFlagBFile(File flagBFile) {
		this.flagBFile = flagBFile;
	}

	public File getCoatOfArms() {
		return coatOfArms;
	}

	public void setCoatOfArms(File coatOfArms) {
		this.coatOfArms = coatOfArms;
	}

	public File getOrthographicProjectionFile() {
		return orthographicProjectionFile;
	}

	public void setOrthographicProjectionFile(File orthographicProjectionFile) {
		this.orthographicProjectionFile = orthographicProjectionFile;
	}

	public File getRegionFile() {
		return regionFile;
	}

	public void setRegionFile(File regionFile) {
		this.regionFile = regionFile;
	}

	public File getMapFile() {
		return mapFile;
	}

	public void setMapFile(File mapFile) {
		this.mapFile = mapFile;
	}

	public String getUrlWikipedia() {
		return urlWikipedia;
	}

	public void setUrlWikipedia(String urlWikipedia) {
		this.urlWikipedia = urlWikipedia;
	}

	public String getUrlWikipedia2() {
		return urlWikipedia2;
	}

	public void setUrlWikipedia2(String urlWikipedia2) {
		this.urlWikipedia2 = urlWikipedia2;
	}

	public String getUrlGeonames() {
		return urlGeonames;
	}

	public void setUrlGeonames(String urlGeonames) {
		this.urlGeonames = urlGeonames;
	}

	public String getUrlGeonamesMap() {
		return urlGeonamesMap;
	}

	public void setUrlGeonamesMap(String urlGeonamesMap) {
		this.urlGeonamesMap = urlGeonamesMap;
	}

	public String getUrlGeonamesOtherCountryNames() {
		return urlGeonamesOtherCountryNames;
	}

	public void setUrlGeonamesOtherCountryNames(String urlGeonamesOtherCountryNames) {
		this.urlGeonamesOtherCountryNames = urlGeonamesOtherCountryNames;
	}

	public String getUrlGeonamesAdministrativeDivision() {
		return urlGeonamesAdministrativeDivision;
	}

	public void setUrlGeonamesAdministrativeDivision(String urlGeonamesAdministrativeDivision) {
		this.urlGeonamesAdministrativeDivision = urlGeonamesAdministrativeDivision;
	}

	@Override
	public String toString() {
		return "Country [iso2=" + iso2 + ", iso3=" + iso3 + ", isoNumeric=" + isoNumeric + ", fips=" + fips
				+ ", shortName=" + shortName + "]";
	}

	
	
}
