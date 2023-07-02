package no.uib.inf101.sem2.highscore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * A class to represent the score from each game
 */
public class HighScoreModel {
	
	private Map<Integer, Integer> capitalScores;
	private Map<Integer, Integer> flagScores;
	
	/**
	 * Constructor to create a new highscore model.
	 */
	public HighScoreModel() {
		this.capitalScores = new TreeMap<>(Collections.reverseOrder());
		this.flagScores = new TreeMap<>(Collections.reverseOrder());
	}
	
	/**
	 * Sets the highscore from the FlagGame
	 * @param totalScore - the possible total score you can get
	 * @param score - the score the player got
	 */
	public void setFlagScores(int totalScore, int score) {
		if(getFlagScores().containsKey(totalScore)){
			if(getFlagScores().get(totalScore) <= score){
				flagScores.put(totalScore, score);
			}
		}
		else if (!getFlagScores().containsKey(totalScore)) {
			flagScores.put(totalScore, score);
		}
	}
	
	/**
	 * @return - The highscores from FlagGame
	 */
	public Map<Integer, Integer> getFlagScores() {
		return flagScores;
	}
	
	/**
	 * Sets the highscore from the GuessCapitalGame
	 * @param totalScore - the possible total score you can get
	 * @param score - the score the player got
	 */
	public void setCapitalScores(int totalScore, int score) {
		if(getCapitalScores().containsKey(totalScore)){
			if(getCapitalScores().get(totalScore) <= score){
				capitalScores.put(totalScore, score);
			}
		}
		else if (!getCapitalScores().containsKey(totalScore)) {
			capitalScores.put(totalScore, score);
		}
	}
	
	/**
	 * @return - the highscores from GuessCapitalGame
	 */
	public Map<Integer, Integer> getCapitalScores() {
		return capitalScores;
	}

}
