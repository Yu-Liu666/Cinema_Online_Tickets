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
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import com.group99.dom.FilmDomParser;
import com.group99.javabean.Film;
/**
 * This is the boundary class of panel of modifying information of movie.
 * @author group 99
 *
 */
public class ModifyInfoPanel extends JPanel {
	private BufferedImage selectedMovieImg;
	private String selectedMovieName;
	private String selectedPrice;
	private String selectedScore;

	private ModifyInfoPanelListener listener;
	/**
	 * This is the callback interface of ModifyInfoPanel.
	 * @author group 99
	 *
	 */
	public interface ModifyInfoPanelListener {
		public void onBackClicked();
	}
	/**
	 * Set ModifyInfoPanel's listener.
	 * @param listener A listener of ModifyInfoPanel.
	 */
	public void setModifyInfoPanelListener(ModifyInfoPanelListener listener) {
		this.listener = listener;
	}
	/**
	 * This is the constructor of ModifyInfoPanel.
	 * @param selectedMovieName The name of selecting movie.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public ModifyInfoPanel(String selectedMovieName) throws ParserConfigurationException, SAXException, IOException {

		this.selectedMovieName = selectedMovieName;
		initData();

	}
	/**
	 * Initialize the data of ModifyInfoPanel.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private void initData() throws ParserConfigurationException, SAXException, IOException {

		List<Film> films = FilmDomParser.getFilms();

		for(Film film : films){
			if(film.getFilmName().equals(selectedMovieName)){
				selectedPrice = film.getFilmPrice().toString();
				selectedScore = film.getFilmScore();
			}
		}
		
		selectedMovieImg = ImageIO.read(new FileInputStream("././././resource/" + selectedMovieName + ".jpg"));
		
		setLayout(null);
		
		JTextField priceTextField = new JTextField(15);
		priceTextField.setBounds(500, 207, 330, 55);
		priceTextField.setText("The old price: " + selectedPrice);
		priceTextField.setFont(new Font("Arial", Font.PLAIN, 19));
		priceTextField.setOpaque(false);
		add(priceTextField);
		
		JTextField scoreTextField = new JTextField(15);
		scoreTextField.setBounds(500, 327, 330, 55);
		scoreTextField.setText("The old score: " + selectedScore);
		scoreTextField.setFont(new Font("Arial", Font.PLAIN, 19));
		scoreTextField.setOpaque(false);
		add(scoreTextField);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Arial", Font.BOLD, 20));
		btnBack.setForeground(Color.WHITE);
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listener.onBackClicked();
			}
		});
		btnBack.setOpaque(true);
		btnBack.setBounds(50, 365, 154, 48);
		btnBack.setUI(new ClientNormalButtonUI());
		add(btnBack);
		
		JLabel errorLabel = new JLabel("The price must be number.The score must be less than 10 !");
		errorLabel.setOpaque(false);
		errorLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		errorLabel.setForeground(Color.RED);
		errorLabel.setBounds(502, 400, 450, 20);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setFont(new Font("Arial", Font.BOLD, 20));
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(isNumeric(scoreTextField.getText()) && isNumeric(priceTextField.getText())){
					if(Float.parseFloat(scoreTextField.getText()) <= 10){
						try {
							FilmDomParser.updateFilms(selectedMovieName, "filmPrice", priceTextField.getText());
							FilmDomParser.updateFilms(selectedMovieName, "filmScore", scoreTextField.getText());
							JLabel successLabel = new JLabel("Update successfully ! ");
							successLabel.setOpaque(false);
							successLabel.setFont(new Font("Arial", Font.PLAIN, 15));
							successLabel.setForeground(Color.RED);
							successLabel.setBounds(58, 510, 450, 20);
							add(successLabel);
							repaint();
						} catch (ParserConfigurationException | SAXException | IOException | TransformerException e1) {
							e1.printStackTrace();
						}
					}else{
						add(errorLabel);
						repaint();
					}
				}else{
					add(errorLabel);
					repaint();
				}
			}
		});
		btnSubmit.setOpaque(true);
		btnSubmit.setBounds(50, 435, 154, 48);
		btnSubmit.setUI(new ClientNormalButtonUI());
		add(btnSubmit);
		
	}

	public static boolean isNumeric(String str){ 
		if (null == str || "".equals(str)) {  
	        return false;  
	    }  
	    Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");  
	    return pattern.matcher(str).matches();  
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

		graphics2d.setFont(new Font("Copperplate", Font.BOLD, 32));
		graphics2d.drawString("Modify Info", 510, 50);

		graphics2d.setFont(new Font("Arial", Font.BOLD, 20));
		graphics2d.drawString("New Price: ", 380, 240);

		graphics2d.drawString("New Score: ", 380, 360);

		super.paintComponent(graphics2d);
	}
}
