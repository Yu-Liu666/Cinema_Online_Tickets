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
 * This is the boundary class of panel of movies' brief information.
 * @author group 99
 *
 */
public class MoviesIntroPanel extends JPanel {
	
	private int BTN_IMAGE_SIZE_WIDTH = 150;
	private int BTN_IMAGE_SIZE_HEIGHT = 222;
	
	List<Film> films = null;
	List<BufferedImage> filmsImage = new ArrayList<>();
	MoviesIntroPanelListener listener;
	/**
	 * This is the callback interface of MoviesIntroPanel.
	 * @author group 99
	 *
	 */
	public interface MoviesIntroPanelListener{
		public void onHomeClicked();
		public void onImageClicked(String filmName);
	}
	/**
	 * Set MoviesIntroPanel's listener.
	 * @param listener A listener of MoviesIntroPanel.
	 */
	public void setMoviesIntroPanelListener(MoviesIntroPanelListener listener){
		this.listener = listener;
	}
	
	/**
	 * This is the constructor of MoviesIntroPanel.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public MoviesIntroPanel() throws ParserConfigurationException, SAXException, IOException {
		initPanel();
	}
	/**
	 * Initialize the data of MoviesIntroPanel.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private void initPanel() throws ParserConfigurationException, SAXException, IOException {
		
		int count = 0;
		List<Film> films = FilmDomParser.getFilms();
		
		setLayout(null);
		
		for(Film film : films){
			String filmName = film.getFilmName();
			String filmType = film.getFilmType();
			String filmScore = film.getFilmScore();
			BufferedImage image = ImageIO.read(new FileInputStream("././././resource/" + filmName + ".jpg"));

			BufferedImage homeImage = ImageIO.read(new FileInputStream("././././resource/home.png"));	
			JButton homeBack = new JButton();
			homeBack.setBounds(10, 500, 50, 50);
			homeBack.setOpaque(false);
			homeBack.setUI(new BasicButtonUI() {

				@Override
				public void paint(Graphics g, JComponent c) {
					Graphics2D graphics2d = (Graphics2D) g;
					graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					graphics2d.drawImage(homeImage, 0, 0, 50, 50, null);
				}
				
			});
			homeBack.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					listener.onHomeClicked();
				}
				
			});
			add(homeBack);
			
			JButton button = new JButton();
			button.setUI(new BasicButtonUI(){
				@Override
				public void paint(Graphics g, JComponent c) {
					Graphics2D graphics2d = (Graphics2D) g;
					graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					graphics2d.drawImage(image, 0, 0, BTN_IMAGE_SIZE_WIDTH, BTN_IMAGE_SIZE_HEIGHT, null);
				}
			});
			button.setBounds(31 + count*185, 150, BTN_IMAGE_SIZE_WIDTH, BTN_IMAGE_SIZE_HEIGHT);
			button.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e) {
					listener.onImageClicked(filmName);
				}
			});
			add(button);
			
			JLabel labelFilmName = new JLabel();
			labelFilmName.setFont(new Font("Arial", Font.BOLD, 12));
			labelFilmName.setText(filmName);
			labelFilmName.setHorizontalAlignment(JLabel.CENTER);
			labelFilmName.setOpaque(false);
			labelFilmName.setBounds(26 + count*185, 400, 160, 12);
			add(labelFilmName);
			
			JLabel labelFilmType = new JLabel();
			labelFilmType.setFont(new Font("Arial", Font.BOLD, 13));
			labelFilmType.setText("Type: " + filmType);
			labelFilmType.setHorizontalAlignment(JLabel.CENTER);
			labelFilmType.setOpaque(false);
			labelFilmType.setBounds(26 + count*185, 430, 160, 12);
			add(labelFilmType);
			
			JLabel labelFilmScore = new JLabel();
			labelFilmScore.setFont(new Font("Arial", Font.BOLD, 15));
			labelFilmScore.setText("Score: " + filmScore);
			labelFilmScore.setForeground(new Color(255, 130, 71));
			labelFilmScore.setHorizontalAlignment(JLabel.CENTER);
			labelFilmScore.setOpaque(false);
			labelFilmScore.setBounds(26 + count*185, 460, 160, 12);
			add(labelFilmScore);
			
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
		
		graphics2d.setFont(new Font("Copperplate", Font.PLAIN, 35));
		graphics2d.drawString("Now Showing", 350, 80);
		
	}
	
}
