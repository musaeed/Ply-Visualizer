package Components;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

import Listener.GListener;
import Listener.KeyBoardListener;
import Listener.canvasMouse;

import com.jogamp.opengl.util.FPSAnimator;

public class fullScreenFrame extends JFrame{
	
	private static final long serialVersionUID = -2726871608728210802L;

	public fullScreenFrame(){
		super();
		init();
	}
	
	public void init(){
		GLProfile glf = GLProfile.getDefault();
		GLCapabilities caps = new GLCapabilities(glf);
		GLCanvas canvas = new GLCanvas(caps);
		canvas.addGLEventListener(new GListener());
		FPSAnimator animator = new FPSAnimator(canvas, 60);
		canvas.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
					dispose();
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		
		canvas.addKeyListener(new KeyBoardListener());
		canvas.addMouseWheelListener(new canvasMouse());
		
		add(canvas);
		setUndecorated(true);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		try {
			Runtime.getRuntime().exec(new String[] {"notify-send","PLY Visualizer","Press Esc to go back"});
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		setVisible(true);
		
		animator.start();
		canvas.requestFocus();
	}
	

}
