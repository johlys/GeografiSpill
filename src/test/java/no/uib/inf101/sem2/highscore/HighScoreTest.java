package no.uib.inf101.sem2.highscore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class HighScoreTest {
	
	HighScoreModel highScoreModel = new HighScoreModel();


	@Test
	void sortedHighScores() {
		
		highScoreModel.setCapitalScores(25, 5); //third
		highScoreModel.setCapitalScores(35, 10); //second
		highScoreModel.setCapitalScores(13, 1); //fifth
		highScoreModel.setCapitalScores(13, 0); //last
		highScoreModel.setCapitalScores(25, 1); //fourth
		highScoreModel.setCapitalScores(35, 26); //top
		
		Map<Integer, Integer> capitalScores = highScoreModel.getCapitalScores();
		assertEquals(capitalScores.size(), 3);
		assertEquals(capitalScores.keySet().toArray()[0], 35);
		assertEquals(capitalScores.keySet().toArray()[capitalScores.size()-1], 13);
		
		highScoreModel.setFlagScores(3, 1); //fifth
		highScoreModel.setFlagScores(3, 2); //last
		highScoreModel.setFlagScores(5, 3); //fourth
		highScoreModel.setFlagScores(57, 50); //top
		highScoreModel.setFlagScores(40, 35); //third
		highScoreModel.setFlagScores(57, 12); //second
		
		Map<Integer, Integer> flagScores = highScoreModel.getFlagScores();
		assertEquals(flagScores.size(), 4);
		assertEquals(flagScores.keySet().toArray()[0], 57);
		assertEquals(flagScores.keySet().toArray()[flagScores.size()-1], 3);
	}

}
