package com.group99.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.group99.javabean.Ticket;

/**
 * This is the boundary class of panel of cart dialog.
 * @author group 99
 *
 */
public class CartDialogPanel extends JPanel {
	
	private BufferedImage movImg;
	private List<Ticket> tickets;
	private float payAmount = 0;
	
	private CartDialogPanelListener listener;
	/**
	 * This is the callback interface of CartDialogPanel.
	 * @author group 99
	 *
	 */
	public interface CartDialogPanelListener{
		public void onReelectClicked();
		public void onPayClicked(float totalPrice);
	}
	/**
	 * Set CartDialogPanel's listener.
	 * @param listener A listener of CartDialogPanel.
	 */
	public void setCartDialogPanelListener(CartDialogPanelListener listener){
		this.listener = listener;
	}
	/**
	 * This is the constructor of CartDialogPanel.
	 * @param tickets A List of Ticket.
	 * @param movImg The Buffered Image of current movie.
	 */
	public CartDialogPanel(List<Ticket> tickets, BufferedImage movImg) {
	
		this.tickets = tickets;
		this.movImg = movImg;
		initData();
	}
	/**
	 * Initialize the data of CartDialogPanel.
	 */
	private void initData() {
		
		setLayout(null);
		
		JButton btnCancel = new JButton("Reelect");
		btnCancel.setFont(new Font("Arial", Font.BOLD, 15));
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setOpaque(true);
		btnCancel.setBounds(35, 286, 108, 34);
		btnCancel.setUI(new ClientNormalButtonUI());
		btnCancel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				listener.onReelectClicked();
			}
			
		});
		add(btnCancel);
		
		for(Ticket ticket : tickets){
			payAmount = payAmount + ticket.getTicketPrice();
		}
		
		JButton btnPay = new JButton("Pay");
		btnPay.setFont(new Font("Arial", Font.BOLD, 15));
		btnPay.setForeground(Color.WHITE);
		btnPay.setOpaque(true);
		btnPay.setBounds(35, 334, 108, 34);
		btnPay.setUI(new ClientNormalButtonUI());
		btnPay.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				listener.onPayClicked(payAmount);
			}
			
		});
		add(btnPay);
		
	}
	/**
	 * Override the method of void paintComponent(Graphics g).
	 */
	@Override
	public void paint(Graphics g) {
		
		Graphics2D graphics2d = (Graphics2D) g;
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2d.drawImage(movImg, 21, 37, 140, 207, null);
		
		graphics2d.drawLine(175, 40, 620, 40);
		graphics2d.setFont(new Font("Arial", Font.BOLD, 16));
		graphics2d.drawString("Ticket ID", 180, 60);
		graphics2d.drawString("Screen", 275, 60);
		graphics2d.drawString("Location", 355, 60);
		graphics2d.drawString("Time", 450, 60);
		graphics2d.drawString("Type", 510, 60);
		graphics2d.drawString("Price", 575, 60);
		graphics2d.drawLine(175, 70, 620, 70);
		
		int i = 0;
		float totalPrice = 0;
		graphics2d.setFont(new Font("Arial", Font.ITALIC, 15));
		for(Ticket ticket : tickets){
			totalPrice = totalPrice + ticket.getTicketPrice();
			graphics2d.drawString(ticket.getTicketId(), 180, 100 + (25*i));
			graphics2d.drawString(ticket.getScreenName(), 275, 100 + (25*i));
			graphics2d.drawString(ticket.getSeatLocation(), 377, 100 + (25*i));
			graphics2d.drawString(ticket.getTimeStr(), 450, 100 + (25*i));
			graphics2d.drawString(ticket.getTiketType(), 510, 100 + (25*i));
			graphics2d.drawString(String.format("%.2f",ticket.getTicketPrice()), 580, 100 + (25*i));
			i++;
		}
		graphics2d.drawLine(175, 90 + (25*i), 620, 90 + (25*i));
		graphics2d.setFont(new Font("Arial", Font.BOLD, 15));
		graphics2d.drawString("Total Price:  $ " + String.format("%.2f",totalPrice), 480, 110 + (25*i));
		super.paint(graphics2d);
		
	}
	
	
	
}
