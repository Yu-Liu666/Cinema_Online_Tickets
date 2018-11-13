package com.group99.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.LookAndFeel;
import javax.swing.plaf.basic.BasicButtonUI;
/**
 * This is the boundary class of selected seat button UI.
 * @author group 99.
 *
 */
public class SeatSelectedButtonUI extends BasicButtonUI{
	
	private static final int SEAT_IMAGE_SIZE_WIDTH = 60;
	private static final int SEAT_IMAGE_SIZE_HEIGHT = 53;
	private BufferedImage image;
	/**
	 * This is the constructor of SeatSelectedButtonUI.
	 * @param number the column of seat id.
	 * @throws IOException
	 */
	public SeatSelectedButtonUI(String number) throws IOException {
		this.image = ImageIO.read(new FileInputStream("././././resource/" + "s" + number + ".png"));
	}
	
	@Override
	protected void paintText(Graphics g, AbstractButton b, Rectangle textRect, String text) {
		super.paintText(g, b, textRect, text);
	}
	/**
	 * Override the method of void installDefaults(AbstractButton b) to set the LookAndFeel.
	 */
	@Override
	protected void installDefaults(AbstractButton b) {
		super.installDefaults(b);
        LookAndFeel.installProperty(b, "opaque", Boolean.FALSE);
	}
	/**
	 * Override the method of void paint(Graphics g, JComponent c) to set the view when button isn't pressed.
	 */
	@Override
	public void paint(Graphics g, JComponent c) {
		Graphics2D graphics2d = (Graphics2D) g;
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2d.drawImage(image, 0, 0, SEAT_IMAGE_SIZE_WIDTH, SEAT_IMAGE_SIZE_HEIGHT, null);
		super.paint(graphics2d, c);
	}

}
