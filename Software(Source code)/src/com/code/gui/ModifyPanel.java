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
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.group99.dom.FilmDomParser;
import com.group99.javabean.Film;
/**
 * This is the boundary class of panel of modifying movie.
 * @author group 99
 *
 */
public class ModifyPanel extends JPanel {
	
	private int BTN_IMAGE_SIZE_WIDTH = 100;
	private int BTN_IMAGE_SIZE_HEIGHT = 148;
	BufferedImage image1, image2, image3, image4, image5;
	
	List<Film> films = null;
	List<BufferedImage> filmsImage = new ArrayList<>();
	ModifyPanelListener listener;
	/**
	 * This is the callback interface of ModifyPanel.
	 * @author group 99
	 *
	 */
	public interface ModifyPanelListener{
		public void onBackClicked();
		public void onImageClicked(String filmName);
	}
	/**
	 * Set ModifyPanel's listener.
	 * @param listener A listener of ModifyPanel.
	 */
	public void setModifyPanelListener(ModifyPanelListener listener){
		this.listener = listener;
	}
	
	/**
	 * This is the constructor of ModifyPanel.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public ModifyPanel() throws ParserConfigurationException, SAXException, IOException {
		initData();
	}
	/**
	 * Initialize the data of ModifyPanel.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private void initData() throws ParserConfigurationException, SAXException, IOException {
		
		image1 = ImageIO.read(new FileInputStream("././././resource/LA LA LAND.jpg"));
		image2 = ImageIO.read(new FileInputStream("././././resource/" + "KONG-SKULL ISLAND" + ".jpg"));
		image3 = ImageIO.read(new FileInputStream("././././resource/" + "BEAUTY AND THE BEAST" + ".jpg"));
		image4 = ImageIO.read(new FileInputStream("././././resource/" + "LOGAN" + ".jpg"));
		image5 = ImageIO.read(new FileInputStream("././././resource/" + "MOONLIGHT" + ".jpg"));
		
		int count = 0;
		List<Film> films = FilmDomParser.getFilms();
		
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
		
		for(Film film : films){
			String filmName = film.getFilmName();
			String filmType = film.getFilmType();
			String filmScore = film.getFilmScore();
			BufferedImage image = ImageIO.read(new FileInputStream("././././resource/" + filmName + ".jpg"));
			
			JButton button = new JButton();
			button.setUI(new BasicButtonUI(){
				@Override
				public void paint(Graphics g, JComponent c) {
					Graphics2D graphics2d = (Graphics2D) g;
					graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					graphics2d.drawImage(image, 0, 0, BTN_IMAGE_SIZE_WIDTH, BTN_IMAGE_SIZE_HEIGHT, null);
				}
			});
			button.setBounds(325 + count*125, 240, BTN_IMAGE_SIZE_WIDTH, BTN_IMAGE_SIZE_HEIGHT);
			button.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e) {
					listener.onImageClicked(filmName);
				}
			});
			add(button);
			count++;
		}
	}
	/**
	 * Override the method of void paintComponent(Graphics g).
	 */
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D graphics2d = (Graphics2D) g;
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		graphics2d.setFont(new Font("Copperplate", Font.BOLD, 32));
		graphics2d.drawString("Modify", 85, 50);

		graphics2d.drawImage(image4, 47, 130, 60, 89, null);
		graphics2d.drawImage(image1, 202, 130, 60, 89, null);
		graphics2d.drawImage(image3, 73, 170, 70, 104, null);
		graphics2d.drawImage(image5, 165, 170, 70, 104, null);
		graphics2d.drawImage(image2, 112, 210, 80, 119, null);
		graphics2d.drawLine(300, 5, 300, 530);
		graphics2d.setFont(new Font("Copperplate", Font.PLAIN, 25));
		graphics2d.drawString("You want to modify: ", 345, 80);
		
		super.paintComponents(graphics2d);
	}
	
}
