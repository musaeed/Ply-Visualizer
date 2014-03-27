package Gui;

import javax.swing.tree.DefaultMutableTreeNode;

public class FileViewer {


	public static void addToTree(String filename){
		if(!isPresent(filename)){
			MainFrame.root.add(new DefaultMutableTreeNode(filename));
			MainFrame.treeModel.reload();
			MainFrame.fileViewer.setSelectionRow(MainFrame.treeModel.getChildCount(MainFrame.treeModel.getRoot()));
		}
	}


	public static void removeFromTree(String filename){
		DefaultMutableTreeNode model = MainFrame.root;

		for(int i = 0 ; i < model.getChildCount() ; i++){

			if(model.getChildAt(i).toString().equals(filename)){
				model.remove(i);
			}
		}

		MainFrame.treeModel.reload();
	}

	public static boolean isPresent(String filepath){
		DefaultMutableTreeNode model = MainFrame.root;

		for(int i = 0 ; i < model.getChildCount() ; i++){

			if(model.getChildAt(i).toString().equals(filepath)){
				return true;
			}
		}
		return false;
	}
}
