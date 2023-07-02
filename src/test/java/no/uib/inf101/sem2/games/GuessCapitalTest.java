package no.uib.inf101.sem2.games;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.highscore.HighScoreModel;
import no.uib.inf101.sem2.map.Country;
import no.uib.inf101.sem2.map.CountryInit;
import no.uib.inf101.sem2.map.CountryLoader;
import no.uib.inf101.sem2.map.WorldRegion;

class GuessCapitalTest {
	
	WorldRegion nordAmerica = new WorldRegion(new CountryInit(), "Nord-Amerika");
	WorldRegion sørAmerica = new WorldRegion(new CountryInit(), "Sør-Amerika");
	WorldRegion europa = new WorldRegion(new CountryInit(), "Europa");
	WorldRegion afrika = new WorldRegion(new CountryInit(), "Afrika");
	WorldRegion asia = new WorldRegion(new CountryInit(), "Asia");
	WorldRegion oseania = new WorldRegion(new CountryInit(), "Oseania");
	HighScoreModel highScoreModel = new HighScoreModel();

	@Test
	void correctNumberOfCountriesTest() {
		
		//See if you are given four alternatives
		GuessCapital capital = new GuessCapital(new WorldRegion(new CountryInit(), "Oseania"), highScoreModel);
		assertEquals(capital.getCapitals().size(), 4); //a
		
		int numCountries = nordAmerica.getAllCountriesInRegion().size() + sørAmerica.getAllCountriesInRegion().size() + europa.getAllCountriesInRegion().size()
		+ afrika.getAllCountriesInRegion().size() + asia.getAllCountriesInRegion().size() + oseania.getAllCountriesInRegion().size();
		
		assertEquals(numCountries, 196);
	}
	
	@Test
	void scoreAndGuessTest() {
		GuessCapital capitalGame = new GuessCapital(afrika, highScoreModel);
		assertEquals(afrika.getAllCountriesInRegion().size(), capitalGame.totalScore());
		assertEquals(0, capitalGame.getScore());
		
		capitalGame.correctGuess(capitalGame.getRandomCounry().getCapital());
		assertEquals(capitalGame.getScore(), 1);
		
		capitalGame.correctGuess("Wrong guess");
		assertEquals(capitalGame.getScore(), 1);
	}
	
	@Test
	void gameOverTest() {
		GuessCapital capitalGame = new GuessCapital(europa, highScoreModel);
		for(int i = 0 ; i < capitalGame.totalScore(); i++) {
			capitalGame.correctGuess("Wrong guess");
		}
		assertTrue(capitalGame.gameOver());
		
		GuessCapital capitalGame2 = new GuessCapital(europa, highScoreModel);
		for(int i = 0 ; i < capitalGame2.totalScore()-1; i++) {
			capitalGame2.correctGuess("Wrong guess");
		}
		assertFalse(capitalGame2.gameOver());
	}
	
	

}
