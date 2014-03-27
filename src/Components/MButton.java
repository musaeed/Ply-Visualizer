package Components;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class MButton extends JButton{

	private static final long serialVersionUID = 1L;

	public MButton(String text , String tooltip , char Mnmonic , KeyStroke stroke){
	
		super(text);
		setToolTipText(tooltip);
		setMnemonic(Mnmonic);
		
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(stroke, 0);
		getActionMap().put(0, new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				doClick();
			}
		});
	}
	
}
