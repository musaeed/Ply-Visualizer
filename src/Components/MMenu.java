package Components;

import javax.swing.JMenu;

public class MMenu extends JMenu{

	private static final long serialVersionUID = 1L;
	
	public MMenu(String text , char Mnmeonic){
		super(text);
		setMnemonic(Mnmeonic);
	}

}
