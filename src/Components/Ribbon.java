package Components;


import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Listener.GListener;

public class Ribbon extends JPanel{

	private static final long serialVersionUID = 1L;
	public static RibbonButton open , close , fullscreen , help;
	
	public Ribbon(){
		init();
		addIcons();
		addActions();
	}
	
	public void init(){
		open = new RibbonButton("Open", "open a ply file for visualization");
		close = new RibbonButton("Close", "Close the current file");
		fullscreen = new RibbonButton("Fullscreen", "view the visualization in full screen");
		help = new RibbonButton("Help", "view help");
		
		setLayout(new FlowCustomLayout(FlowLayout.LEFT));
		
		add(open);
		add(new MSeparator());
		add(fullscreen);
		add(new MSeparator());
		add(close);
		add(new MSeparator());
		add(help);
	}
	
	public void addIcons(){
		open.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Ribbon.class.getClassLoader().getResource("images/document-open-2.png"))));
		close.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Ribbon.class.getClassLoader().getResource("images/edit-delete-6.png"))));
		fullscreen.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Ribbon.class.getClassLoader().getResource("images/run-rebuild.png"))));
		help.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Ribbon.class.getClassLoader().getResource("images/document-properties.png"))));
	}
	
	public void addActions(){
		open.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FrameMenu.open.doClick();
			}
		});
		
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GListener.normals.clear();
				GListener.points.clear();
				GListener.triangles.clear();
			}
		});
		
		fullscreen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameMenu.fullscreen.doClick();
			}
		});
		
		help.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FrameMenu.helpMenu.doClick();
			}
		});
	}
}

