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
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.group99.dom.TicketDomParser;
import com.group99.javabean.Ticket;

/**
 * This is the boundary class of panel of menu of modifying movie.
 * @author group 99
 *
 */
public class ModifyMenuPanel extends JPanel {
	
	BufferedImage image1, image2, image3, image4, image5;
	
	private ModifyMenuPanelListener listener;
	/**
	 * This is the callback interface of ModifyMenuPanel.
	 * @author group 99
	 *
	 */
	public interface ModifyMenuPanelListener{
		public void onBackClicked();
		public void onResetSeatClicked();
		public void onModifyFilmInfoClicked();
	}
	/**
	 * Set ModifyMenuPanel's listener.
	 * @param listener A listener of ModifyMenuPanel.
	 */
	public void setModifyMenuPanelListener(ModifyMenuPanelListener listener){
		this.listener = listener;
	}
	/**
	 * This is the constructor of ModifyMenuPanel.
	 * @throws IOException
	 */
	public ModifyMenuPanel() throws IOException {
		initData();
	}
	/**
	 * Initialize the data of ModifyMenuPanel.
	 * @throws IOException
	 */
	private void initData() throws IOException {
		
		image1 = ImageIO.read(new FileInputStream("././././resource/LA LA LAND.jpg"));
		image2 = ImageIO.read(new FileInputStream("././././resource/" + "KONG-SKULL ISLAND" + ".jpg"));
		image3 = ImageIO.read(new FileInputStream("././././resource/" + "BEAUTY AND THE BEAST" + ".jpg"));
		image4 = ImageIO.read(new FileInputStream("././././resource/" + "LOGAN" + ".jpg"));
		image5 = ImageIO.read(new FileInputStream("././././resource/" + "MOONLIGHT" + ".jpg"));
		
		setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Arial", Font.BOLD, 20));
		btnBack.setForeground(Color.WHITE);
		btnBack.setOpaque(true);
		btnBack.setBounds(70, 415, 154, 48);
		btnBack.setUI(new ClientNormalButtonUI());
		btnBack.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				listener.onBackClicked();
			}
			
		});
		add(btnBack);
		
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
		
		JButton updateButton = new JButton("Reset Seat");
		updateButton.setUI(buttonUI);
		updateButton.setForeground(Color.WHITE);
		updateButton.setFont(new Font("Copperplate", Font.BOLD, 23));
		updateButton.setBounds(390, 170, 180, 240);
		updateButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				listener.onResetSeatClicked();
			}
			
		});
		add(updateButton);
		
		JButton modifyButton = new JButton("Modify Film");
		modifyButton.setUI(buttonUI);
		modifyButton.setForeground(Color.WHITE);
		modifyButton.setFont(new Font("Copperplate", Font.BOLD, 22));
		modifyButton.setBounds(700, 170, 180, 240);
		modifyButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				listener.onModifyFilmInfoClicked();;
			}
			
		});
		add(modifyButton);
		
	}
	/**
	 * Override the method of void paint(Graphics g).
	 */
	@Override
	public void paint(Graphics g) {
		
		Graphics2D graphics2d = (Graphics2D) g;
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		graphics2d.setFont(new Font("Copperplate", Font.BOLD, 32));
		graphics2d.drawString("Modification", 40, 50);
		
		graphics2d.drawImage(image4, 47, 130, 60, 89, null);
		graphics2d.drawImage(image1, 202, 130, 60, 89, null);
		graphics2d.drawImage(image3, 73, 170, 70, 104, null);
		graphics2d.drawImage(image5, 165, 170, 70, 104, null);
		graphics2d.drawImage(image2, 112, 210, 80, 119, null);
		graphics2d.drawLine(300, 5, 300, 530);
		
		graphics2d.setFont(new Font("Arial", Font.BOLD, 25));
		graphics2d.drawString("You want to : ", 320, 50);
		
		super.paint(graphics2d);

		
	}
	
	
	
}
