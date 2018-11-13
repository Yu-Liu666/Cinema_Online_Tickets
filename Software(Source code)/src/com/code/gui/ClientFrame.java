package com.group99.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import com.group99.dom.AdministratorDomParser;
import com.group99.dom.ScreenSeatDomParser;
import com.group99.gui.AdministratorLoginPanel.AdministratorLoginPanelListener;
import com.group99.gui.AdministratorMenuPanel.AdministratorMenuPanelListener;
import com.group99.gui.InfoPanel.InfoPanelListener;
import com.group99.gui.ModifyInfoPanel.ModifyInfoPanelListener;
import com.group99.gui.ModifyMenuPanel.ModifyMenuPanelListener;
import com.group99.gui.ModifyPanel.ModifyPanelListener;
import com.group99.gui.MoviesIntroPanel.MoviesIntroPanelListener;
import com.group99.gui.Screen1SeatSelectPanel.Screen1SeatSelectPanelListener;
import com.group99.gui.Screen2SeatSelectPanel.Screen2SeatSelectPanelListener;
import com.group99.gui.Screen3SeatSelectPanel.Screen3SeatSelectPanelListener;
import com.group99.gui.ScreenSelectPanel.ScreenSelectPanelListener;
import com.group99.gui.StatisticsPanel.StatisticsPanelListener;
import com.group99.gui.WelcomePanel.WelcomePanelListener;
import com.group99.javabean.Administrator;
import com.group99.javabean.Screen;

import javax.swing.AbstractButton;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.util.List;
import java.awt.Font;
import javax.swing.JLabel;
/**
 * This is the control class of kiosk's client.
 */
public class ClientFrame extends JFrame {

	private ClientBackgroundPanel contentPane;
	private int mouseX, mouseY, jFrameX, jFrameY;
	public static ClientFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ClientFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create kiosk's client.
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public ClientFrame() throws ParserConfigurationException, SAXException, IOException {
	
		setUndecorated(true);
		setBackground(new Color(0,0,0,0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 600);
		
		
		
		contentPane = new ClientBackgroundPanel("Client Frame");
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				setLocation(jFrameX + (e.getXOnScreen() -mouseX), jFrameY + (e.getYOnScreen() -mouseY));
			}
		});
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getXOnScreen();
				mouseY = e.getYOnScreen();
				jFrameX = getX();
				jFrameY = getY();
			}
			
		});
		contentPane.setOpaque(false);
		setContentPane(contentPane);

		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(880, 3, 69, 24);
		btnExit.setUI(new ClientSpecialButtonUI(){

			@Override
			protected void paintButtonPressed(Graphics g, AbstractButton b) {
				g.setColor(new Color(250, 0, 0, 200));
				g.fillRoundRect(0, 0, b.getWidth(), b.getHeight() + 5, 10, 10);
			}
			
		});
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		
		JButton btnMin = new JButton("Min");
		btnMin.setBounds(800, 3, 69, 24);
		btnMin.setUI(new ClientSpecialButtonUI());
		btnMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setExtendedState(JFrame.ICONIFIED);
			}
		});

		contentPane.setLayout(null);
		contentPane.add(btnMin);
		contentPane.add(btnExit);
		
		showWelcomePanel();
	}
	/**
	 * Display the layout of certain screen selected by customers and customers can also buy tickets by clicking related seats.
	 * @param movName The name of movie selected by customers.
	 * @param screenName The screen name selected by customers.
	 * @param timeStr The time selected by customers.
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	public void showScreenSeatSelectPanel(String movName, String screenName,String timeStr) throws IOException, ParserConfigurationException, SAXException{
		
		if("1".equals(screenName)){
			Screen1SeatSelectPanel screen1SeatSelectPanel = new Screen1SeatSelectPanel(movName, timeStr, frame);
			screen1SeatSelectPanel.setBounds(5, 35, 956, 562);
			screen1SeatSelectPanel.setOpaque(false);
			screen1SeatSelectPanel.setScreen1SeatSelectPanelListener(new Screen1SeatSelectPanelListener() {
				
				@Override
				public void onBtnBackClicked() {
					contentPane.remove(screen1SeatSelectPanel);
					contentPane.repaint();
					try {
						showScreenSelectPanel(movName, frame);
					} catch (ParserConfigurationException e) {
						e.printStackTrace();
					} catch (SAXException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				@Override
				public void repaintPanel(String movName, String screenName, String timeStr) {
					contentPane.remove(screen1SeatSelectPanel);
					contentPane.repaint();
					try {
						showScreenSeatSelectPanel(movName, screenName, timeStr);
					} catch (IOException | ParserConfigurationException | SAXException e) {
						e.printStackTrace();
					}
				}
			});
			contentPane.add(screen1SeatSelectPanel);
		}else if("2".equals(screenName)){
			Screen2SeatSelectPanel screen2SeatSelectPanel = new Screen2SeatSelectPanel(movName, timeStr, frame);
			screen2SeatSelectPanel.setBounds(5, 35, 956, 562);
			screen2SeatSelectPanel.setOpaque(false);
			screen2SeatSelectPanel.setScreen2SeatSelectPanelListener(new Screen2SeatSelectPanelListener() {		
				@Override
				public void onBtnBackClicked() {
					contentPane.remove(screen2SeatSelectPanel);
					contentPane.repaint();
					try {
						showScreenSelectPanel(movName, frame);
					} catch (ParserConfigurationException e) {
						e.printStackTrace();
					} catch (SAXException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				@Override
				public void repaintPanel(String movName, String screenName, String timeStr) {
					contentPane.remove(screen2SeatSelectPanel);
					contentPane.repaint();
					try {
						showScreenSeatSelectPanel(movName, screenName, timeStr);
					} catch (IOException | ParserConfigurationException | SAXException e) {
						e.printStackTrace();
					}
				}
			});
			contentPane.add(screen2SeatSelectPanel);
		}else if("3".equals(screenName)){
			Screen3SeatSelectPanel screen3SeatSelectPanel = new Screen3SeatSelectPanel(movName, timeStr, frame);
			screen3SeatSelectPanel.setBounds(5, 35, 956, 562);
			screen3SeatSelectPanel.setOpaque(false);
			screen3SeatSelectPanel.setScreen3SeatSelectPanelListener(new Screen3SeatSelectPanelListener() {		
				@Override
				public void onBtnBackClicked() {
					contentPane.remove(screen3SeatSelectPanel);
					contentPane.repaint();
					try {
						showScreenSelectPanel(movName, frame);
					} catch (ParserConfigurationException e) {
						e.printStackTrace();
					} catch (SAXException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				@Override
				public void repaintPanel(String movName, String screenName, String timeStr) {
					contentPane.remove(screen3SeatSelectPanel);
					contentPane.repaint();
					try {
						showScreenSeatSelectPanel(movName, screenName, timeStr);
					} catch (IOException | ParserConfigurationException | SAXException e) {
						e.printStackTrace();
					}
				}
			});
			contentPane.add(screen3SeatSelectPanel);
		}
	
	}
	
	/**
	 * Display timetable of films, the screen and related time will be selected by customers.
	 * @param selectName The name of film selected by customers.
	 * @param frame Current frame.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public void showScreenSelectPanel(String selectName,JFrame frame) throws ParserConfigurationException, SAXException, IOException{
		ScreenSelectPanel screenSelectPanel = new ScreenSelectPanel(selectName, frame);
		screenSelectPanel.setBounds(5, 35, 956, 562);
		screenSelectPanel.setOpaque(false);
		
		screenSelectPanel.setScreenSelectPanelListener(new ScreenSelectPanelListener() {
			
			@Override
			public void onBtnBackClicked() {
				contentPane.remove(screenSelectPanel);
				contentPane.repaint();
				try {
					showMoviesIntroPanel();
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				} catch (SAXException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onTimeSelectClicked(String screenName, String timeStr) {
				try {
					contentPane.remove(screenSelectPanel);
					contentPane.repaint();
					showScreenSeatSelectPanel(selectName,screenName,timeStr);
				} catch (IOException | ParserConfigurationException | SAXException e) {
					e.printStackTrace();
				}
			}
		});
		
		contentPane.add(screenSelectPanel);
	}
	/**
	 * Display detailed information of films.
	 * @param selectedMovieName The name of movie selected by customers.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public void showInfoPanel(String selectedMovieName) throws ParserConfigurationException, SAXException, IOException{
		InfoPanel infoPanel = new InfoPanel(selectedMovieName);
		infoPanel.setBounds(5, 35, 956, 562);
		infoPanel.setOpaque(false);
		
		infoPanel.setInfoPanelListener(new InfoPanelListener() {
			
			@Override
			public void onBtnPurchaseClicked() {
				contentPane.remove(infoPanel);
				contentPane.repaint();
				try {
					showScreenSelectPanel(selectedMovieName, frame);
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				} catch (SAXException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			@Override
			public void onBtnBackClicked() {
				contentPane.remove(infoPanel);
				contentPane.repaint();
				try {
					showMoviesIntroPanel();
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				} catch (SAXException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		contentPane.add(infoPanel);
	}
	/**
	 * Display brief information of films and select certain movies.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public void showMoviesIntroPanel() throws ParserConfigurationException, SAXException, IOException{
		MoviesIntroPanel moviesInroPanel = new MoviesIntroPanel();
		moviesInroPanel.setBounds(5, 35, 956, 562);
		moviesInroPanel.setOpaque(false);
		moviesInroPanel.setMoviesIntroPanelListener(new MoviesIntroPanelListener(){
			@Override
			public void onImageClicked(String filmName) {
				contentPane.remove(moviesInroPanel);
				contentPane.repaint();
				try {
					showInfoPanel(filmName);
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				} catch (SAXException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onHomeClicked() {
				contentPane.remove(moviesInroPanel);
				contentPane.repaint();
				showWelcomePanel();
			}
		});
		contentPane.add(moviesInroPanel);
		
	}
	/**
	 * Set all seats in all screens to empty.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws TransformerException
	 */
	public void resetSeat() throws ParserConfigurationException, SAXException, IOException, TransformerException{
		
		String[] seatFilesName = {
				"screen1At10_00.xml","screen1At12_30.xml","screen1At15_30.xml","screen1At18_30.xml","screen1At21_00.xml",
				"screen2At10_30.xml","screen2At13_00.xml","screen2At16_00.xml","screen2At18_00.xml","screen2At20_00.xml",
				"screen3At10_30.xml","screen3At13_00.xml","screen3At15_30.xml","screen3At18_00.xml","screen3At20_30.xml",
				};
		
		for(int i = 0; i < seatFilesName.length; i ++){
			List<Screen> screens1 = ScreenSeatDomParser.getScreen(seatFilesName[i]);
			for (Screen screen : screens1) {
				ScreenSeatDomParser.updateScreenSeat(seatFilesName[i], screen.getSeatId(), "true");
			}
		}
		
	}
	/**
	 * Display the information of modifying films.
	 * @param selectedMovieName The name of movie whose information is updated.
	 */
	public void showModifyInfoPanel(String selectedMovieName){
		try {
			ModifyInfoPanel modifyInfoPanel = new ModifyInfoPanel(selectedMovieName);
			modifyInfoPanel.setOpaque(false);
			modifyInfoPanel.setBounds(5, 35, 956, 562);
			modifyInfoPanel.setModifyInfoPanelListener(new ModifyInfoPanelListener() {
				@Override
				public void onBackClicked() {
					contentPane.remove(modifyInfoPanel);
					contentPane.repaint();
					showModifyPanel();
				}
			});
			contentPane.add(modifyInfoPanel);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * Display several movies and select which film information will be updated.
	 */
	public void showModifyPanel() {
		try {
			ModifyPanel modifyPanel = new ModifyPanel();
			modifyPanel.setOpaque(false);
			modifyPanel.setBounds(5, 35, 956, 562);
			modifyPanel.setModifyPanelListener(new ModifyPanelListener() {
				
				@Override
				public void onImageClicked(String filmName) {
					contentPane.remove(modifyPanel);
					contentPane.repaint();
					showModifyInfoPanel(filmName);
				}
				
				@Override
				public void onBackClicked() {
					contentPane.remove(modifyPanel);
					contentPane.repaint();
					try {
						showModifyMenuPanel();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			contentPane.add(modifyPanel);
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * Display the menu of modifying mode.
	 * @throws IOException
	 */
	public void showModifyMenuPanel() throws IOException{
		ModifyMenuPanel modifyMenuPanel = new ModifyMenuPanel();
		modifyMenuPanel.setOpaque(false);
		modifyMenuPanel.setBounds(5, 35, 956, 562);
		modifyMenuPanel.setModifyMenuPanelListener(new ModifyMenuPanelListener() {
			
			@Override
			public void onBackClicked() {
				contentPane.remove(modifyMenuPanel);
				contentPane.repaint();
				try {
					showAdministratorMenuSelect();
				} catch (ParserConfigurationException | SAXException | IOException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onResetSeatClicked() {
				try {
					resetSeat();
				} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
					e.printStackTrace();
				}
				
				JLabel resetSucessLabel = new JLabel("Reset successfully !");
				resetSucessLabel.setFont(new Font("Arial", Font.PLAIN, 15));
				resetSucessLabel.setOpaque(false);
				resetSucessLabel.setForeground(Color.RED);
				resetSucessLabel.setBounds(410, 430, 180, 20);
				modifyMenuPanel.add(resetSucessLabel);
				modifyMenuPanel.repaint();
			}

			@Override
			public void onModifyFilmInfoClicked() {
				contentPane.remove(modifyMenuPanel);
				contentPane.repaint();
				showModifyPanel();
			}
		});
		add(modifyMenuPanel);
	}
	/**
	 * Display the statistical report.
	 * @throws IOException
	 */
	public void showStatisticsPanel() throws IOException{
		StatisticsPanel statisticsPanel = new StatisticsPanel();
		statisticsPanel.setOpaque(false);
		statisticsPanel.setBounds(5, 35, 956, 562);
		statisticsPanel.setStatisticsPanelListener(new StatisticsPanelListener() {
			
			@Override
			public void onBackClicked() {
				contentPane.remove(statisticsPanel);
				contentPane.repaint();
				try {
					showAdministratorMenuSelect();
				} catch (ParserConfigurationException | SAXException | IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		add(statisticsPanel);
	}
	/**
	 * The main menu of administrator mode.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public void showAdministratorMenuSelect() throws ParserConfigurationException, SAXException, IOException{
		AdministratorMenuPanel administratorMenuPanel = new AdministratorMenuPanel();
		administratorMenuPanel.setBounds(5, 35, 956, 562);
		administratorMenuPanel.setOpaque(false);
		administratorMenuPanel.setAdministratorMenuPanelListener(new AdministratorMenuPanelListener() {
			
			@Override
			public void onUpdateClicked() {
				contentPane.remove(administratorMenuPanel);
				contentPane.repaint();
				try {
					showModifyMenuPanel();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			@Override
			public void onStatisticsClicked() {
				contentPane.remove(administratorMenuPanel);
				contentPane.repaint();
				try {
					showStatisticsPanel();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onHomeClicked() {
				contentPane.remove(administratorMenuPanel);
				contentPane.repaint();
				showWelcomePanel();
			}
		});
		
		contentPane.add(administratorMenuPanel);
	}
	/**
	 * Display the interface of logging in by administrator. 
	 * @param id The id of administrator.
	 * @param password The password of administrator's account.
	 * @param isErrorHint Whether the panel generate error message or not.
	 */
	public void showAdministratorLogin(String id, String password, boolean isErrorHint) {
		AdministratorLoginPanel administratorLoginPanel = new AdministratorLoginPanel(id, password, isErrorHint);
		administratorLoginPanel.setBounds(5, 35, 956, 562);
		administratorLoginPanel.setOpaque(false);
		administratorLoginPanel.setAdministratorLoginPanelListener(new AdministratorLoginPanelListener() {
			
			@Override
			public void onLoginCicked(String id, String password) {
				try {
					List<Administrator> administrators = AdministratorDomParser.getAdministrators();
					int count = 0;
					for(Administrator administrator : administrators){
						if(id.equals(administrator.getId())){
							if(password.equals(administrator.getPassword())){
								contentPane.remove(administratorLoginPanel);
								contentPane.repaint();
								showAdministratorMenuSelect();
								return;
							}
						}
						if(count == administrators.size() - 1){
							contentPane.remove(administratorLoginPanel);
							contentPane.repaint();
							showAdministratorLogin(id, password, true);
							return;
						}
						count++;
					}
				} catch (ParserConfigurationException | SAXException | IOException e) {
					e.printStackTrace();
				}
			}
			
			@Override
			public void onBackClicked() {
				contentPane.remove(administratorLoginPanel);
				contentPane.repaint();
				showWelcomePanel();
			}
		});
		
		contentPane.add(administratorLoginPanel);
	}
	/**
	 * Display the welcome interface. Customer mode or Administrator mode can be selected. 
	 */
	public void showWelcomePanel() {
		WelcomePanel welcomePanel = new WelcomePanel();
		welcomePanel.setBounds(5, 35, 956, 562);
		welcomePanel.setOpaque(false);
		welcomePanel.setWelcomePanelListener(new WelcomePanelListener() {
			
			@Override
			public void onCustomerClicked() {
				contentPane.remove(welcomePanel);
				contentPane.repaint();
				try {
					showMoviesIntroPanel();
				} catch (ParserConfigurationException | SAXException | IOException e) {
					e.printStackTrace();
				}
			}
			
			@Override
			public void onAdministratorClicked() {
				contentPane.remove(welcomePanel);
				contentPane.repaint();
				showAdministratorLogin(null, null, false);
			}
		});
		
		contentPane.add(welcomePanel);
	}
}
