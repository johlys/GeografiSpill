package no.uib.inf101.sem2.map;

/**
 * A class to represent a country
 */
public class Country {

	private final String country;
	private final String capital;
	private final String region;
	private final String flagPath;
	
	/**
	 * Constructor to create a country
	 * @param country - The country name
	 * @param capital - The capital of the country
	 * @param region - The region where the country is located
	 * @param flagPath - The name of the .png image
	 */
	public Country(String country, String capital, String region, String flagPath) {
		this.country = country;
		this.capital = capital;
		this.region = region;
		this.flagPath = flagPath;
	}

	/**
	 * @return - the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @return - the capital of the country
	 */
	public String getCapital() {
		return capital;
	}

	/**
	 * @return - the name of the region the country is located
	 */
	public String getRegion() {
		return region;
	}
	
	/**
	 * The flag path is used when loading images on the screen. Every country
	 * has a file that can be loaded and displayed on the screen.
	 * @return - the name of the flag file
	 */
	public String getFlagPath() {
		return flagPath;
	}
}
