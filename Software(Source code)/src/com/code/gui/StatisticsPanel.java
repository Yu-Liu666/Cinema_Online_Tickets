package com.group99.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.group99.dom.TicketDomParser;
import com.group99.javabean.Ticket;
/**
 * This is the boundary class of panel of statistics of ticket.
 * @author group 99
 *
 */
public class StatisticsPanel extends JPanel {

	BufferedImage image1, image2, image3, image4, image5;
	int totalSalesOfLA, totalSalesOfKO, totalSalesOfBE, totalSalesOfLO, totalSalesOfMO;
	int totalSalesOfChild, totalSalesOfAdult, totalSalesOfSenior, totalSalesOfStudent;
	float totalProfitOfLA, totalProfitOfKO, totalProfitOfBE, totalProfitOfLO, totalProfitOfMO;
	float totalProfitOfChild, totalProfitOfAdult, totalProfitOfSenior, totalProfitOfStudent;
	int totalSales;
	float totalProfit;

	private StatisticsPanelListener listener;
	/**
	 * This is the callback interface of StatisticsPanel.
	 * @author group 99
	 *
	 */
	public interface StatisticsPanelListener {
		public void onBackClicked();
	}
	/**
	 * Set StatisticsPanel's listener.
	 * @param listener A listener of StatisticsPanel.
	 */
	public void setStatisticsPanelListener(StatisticsPanelListener listener) {
		this.listener = listener;
	}
	/**
	 * This is the constructor of StatisticsPanel.
	 * @throws IOException
	 */
	public StatisticsPanel() throws IOException {
		initData();
	}
	/**
	 * Initialize the data of StatisticsPanel.
	 * @throws IOException
	 */
	private void initData() throws IOException {

		image1 = ImageIO.read(new FileInputStream("././././resource/LA LA LAND.jpg"));
		image2 = ImageIO.read(new FileInputStream("././././resource/" + "KONG-SKULL ISLAND" + ".jpg"));
		image3 = ImageIO.read(new FileInputStream("././././resource/" + "BEAUTY AND THE BEAST" + ".jpg"));
		image4 = ImageIO.read(new FileInputStream("././././resource/" + "LOGAN" + ".jpg"));
		image5 = ImageIO.read(new FileInputStream("././././resource/" + "MOONLIGHT" + ".jpg"));

		try {
			List<Ticket> tickets = TicketDomParser.getTickets();
			for (Ticket ticket : tickets) {

				totalSales++;
				totalProfit = totalProfit + ticket.getTicketPrice();

				if ("LA LA LAND".equals(ticket.getFilmName())) {
					totalSalesOfLA++;
					totalProfitOfLA = totalProfitOfLA + ticket.getTicketPrice();

				}
				if ("KONG-SKULL ISLAND".equals(ticket.getFilmName())) {
					totalSalesOfKO++;
					totalProfitOfKO = totalProfitOfKO + ticket.getTicketPrice();

				}
				if ("BEAUTY AND THE BEAST".equals(ticket.getFilmName())) {
					totalSalesOfBE++;
					totalProfitOfBE = totalProfitOfBE + ticket.getTicketPrice();

				}
				if ("LOGAN".equals(ticket.getFilmName())) {
					totalSalesOfLO++;
					totalProfitOfLO = totalProfitOfLO + ticket.getTicketPrice();

				}
				if ("MOONLIGHT".equals(ticket.getFilmName())) {
					totalSalesOfMO++;
					totalProfitOfMO = totalProfitOfMO + ticket.getTicketPrice();

				}
				if ("child".equals(ticket.getTiketType())) {
					totalSalesOfChild++;
					totalProfitOfChild = totalProfitOfChild + ticket.getTicketPrice();

				}
				if ("adult".equals(ticket.getTiketType())) {
					totalSalesOfAdult++;
					totalProfitOfAdult = totalProfitOfAdult + ticket.getTicketPrice();

				}
				if ("senior".equals(ticket.getTiketType())) {
					totalSalesOfSenior++;
					totalProfitOfSenior = totalProfitOfSenior + ticket.getTicketPrice();

				}
				if ("student".equals(ticket.getTiketType())) {
					totalSalesOfStudent++;
					totalProfitOfStudent = totalProfitOfStudent + ticket.getTicketPrice();

				}
			}

		} catch (ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}

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

		BufferedImage emailImage = ImageIO.read(new FileInputStream("././././resource/email.png"));
		JButton emailButton = new JButton();
		emailButton.setBounds(73, 475, 60, 60);
		emailButton.setOpaque(false);
		emailButton.setUI(new BasicButtonUI() {

			@Override
			public void paint(Graphics g, JComponent c) {
				Graphics2D graphics2d = (Graphics2D) g;
				graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				graphics2d.drawImage(emailImage, 0, 0, 60, 60, null);
			}

		});
		emailButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				JLabel sendSucessLabel = new JLabel("Send successfully !");
				sendSucessLabel.setFont(new Font("Arial", Font.PLAIN, 15));
				sendSucessLabel.setOpaque(false);
				sendSucessLabel.setForeground(Color.RED);
				sendSucessLabel.setBounds(85, 530, 200, 20);
				add(sendSucessLabel);
				repaint();

				try {
					Calendar calendar = Calendar.getInstance();
					FileWriter fw = new FileWriter("./statisticsOf" + calendar.get(Calendar.YEAR) + "_" +  (calendar.get(Calendar.MONTH) + 1) + "_" + calendar.get(Calendar.DAY_OF_MONTH) +".txt");
					BufferedWriter bufw = new BufferedWriter(fw);
					bufw.write("———————————————————————————————————————————————————————————————————————————————————————————-");
					bufw.newLine();
					bufw.write("Film Name				" + "   Total Sales				" + "Total Profit");
					bufw.newLine();
					bufw.write("———————————————————————————————————————————————————————————————————————————————————————————-");
					bufw.newLine();
					bufw.write("LA LA LAND					" + totalSalesOfLA + "				   $ " + totalProfitOfLA );
					bufw.newLine();
					bufw.write("KONG-SKULL ISLAND				" + totalSalesOfKO + "				   $ " + totalProfitOfKO );
					bufw.newLine();
					bufw.write("BEAUTY AND THE BEAST				" + totalSalesOfBE + "				   $ " + totalProfitOfBE );
					bufw.newLine();
					bufw.write("LOGAN						" + totalSalesOfLO + "				   $ " + totalProfitOfLO );
					bufw.newLine();
					bufw.write("MOONLIGHT					" + totalSalesOfMO + "				   $ " + totalProfitOfMO );
					bufw.newLine();
					bufw.newLine();
					bufw.newLine();
					bufw.write("———————————————————————————————————————————————————————————————————————————————————————————-");
					bufw.newLine();
					bufw.write("Ticket Type				" + "   Total Sales				" + "Total Profit");
					bufw.newLine();
					bufw.write("———————————————————————————————————————————————————————————————————————————————————————————-");
					bufw.newLine();
					bufw.write("Adult						" + totalSalesOfAdult + "				   $ " + totalProfitOfAdult );
					bufw.newLine();
					bufw.write("Child						" + totalSalesOfChild + "				   $ " + totalProfitOfChild );
					bufw.newLine();
					bufw.write("Senior						" + totalSalesOfSenior + "				   $ " + totalProfitOfSenior );
					bufw.newLine();
					bufw.write("Student						" + totalSalesOfStudent + "				   $ " + totalProfitOfStudent );
					bufw.newLine();
					bufw.newLine();
					bufw.newLine();
					bufw.write("———————————————————————————————————————————————————————————————————————————————————————————-");
					bufw.newLine();
					bufw.write("         				" + "   Total Sales				" + "Total Profit");
					bufw.newLine();
					bufw.write("———————————————————————————————————————————————————————————————————————————————————————————-");
					bufw.newLine();
					bufw.write("     						" + totalSales + "				   $ " + totalProfit );
					bufw.newLine();
					bufw.flush();
					bufw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}

		});
		add(emailButton);

	}
	/**
	 * Override the method of void paint(Graphics g).
	 */
	@Override
	public void paint(Graphics g) {

		Graphics2D graphics2d = (Graphics2D) g;
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		graphics2d.setFont(new Font("Copperplate", Font.BOLD, 32));
		graphics2d.drawString("Statistics", 70, 50);

		graphics2d.drawImage(image4, 47, 130, 60, 89, null);
		graphics2d.drawImage(image1, 202, 130, 60, 89, null);
		graphics2d.drawImage(image3, 73, 170, 70, 104, null);
		graphics2d.drawImage(image5, 165, 170, 70, 104, null);
		graphics2d.drawImage(image2, 112, 210, 80, 119, null);
		graphics2d.drawLine(300, 5, 300, 530);

		graphics2d.setFont(new Font("Arial", Font.BOLD, 25));
		graphics2d.drawString("Email", 150, 513);

		graphics2d.drawLine(400, 40, 870, 40);
		graphics2d.setFont(new Font("Arial", Font.BOLD, 16));
		graphics2d.drawString("Film Name", 455, 60);
		graphics2d.drawString("Total Sales", 610, 60);
		graphics2d.drawString("Total Profit", 740, 60);
		graphics2d.drawLine(400, 70, 870, 70);

		graphics2d.setFont(new Font("Arial", Font.PLAIN, 13));
		graphics2d.drawString("LA LA LAND", 458, 100);
		graphics2d.drawString(String.valueOf(totalSalesOfLA), 650, 100);
		graphics2d.drawString("$ " + String.format("%.2f", totalProfitOfLA), 760, 100);
		graphics2d.drawString("KONG-SKULL ISLAND", 425, 130);
		graphics2d.drawString(String.valueOf(totalSalesOfKO), 650, 130);
		graphics2d.drawString("$ " + String.format("%.2f", totalProfitOfKO), 760, 130);
		graphics2d.drawString("BEAUTY AND THE BEAST", 415, 160);
		graphics2d.drawString(String.valueOf(totalSalesOfBE), 650, 160);
		graphics2d.drawString("$ " + String.format("%.2f", totalProfitOfBE), 760, 160);
		graphics2d.drawString("LOGAN", 470, 190);
		graphics2d.drawString(String.valueOf(totalSalesOfLO), 650, 190);
		graphics2d.drawString("$ " + String.format("%.2f", totalProfitOfLO), 760, 190);
		graphics2d.drawString("MOONLIGHT", 455, 220);
		graphics2d.drawString(String.valueOf(totalSalesOfMO), 650, 220);
		graphics2d.drawString("$ " + String.format("%.2f", totalProfitOfMO), 760, 220);

		graphics2d.drawLine(400, 270, 870, 270);
		graphics2d.setFont(new Font("Arial", Font.BOLD, 16));
		graphics2d.drawString("Ticket Type", 455, 290);
		graphics2d.drawString("Total Sales", 610, 290);
		graphics2d.drawString("Total Profit", 740, 290);
		graphics2d.drawLine(400, 300, 870, 300);

		graphics2d.setFont(new Font("Arial", Font.PLAIN, 15));
		graphics2d.drawString("Adult", 475, 330);
		graphics2d.drawString(String.valueOf(totalSalesOfAdult), 650, 330);
		graphics2d.drawString("$ " + String.format("%.2f", totalProfitOfAdult), 760, 330);
		graphics2d.drawString("Child", 475, 360);
		graphics2d.drawString(String.valueOf(totalSalesOfChild), 650, 360);
		graphics2d.drawString("$ " + String.format("%.2f", totalProfitOfChild), 760, 360);
		graphics2d.drawString("Senior", 472, 390);
		graphics2d.drawString(String.valueOf(totalSalesOfSenior), 650, 390);
		graphics2d.drawString("$ " + String.format("%.2f", totalProfitOfSenior), 760, 390);
		graphics2d.drawString("Student", 469, 420);
		graphics2d.drawString(String.valueOf(totalSalesOfStudent), 650, 420);
		graphics2d.drawString("$ " + String.format("%.2f", totalProfitOfStudent), 760, 420);

		graphics2d.drawLine(400, 460, 870, 460);
		graphics2d.setFont(new Font("Arial", Font.BOLD, 16));
		graphics2d.drawString("Total Sales", 610, 480);
		graphics2d.drawString("Total Profit", 740, 480);
		graphics2d.drawLine(400, 490, 870, 490);

		graphics2d.setFont(new Font("Arial", Font.PLAIN, 15));
		graphics2d.drawString(String.valueOf(totalSales), 650, 520);
		graphics2d.drawString("$ " + String.format("%.2f", totalProfit), 760, 520);

		super.paint(graphics2d);

	}

}
