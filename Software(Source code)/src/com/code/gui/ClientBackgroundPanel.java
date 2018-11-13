package com.group99.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;
/**
 * This is the boundary class of client background panel.
 * @author group 99.
 *
 */
public class ClientBackgroundPanel extends JPanel {
	
	private String title;
	
	public ClientBackgroundPanel(String title){
		this.title = title;
	}
	/**
	 * Override the method of void paintComponent(Graphics g).
	 */
	@Override
	protected void paintComponent(Graphics g) {
		
		Graphics2D graphics2d = (Graphics2D) g;
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		graphics2d.setColor(new Color(255, 255, 255, 230));
		graphics2d.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 20, 20);
		
		graphics2d.setColor(Color.WHITE);
		graphics2d.setClip(0, 0, getWidth(), 30);
		graphics2d.fillRoundRect(1, 3, getWidth() - 2, getHeight() - 1, 20, 20);
		graphics2d.setClip(null);
		
		graphics2d.setStroke(new BasicStroke(2));
		graphics2d.setColor(Color.GRAY);
		graphics2d.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 20, 20);
		
		graphics2d.setFont(new Font("Arial", Font.BOLD, 16));
		graphics2d.setColor(Color.DARK_GRAY);
		graphics2d.drawString(title, 15, 23);

	}
	
}
