package org.geoobject.model;

import java.util.ArrayList;
import java.util.List;

import org.cendra.model.commons.EntityErasableCoded;
import org.cendra.model.commons.EntityId;
import org.cendra.model.commons.File;

public class Country extends EntityId {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7569489252323330439L;

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
	private List<Currency> currencies = new ArrayList<Currency>();
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

	public List<String> getIataCodes() {
		iataCodes = formatValueIataCodes(iataCodes);
		return iataCodes;
	}

	public void setIataCodes(List<String> iataCodes) {
		iataCodes = formatValueIataCodes(iataCodes);
		this.iataCodes = iataCodes;
	}

	public void addIataCode(String iataCode) {
		iataCodes = formatValueIataCodes(iataCodes);
		this.iataCodes.add(iataCode);
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
		timeZones = formatValueTimeZones(timeZones);
		return timeZones;
	}

	public void setTimeZones(List<TimeZone> timeZones) {
		timeZones = formatValueTimeZones(timeZones);
		this.timeZones = timeZones;
	}

	public void addTimeZone(TimeZone timeZone) {
		timeZones = formatValueTimeZones(timeZones);
		this.timeZones.add(timeZone);
	}

	public List<Currency> getCurrencies() {
		currencies = formatValueCurrencies(currencies);
		return currencies;
	}

	public void setCurrencies(List<Currency> currencies) {
		currencies = formatValueCurrencies(currencies);
		this.currencies = currencies;
	}

	public void addCurrency(Currency currency) {
		currencies = formatValueCurrencies(currencies);
		this.currencies.add(currency);
	}

	public List<Languaje> getLanguajes() {
		languajes = formatValueLanguajes(languajes);
		return languajes;
	}

	public void setLanguajes(List<Languaje> languajes) {
		languajes = formatValueLanguajes(languajes);
		this.languajes = languajes;
	}

	public void addLanguaje(Languaje languaje) {
		languajes = formatValueLanguajes(languajes);
		this.languajes.add(languaje);
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
	public Country clone() throws CloneNotSupportedException {
		Country other = (Country) super.clone();

		other.setIndependent(this.getIndependent());
		other.setRecognisedStates(this.getRecognisedStates());
		other.setIso2State(this.getIso2State());
		if (this.getContinent() != null) {
			other.setContinent(this.getContinent().clone());
		} else {
			other.setContinent(null);
		}
		other.setIso2(this.getIso2());
		other.setIso3(this.getIso3());
		other.setIsoNumeric(this.getIsoNumeric());
		other.setFips(this.getFips());
		other.setEquivalentFipsCode(this.getEquivalentFipsCode());
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
		if (this.getIataCodes() != null) {
			for (String iataCode : this.getIataCodes()) {
				if (iataCode != null) {
					this.addIataCode(iataCode);
					;
				}

			}
		} else {
			other.setIataCodes(null);
			;
		}
		other.setCapital(this.getCapital());
		other.setTld(this.getTld());
		other.setPhone(this.getPhone());
		other.setPostalCodeFormat(this.getPostalCodeFormat());
		other.setPostalCodeRegex(this.getPostalCodeRegex());

		if (this.getTimeZones() != null) {
			for (TimeZone timeZone : this.getTimeZones()) {
				if (timeZone != null) {
					this.addTimeZone(timeZone.clone());
				}

			}
		} else {
			other.setLanguajes(null);
		}

		if (this.getCurrencies() != null) {
			for (Currency currency : this.getCurrencies()) {
				if (currency != null) {
					this.addCurrency((Currency) currency.clone());
				}

			}
		} else {
			other.setCurrencies(null);
		}
		if (this.getLanguajes() != null) {
			for (Languaje languaje : this.getLanguajes()) {
				if (languaje != null) {
					this.addLanguaje(languaje.clone());
				}

			}
		} else {
			other.setLanguajes(null);
		}
		other.setGeonameId(this.geonameId);

		if (this.getFlagAFile() != null) {
			other.setFlagAFile(this.getFlagAFile().clone());
		} else {
			other.setFlagAFile(null);
		}
		if (this.getFlagBFile() != null) {
			other.setFlagBFile(this.getFlagBFile().clone());
		} else {
			other.setFlagBFile(null);
		}
		if (this.getCoatOfArms() != null) {
			other.setCoatOfArms(this.getCoatOfArms().clone());
		} else {
			other.setCoatOfArms(null);
		}
		if (this.getOrthographicProjectionFile() != null) {
			other.setOrthographicProjectionFile(this.getOrthographicProjectionFile().clone());
		} else {
			other.setOrthographicProjectionFile(null);
		}
		if (this.getRegionFile() != null) {
			other.setRegionFile(this.getRegionFile().clone());
		} else {
			other.setRegionFile(null);
		}
		if (this.getMapFile() != null) {
			other.setMapFile(this.getMapFile());
		} else {
			other.setMapFile(null);
		}
		other.setUrlWikipedia(this.getUrlWikipedia());
		other.setUrlWikipedia2(this.getUrlWikipedia2());
		other.setUrlGeonames(this.getUrlGeonames());
		other.setUrlGeonamesMap(this.getUrlGeonamesMap());
		other.setUrlGeonamesOtherCountryNames(this.getUrlGeonamesOtherCountryNames());
		other.setUrlGeonamesAdministrativeDivision(this.getUrlGeonamesAdministrativeDivision());

		return other;
	}

	@Override
	public String toString() {
		return "Country [iso2=" + iso2 + ", iso3=" + iso3 + ", isoNumeric=" + isoNumeric + ", fips=" + fips
				+ ", shortName=" + shortName + "]";
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

	protected List<String> formatValueIataCodes(List<String> iataCodes) {
		List<String> r = new ArrayList<String>();

		if (iataCodes != null) {
			for (String iataCode : iataCodes) {
				if (iataCode != null) {
					r.add(iataCode);
				}
			}
		}

		return r;
	}

	protected List<Languaje> formatValueLanguajes(List<Languaje> languajes) {
		List<Languaje> r = new ArrayList<Languaje>();

		if (languajes != null) {
			for (Languaje languaje : languajes) {
				if (languaje != null) {
					r.add(languaje);
				}
			}
		}

		return r;
	}

	protected List<TimeZone> formatValueTimeZones(List<TimeZone> timeZones) {
		List<TimeZone> r = new ArrayList<TimeZone>();

		if (timeZones != null) {
			for (TimeZone timeZone : timeZones) {
				if (timeZone != null) {
					r.add(timeZone);
				}
			}
		}

		return r;
	}

	protected List<Currency> formatValueCurrencies(List<Currency> currencies) {
		List<Currency> r = new ArrayList<Currency>();

		if (currencies != null) {
			for (Currency currency : currencies) {
				if (currency != null) {
					r.add(currency);
				}
			}
		}

		return r;
	}

}
