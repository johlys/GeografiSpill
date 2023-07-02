package no.uib.inf101.sem2.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import no.uib.inf101.sem2.games.FlagGame;
import no.uib.inf101.sem2.highscore.HighScoreModel;
import no.uib.inf101.sem2.map.CountryInit;
import no.uib.inf101.sem2.map.WorldRegion;

/**
 * A class to display highscores from the different games
 *
 */
public class HighScoreMenu implements ActionListener{

	private final JFrame frame;
	private HighScoreModel highScoreModel;
	private final JButton tilbakeButton;
	
	public HighScoreMenu(HighScoreModel highScoreModel) {
		this.frame = new JFrame();
		
		this.highScoreModel = highScoreModel;
		JPanel buttonsLayout = new JPanel();
		
		JLabel label = new JLabel("Flagg-poeng");
		label.setFont(new Font("SansSerif", Font.BOLD, 20));
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		
	    buttonsLayout.add(label);
	    
	    for (Map.Entry<Integer, Integer> game : highScoreModel.getFlagScores().entrySet()) {
	        String totalScore = Integer.toString(game.getKey());
	        String score = Integer.toString(game.getValue());
			JLabel labelScore = new JLabel(score + "/" + totalScore);
			labelScore.setFont(new Font("SansSerif", Font.PLAIN, 15));
			labelScore.setAlignmentX(Component.CENTER_ALIGNMENT);
			buttonsLayout.add(labelScore);
	    }
	    
		JLabel label2 = new JLabel("Hovedsteder-poeng");
		label2.setFont(new Font("SansSerif", Font.BOLD, 20));
		label2.setAlignmentX(Component.CENTER_ALIGNMENT);
		
	    buttonsLayout.add(label2);
	    
	    for (Map.Entry<Integer, Integer> game : highScoreModel.getCapitalScores().entrySet()) {
	        String totalScore2 = Integer.toString(game.getKey());
	        String score2 = Integer.toString(game.getValue());
			JLabel labelScore2 = new JLabel(score2 + "/" + totalScore2);
			labelScore2.setFont(new Font("SansSerif", Font.PLAIN, 15));
			labelScore2.setAlignmentX(Component.CENTER_ALIGNMENT);
			buttonsLayout.add(labelScore2);
	    }
	    
	    this.tilbakeButton = addButton(buttonsLayout, "Tilbake");
	    
		buttonsLayout.setLayout(new BoxLayout(buttonsLayout, BoxLayout.Y_AXIS));
		buttonsLayout.setBorder(new EmptyBorder(10, 10, 20, 10));
		
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(buttonsLayout);
		frame.setPreferredSize(new Dimension(400, 400));
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * This code is inspired from the assignment "BlobWars" from spring 2022.
	 * @param buttons
	 * @param name
	 * @return - the button
	 */
	JButton addButton(JPanel buttons, String name) {
		JButton button = new JButton();
		button.setText(name);
		button.setFont(new Font("Arial", Font.PLAIN, 30));
		button.addActionListener(this);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttons.add(Box.createRigidArea(new Dimension(20, 20)));
		buttons.add(button);
		return button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == tilbakeButton) {
			frame.dispose();
			RegionMenu menu = new RegionMenu();
		}
		
	}
}
