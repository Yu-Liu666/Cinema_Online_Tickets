package com.group99.gui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JComponent;
import javax.swing.LookAndFeel;
import javax.swing.plaf.basic.BasicButtonUI;
/**
 * This is the boundary class of special client button UI.
 * @author group 99
 *
 */
public class ClientSpecialButtonUI extends BasicButtonUI {

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
		g.setColor(new Color(245, 245, 245, 200));
		g.fillRoundRect(0, 0, c.getWidth(), c.getHeight() + 5, 10, 10);
		super.paint(g, c);
	}
	/**
	 * Override the method of void paintButtonPressed(Graphics g, AbstractButton b) to set the view when button is pressed.
	 */
	@Override
	protected void paintButtonPressed(Graphics g, AbstractButton b) {
		g.setColor(new Color(0, 91, 255, 200));
		g.fillRoundRect(0, 0, b.getWidth(), b.getHeight() + 5, 10, 10);
	}
	
}
