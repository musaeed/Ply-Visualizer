package Gui;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import Components.BottomPanel;
import Components.FlowCustomLayout;
import Components.MButton;

public class helpDialog {

	public static JDialog dialog;

	public static void getDialog(){
		dialog = new JDialog();
		dialog.setLayout(new BorderLayout());
		dialog.add(getTabs() , BorderLayout.CENTER);
		dialog.add(getButtonPanel() , BorderLayout.SOUTH);
		dialog.setSize(new Dimension(500,400));
		dialog.setModal(true);
		dialog.setTitle("Help");
		dialog.setLocationRelativeTo(MainFrame.frame);
		dialog.setVisible(true);
	}

	private static JPanel getButtonPanel(){
		final JPanel bPanel = new JPanel();
		MButton close = new MButton("Close", "close the dialog", 'C', KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
		MButton sendEmail = new MButton("Send email", "mailto:muhammad.omar555@gmail.com", 'S', null);
		bPanel.setLayout(new FlowCustomLayout(FlowLayout.RIGHT));
		bPanel.add(sendEmail);
		bPanel.add(close);

		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dialog.dispose();
				MainFrame.canvas.requestFocus();
			}
		});

		sendEmail.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Desktop.getDesktop().browse(new URI("mailto:muhammad.omar555@gmail.com"));
					dialog.dispose();
					MainFrame.canvas.requestFocus();
				} catch (IOException | URISyntaxException e) {
					e.printStackTrace();
				}
			}
		});

		return bPanel;
	}

	private static JTabbedPane getTabs(){
		JTabbedPane tabs = new JTabbedPane();
		tabs.setTabPlacement(JTabbedPane.LEFT);
		tabs.addTab("Shortcuts", getShortcutsPanel());
		tabs.addTab("About", getOwnerPanel());
		return tabs;
	}

	private static JPanel getShortcutsPanel(){
		JPanel shortcuts = new JPanel();

		String[] columnNames = {"Keys" , "Function"};
		Object [][] data = {{"Ctrl + O" , "Open Ply file"},
				{"Alt + F4" , "Exit application"},
				{"W","move scene upwards"},
				{"S","move scene downwards"},
				{"A","move scene to the left"},
				{"D","move scene to the right"},
				{"L","move scene forward"},
				{"K","move scene backward"},
				{"Up key","move camera upwards"},
				{"Down key" , "move camera downwards"},
				{"Right key","move camera to the right"},
				{"Left key", "move camera to the keft"}};

		JTable table = new JTable(data, columnNames);
		JScrollPane pTable = new JScrollPane(table);

		shortcuts.setLayout(new BorderLayout());
		shortcuts.add(pTable , BorderLayout.CENTER);
		return shortcuts;
	}

	private static JPanel getOwnerPanel(){
		JPanel owner = new JPanel();

		JEditorPane pane = new JEditorPane();
		pane.setOpaque(false);
		pane.setEditable(false);

		InputStream input = BottomPanel.class.getResourceAsStream("/other/developer.html");
		Scanner in = new Scanner(input);
		String temp = "";
		while(in.hasNext()){
			temp += in.nextLine();
		}
		in.close();
		pane.setContentType("text/html");
		pane.setText(temp);

		pane.addHyperlinkListener(new HyperlinkListener() {

			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					try {
						Desktop.getDesktop().browse(e.getURL().toURI());
					} catch (IOException | URISyntaxException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		owner.setLayout(new BorderLayout());
		owner.add(pane);
		return owner;
	}
}
