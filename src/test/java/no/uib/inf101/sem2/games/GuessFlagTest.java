package no.uib.inf101.sem2.games;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.highscore.HighScoreModel;
import no.uib.inf101.sem2.map.CountryInit;
import no.uib.inf101.sem2.map.WorldRegion;

class GuessFlagTest {
	
	WorldRegion nordAmerica = new WorldRegion(new CountryInit(), "Nord-Amerika");
	WorldRegion sørAmerica = new WorldRegion(new CountryInit(), "Sør-Amerika");
	WorldRegion europa = new WorldRegion(new CountryInit(), "Europa");
	WorldRegion afrika = new WorldRegion(new CountryInit(), "Afrika");
	WorldRegion asia = new WorldRegion(new CountryInit(), "Asia");
	WorldRegion oseania = new WorldRegion(new CountryInit(), "Oseania");
	HighScoreModel highScoreModel = new HighScoreModel();

	@Test
	void scoreAndGuessTest() {
		FlagGame flagGame = new FlagGame(afrika, highScoreModel);
		assertEquals(afrika.getAllCountriesInRegion().size(), flagGame.totalScore());
		assertEquals(0, flagGame.getScore());
		
		flagGame.correctGuess(flagGame.getRandomCounry().getFlagPath());
		assertEquals(flagGame.getScore(), 1);
		
		flagGame.correctGuess("Wrong guess");
		assertEquals(flagGame.getScore(), 1);
	}
	
	@Test
	void gameOverTest() {
		FlagGame flagGame = new FlagGame(europa, highScoreModel);
		for(int i = 0 ; i < flagGame.totalScore(); i++) {
			flagGame.correctGuess("Wrong guess");
		}
		assertTrue(flagGame.gameOver());
		
		FlagGame flagGame2 = new FlagGame(europa, highScoreModel);
		for(int i = 0 ; i < flagGame2.totalScore()-1; i++) {
			flagGame2.correctGuess("Wrong guess");
		}
		assertFalse(flagGame2.gameOver());
	}

}
