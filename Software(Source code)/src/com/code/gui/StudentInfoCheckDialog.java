package com.group99.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
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
 * This is the boundary class of dialog of checking student info.
 * @author group 99
 *
 */
public class StudentInfoCheckDialog extends JDialog{
	
	StudentInfoCheckDialogListener listener;
	/**
	 * This is the callback interface of StudentInfoCheckDialog.
	 * @author group 99
	 *
	 */
	public interface StudentInfoCheckDialogListener{
		public void onCancelClicked();
		public void onOkClicked(String studentName, String studentNum);
	}
	/**
	 * Set StudentInfoCheckDialog's listener.
	 * @param listener A listener of StudentInfoCheckDialog.
	 */
	public void setStudentInfoCheckDialogListener(StudentInfoCheckDialogListener listener){
		this.listener = listener;
	}
	
	
	Container contentPane = getContentPane();
	/**
	 * This is the constructor of StudentInfoCheckDialog.
	 * @param frame The current frame.
	 * @param x The x location of current frame.
	 * @param y The y location of current frame.
	 */
	public StudentInfoCheckDialog(JFrame frame,int x, int y) {
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
		
		JTextField nameTextField = new JTextField(10);
		nameTextField.setBounds(2, 5, 195, 25);
		nameTextField.setText("Your name...");
		contentPane.add(nameTextField);
		
		JTextField numTextField = new JTextField(10);
		numTextField.setBounds(2, 35, 195, 25);
		numTextField.setText("Your studentNum...");
		contentPane.add(numTextField);
		
		JButton studentBtn = new JButton("Ok");
		studentBtn.setUI(buttonUI);
		studentBtn.setForeground(Color.WHITE);
		studentBtn.setBounds(5, 65, 90, 25);
		studentBtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				listener.onOkClicked(nameTextField.getText(), numTextField.getText());
			}
			
		});
		contentPane.add(studentBtn);
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setUI(buttonUI);
		cancelBtn.setForeground(Color.WHITE);
		cancelBtn.setBounds(105, 65, 90, 25);
		cancelBtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				listener.onCancelClicked();
			}
			
		});
		contentPane.add(cancelBtn);
	}
}
