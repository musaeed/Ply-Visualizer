package Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLJPanel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import Components.BottomPanel;
import Components.FrameMenu;
import Components.Ribbon;
import Listener.GListener;
import Listener.KeyBoardListener;
import Listener.canvasMouse;
import Listener.treePopupMenu;

import com.jogamp.opengl.util.FPSAnimator;


public class MainFrame {

	public static JFrame frame;
	public static JSplitPane splitPane;
	public static JTree fileViewer;
	public static JScrollPane scrollPane;
	public static DefaultTreeModel treeModel;
	public static DefaultMutableTreeNode root;
	public static GLJPanel canvas;


	public MainFrame(String filename){
		initGUI(filename);
	}

	public void initGUI(final String filename){
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setSize(new Dimension(850,600));
		frame.setTitle("PLY Visualizer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GLProfile glf = GLProfile.getDefault();
		GLCapabilities caps = new GLCapabilities(glf);
		caps.setDepthBits(16);
		canvas = new GLJPanel(caps);
		final FPSAnimator animator = new FPSAnimator(canvas , 60);
		canvas.addGLEventListener(new GListener());
		canvas.addKeyListener(new KeyBoardListener());
		canvas.addMouseWheelListener(new canvasMouse());

		splitPane = new JSplitPane();
		scrollPane = new JScrollPane(fileViewer = new JTree() , JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		splitPane.setLeftComponent(scrollPane);
		splitPane.setRightComponent(canvas);
		splitPane.setDividerLocation(200);

		root = new DefaultMutableTreeNode("Recent files:");
		treeModel = new DefaultTreeModel(root);
		fileViewer.setModel(treeModel);
		fileViewer.setComponentPopupMenu(new treePopupMenu());

		frame.add(splitPane , BorderLayout.CENTER);
		frame.add(new BottomPanel() , BorderLayout.SOUTH);
		frame.add(new Ribbon() , BorderLayout.NORTH);
		frame.setJMenuBar(new FrameMenu());
		frame.setVisible(true);

		animator.start();
		canvas.requestFocus();

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				if(filename != null){
					GListener.currentFile = filename;
					GListener.isNewFile = true;
					FileViewer.addToTree(filename);
				}
			}
		});

	}

}
