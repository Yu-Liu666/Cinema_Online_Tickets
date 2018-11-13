package com.group99.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicButtonUI;
/**
 * This is the boundary class of dialog of error hint of student info.
 * @author group 99
 *
 */
public class StudentInfoCheckHintDialog extends JDialog{
	
	StudentInfoCheckHintDialogListener listener;
	/**
	 * This is the callback interface of StudentInfoCheckHintDialog.
	 * @author group 99
	 *
	 */
	public interface StudentInfoCheckHintDialogListener{
		public void onCancelClicked();
	}
	/**
	 * Set StudentInfoCheckHintDialog's listener.
	 * @param listener A listener of StudentInfoCheckHintDialog.
	 */
	public void setStudentInfoCheckHintDialogListener(StudentInfoCheckHintDialogListener listener){
		this.listener = listener;
	}
	
	
	Container contentPane = getContentPane();
	/**
	 * Initialize the data of StudentInfoCheckHintDialog.
	 * @param frame The current frame.
	 * @param x The x location of current frame.
	 * @param y The y location of current frame.
	 */
	public StudentInfoCheckHintDialog(JFrame frame,int x, int y) {
		setUndecorated(true);
		setBounds(frame.getX() + x - 65, frame.getY() + y - 115, 200, 100);
		contentPane.setLayout(null);
		BasicButtonUI buttonUI = new BasicButtonUI(){
			@Override
			public void paint(Graphics g, JComponent c) {
				g.setColor(new Color(255, 130, 71));
				g.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 10, 10);
				super.paint(g, c);
			}

			@Override
			protected void paintButtonPressed(Graphics g, AbstractButton b) {
				g.setColor(new Color(192, 192, 192));
				g.fillRoundRect(0, 0, b.getWidth(), b.getHeight(), 10, 10);
			}
		};
		
		contentPane.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				listener.onCancelClicked();
			}
			
		});
		JLabel cancelLabel = new JLabel("Your info is error!",JLabel.CENTER);
		cancelLabel.setFont(new Font("Arial", Font.BOLD, 15));
		cancelLabel.setBounds(30, 30, 142, 40);
		contentPane.add(cancelLabel);
	}
}
