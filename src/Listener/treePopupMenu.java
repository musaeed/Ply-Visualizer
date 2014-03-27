package Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;

import Components.MMenuItem;
import Components.Ribbon;
import Gui.MainFrame;

public class treePopupMenu extends JPopupMenu{

	private static final long serialVersionUID = 1L;
	private MMenuItem open,close;
	
	public treePopupMenu(){
		init();
		addActions();
	}
	
	public void init(){
		open = new MMenuItem("Open", 'O', "open this file", null, null);
		close = new MMenuItem("Close", 'C', "Close current file", null, null);
		
		add(open);
		addSeparator();
		add(close);
	}
	
	public void addActions(){
		open.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)
						MainFrame.fileViewer.getLastSelectedPathComponent();

				if(node == null){
					return;
				}
				
				close.doClick();
				
				GListener.currentFile = node.toString();
				GListener.isNewFile = true;
			}
		});
		
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Ribbon.close.doClick();
			}
		});
	}

}
