import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Gui.MainFrame;

public class MainClass{

	public static void main(String args[]){
		try {
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		if(args.length == 0){
			new MainFrame(null);
		}
		else{
			new MainFrame(args[0]);
		}

	}
}