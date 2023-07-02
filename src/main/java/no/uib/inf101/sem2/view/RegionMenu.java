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
 * Main menu class to show the player what world regions he can play
 */
public class RegionMenu implements ActionListener{
	
	private final JButton NorthAmericaButton;
	private final JButton SouthAmericaButton;
	private final JButton EuropeButton;
	private final JButton AfricaButton;
	private final JButton AsiaButton;
	private final JButton OceaniaButton;
	private final JFrame frame;
	private static HighScoreModel highScoreModel = new HighScoreModel();
	
	public RegionMenu() {
		this.frame = new JFrame();
//		this.highScoreModel = new HighScoreModel();
		JLabel label = new JLabel("VELG VERDENSDEL");
		label.setFont(new Font("SansSerif", Font.BOLD, 20));
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel buttonsLayout = new JPanel();
		buttonsLayout.add(label);
		buttonsLayout.setLayout(new BoxLayout(buttonsLayout, BoxLayout.Y_AXIS));
		buttonsLayout.setBorder(new EmptyBorder(10, 10, 20, 10));
		
		NorthAmericaButton = addButton(buttonsLayout, "Nord Amerika");
		SouthAmericaButton = addButton(buttonsLayout, "Sør Amerika");
		EuropeButton = addButton(buttonsLayout, "Europa");
		AfricaButton = addButton(buttonsLayout, "Afrika");
		AsiaButton = addButton(buttonsLayout, "Asia");
		OceaniaButton = addButton(buttonsLayout, "Oseania");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(buttonsLayout);
		frame.setPreferredSize(new Dimension(400, 600));
		frame.pack();
		frame.setVisible(true);
				
	}
	
	/**
	 * This code is inspired from the assignment "BlobWars" from spring 2022.
	 * @param buttons
	 * @param name
	 * @return - The button
	 */
	JButton addButton(JPanel buttons, String name) {
		JButton button = new JButton();
		button.setText(name);
		button.setFont(new Font("Arial", Font.PLAIN, 40));
		button.addActionListener(this);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttons.add(Box.createRigidArea(new Dimension(20, 20)));
		buttons.add(button);
		return button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == NorthAmericaButton) {
			GameMenu menu = new GameMenu("Nord-Amerika", highScoreModel);
			frame.dispose();
		}
		if(e.getSource() == SouthAmericaButton) {
			GameMenu menu = new GameMenu("Sør-Amerika", highScoreModel);
			frame.dispose();
		}
		if(e.getSource() == EuropeButton) {
			GameMenu menu = new GameMenu("Europa", highScoreModel);
			frame.dispose();
		}
		if(e.getSource() == AfricaButton) {
			GameMenu menu = new GameMenu("Afrika", highScoreModel);
			frame.dispose();
		}
		if(e.getSource() == AsiaButton) {
			GameMenu menu = new GameMenu("Asia", highScoreModel);
			frame.dispose();
		}
		if(e.getSource() == OceaniaButton) {
			GameMenu menu = new GameMenu("Oseania", highScoreModel);
			frame.dispose();
		}
		
	}

}
