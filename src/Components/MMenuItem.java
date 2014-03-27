package Components;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MMenuItem extends JMenuItem{

	private static final long serialVersionUID = 1L;

	public MMenuItem(String text , char Mnmonic , String tooltip,KeyStroke stroke , ImageIcon icon){
		super(text);
		setMnemonic(Mnmonic);
		setToolTipText(tooltip);
		setIcon(icon);
		setAccelerator(stroke);
	}
}
