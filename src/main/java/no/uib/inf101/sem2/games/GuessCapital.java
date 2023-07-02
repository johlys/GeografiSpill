package no.uib.inf101.sem2.games;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import no.uib.inf101.sem2.highscore.HighScoreModel;
import no.uib.inf101.sem2.map.Country;
import no.uib.inf101.sem2.map.CountryLoader;
import no.uib.inf101.sem2.map.WorldRegion;
import no.uib.inf101.sem2.view.ViewableGeographyGame;

/**
 * A game where you guess the capital of a country.
 * You are given a country name and you are provided with
 * 4 options where one is the capital to the given country. The order is random.
 *
 */
public class GuessCapital implements ViewableGeographyGame{
	
	private WorldRegion region;
	private List<Country> countryList;
	private List<String> allCapitals;
	private List<String> capitalAlternatives;
	private int score;
	private int totalScore;
	private HighScoreModel highScores;
	
	/**
	 * Constructor to set up what region the game is in
	 * Adds a list of countries that is shuffled in random order
	 * @param region - the region of the game
	 */
	public GuessCapital(WorldRegion region, HighScoreModel highScores) {
		this.region = region;
		this.countryList = new ArrayList<>();
		this.countryList.addAll(region.getAllCountriesInRegion());
		Collections.shuffle(this.countryList);
		this.capitalAlternatives = new ArrayList<>();
		this.score = 0;
		this.totalScore = countryList.size();
		this.highScores = highScores;
		this.allCapitals = new ArrayList<>();
		for(Country cap : region.getAllCountriesInRegion()) {
			this.allCapitals.add(cap.getCapital());
		}
		setCapitals();
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
	 * The country name is first in the list followed by four capital cities in random order.
	 */
	private void setCapitals(){
		Country country = getRandomCounry();
		List<String> capitals = new ArrayList<>();
		capitals.add(country.getCapital());
		for(int i = 1; i < 4 ; i++) {
			capitals.add(allCapitals.get(i));
		}
		Collections.shuffle(capitals);
		this.capitalAlternatives = capitals;
	}
	
	public boolean correctGuess(String capitalGuess) {
		if(getCapitals().contains(capitalGuess)) {
			if(!gameOver()) {
				this.countryList.remove(0);
			}
			if(countryList.size() > 0) {
				setCapitals();
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
				setCapitals();
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
	public List<String> getCapitals() {
		return this.capitalAlternatives;
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
			this.highScores.setCapitalScores(totalScore(), getScore());
		}
	}

}
