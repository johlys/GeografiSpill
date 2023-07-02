package no.uib.inf101.sem2.map;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Johannes Lysne
 * A class for each region of the world
 */
public class WorldRegion implements CountryLoader{

	private CountryLoader loader;
	private List<Country> countriesInRegion;
	
	/**
	 * Constructor to get all countries into different regions
	 * @param loader - the loader that gets all the data from the .csv file.
	 * @param region - the world region
	 */
	public WorldRegion(CountryLoader loader, String region) {
		this.loader = loader;
		this.countriesInRegion = new ArrayList<>();
		for(Country country : loader.getAllCountries()) {
			if(country.getRegion().equals(region)) {
				this.countriesInRegion.add(country);
			}
		}
	}
	
	/**
	 * @return - a list of all countries in the given region
	 */
	public List<Country> getAllCountriesInRegion(){
		return this.countriesInRegion;
	}

	@Override
	public List<Country> getAllCountries() {
		return getAllCountries();
	}
	
	
}
