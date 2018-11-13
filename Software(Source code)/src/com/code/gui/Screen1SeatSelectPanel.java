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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.group99.dom.FilmDomParser;
import com.group99.dom.ScreenSeatDomParser;
import com.group99.dom.StudentDomParser;
import com.group99.dom.TicketDomParser;
import com.group99.gui.CartDialog.CartDialogListener;
import com.group99.gui.SeatTypeSelectDialog.SeatSelectDialogListener;
import com.group99.gui.StudentInfoCheckDialog.StudentInfoCheckDialogListener;
import com.group99.gui.StudentInfoCheckHintDialog.StudentInfoCheckHintDialogListener;
import com.group99.javabean.Film;
import com.group99.javabean.Screen;
import com.group99.javabean.Student;
import com.group99.javabean.Ticket;
/**
 * This is the boundary class of panel of selecting seat from screen 1.
 * @author group 99.
 *
 */
public class Screen1SeatSelectPanel extends JPanel {
	
	private String filmName;
	private String timeStr;
	private float ticketPrice;
	private BufferedImage movImg;
	private BufferedImage selectingImg;
	private BufferedImage selectedImg;
	private JFrame frame;
	
	private List<Ticket> tickets = null;
	
	private Screen1SeatSelectPanelListener listener;
	/**
	 * This is the callback interface of Screen1SeatSelectPanel.
	 * @author group 99
	 *
	 */
	public interface Screen1SeatSelectPanelListener{
		public void onBtnBackClicked();
		public void repaintPanel(String movName, String screenName, String timeStr);
	}
	/**
	 * Set Screen1SeatSelectPanel's listener.
	 * @param listener A listener of Screen1SeatSelectPanel.
	 */
	public void setScreen1SeatSelectPanelListener(Screen1SeatSelectPanelListener listener){
		this.listener = listener;
	}
	
	/**
	 * This is the constructor of Screen1SeatSelectPanel.
	 * @param filmName The name of selecting movie.
	 * @param timeStr The time you selecting.
	 * @param frame The current frame.
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	public Screen1SeatSelectPanel(String filmName, String timeStr, JFrame frame) throws IOException, ParserConfigurationException, SAXException {
		this.filmName = filmName;
		this.timeStr = timeStr;
		this.frame = frame;
		initData();
	}
	/**
	 * Make a object of Ticket.
	 * @param ticketType The type of Ticket.
	 * @param row The row of seat.
	 * @param column The column of seat.
	 * @param studentId The student id of Ticket.
	 */
	private void makeTicket(String ticketType, String row, int column, String studentId){
		Ticket ticket = new Ticket();
		ticket.setFilmName(filmName);
		ticket.setScreenName("screen 1");
		
		String ticketId = "";
		
		for(int i = 0; i < 8; i++){
			int randomNum = (int)(Math.random()*4) + 1;
			ticketId = ticketId + randomNum;
		}
		
		try {
			int count = 0;
			List<Ticket> allTickets = TicketDomParser.getTickets();
			for(Ticket currentTicket : allTickets){
				if(ticketId.equals(currentTicket.getTicketId())){
					makeTicket(ticketType, row, column, studentId);
					return;
				}
				if(count == allTickets.size() - 1){
					ticket.setTicketId(ticketId);
				}
				count ++;
			}
			
			
		} catch (ParserConfigurationException | SAXException | IOException e1) {
			e1.printStackTrace();
		}
		
		ticket.setSeatLocation(row + column);
		
		ticket.setTiketType(ticketType);
		
		List<Film> films = null;
		try {
			films = FilmDomParser.getFilms();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Film film : films){
			if(filmName.equals(film.getFilmName()))
				ticketPrice = film.getFilmPrice();
		}
		
		if("child".equals(ticketType)){
			ticket.setTicketPrice(ticketPrice*0.5f);
		}else if("senior".equals(ticketType)){
			ticket.setTicketPrice(ticketPrice*0.2f);
		}else if("student".equals(ticketType)){
			ticket.setTicketPrice(ticketPrice*0.15f);
		}else{
			ticket.setTicketPrice(ticketPrice);
		}
		ticket.setTimeStr(timeStr);
		ticket.setStudentId(studentId);
		tickets.add(ticket);
	}
	/**
	 * To check what type of the ticket and the student information.
	 * @param ticketType The type of ticket.
	 * @param button The current button.
	 * @param row The row of current seat.
	 * @param column The column of current seat.
	 * @param seatSelectDialog The current dialog of selecting seat.
	 */
	private void typeInfoCheck(String ticketType, JButton button,String row, int column, SeatTypeSelectDialog seatSelectDialog){
		if("student".equals(ticketType)){
			StudentInfoCheckDialog studentInfoCheckDialog = new StudentInfoCheckDialog(frame, button.getX(), button.getY());
			studentInfoCheckDialog.setAlwaysOnTop(true);
			studentInfoCheckDialog.setVisible(true);
			studentInfoCheckDialog.setStudentInfoCheckDialogListener(new StudentInfoCheckDialogListener() {
				
				@Override
				public void onOkClicked(String studentName, String studentNum) {
					
					List<Student> students = null;
					try {
						students = StudentDomParser.getStudents();
					} catch (ParserConfigurationException | SAXException | IOException e) {
						e.printStackTrace();
					}
					int i = 0;
					for(Student student : students){
						if(studentName.equals(student.getName())){
							if(studentNum.equals(student.getStudentNum())){
								studentInfoCheckDialog.dispose();
								button.setEnabled(false);
								try {
									button.setUI(new SeatSelectedButtonUI(column + ""){
										@Override
										public void paint(Graphics g, JComponent c) {
											Graphics2D graphics2d = (Graphics2D) g;
											graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
											graphics2d.drawImage(selectedImg, 0, 0, 60, 53, null);
										}		
									});
								} catch (IOException e) {
									e.printStackTrace();
								}
								makeTicket(ticketType, row, column, studentNum);
								seatSelectDialog.dispose();
								return;
							}
						}
						if((students.size() - 1) == i){
							StudentInfoCheckHintDialog studentInfoCheckHintDialog = new StudentInfoCheckHintDialog(frame, button.getX(),button.getY());
							studentInfoCheckHintDialog.setAlwaysOnTop(true);
							studentInfoCheckHintDialog.setVisible(true);
							studentInfoCheckHintDialog.setStudentInfoCheckHintDialogListener(new StudentInfoCheckHintDialogListener() {

								@Override
								public void onCancelClicked() {
									studentInfoCheckHintDialog.dispose();
								}
							});
							return;
						}
						i++;
					}
					
				}
				
				@Override
				public void onCancelClicked() {
					studentInfoCheckDialog.dispose();
				}
			});
		}else{
			button.setEnabled(false);
			try {
				button.setUI(new SeatSelectedButtonUI(column + ""){
					@Override
					public void paint(Graphics g, JComponent c) {
						Graphics2D graphics2d = (Graphics2D) g;
						graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
						graphics2d.drawImage(selectedImg, 0, 0, 60, 53, null);
					}		
				});
			} catch (IOException e) {
				e.printStackTrace();
			}
			makeTicket(ticketType, row, column, " ");
			seatSelectDialog.dispose();
		}
	}
	/**
	 * Initialize the data of Screen1SeatSelectPanel.
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	private void initData() throws IOException, ParserConfigurationException, SAXException {
		
		tickets = new ArrayList<Ticket>();
		
		movImg = ImageIO.read(new FileInputStream("././././resource/" + filmName + ".jpg"));
		selectingImg = ImageIO.read(new FileInputStream("././././resource/" + "0" + ".png"));
		selectedImg = ImageIO.read(new FileInputStream("././././resource/" + "s0" + ".png"));
		
		setLayout(null);
		
		List<Screen> screens = ScreenSeatDomParser.getScreen("screen1At" + timeStr.split(":")[0] + "_" + timeStr.split(":")[1] + ".xml");
		for(Screen screen : screens){
			String row = screen.getSeatId().substring(0, 1);
			int column = Integer.parseInt(screen.getSeatId().substring(1, 2));
			if("D".equals(row)){
				JButton button = new JButton();
				if("true".equals(screen.getSeatIsEmpty())){
					button.setUI(new SeatSelectingButtonUI(column + ""));
					button.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							SeatTypeSelectDialog seatSelectDialog = new SeatTypeSelectDialog(frame,button.getX(), button.getY());							
							seatSelectDialog.setAlwaysOnTop(true);
							seatSelectDialog.setVisible(true);
							seatSelectDialog.setSeatSelectDialogListener(new SeatSelectDialogListener() {
								@Override
								public void onCancelClicked() {
									seatSelectDialog.dispose();
								}

								@Override
								public void onSeatSelectClicked(String ticketType) {
									typeInfoCheck(ticketType, button, "D", column, seatSelectDialog);
								}
							});
						}
					});
				}else if("false".equals(screen.getSeatIsEmpty())){
					button.setUI(new SeatSelectedButtonUI(column + ""));
					button.setEnabled(false);
				}
				if(column <= 4){
					button.setBounds(920 - 61*column, 120, 60, 53);
				}else if(column > 4){
					button.setBounds(880 - 61*column, 120, 60, 53);
				}
				
				add(button);
			}else if("C".equals(row)){
				JButton button = new JButton();
				if("true".equals(screen.getSeatIsEmpty())){
					button.setUI(new SeatSelectingButtonUI(column + ""));
					button.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							SeatTypeSelectDialog seatSelectDialog = new SeatTypeSelectDialog(frame,button.getX(), button.getY());
							seatSelectDialog.setAlwaysOnTop(true);
							seatSelectDialog.setVisible(true);
							seatSelectDialog.setSeatSelectDialogListener(new SeatSelectDialogListener() {
								@Override
								public void onCancelClicked() {
									seatSelectDialog.dispose();
								}

								@Override
								public void onSeatSelectClicked(String ticketType) {
									typeInfoCheck(ticketType, button, "C", column, seatSelectDialog);
								}
							});
						}
					});
				}else if("false".equals(screen.getSeatIsEmpty())){
					button.setUI(new SeatSelectedButtonUI(column + ""));
				}
				if(column <= 4){
					button.setBounds(920 - 61*column, 210, 60, 53);
				}else if(column > 4){
					button.setBounds(880 - 61*column, 210, 60, 53);
				}
				add(button);
			}else if("B".equals(row)){
				JButton button = new JButton();
				if("true".equals(screen.getSeatIsEmpty())){
					button.setUI(new SeatSelectingButtonUI(column + ""));
					button.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							SeatTypeSelectDialog seatSelectDialog = new SeatTypeSelectDialog(frame,button.getX(), button.getY());
							seatSelectDialog.setAlwaysOnTop(true);
							seatSelectDialog.setVisible(true);
							seatSelectDialog.setSeatSelectDialogListener(new SeatSelectDialogListener() {
								@Override
								public void onCancelClicked() {
									seatSelectDialog.dispose();
								}

								@Override
								public void onSeatSelectClicked(String ticketType) {
									typeInfoCheck(ticketType, button, "B", column, seatSelectDialog);
								}
							});
						}
					});
				}else if("false".equals(screen.getSeatIsEmpty())){
					button.setUI(new SeatSelectedButtonUI(column + ""));
				}
				if(column <= 4){
					button.setBounds(920 - 61*column, 300, 60, 53);
				}else if(column > 4){
					button.setBounds(880 - 61*column, 300, 60, 53);
				}
				add(button);
			}else if("A".equals(row)){
				JButton button = new JButton();
				if("true".equals(screen.getSeatIsEmpty())){
					button.setUI(new SeatSelectingButtonUI(column + ""));
					button.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							SeatTypeSelectDialog seatSelectDialog = new SeatTypeSelectDialog(frame,button.getX(),button.getY());
							seatSelectDialog.setAlwaysOnTop(true);
							seatSelectDialog.setVisible(true);
							seatSelectDialog.setSeatSelectDialogListener(new SeatSelectDialogListener() {
								@Override
								public void onCancelClicked() {
									seatSelectDialog.dispose();
								}

								@Override
								public void onSeatSelectClicked(String ticketType) {
									typeInfoCheck(ticketType, button, "A", column, seatSelectDialog);
								}
							});
						}
					});
				}else if("false".equals(screen.getSeatIsEmpty())){
					button.setUI(new SeatSelectedButtonUI(column + ""));
				}
				if(column <= 4){
					button.setBounds(920 - 61*column, 390, 60, 53);
				}else if(column > 4){
					button.setBounds(880 - 61*column, 390, 60, 53);
				}
				add(button);
			}
		}
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Arial", Font.BOLD, 20));
		btnBack.setForeground(Color.WHITE);
		btnBack.setOpaque(true);
		btnBack.setBounds(50, 365, 154, 48);
		btnBack.setUI(new ClientNormalButtonUI());
		btnBack.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				listener.onBtnBackClicked();
			}
			
		});
		add(btnBack);
		
		JButton btnCart = new JButton("Cart");
		btnCart.setFont(new Font("Arial", Font.BOLD, 20));
		btnCart.setForeground(Color.WHITE);
		btnCart.setOpaque(true);
		btnCart.setBounds(50, 435, 154, 48);
		btnCart.setUI(new ClientNormalButtonUI());
		btnCart.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(tickets.isEmpty()){
					return;
				}
				
				CartDialog cartDialog = new CartDialog(tickets, movImg, frame.getX(), frame.getY());
				cartDialog.setVisible(true);
				cartDialog.setAlwaysOnTop(true);
				cartDialog.setCartDialogListener(new CartDialogListener() {
					
					@Override
					public void onCancelClicked() {
						// TODO Auto-generated method stub
						tickets.clear();
						cartDialog.dispose();
						listener.repaintPanel(filmName, "1", timeStr);
					}
				});
			}
		});
		add(btnCart);
		
	}
	/**
	 * Override the method of void paint(Graphics g).
	 */
	@Override
	public void paint(Graphics g) {
		Graphics2D graphics2d = (Graphics2D) g;
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		graphics2d.drawImage(movImg, 30, 10, 200, 296, null);
		graphics2d.drawString("Screen 1 at " + timeStr, 70, 340);
		graphics2d.drawLine(300, 5, 300, 530);
		graphics2d.setFont(new Font("Arial", Font.BOLD, 18));
		graphics2d.drawString("SEAT SELECT", 570, 60);
		
		graphics2d.setFont(new Font("Arial", Font.PLAIN, 18));
		graphics2d.drawString("D", 350, 160);
		graphics2d.drawString("C", 350, 250);
		graphics2d.drawString("B", 350, 340);
		graphics2d.drawString("A", 350, 430);
		
		graphics2d.drawLine(550, 490, 763, 490);
		graphics2d.setFont(new Font("Arial", Font.ITALIC, 15));
		graphics2d.drawString("screen", 630, 508);
		graphics2d.drawLine(550, 515, 763, 515);
		
		graphics2d.drawImage(selectingImg, 805, 480, 30, 27, null);
		graphics2d.drawString("selecting seat", 840, 500);
		graphics2d.drawImage(selectedImg, 805, 520, 30, 27, null);
		graphics2d.drawString("selected seat", 840, 540);
		
		super.paint(graphics2d);
		
	}
	
	
}
