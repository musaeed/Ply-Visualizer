package Listener;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;


public class canvasMouse implements MouseWheelListener{

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(e.getWheelRotation() > 0){
			GListener.translationVector.setXYZ(0.0f,0.0f,-0.1f);
			GListener.isTranslation = true;
		}
		else{
			GListener.translationVector.setXYZ(0.0f,0.0f,0.1f);
			GListener.isTranslation = true;
		}
	}
}
