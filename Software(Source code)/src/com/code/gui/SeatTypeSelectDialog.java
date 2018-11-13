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
import javax.swing.plaf.basic.BasicButtonUI;
/**
 * This is the boundary class of panel of selecting ticket type.
 * @author group 99
 *
 */
public class SeatTypeSelectDialog extends JDialog{
	
	SeatSelectDialogListener listener;
	/**
	 * This is the callback interface of SeatTypeSelectDialog.
	 * @author group 99
	 *
	 */
	public interface SeatSelectDialogListener{
		public void onCancelClicked();
		public void onSeatSelectClicked(String ticketType);
	}
	/**
	 * Set SeatTypeSelectDialog's listener.
	 * @param listener A listener of SeatTypeSelectDialog.
	 */
	public void setSeatSelectDialogListener(SeatSelectDialogListener listener){
		this.listener = listener;
	}
	
	
	Container contentPane = getContentPane();
	/**
	 * This is the constructor of ScreenSelectPanel.
	 * @param frame The current frame.
	 * @param seatX The x location of seat.
	 * @param seatY The y location of seat.
	 */
	public SeatTypeSelectDialog(JFrame frame,int seatX, int seatY) {
		setUndecorated(true);
		setBounds(frame.getX() + seatX - 20, frame.getY() + seatY - 115, 110, 160);
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
		
		JButton childBtn = new JButton("Child");
		childBtn.setUI(buttonUI);
		childBtn.setForeground(Color.WHITE);
		childBtn.setBounds(5, 5, 100, 30);
		childBtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				listener.onSeatSelectClicked("child");
			}
			
		});
		contentPane.add(childBtn);
		
		JButton adultBtn = new JButton("Adult");
		adultBtn.setUI(buttonUI);
		adultBtn.setForeground(Color.WHITE);
		adultBtn.setBounds(5, 40, 100, 30);
		adultBtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				listener.onSeatSelectClicked("adult");
			}
			
		});
		contentPane.add(adultBtn);
		
		JButton seniorBtn = new JButton("Senior");
		seniorBtn.setUI(buttonUI);
		seniorBtn.setForeground(Color.WHITE);
		seniorBtn.setBounds(5, 75, 100, 30);
		seniorBtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				listener.onSeatSelectClicked("senior");
			}
			
		});
		contentPane.add(seniorBtn);
		
		JButton studentBtn = new JButton("Student");
		studentBtn.setUI(buttonUI);
		studentBtn.setForeground(Color.WHITE);
		studentBtn.setBounds(5, 110, 100, 30);
		studentBtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				listener.onSeatSelectClicked("student");
			}
			
		});
		contentPane.add(studentBtn);
		
		contentPane.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				listener.onCancelClicked();
			}

		});
		JLabel cancelLabel = new JLabel("cancel",JLabel.CENTER);
		cancelLabel.setFont(new Font("Arial", Font.BOLD, 12));
		cancelLabel.setBounds(5, 135, 100, 30);
		contentPane.add(cancelLabel);
	}
}
