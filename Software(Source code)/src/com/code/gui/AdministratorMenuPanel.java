package com.group99.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
/**
 * This is the boundary class of panel of menu of administrator mode.
 * @author group 99.
 *
 */
public class AdministratorMenuPanel extends JPanel {
	
	private AdministratorMenuPanelListener listener;
	/**
	 * This is the callback interface of menu of administrator mode.
	 * @author group 99
	 *
	 */
	public interface AdministratorMenuPanelListener{
		public void onHomeClicked();
		public void onUpdateClicked();
		public void onStatisticsClicked();
	}
	/**
	 * Set AdministratorMenuPanel's listener.
	 * @param listener A listener of AdministratorMenuPanel.
	 */
	public void setAdministratorMenuPanelListener(AdministratorMenuPanelListener listener){
		this.listener = listener;
	}
	/**
	 * This is the constructor of AdministratorMenuPanel.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public AdministratorMenuPanel() throws ParserConfigurationException, SAXException, IOException {
		initData();
	}
	/**
	 * Initialize the data of AdministratorMenuPanel.
	 */
	private void initData() throws ParserConfigurationException, SAXException, IOException {
			
		setLayout(null);
		
		BufferedImage image = ImageIO.read(new FileInputStream("././././resource/home.png"));	
		JButton homeBack = new JButton();
		homeBack.setBounds(10, 500, 50, 50);
		homeBack.setOpaque(false);
		homeBack.setUI(new BasicButtonUI() {

			@Override
			public void paint(Graphics g, JComponent c) {
				Graphics2D graphics2d = (Graphics2D) g;
				graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				graphics2d.drawImage(image, 0, 0, 50, 50, null);
			}
			
		});
		homeBack.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				listener.onHomeClicked();
			}
			
		});
		add(homeBack);
		
		BasicButtonUI buttonUI = new BasicButtonUI(){
			@Override
			public void paint(Graphics g, JComponent c) {
				g.setColor(new Color(30, 144, 255));
				g.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 10, 10);
				super.paint(g, c);
			}

			@Override
			protected void paintButtonPressed(Graphics g, AbstractButton b) {
				g.setColor(new Color(255, 130, 71));
				g.fillRoundRect(0, 0, b.getWidth(), b.getHeight(), 10, 10);
			}
		};
		
		JButton updateButton = new JButton("Update");
		updateButton.setUI(buttonUI);
		updateButton.setForeground(Color.WHITE);
		updateButton.setFont(new Font("Copperplate", Font.BOLD, 40));
		updateButton.setBounds(150, 170, 225, 300);
		updateButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				listener.onUpdateClicked();
			}
			
		});
		add(updateButton);
		
		JButton statisticsButton = new JButton("Statistics");
		statisticsButton.setUI(buttonUI);
		statisticsButton.setForeground(Color.WHITE);
		statisticsButton.setFont(new Font("Copperplate", Font.BOLD, 33));
		statisticsButton.setBounds(590, 170, 225, 300);
		statisticsButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				listener.onStatisticsClicked();
			}
			
		});
		add(statisticsButton);
		
		
	}
	/**
	 * Override the method of void paintComponent(Graphics g).
	 */
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D graphics2d = (Graphics2D) g;
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		graphics2d.setFont(new Font("Copperplate", Font.PLAIN, 32));
		graphics2d.drawString("Administrator Menu", 313, 80);
		
	}
	
}
