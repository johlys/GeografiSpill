package no.uib.inf101.sem2.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import no.uib.inf101.sem2.games.FlagGame;
import no.uib.inf101.sem2.games.GuessCapital;
import no.uib.inf101.sem2.highscore.HighScoreModel;
import no.uib.inf101.sem2.map.CountryInit;
import no.uib.inf101.sem2.map.WorldRegion;

/**
 * A class to represent the menu where you select the game mode
 */
public class GameMenu implements ActionListener{
	
	private final JButton FlagGameButton;
	private final JButton GuessCapitalButton;
	private final JButton poengTavle;
	private final JButton tilbakeButton;
	private String region;
	private final JFrame frame;
	private HighScoreModel highScoreModel;
	
	/**
	 * @param region - the world region the game is set in
	 */
	public GameMenu(String region, HighScoreModel highScoreModel) {
		this.frame = new JFrame();
		this.region = region;
		this.highScoreModel = highScoreModel;
		
		JPanel buttonsLayout = new JPanel();
		
		JLabel label = new JLabel("VELG SPILLMODUS");
		label.setFont(new Font("SansSerif", Font.BOLD, 20));
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		
	    buttonsLayout.add(label);
		buttonsLayout.setLayout(new BoxLayout(buttonsLayout, BoxLayout.Y_AXIS));
		buttonsLayout.setBorder(new EmptyBorder(10, 10, 20, 10));
		
		
		FlagGameButton = addButton(buttonsLayout, "GJETT FLAGGET");
		GuessCapitalButton = addButton(buttonsLayout, "GJETT HOVEDSTADEN");
		poengTavle = addButton(buttonsLayout, "POENGTAVLE");
		tilbakeButton = addButton(buttonsLayout, "GÅ TILBAKE");
		
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
		if (e.getSource() == GuessCapitalButton) {
			ViewableGeographyGame game = new GuessCapital(new WorldRegion(new CountryInit(), region), highScoreModel);
			GeographyView gameView = new GeographyView(game);
			frame.dispose();
		}
		if (e.getSource() == FlagGameButton) {
			ViewableFlagGame game = new FlagGame(new WorldRegion(new CountryInit(), region), highScoreModel);
			GeographyFlagView gameView = new GeographyFlagView(game);
			frame.dispose();
		}
		if (e.getSource() == poengTavle) {
			GuessCapital game = new GuessCapital(new WorldRegion(new CountryInit(), region), highScoreModel);
			FlagGame game2 = new FlagGame(new WorldRegion(new CountryInit(), region), highScoreModel);
			HighScoreMenu highScores = new HighScoreMenu(highScoreModel);
			frame.dispose();
		}
		if (e.getSource() == tilbakeButton) {
			RegionMenu menu = new RegionMenu();
			frame.dispose();
		}
	}

}
