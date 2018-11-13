package com.group99.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This is the boundary class of welcome panel.
 * @author group 99
 *
 */
public class WelcomePanel extends JPanel{
	
	private WelcomePanelListener listener;
	JLabel label = null;
	JButton customerButton = null;
	JButton administratorButton = null;
	int count;
	/**
	 * This is the callback interface of WelcomePanel.
	 * @author group 99
	 *
	 */
	public interface WelcomePanelListener{
		public void onCustomerClicked();
		public void onAdministratorClicked();
	}
	/**
	 * Set WelcomePanel's listener.
	 * @param listener A listener of WelcomePanel.
	 */
	public void setWelcomePanelListener(WelcomePanelListener listener){
		this.listener = listener;
	}
	
	/**
	 * This is the constructor of WelcomePanel.
	 */
	public WelcomePanel() {
		initData();
	}
	/**
	 * Initialize the data of WelcomePanel.
	 */
	private void initData() {
		
		setLayout(null);
		
		customerButton = new JButton("Customer");
		customerButton.setUI(new ClientNormalButtonUI());
		customerButton.setForeground(Color.WHITE);
		customerButton.setFont(new Font("Arial", Font.BOLD, 20));
		
		customerButton.setVisible(true);
		customerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listener.onCustomerClicked();
			}
		});
		add(customerButton);
		
		administratorButton = new JButton("Administrator");
		administratorButton.setUI(new ClientNormalButtonUI());
		administratorButton.setForeground(Color.WHITE);
		administratorButton.setFont(new Font("Arial", Font.BOLD, 17));
		
		administratorButton.setVisible(true);
		administratorButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listener.onAdministratorClicked();
			}
		});
		add(administratorButton);
		
		label = new JLabel();
		label.setFont(new Font("Copperplate", Font.PLAIN, 80));
		label.setText("99 Kiosk");
		label.setOpaque(false);
		configTimeArea();
		add(label);
		
	}

	/**
	 * This method creates a timer task to update the time per 10 ms.
	 */
	private void configTimeArea() {
		Timer tmr = new Timer();
		tmr.scheduleAtFixedRate(new JLabelTimerTask(), new Date(), 10);
	}

	/**
	 * This is a class of timer task.
	 * @author group 99
	 *
	 */
	protected class JLabelTimerTask extends TimerTask {

		@Override
		public void run() {
			
			if(count <= 90 && count >= 10){
				label.setBounds(310, 290 - count, 800, 80);
				customerButton.setBounds(200, 540 - count, 155, 50);
				administratorButton.setBounds(600, 540 - count, 155, 50);
			}else if(count > 90){
				return;
			}
			
			count ++;
		}
	}
	
}
