package logic;

import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GameOfLife {
	private static final int MINIMUM_CROWDING = 2;
	private static final int MAX_CROWDING = 3;
	private static final int NUMBER_OF_PARENTS = 3;
	
	private final int boardRowSize;
	private final int boardColumnSize;
	
	private boolean[][] board;
	private ArrayList<Coordinate> cellsToBeChanged = new ArrayList<Coordinate>();
	private ArrayList<ChangeListener> changeListeners = new ArrayList<ChangeListener>();
	
	public GameOfLife(int boardWidth, int boardLength) {
		this.boardRowSize = boardWidth;
		this.boardColumnSize = boardLength;
		this.board = new boolean[this.boardRowSize][this.boardColumnSize];
		for (int i = 0; i < this.boardColumnSize; i++) {
			for (int j = 0; j < this.boardRowSize; j++) {
				board[j][i] = false;
			}
		}
	}
	public int getBoardWidth() {
		return boardRowSize;
	}
	public int getBoardLength() {
		return boardColumnSize;
	}
	public boolean getIndexAt(int x, int y) {
		return board[x][y];
	}
	public void changeCellState(Coordinate cell) {
		int x = cell.getX();
		int y = cell.getY();
		board[x][y] = !board[x][y];
		ChangeEvent changeEvent = new ChangeEvent(cell);
		for (ChangeListener listener : changeListeners) {
			listener.stateChanged(changeEvent);
		}
	}
	private int livingNeighbourCount(Coordinate cell) {
		int x = cell.getX();
		int y = cell.getY();
		int count = 0;
		for (int j = (-1); j <= 1; j++) {
			for (int i = (-1); i <=  1; i++) {
				int xIndex = x + i;
				int yIndex = y + j;
				boolean xValueInBounds = xIndex >= 0 && xIndex < boardRowSize;
				boolean yValueInBounds = yIndex >= 0 && yIndex < boardColumnSize;
				boolean isSelf = i == 0 && j == 0;
				
				if (xValueInBounds && yValueInBounds && !isSelf) {
					boolean isCellAlive = board[xIndex][yIndex];
					if (isCellAlive) {
						count++;
					}
				}
			}
		}
		return count;
	}
	public void nextIteration() {
		checkForLife();
		updateBoard();
	}
	private void checkForLife() {
		for (int i = 0; i < this.boardColumnSize; i++) {
			for (int j = 0; j < this.boardRowSize; j++) {
				boolean isAlive = board[j][i];
				Coordinate cell = new Coordinate(j,i);
				int liveNeighbours = livingNeighbourCount(cell);
				boolean isBadLivingConditions = liveNeighbours < MINIMUM_CROWDING ||
						liveNeighbours > MAX_CROWDING;
				if (isAlive && isBadLivingConditions) {
					cellsToBeChanged.add(cell);
				}
				else if (!isAlive && liveNeighbours == NUMBER_OF_PARENTS) {
					cellsToBeChanged.add(cell);
				}
			}
		}
	}
	private void updateBoard() {
		for (Coordinate cell : cellsToBeChanged) {
			changeCellState(cell);
		}
		cellsToBeChanged = new ArrayList<Coordinate>();
	}
	
	public void addChangeListener(ChangeListener listener) {
		changeListeners.add(listener);
	}
	public void printBoard() {
		for (int i = 0; i < boardRowSize; i++) {
			for (int j = 0; j < boardColumnSize; j++) {
				if (board[j][i]) {
					System.out.print("O");
				} else {
					System.out.print("X");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}
