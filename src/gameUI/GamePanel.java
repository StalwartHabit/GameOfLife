package gameUI;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import logic.Coordinate;
import logic.GameOfLife;
/**
 * 
 * @author chris
 *
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	
	private static final int ICON_LENGTH = 20;
	private static final int ICON_WIDTH = 20;
	private final JLabel[][] gridContents;
	private GameOfLife gameOfLife;
	
	public GamePanel(GameOfLife gameOfLife) {
		super();
		
		this.gameOfLife = gameOfLife;
		int gameWidth = gameOfLife.getBoardWidth();
		int gameLength = gameOfLife.getBoardLength();
		setLayout(new GridLayout(gameWidth, gameLength));
		gridContents = new JLabel[gameWidth][gameLength];
		
		gameOfLife.addChangeListener(createChangeListener());
		
		for (int row = 0; row < gameLength; row++) {
			for (int col = 0; col < gameWidth; col++) {
				JLabel cellLabel = createCellLabel(row, col);
				gridContents[col][row] = cellLabel;
				add(gridContents[col][row]);
			}
		}
	}

	public JLabel createCellLabel(final int row, final int col) {
		final JLabel cellLabel = new JLabel(new DeadCellIcon(ICON_WIDTH,ICON_LENGTH));
		cellLabel.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				gameOfLife.changeCellState(new Coordinate(col, row));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// left blank
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// left blank
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// left blank
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// left blank
			}
		});
		return cellLabel;
	}
	private ChangeListener createChangeListener() {
		return new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				Object source = e.getSource();
				if (source instanceof Coordinate) {
					Coordinate cell = (Coordinate) source;
					int x = cell.getX();
					int y = cell.getY();
					if (gameOfLife.getIndexAt(x, y)) {
						gridContents[x][y].setIcon(new LiveCellIcon(ICON_WIDTH,ICON_LENGTH));
					} else {
						gridContents[x][y].setIcon(new DeadCellIcon(ICON_WIDTH,ICON_LENGTH));
					}
				}
			}
		};
	}
}
