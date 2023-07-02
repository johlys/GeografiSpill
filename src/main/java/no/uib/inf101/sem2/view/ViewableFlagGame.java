package no.uib.inf101.sem2.view;

import java.util.List;

import no.uib.inf101.sem2.map.Country;

/**
 * Interface for FlagGame
 *
 */
public interface ViewableFlagGame {
	
	/**
	 * A method to get a random country from the region
	 * @return - A random country in the region
	 */
	Country getRandomCounry();
	
	/**
	 * @return - a list of four flag alternatives. One of the alternatives
	 * is the correct one.
	 */
	public List<String> getFlagAlternatives();
	
	/**
	 * Method to check if a guess was correct. When this method is called the same question will
	 * not appear again. If the guess was correct your score is increased by one. You
	 * get no points if the guess was wrong.
	 * @param capitalGuess - the guess
	 * @return - True if guess was correct, false if not.
	 */
	boolean correctGuess(String capitalGuess);
	
	/**
	 * The score is increased by one if the player guesses correctly.
	 * @return - the score the player has.
	 */
	int getScore();
	
	/**
	 * The total score is the biggest score you can get in a game mode. It is equal to the
	 * amount of questions. For Europe the total score is equal to the number of countries in Europe.
	 * @return - the total score
	 */
	int totalScore();
	
	/**
	 * The game is over if you have gone through all questions.
	 * @return - True if game is over, False if not.
	 */
	boolean gameOver();

}
