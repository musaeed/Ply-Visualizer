package Gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Progressbar {

	private static JProgressBar progressbar;

	public static JPanel getProgressPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(progressbar = new JProgressBar());

		return panel;
	}

	public static void setValue(final int value){

		progressbar.getModel().setValue(value);
		progressbar.update(progressbar.getGraphics());
		
	}



}
