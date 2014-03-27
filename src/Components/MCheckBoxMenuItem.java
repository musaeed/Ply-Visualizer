package Components;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.KeyStroke;

public class MCheckBoxMenuItem extends JCheckBoxMenuItem{

	private static final long serialVersionUID = 1L;
	
	public MCheckBoxMenuItem(String text, char Mnmonic , boolean selection , KeyStroke stroke , String tooltip , ImageIcon icon){
		super(text);
		setMnemonic(Mnmonic);
		setSelected(selection);
		setAccelerator(stroke);
		setToolTipText(tooltip);
		setIcon(icon);
	}

}
