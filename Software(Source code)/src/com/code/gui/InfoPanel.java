package com.group99.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.group99.dom.FilmDomParser;
import com.group99.javabean.Film;
/**
 * This is the boundary class of panel of movie information.
 * @author group 99
 *
 */
public class InfoPanel extends JPanel {
	private BufferedImage selectedMovieImg;
	private String movieImgName;
	private String selectedMovieName;
	private String selectedDirector;
	private String selectedStars;
	private String selectedName;
	private String selectedPrice;
	private String selectedDuration;
	private String selectedStoryline;
	
	private InfoPanelListener listener;
	/**
	 * This is the callback interface of InfoPanel.
	 * @author group 99
	 *
	 */
	public interface InfoPanelListener{
		public void onBtnBackClicked();
		public void onBtnPurchaseClicked();
	}
	/**
	 * Set InfoPanel's listener.
	 * @param listener A listener of InfoPanel.
	 */
	public void setInfoPanelListener(InfoPanelListener listener){
		this.listener = listener;
	}
	/**
	 * This is the constructor of InfoPanel.
	 * @param selectedMovieName The name of selecting movie.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public InfoPanel(String selectedMovieName) throws ParserConfigurationException, SAXException, IOException {
		
		this.selectedMovieName = selectedMovieName; 
		initData();
		
	}
	/**
	 * Initialize the data of InfoPanel.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private void initData() throws ParserConfigurationException, SAXException, IOException {

		setLayout(null);
		
		List<Film> films = FilmDomParser.getFilms();

		for(Film film : films){
			if(film.getFilmName().equals(selectedMovieName)){
				movieImgName = selectedMovieName;
				selectedDirector = film.getFilmDirector();
				selectedStars = film.getFilmStars();
				selectedStoryline = film.getFilmStoryline();
				selectedName = film.getFilmName();
				selectedPrice = film.getFilmPrice().toString();
				selectedDuration = film.getFilmDuration() + "";
				selectedMovieImg = ImageIO.read(new FileInputStream("././././resource/" + movieImgName + ".jpg"));
			}
		}
		
		JTextPane storyline = new JTextPane();
		storyline.setEditable(false);
		storyline.setFont(new Font("Arial", Font.ITALIC, 15));
		storyline.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		storyline.setOpaque(false);
		storyline.setText(selectedStoryline);
		storyline.setBounds(480, 207, 403, 350);
		add(storyline);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Arial", Font.BOLD, 20));
		btnBack.setForeground(Color.WHITE);
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listener.onBtnBackClicked();
			}
		});
		btnBack.setOpaque(true);
		btnBack.setBounds(50, 365, 154, 48);
		btnBack.setUI(new ClientNormalButtonUI());
		add(btnBack);
		
		JButton btnPurchase = new JButton("Purchase");
		btnPurchase.setForeground(Color.WHITE);
		btnPurchase.setFont(new Font("Arial", Font.BOLD, 20));
		btnPurchase.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listener.onBtnPurchaseClicked();
			}
		});
		btnPurchase.setOpaque(true);
		btnPurchase.setBounds(50, 435, 154, 48);
		btnPurchase.setUI(new ClientNormalButtonUI());
		add(btnPurchase);
		
	}

	public String getSelectedName() {
		return selectedName;
	}

	public void setSelectedName(String selectedName) {
		this.selectedName = selectedName;
	}

	public BufferedImage getSelectedMovieImg() {
		return selectedMovieImg;
	}

	public void setSelectedMovieImg(BufferedImage selectedMovieImg) {
		this.selectedMovieImg = selectedMovieImg;
	}

	public String getSelectedStoryline() {
		return selectedStoryline;
	}

	public void setSelectedStoryline(String selectedStoryline) {
		this.selectedStoryline = selectedStoryline;
	}
	/**
	 * Override the method of void paintComponent(Graphics g).
	 */
	@Override
	protected void paintComponent(Graphics g) {
		
		Graphics2D graphics2d = (Graphics2D) g;
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		graphics2d.drawImage(selectedMovieImg, 30, 10, 200, 296, null);
		graphics2d.drawLine(300, 5, 300, 500);
		
		graphics2d.setFont(new Font("Arial", Font.BOLD, 15));
		graphics2d.drawString("Film Name : " + selectedName, 360, 20);
		graphics2d.drawString("Film Duration : " + selectedDuration + " MINUTES", 360, 60);
		graphics2d.drawString("Film Price : $ " + selectedPrice, 360, 100);
		graphics2d.drawString("Film Director : " + selectedDirector, 360, 140);
		graphics2d.drawString("Film Stars : " + selectedStars, 360, 180);
		graphics2d.drawString("Film Storyline : ", 360, 220);
		
	}
}
