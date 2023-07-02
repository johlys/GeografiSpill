package no.uib.inf101.sem2.map;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A helper class to load all countries
 */
public class CountryInit implements CountryLoader{
	
	private List<Country> countries;
	
	/**
	 * This method loads all countries from the land-hovedstader.csv file.
	 */
	public CountryInit() {
		this.countries = new ArrayList<>();
		String userPath = System.getProperty("user.dir");
		String path = userPath + "/land-hovedstader.csv";
		String line = "";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			int i = 0;
			while((line = br.readLine()) != null) {
				String[] values = line.split(";");
				
				if(i != 0 && !line.isBlank()) {
					this.countries.add(new Country(values[0], values[1], values[2], values[3]));
				}
				i++;
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
		}
	}

	@Override
	public List<Country> getAllCountries() {
		return this.countries;
	}
	
}
