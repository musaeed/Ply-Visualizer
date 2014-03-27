package Components;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.JColorChooser;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;

import Gui.FileViewer;
import Gui.MainFrame;
import Gui.helpDialog;
import Listener.GListener;

public class FrameMenu extends JMenuBar{

	private static final long serialVersionUID = 1L;
	private MMenu file;
	public static MMenuItem open,exit;
	private MMenu view;
	private MCheckBoxMenuItem viewSidePane,showAxes; 
	public static MMenuItem backgroundColor,fullscreen,objectColor;
	private MMenu help;
	public static MMenuItem helpMenu;
	
	public FrameMenu(){
		super();
		init();
		addItems();
		addActions();
	}

	public void init(){
		file = new MMenu("File",'F');

		open = new MMenuItem("Open", 'O', "Open a ply file",KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK), null);
		exit = new MMenuItem("Exit", 'E', "Exit the application",KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK), null);

		view = new MMenu("View", 'V');

		viewSidePane = new MCheckBoxMenuItem("View side pane", 'V', true, KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), "view the side pane", null);
		showAxes = new MCheckBoxMenuItem("Show Axes", 'S', true, KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0), "show the axes in the simulation", null);
		fullscreen = new MMenuItem("Full screen", 'F', "view the visualization in full screen", KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0), null);
		backgroundColor = new MMenuItem("Background color", 'B', "select the background color", KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK), null);
		objectColor = new MMenuItem("Object color", 'O', "Change the color of the object", KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK), null);
		
		helpMenu = new MMenuItem("Help", 'H', "shortcuts and developer info", KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), null);

		help = new MMenu("Help", 'H');
	}

	public void addItems(){

		file.add(open);
		file.addSeparator();
		file.add(exit);

		view.add(viewSidePane);
		view.add(showAxes);
		view.add(fullscreen);
		view.add(objectColor);
		view.add(backgroundColor);

		help.add(helpMenu);
		
		add(file);
		add(view);
		add(help);
	}

	public void addActions(){
		open.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				FileDialog dialog = new FileDialog(MainFrame.frame);
				dialog.setTitle("Open PLY file");
				
				dialog.setFilenameFilter(new FilenameFilter(){
	                @Override
	                public boolean accept(File dir, String name) {
	                    return name.endsWith(".ply");
	                }
	            });
				
				dialog.setVisible(true);
				try{
					if(dialog.getFiles()[0] != null){
						GListener.currentFile = dialog.getFiles()[0].getAbsolutePath();
						GListener.isNewFile = true;
					}
				}catch(Exception e){
					return;
				}
				
				FileViewer.addToTree(GListener.currentFile);
				MainFrame.canvas.requestFocus();
			}
		});
		

		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		viewSidePane.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(viewSidePane.isSelected()){
					MainFrame.splitPane.setDividerLocation(200);
				}
				else{
					MainFrame.splitPane.setDividerLocation(0);
				}
			}
		});
		
		showAxes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(showAxes.isSelected()){
					GListener.showAxes = true;
				}
				else{
					GListener.showAxes = false;
				}
			}
		});
		
		fullscreen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new fullScreenFrame();
			}
		});
		
		backgroundColor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Color result = JColorChooser.showDialog(MainFrame.frame, "Choose background color", new Color(GListener.cR, GListener.cG, GListener.cB));
				
				if(result == null){
					return;
				}
				
				GListener.cR = (float)result.getRed()/255.0f;
				GListener.cG = (float)result.getGreen()/255.0f;
				GListener.cB = (float)result.getBlue()/255.0f;
			}
		});
		
		objectColor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Color result = JColorChooser.showDialog(MainFrame.frame, "Choose the object color", new Color(GListener.oR, GListener.oG, GListener.oB));
				
				if(result == null){
					return;
				}
				
				GListener.oR = (float)result.getRed() / 255.0f;
				GListener.oG = (float)result.getGreen() / 255.0f;
				GListener.oB = (float)result.getBlue() / 255.0f;
			}
		});
		
		helpMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				helpDialog.getDialog();
			}
		});
	}
}
