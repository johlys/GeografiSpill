package no.uib.inf101.sem2.map;

import java.util.List;

/**
 * Interface for CountryInit
 */
public interface CountryLoader {
	
	/**
	 * @return - a list of all countries containing name of country, capital and region
	 */
	List<Country> getAllCountries();

}
