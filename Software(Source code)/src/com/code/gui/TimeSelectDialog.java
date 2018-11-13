package com.group99.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * This is the boundary class of dialog of selecting time.
 * @author group 99
 *
 */
public class TimeSelectDialog extends JDialog{
	
	TimeSelectDialogListener listener;
	Container contentPane = getContentPane();
	/**
	 * This is the callback interface of TimeSelectDialog.
	 * @author group 99
	 *
	 */
	public interface TimeSelectDialogListener{
		public void onCancelClicked();
		public void onTimeSelectClicked(String screenName, String timeStr);
	}
	/**
	 * Set TimeSelectDialog's listener.
	 * @param listener A listener of TimeSelectDialog.
	 */
	public void setTimeSelectDialogListener(TimeSelectDialogListener listener){
		this.listener = listener;
	}
	/**
	 * This is the constructor of TimeSelectDialog.
	 * @param frame The current frame.
	 * @param timeOfScreen The selecting time.
	 * @param screenX The current location x.
	 * @param filmName The current film name.
	 * @param screen The current screen.
	 */
	public TimeSelectDialog(JFrame frame, List<String> timeOfScreen, int screenX, String filmName, String screen) {
		setUndecorated(true);
		setBounds(frame.getX() + screenX + 5, frame.getY() + 320, 154, 180);
		contentPane.setLayout(null);
		int count = 0;
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
		for(String timeStr : timeOfScreen){
			
			DateFormat dateFormat = new SimpleDateFormat("HH:mm");
			Date mDate = null;
			Date currentDate = null;
			try {
				mDate = dateFormat.parse(timeStr);
				currentDate = dateFormat.parse(dateFormat.format(new Date()));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			if(mDate.getTime() > currentDate.getTime()){
				JButton timeBtn = new JButton(timeStr);
				timeBtn.setUI(buttonUI);
				timeBtn.setForeground(Color.WHITE);
				timeBtn.setBounds(6, 10 + 50*count, 142, 41);
				
				timeBtn.addMouseListener(new MouseAdapter() {
				
					@Override
					public void mouseClicked(MouseEvent e) {
						listener.onTimeSelectClicked(screen,timeStr);
					}
					
				});
				
				contentPane.add(timeBtn);
				count++;
			}
			
		}
		
		contentPane.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				listener.onCancelClicked();
			}
			
		});
		JLabel cancelLabel = new JLabel("cancel",JLabel.CENTER);
		cancelLabel.setFont(new Font("Arial", Font.BOLD, 12));
		cancelLabel.setBounds(6, 10 + 45*count, 142, 41);
		contentPane.add(cancelLabel);
	}
}
