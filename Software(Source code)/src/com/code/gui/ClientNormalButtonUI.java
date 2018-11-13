package com.group99.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.LookAndFeel;
import javax.swing.plaf.basic.BasicButtonUI;
/**
 * This is the boundary class of normal client button UI.
 * @author group 99
 *
 */
public class ClientNormalButtonUI extends BasicButtonUI {
	
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
		g.setColor(new Color(0, 91, 255));
		g.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 10, 10);
		super.paint(g, c);
	}
	/**
	 * Override the method of void paintButtonPressed(Graphics g, AbstractButton b) to set the view when button is pressed.
	 */
	@Override
	protected void paintButtonPressed(Graphics g, AbstractButton b) {
		g.setColor(new Color(255, 130, 71));
		g.fillRoundRect(0, 0, b.getWidth(), b.getHeight(), 10, 10);
	}
	
}
