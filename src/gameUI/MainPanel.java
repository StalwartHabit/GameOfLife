/**
 * 
 */
package gameUI;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import logic.GameOfLife;

/** main panel of the Game of Life aggregates all the components to be added to 
 * the main window
 * @author chris
 *
 */
@SuppressWarnings("serial")
public class MainPanel extends JPanel {
	private static final int GAME_HEIGHT = 20;
	private static final int GAME_WIDTH = 20;
	GameOfLife gameOfLife;
	
	public MainPanel() {
		super();
		setLayout(new BorderLayout());
		gameOfLife = new GameOfLife(GAME_WIDTH, GAME_HEIGHT);
		add(new GamePanel(gameOfLife), BorderLayout.CENTER);
		add(new ControlPanel(gameOfLife), BorderLayout.SOUTH);
	}
}
