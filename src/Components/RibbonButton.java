package Components;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class RibbonButton extends JButton{
	
	private static final long serialVersionUID = 1L;

	public RibbonButton(String s , String t){
		
		super(s);

		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				setContentAreaFilled(true);
				setOpaque(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setContentAreaFilled(false);
				setOpaque(false);
			}


			
		});

		setContentAreaFilled(false);
		setOpaque(false);
		
		setToolTipText(t);
	}
}
