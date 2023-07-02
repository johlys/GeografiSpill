package no.uib.inf101.sem2.view;

import java.util.List;

import no.uib.inf101.sem2.map.Country;

/**
 * Interface for GuessCapital game
 *
 */
public interface ViewableGeographyGame {

	/**
	 * A method to get a random country from the region
	 * @return - A random country in the region
	 */
	Country getRandomCounry();
	
	/**
	 * Method to get the four capital-alternatives as a list. The list is arranged
	 * in random order.
	 * @return - A list of four capital cities.
	 */
	public List<String> getCapitals();
	
	/**
	 * Method to check if a guess was correct. When this method is called the same question will
	 * not appear again. If the guess was correct your score is increased by one. You
	 * get no points if the guess was wrong.
	 * @param capitalGuess - the guess
	 * @return - True if guess was correct, False if not
	 */
	boolean correctGuess(String capitalGuess);
	
	/**
	 * The score is increased by one if the player guesses correctly.
	 * @return - the score
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
