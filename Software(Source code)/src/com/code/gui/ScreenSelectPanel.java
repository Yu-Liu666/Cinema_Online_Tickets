package com.group99.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.group99.dom.FilmDomParser;
import com.group99.dom.TimeTableDomParser;
import com.group99.gui.TimeSelectDialog.TimeSelectDialogListener;
import com.group99.javabean.Film;
import com.group99.javabean.TimeTable;

/**
 * This is the boundary class of panel of selecting screen.
 * @author group 99
 *
 */
public class ScreenSelectPanel extends JPanel {

	private BufferedImage selectedMovieImg;
	private String selectedName;
	private JFrame frame;
	List<String> timeOfScreen1 = null, timeOfScreen2 = null, timeOfScreen3 = null;
	
	private ScreenSelectPanelListener listener;
	/**
	 * This is the callback interface of ScreenSelectPanel.
	 * @author group 99
	 *
	 */
	public interface ScreenSelectPanelListener{
		public void onBtnBackClicked();
		public void onTimeSelectClicked(String screenName, String timeStr);
	}
	/**
	 * Set ScreenSelectPanel's listener.
	 * @param listener A listener of ScreenSelectPanel.
	 */
	public void setScreenSelectPanelListener(ScreenSelectPanelListener listener){
		this.listener = listener;
	}
	/**
	 * This is the constructor of ScreenSelectPanel.
	 */
	public ScreenSelectPanel() {
	}
	/**
	 * This is the constructor of ScreenSelectPanel.
	 * @param selectedName The name of selecting movie.
	 * @param frame The current frame.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public ScreenSelectPanel(String selectedName, JFrame frame) throws ParserConfigurationException, SAXException, IOException{
		this.selectedName = selectedName;
		this.frame = frame;
		initData();
	}
	/**
	 * Initialize the data of ScreenSelectPanel.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private void initData() throws ParserConfigurationException, SAXException, IOException {
		
		setLayout(null);
		
		List<Film> films = FilmDomParser.getFilms();
		
		for(Film film : films){
			if(film.getFilmName().equals(selectedName)){
				selectedMovieImg = ImageIO.read(new FileInputStream("././././resource/" + selectedName + ".jpg"));
			}
		}
		
		List<TimeTable> tables = TimeTableDomParser.getTimeTables();
		timeOfScreen1 = new ArrayList<String>();
		timeOfScreen2 = new ArrayList<String>();
		timeOfScreen3 = new ArrayList<String>();
		
		for(TimeTable table : tables){
			if(table.getMovie().equals(selectedName) && table.getScreen().equals("1")){
				timeOfScreen1.add(table.getTime());
			}else if(table.getMovie().equals(selectedName) && table.getScreen().equals("2")){
				timeOfScreen2.add(table.getTime());
			}else if(table.getMovie().equals(selectedName) && table.getScreen().equals("3")){
				timeOfScreen3.add(table.getTime());
			}
		}
		
		JButton btnScreen1 = new JButton("Screen1");
		if(timeOfScreen1.isEmpty()){
			btnScreen1.setEnabled(false);
			
			btnScreen1.setUI(new BasicButtonUI(){
				@Override
				public void paint(Graphics g, JComponent c) {
					g.setColor(new Color(169, 169, 169));
					g.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 10, 10);
					super.paint(g, c);
				}
			});
		}else{
			btnScreen1.setUI(new ClientNormalButtonUI());
			btnScreen1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					TimeSelectDialog timeSelectDialog = new TimeSelectDialog(frame, timeOfScreen1, btnScreen1.getX(), selectedName, "1");
					timeSelectDialog.setAlwaysOnTop(true);
					timeSelectDialog.setVisible(true);
					timeSelectDialog.setTimeSelectDialogListener(new TimeSelectDialogListener() {
						@Override
						public void onCancelClicked() {
							timeSelectDialog.dispose();
						}

						@Override
						public void onTimeSelectClicked(String screenName, String timeStr) {
							listener.onTimeSelectClicked(screenName, timeStr);
							timeSelectDialog.dispose();
						}
					});
				}
			});
		}
		btnScreen1.setForeground(Color.WHITE);
		btnScreen1.setFont(new Font("Arial", Font.BOLD, 20));
		btnScreen1.setBounds(381, 468, 154, 48);
		
		JButton btnScreen2 = new JButton("Screen2");
		if(timeOfScreen2.isEmpty()){
			btnScreen2.setEnabled(false);
			btnScreen2.setUI(new BasicButtonUI(){
				@Override
				public void paint(Graphics g, JComponent c) {
					g.setColor(new Color(169, 169, 169));
					g.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 10, 10);
					super.paint(g, c);
				}
			});
		}else{
			btnScreen2.setUI(new ClientNormalButtonUI());
			btnScreen2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					TimeSelectDialog timeSelectDialog = new TimeSelectDialog(frame, timeOfScreen2, btnScreen2.getX(), selectedName, "2");
					timeSelectDialog.setAlwaysOnTop(true);
					timeSelectDialog.setVisible(true);
					timeSelectDialog.setTimeSelectDialogListener(new TimeSelectDialogListener() {
						@Override
						public void onCancelClicked() {
							timeSelectDialog.dispose();
						}

						@Override
						public void onTimeSelectClicked(String screenName, String timeStr) {
							listener.onTimeSelectClicked(screenName, timeStr);
							timeSelectDialog.dispose();
						}
					});
				}
			});
		}
		btnScreen2.setForeground(Color.WHITE);
		btnScreen2.setFont(new Font("Arial", Font.BOLD, 20));
		btnScreen2.setBounds(555, 468, 154, 48);
		
		JButton btnScreen3 = new JButton("Screen3");
		if(timeOfScreen3.isEmpty()){
			btnScreen3.setEnabled(false);
			btnScreen3.setUI(new BasicButtonUI(){
				@Override
				public void paint(Graphics g, JComponent c) {
					g.setColor(new Color(169, 169, 169));
					g.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 10, 10);
					super.paint(g, c);
				}
			});
		}else{
			btnScreen3.setUI(new ClientNormalButtonUI());
			btnScreen3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					TimeSelectDialog timeSelectDialog = new TimeSelectDialog(frame, timeOfScreen3, btnScreen3.getX(), selectedName, "3");
					timeSelectDialog.setAlwaysOnTop(true);
					timeSelectDialog.setVisible(true);
					timeSelectDialog.setTimeSelectDialogListener(new TimeSelectDialogListener() {
						@Override
						public void onCancelClicked() {
							timeSelectDialog.dispose();
						}

						@Override
						public void onTimeSelectClicked(String screenName, String timeStr) {
							listener.onTimeSelectClicked(screenName, timeStr);
							timeSelectDialog.dispose();
						}
					});
				}
			});
		}
		btnScreen3.setForeground(Color.WHITE);
		btnScreen3.setFont(new Font("Arial", Font.BOLD, 20));
		btnScreen3.setBounds(729, 468, 154, 48);
		
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
		add(btnScreen1);
		add(btnScreen2);
		add(btnScreen3);
		
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
		graphics2d.setFont(new Font("Arial", Font.BOLD, 18));
		int count = 0;
		graphics2d.drawString("TIMETABLE", 560, 60);

		graphics2d.setFont(new Font("Arial", Font.ITALIC, 15));
		if(!timeOfScreen1.isEmpty()){
			graphics2d.drawLine(420, 120 + 30 * (count), 820, 120 + 30 * (count));
			count ++;
			graphics2d.drawString("SCREEN 1 : ", 500, 120 + 30*(count));
			for(int i = 0; i < timeOfScreen1.size(); i++ ){
				graphics2d.drawString(timeOfScreen1.get(i), 700, 120 + 30 * (count));
				count++;
			}
		}
		if(!timeOfScreen2.isEmpty()){
			graphics2d.drawLine(420, 120 + 30 * (count), 820, 120 + 30 * (count));
			count ++;
			graphics2d.drawString("SCREEN 2 : ", 500, 120 + 30 * (count));
			for(int i = 0; i < timeOfScreen2.size(); i++ ){
				graphics2d.drawString(timeOfScreen2.get(i), 700, 120 + 30 * (count));
				count++;
			}
		}
		if(!timeOfScreen3.isEmpty()){
			graphics2d.drawLine(420, 120 + 30 * (count), 820, 120 + 30 * (count));
			count++;
			graphics2d.drawString("SCREEN 3 : ", 500, 120 + 30 * (count));
			for(int i = 0; i < timeOfScreen3.size(); i++ ){
				graphics2d.drawString(timeOfScreen3.get(i), 700, 120 + 30 * (count));
				count++;
			}
		}
		graphics2d.drawLine(420, 120 + 30 * (count), 820, 120 + 30 * (count));

	}

}
