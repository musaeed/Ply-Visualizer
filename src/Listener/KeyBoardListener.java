package Listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardListener implements KeyListener{

	@Override
	public void keyPressed(KeyEvent e) {
	
		if(e.getKeyCode() == KeyEvent.VK_UP){
			GListener.theta += 0.1;
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			GListener.theta -= 0.1;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			GListener.phi -= 0.1;
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			GListener.phi += 0.1;
		}
		else if(e.getKeyCode() == KeyEvent.VK_W){
			GListener.translationVector.setXYZ(0.0f,0.1f,0.0f);
			GListener.isTranslation = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_S){
			GListener.translationVector.setXYZ(0.0f,-0.1f,0.0f);
			GListener.isTranslation = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_A){
			GListener.translationVector.setXYZ(0.1f,0.0f,0.0f);
			GListener.isTranslation = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_D){
			GListener.translationVector.setXYZ(-0.1f,0.0f,0.0f);
			GListener.isTranslation = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_K){
			GListener.translationVector.setXYZ(0.0f,0.0f,0.1f);
			GListener.isTranslation = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_L){
			GListener.translationVector.setXYZ(0.0f,0.0f,-0.1f);
			GListener.isTranslation = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

}
