package no.uib.inf101.sem2.view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import no.uib.inf101.sem2.map.Country;

/**
 * A class to draw the guess capital game.
 * 
 */
public class GeographyView extends JPanel implements KeyListener, ActionListener{

	private ViewableGeographyGame game;
	private boolean mouseIsInTheRectangle = false;
	private boolean mouseIsPressed = false;
	private Rectangle2D correctGuessRectangle;
	private int OUTERMARGIN = 2;
	private JFrame frame;
	private String message = "";
	private Color messageColor;
	private final JButton tilbakeButton;
	private final JPanel button; 

	public GeographyView(ViewableGeographyGame game) {
		this.game = game;
		this.button = new JPanel();
		this.tilbakeButton = addButton(button, "Tilbake");
		this.setPreferredSize(new Dimension(650, 650));
		this.setupMousePositionUpdater();
		this.setupMousePressedUpdater();
		this.frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Geo");
		frame.setContentPane(this);
		frame.pack();
		frame.setVisible(true);
		this.addKeyListener(this);
		this.setFocusable(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if(!game.gameOver()) {			
			g.setColor(Color.BLACK);
			g.setFont(new Font("SansSerif",Font.PLAIN, 20));			
		    Rectangle2D rect = this.questionRectangle();
		    Country randomCountry = game.getRandomCounry();
		    if(randomCountry.getCountry().length()>20) {
		    	g.setFont(new Font("SansSerif", Font.PLAIN, 10));
		    }
		    Inf101Graphics.drawCenteredString(g2, randomCountry.getCountry(), rect);
		    for(int i = 0; i < 4 ; i++) {
		    	Rectangle2D rec = getRectangles().get(i);
		    	Color color = mouseIsInTheRectangle ? (mouseIsPressed ? Color.RED : Color.BLUE) : Color.BLACK;
		    	g2.setColor(color);
		    	String capital = game.getCapitals().get(i);
		    	g2.draw(rec);
			    if(capital.length()>20) {
			    	g.setFont(new Font("SansSerif", Font.PLAIN, 10));
			    }
			    else {
			    	g.setFont(new Font("SansSerif", Font.PLAIN, 20));
				}
			    Inf101Graphics.drawCenteredString(g2, capital, rec);
		    	if(capital.equals(game.getRandomCounry().getCapital())) {
		    		this.correctGuessRectangle = rec;
		    	}
		    }
		    String score = Integer.toString(game.getScore());
			g.setColor(Color.BLACK);
			g.setFont(new Font("SansSerif", Font.BOLD, 20));
			Rectangle2D rectangle = new Rectangle2D.Double(getFrameWidth()/2, getY(), getFrameWidth()/2, getFrameHeight()*0.1);
			Inf101Graphics.drawCenteredString(g,"SCORE: " +  score, rectangle);
			
			g.setColor(messageColor);
			g.setFont(new Font("SansSerif", Font.BOLD, 20));
			Rectangle2D rectangle2 = new Rectangle2D.Double(getX(), getY(), getFrameWidth()/2, getFrameHeight()*0.1);
			Inf101Graphics.drawCenteredString(g, message, rectangle2);
		}
		if(game.gameOver()) {
			gameOverScreen(g2);
		}
		
	}
	/**
	 * Method to display the game over screen.
	 * @param g - the graphics to be drawn
	 */
	private void gameOverScreen(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		String score = Integer.toString(game.getScore());
		String possibleScore = Integer.toString(game.totalScore());
		Rectangle2D rectangle = new Rectangle2D.Double(OUTERMARGIN, OUTERMARGIN, getFrameWidth(), getFrameHeight()/2);
		g2.setFont(new Font("SansSerif", Font.BOLD, 35));
		Inf101Graphics.drawCenteredString(g2, "GAME OVER", rectangle);
		
		g2.setFont(new Font("SansSerif", Font.BOLD, 20));
		Rectangle2D rectangleScore = new Rectangle2D.Double(OUTERMARGIN, OUTERMARGIN, getFrameWidth(), getFrameHeight());
		Inf101Graphics.drawCenteredString(g2, "SCORE: " + score + "/" + possibleScore, rectangleScore);
	
		this.button.setLayout(new BoxLayout(this.button, BoxLayout.Y_AXIS));
		this.button.setBorder(new EmptyBorder(10, 10, 20, 10));
		frame.add(this.button);
		frame.pack();
		frame.setVisible(true);
	}

	//Denne koden er hentet fra eksempel!
	private void setupMousePressedUpdater() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseIsPressed = true;
				repaint();
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseIsPressed = false;
				if(mouseIsInTheRectangle && !game.gameOver()) {
					if(mouseIsInTheRectangle = correctGuessRectangle.contains(e.getPoint())) {
						game.correctGuess(game.getRandomCounry().getCapital());
					    //The code to display correct was found online:
						//https://stackoverflow.com/questions/38950982/how-to-make-my-joptionpane-dialogue-box-disappear-after-10-seconds
						message = "Riktig!";
					    messageColor = Color.GREEN;
					    repaint();
					    Timer timer = new Timer(2000, new ActionListener() {
					        public void actionPerformed(ActionEvent e) {
					            message = "";
					            repaint();
					        }
					    });
					    timer.setRepeats(false);
					    timer.start();
					}
					else {
						game.correctGuess("Wrong country");
					    message = "Feil!";
					    messageColor = Color.RED;
					    repaint();
					    Timer timer = new Timer(2000, new ActionListener() {
					        public void actionPerformed(ActionEvent e) {
					            message = "";
					            repaint();
					        }
					    });
					    timer.setRepeats(false);
					    timer.start();
					}
				}
			}
		});
	}

	private void setupMousePositionUpdater() {
		this.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {				
				if(mouseIsInTheRectangle = getRectangles().get(0).contains(e.getPoint())) {
					updateCursor();
					repaint();
				}
				else if(mouseIsInTheRectangle = getRectangles().get(1).contains(e.getPoint())) {
					updateCursor();
					repaint();
				}
				else if(mouseIsInTheRectangle = getRectangles().get(2).contains(e.getPoint())) {
					updateCursor();
					repaint();
				}
				else if(mouseIsInTheRectangle = getRectangles().get(3).contains(e.getPoint())) {
					updateCursor();
					repaint();
				}
			}
		});
	}
	
	private Rectangle2D questionRectangle() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		return new Rectangle2D.Double(this.getWidth()/2-100/2, 0, 100, 50);
	}
	
	/**
	 * @return - a list of the four rectangles containing the alternatives
	 */
	private List<Rectangle2D> getRectangles() {
		List<Rectangle2D> rectangle = new ArrayList<>();
		int recHeight = 200;
		int recWidth = 200;
		
		rectangle.add(new Rectangle2D.Double(OUTERMARGIN, 50, getFrameWidth()/2, getFrameHeight()/3));
		rectangle.add(new Rectangle2D.Double(getFrameWidth()/2, 50, getFrameWidth()/2, getFrameHeight()/3));
		rectangle.add(new Rectangle2D.Double(OUTERMARGIN, getFrameHeight()/2, getFrameWidth()/2, getFrameHeight()/3));
		rectangle.add(new Rectangle2D.Double(getFrameWidth()/2, getFrameHeight()/2, getFrameWidth()/2, getFrameHeight()/3));
		return rectangle;
	}

	private void updateCursor() {
		if (mouseIsInTheRectangle) {
			setCursor(new Cursor(Cursor.HAND_CURSOR));
		} else {
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}
	
	//Koden under er hentet fra TETRIS (Semesteroppgave 1)
	/**
	 * Method to get the height of the frame to be drawn
	 * @return - the height of the frame
	 */
	private double getFrameHeight() {
		double y = OUTERMARGIN;
		double height = this.getHeight() - 2 * OUTERMARGIN;
		return height;
	}
	
	/**
	 * Method to get the width of the frame to be drawn
	 * @return - the width of the frame
	 */
	private double getFrameWidth() {
		double x = OUTERMARGIN;
		double width = this.getWidth() - 2 * OUTERMARGIN;
		return width;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
        	frame.dispose();
            RegionMenu menu = new RegionMenu();
        }
	}

	@Override
	public void keyReleased(KeyEvent e) {
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
