package Components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;

import Gui.MainFrame;
import Gui.Progressbar;
import Listener.GListener;

public class BottomPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel ,examplePanel, directionPanel , controlPanel;
	private JPanel upPanel,lrPanel , downPanel , movementPanel , sPanel , suPanel , slrPanel , sdPanel;
	private MButton up,left,right,down,forward,backward;
	private JRadioButton stanfordBunny , dragon;
	private MButton load;
	private MButton sUp,sDown,sLeft,sRight;
	private MButton minimize,maximize,exit;
	
	public static InputStream fileStream;
	
	public BottomPanel(){
		init();
		addActions();
		addIcons();
	}
	
	public void init(){
		mainPanel = new JPanel();
		examplePanel = new JPanel();
		directionPanel = new JPanel();
		controlPanel = new JPanel();
		
		upPanel = new JPanel();
		lrPanel = new JPanel();
		downPanel = new JPanel();
		
		movementPanel = new JPanel();
		sPanel = new JPanel();
		
		upPanel.setLayout(new FlowLayout());
		lrPanel.setLayout(new FlowLayout());
		downPanel.setLayout(new FlowLayout());
		
		upPanel.add(up = new MButton("", "move the scene upwards", '8', null));
		lrPanel.add(left = new MButton("", "move the scene left wards", '4', null));
		lrPanel.add(forward = new MButton("", "move the scene forward", '1', null));
		lrPanel.add(backward = new MButton("", "move the scene backward", '3', null));
		lrPanel.add(right = new MButton("", "move the scene right wards", '6',null));
		downPanel.add(down = new MButton("", "move the scene downwards", '2', null));
		
		suPanel = new JPanel();
		slrPanel = new JPanel();
		sdPanel = new JPanel();
		
		suPanel.add(sUp = new MButton("", "move the camera upward", '0', null));
		slrPanel.add(sRight = new MButton("", "move the camera to the right", '0', null));
		slrPanel.add(sLeft = new MButton("", "move the camera to the left", '0', null));
		sdPanel.add(sDown = new MButton("", "move the camera downwards", '0', null));
		
		
		
		directionPanel.setLayout(new GridLayout(1,2));
		
		movementPanel.setLayout(new BorderLayout());
		movementPanel.add(upPanel , BorderLayout.NORTH);
		movementPanel.add(lrPanel , BorderLayout.CENTER);
		movementPanel.add(downPanel , BorderLayout.SOUTH);
		
		sPanel.setLayout(new BorderLayout());
		sPanel.add(suPanel , BorderLayout.NORTH);
		sPanel.add(slrPanel , BorderLayout.CENTER);
		sPanel.add(sdPanel, BorderLayout.SOUTH);
		
		directionPanel.add(movementPanel);
		directionPanel.add(sPanel);
		directionPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.GRAY, Color.DARK_GRAY.brighter()), "Controls"));
		
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
		controlPanel.add(maximize = new MButton("Maximize", "maximize the window", 'M', null));
		controlPanel.add(minimize = new MButton("Minimize", "minimize the window", 'Z', null));
		controlPanel.add(exit = new MButton("Exit", "exit the application", 'E', null));
		controlPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.GRAY, Color.DARK_GRAY.brighter()), "Window options"));
		
		examplePanel.setLayout(new BoxLayout(examplePanel, BoxLayout.Y_AXIS));
		examplePanel.add(stanfordBunny = new JRadioButton("Stanford bunny"));
		examplePanel.add(dragon = new JRadioButton("Dragon"));
		examplePanel.add(load = new MButton("Load", "load the selected example", 'L', null));
		examplePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.GRAY, Color.DARK_GRAY.brighter()), "Examples"));
		
		
		
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(examplePanel , BorderLayout.WEST);
		mainPanel.add(directionPanel , BorderLayout.CENTER);
		mainPanel.add(controlPanel , BorderLayout.EAST);
		mainPanel.add(Progressbar.getProgressPanel() , BorderLayout.SOUTH);
		
		setLayout(new BorderLayout());
		add(mainPanel , BorderLayout.CENTER);
	}
	
	public void addActions(){
		up.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GListener.translationVector.setXYZ(0.0f,0.1f,0.0f);
				GListener.isTranslation = true;
				MainFrame.canvas.requestFocus();
			}
		});
		
		left.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GListener.translationVector.setXYZ(-0.1f,0.0f,0.0f);
				GListener.isTranslation = true;
				MainFrame.canvas.requestFocus();
			}
		});
		
		right.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GListener.translationVector.setXYZ(0.1f,0.0f,0.0f);
				GListener.isTranslation = true;
				MainFrame.canvas.requestFocus();
			}
		});
		
		down.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GListener.translationVector.setXYZ(0.0f,-0.1f,0.0f);
				GListener.isTranslation = true;
				MainFrame.canvas.requestFocus();
			}
		});
		
		forward.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GListener.translationVector.setXYZ(0.0f,0.0f,-0.1f);
				GListener.isTranslation = true;
				MainFrame.canvas.requestFocus();
			}
		});
		
		backward.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GListener.translationVector.setXYZ(0.0f,0.0f,0.1f);
				GListener.isTranslation = true;
				MainFrame.canvas.requestFocus();
			}
		});
		
		sUp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GListener.theta += 0.1;
				MainFrame.canvas.requestFocus();
			}
		});
		
		sLeft.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GListener.phi -= 0.1;
				MainFrame.canvas.requestFocus();
			}
		});
		
		sRight.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GListener.phi += 0.1;
				MainFrame.canvas.requestFocus();
			}
		});
		
		sDown.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GListener.theta -= 0.1;
				MainFrame.canvas.requestFocus();
			}
		});
		
		stanfordBunny.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(stanfordBunny.isSelected()){
					dragon.setSelected(false);
				}
			}
		});
		
		dragon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(dragon.isSelected()){
					stanfordBunny.setSelected(false);
				}
			}
		});
		
		load.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!stanfordBunny.isSelected() && !dragon.isSelected()){
					JOptionPane.showMessageDialog(MainFrame.frame, "Please make a selection.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(stanfordBunny.isSelected()){
					fileStream = BottomPanel.class.getResourceAsStream("/plyfiles/bun_zipper.ply");
					GListener.isNewStream = true;
				}
				
				if(dragon.isSelected()){
					fileStream = BottomPanel.class.getResourceAsStream("/plyfiles/dragon_vrip_res4.ply");
					GListener.isNewStream = true;
				}
			}
		});
		
		maximize.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			}
		});
		
		minimize.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.frame.setExtendedState(JFrame.ICONIFIED);
			}
		});
		
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
	}

	public void addIcons(){
		up.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Ribbon.class.getClassLoader().getResource("images/up.png"))));
		down.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Ribbon.class.getClassLoader().getResource("images/down.png"))));
		left.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Ribbon.class.getClassLoader().getResource("images/left.png"))));
		right.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Ribbon.class.getClassLoader().getResource("images/right.png"))));
		backward.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Ribbon.class.getClassLoader().getResource("images/navigate_right.png"))));
		forward.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Ribbon.class.getClassLoader().getResource("images/navigate_left.png"))));
		
		sUp.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Ribbon.class.getClassLoader().getResource("images/go-up.png"))));
		sDown.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Ribbon.class.getClassLoader().getResource("images/go-down.png"))));
		sLeft.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Ribbon.class.getClassLoader().getResource("images/go-next.png"))));
		sRight.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Ribbon.class.getClassLoader().getResource("images/go-previous.png"))));
	}
}

