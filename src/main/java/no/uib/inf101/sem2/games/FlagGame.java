package no.uib.inf101.sem2.games;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import no.uib.inf101.sem2.highscore.HighScoreModel;
import no.uib.inf101.sem2.map.Country;
import no.uib.inf101.sem2.map.WorldRegion;
import no.uib.inf101.sem2.view.ViewableFlagGame;

/**
 * In this game you are given a country as a string and you have to select what flag
 * is the correct flag for this country based on four alternatives. The game is over
 * when you have gone through all countries in the region.
 *
 */
public class FlagGame implements ViewableFlagGame{
	
	private WorldRegion region;
	private List<Country> countryList;
	private List<String> allFlags;
	private List<String> flagAlternatives;
	private int score;
	private int totalScore;
	private HighScoreModel highScores;
	
	/**
	 * Constructor to set up what region the game is in
	 * Adds a list of countries that is shuffled in random order
	 * @param region - the region of the game
	 */
	public FlagGame(WorldRegion region, HighScoreModel highScores) {
		this.region = region;
		this.countryList = new ArrayList<>();
		this.countryList.addAll(region.getAllCountriesInRegion());
		this.highScores = highScores;
		
		Collections.shuffle(this.countryList);
		this.flagAlternatives = new ArrayList<>();
		this.score = 0;
		this.totalScore = countryList.size();
		this.allFlags = new ArrayList<>();
		for(Country flag : region.getAllCountriesInRegion()) {
			this.allFlags.add(flag.getFlagPath());
		}
		setFlags();
	}

	public Country getRandomCounry(){
		try {
			return countryList.get(0);
		} catch (IndexOutOfBoundsException e) {
	        System.err.println("Error: Game is over");
	        return null;
		}
	}
	
	/**
	 * A method to set the flag alternatives.
	 * The country name is first in the list followed by four capital cities in random order.
	 */
	private void setFlags(){
		Country country = getRandomCounry();
		List<String> flags = new ArrayList<>();
		flags.add(country.getFlagPath());
		Collections.shuffle(this.allFlags);
		int i = 0;
		while(flags.size() < 4) {
			String flag = allFlags.get(i);
			if(!flag.equals(country.getFlagPath())) {
				flags.add(flag);
			}
			i++;
		}
		Collections.shuffle(flags);
		this.flagAlternatives = flags;
	}
	
	public boolean correctGuess(String flagGuess) {
		if(getFlagAlternatives().contains(flagGuess)) {
			if(!gameOver()) {
				this.countryList.remove(0);
			}
			if(countryList.size() > 0) {
				setFlags();
			}
			this.score++;
			setHighScore();
			return true;
		}
		else {
			if(!gameOver()) {
				this.countryList.remove(0);
			}
			if(countryList.size() > 0) {
				setFlags();
			}
			setHighScore();
			return false;
			
		}
	}
	
	@Override
	public int getScore() {
		return this.score;
	}

	@Override
	public List<String> getFlagAlternatives() {
		return this.flagAlternatives;
	}

	@Override
	public boolean gameOver() {
		return this.countryList.isEmpty();
	}

	@Override
	public int totalScore() {
		return totalScore;
	}
	
	private void setHighScore() {
		if(gameOver()) {
			this.highScores.setFlagScores(totalScore(), getScore());
		}
	}


}
