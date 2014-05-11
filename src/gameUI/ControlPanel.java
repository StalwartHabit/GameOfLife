package gameUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import logic.GameOfLife;

@SuppressWarnings("serial")
public class ControlPanel extends JPanel {
	private static final String STEP_TEXT = "Step";

	private static final String PAUSE_TEXT = "Pause";

	private static final int DELAY = 500;

	private static final String RUN_TEXT = "Run";
	
	private GameOfLife gameOfLife;
	
	private Timer timer;

	public ControlPanel(GameOfLife gameOfLife) {
		super();
		this.gameOfLife = gameOfLife;
		timer = createTimer();
		
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		add(Box.createHorizontalGlue());
		add(createStepButton());
		add(startStopButton());
		
	}
	
	private JButton startStopButton() {
		final JButton startStopButton = new JButton(RUN_TEXT);
		startStopButton.addActionListener(new ActionListener() {
			private boolean isRunning = false;
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isRunning == true) {
					isRunning = false;
					startStopButton.setText(RUN_TEXT);
					timer.stop();
				} else {
					isRunning = true;
					startStopButton.setText(PAUSE_TEXT);
					timer.start();
				}
			}
		});
		return startStopButton;
	} 
	private JButton createStepButton() {
		JButton stepButton = new JButton(STEP_TEXT);
		stepButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
					gameOfLife.nextIteration();
			}
		});
		return stepButton;
	}
	
	private Timer createTimer() {
		ActionListener updateText = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gameOfLife.nextIteration();
			}
		};
		return new Timer(DELAY, updateText);
	}
}
