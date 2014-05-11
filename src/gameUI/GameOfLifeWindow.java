package gameUI;

import javax.swing.JFrame;
/**
 * 
 * @author chris
 *
 */
public class GameOfLifeWindow {
	private static final String GAME_OF_LIFE_TITLE = "Game Of Life";

	public static void main(String[] args) {
		JFrame mainWindow = new JFrame(GAME_OF_LIFE_TITLE);
		mainWindow.add(new MainPanel());
		
		mainWindow.pack();
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
	}
}
