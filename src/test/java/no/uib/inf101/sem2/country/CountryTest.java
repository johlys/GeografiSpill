package no.uib.inf101.sem2.country;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.map.Country;
import no.uib.inf101.sem2.map.CountryInit;
import no.uib.inf101.sem2.map.WorldRegion;

class CountryTest {
	
	WorldRegion nordAmerica = new WorldRegion(new CountryInit(), "Nord-Amerika");
	WorldRegion sørAmerica = new WorldRegion(new CountryInit(), "Sør-Amerika");
	WorldRegion europa = new WorldRegion(new CountryInit(), "Europa");
	WorldRegion afrika = new WorldRegion(new CountryInit(), "Afrika");
	WorldRegion asia = new WorldRegion(new CountryInit(), "Asia");
	WorldRegion oseania = new WorldRegion(new CountryInit(), "Oseania");

	
	@Test
	void correctRegions() {
		List<String> northAmericaList = new ArrayList<>();
		for(Country co : nordAmerica.getAllCountriesInRegion()) {
			northAmericaList.add(co.getCountry());
		}
		assertTrue(northAmericaList.contains("Cuba"));
		
		List<String> southAmericaList = new ArrayList<>();
		for(Country co : sørAmerica.getAllCountriesInRegion()) {
			southAmericaList.add(co.getCountry());
		}
		assertTrue(southAmericaList.contains("Argentina"));
		
		List<String> europaList = new ArrayList<>();
		for(Country co : europa.getAllCountriesInRegion()) {
			europaList.add(co.getCountry());
		}
		assertTrue(europaList.contains("Norge"));
		
		List<String> africaList = new ArrayList<>();
		for(Country co : afrika.getAllCountriesInRegion()) {
			africaList.add(co.getCountry());
		}
		assertTrue(africaList.contains("Angola"));
		
		List<String> asiaList = new ArrayList<>();
		for(Country co : asia.getAllCountriesInRegion()) {
			asiaList.add(co.getCountry());
		}
		assertTrue(asiaList.contains("Japan"));
		
		List<String> oceaniaList = new ArrayList<>();
		for(Country co : oseania.getAllCountriesInRegion()) {
			oceaniaList.add(co.getCountry());
		}
		assertTrue(oceaniaList.contains("New Zealand"));
		
	}
	
	@Test
	void correctImageNameFormat() {
		boolean northAmericaBoolean = true;
		for(Country country : nordAmerica.getAllCountriesInRegion()) {
			String format = country.getCountry() + ".png";
			if(!country.getFlagPath().equals(format)) {
				northAmericaBoolean = false;
				break;
			}
		}
		assertTrue(northAmericaBoolean);
		
		boolean southAmericaBoolean = true;
		for(Country country : sørAmerica.getAllCountriesInRegion()) {
			String format = country.getCountry() + ".png";
			if(!country.getFlagPath().equals(format)) {
				southAmericaBoolean = false;
				break;
			}
		}
		assertTrue(southAmericaBoolean);
		
		boolean europeBoolean = true;
		for(Country country : europa.getAllCountriesInRegion()) {
			String format = country.getCountry() + ".png";
			if(!country.getFlagPath().equals(format)) {
				europeBoolean = false;
				break;
			}
		}
		assertTrue(europeBoolean);
		
		boolean afrikaBoolean = true;
		for(Country country : afrika.getAllCountriesInRegion()) {
			String format = country.getCountry() + ".png";
			if(!country.getFlagPath().equals(format)) {
				afrikaBoolean = false;
				break;
			}
		}
		assertTrue(afrikaBoolean);
		
		boolean asiaBoolean = true;
		for(Country country : asia.getAllCountriesInRegion()) {
			String format = country.getCountry() + ".png";
			if(!country.getFlagPath().equals(format)) {
				asiaBoolean = false;
				break;
			}
		}
		assertTrue(asiaBoolean);
		
		boolean oceaniaBoolean = true;
		for(Country country : oseania.getAllCountriesInRegion()) {
			String format = country.getCountry() + ".png";
			if(!country.getFlagPath().equals(format)) {
				oceaniaBoolean = false;
				break;
			}
		}
		assertTrue(oceaniaBoolean);
	}

}
