package com.group99.test;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TimeSelectDialogTest extends JDialog {

	Container contentPane = getContentPane();
	static TimeSelectDialogTest dialog;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog = new TimeSelectDialogTest();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TimeSelectDialogTest() {
		setUndecorated(true);
		setBounds(100, 100, 154, 200);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(6, 16, 142, 41);
		contentPane.add(btnNewButton);
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				dialog.dispose();
			}
		});
	}
}
