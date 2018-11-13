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
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.group99.javabean.Ticket;

/**
 * This is the boundary class of panel of payment dialog.
 * @author group 99
 *
 */
public class PaymentDialogPanel extends JPanel {
	
	private BufferedImage movImg;
	private float payAmount = 0;
	private String whichHandle;
	
	public static final String ENTER_HANDLE = "enter interface";
	public static final String ERROR_HANDLE = "error interface";
	public static final String NSF_HANDLE = "not sufficient funds interface";
	public static final String ASK_HANDLE = "ask interface";
	public static final String SUCCESS_HANDLE = "successful payment interface";
	
	private PaymentDialogPanelListener listener;
	/**
	 * This is the callback interface of PaymentDialogPanel.
	 * @author group 99
	 *
	 */
	public interface PaymentDialogPanelListener{
		public void onConfirmClicked(String accountNum, String accountPassword, float payAmount);
		public void onCancelClicked();
	}
	/**
	 * Set PaymentDialogPanel's listener.
	 * @param listener A listener of PaymentDialogPanel.
	 */
	public void setPaymentDialogPanelListener(PaymentDialogPanelListener listener){
		this.listener = listener;
	}
	/**
	 * This is the constructor of PaymentDialogPanel.
	 * @param movImg The name of selecting movie.
	 * @param payAmount The pay amount.
	 * @param whichHandle	Which handle you want to do.
	 */
	public PaymentDialogPanel(BufferedImage movImg, float payAmount, String whichHandle) {
	
		this.movImg = movImg;
		this.payAmount = payAmount;
		this.whichHandle = whichHandle;
		initData();
	}
	/**
	 * Initialize the data of PaymentDialogPanel.
	 */
	private void initData() {
		
		setLayout(null);
		
		JButton btnCancel = new JButton("Back");
		btnCancel.setFont(new Font("Arial", Font.BOLD, 15));
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setOpaque(true);
		btnCancel.setBounds(35, 286, 108, 34);
		btnCancel.setUI(new ClientNormalButtonUI());
		btnCancel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				listener.onCancelClicked();
			}
			
		});
		add(btnCancel);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setFont(new Font("Arial", Font.BOLD, 15));
		btnConfirm.setForeground(Color.WHITE);
		btnConfirm.setOpaque(true);
		btnConfirm.setBounds(35, 334, 108, 34);
		btnConfirm.setUI(new ClientNormalButtonUI());
		
		if("enter interface".equals(whichHandle)){
			
			JTextField idTextField = new JTextField(10);;
			JTextField passwordTextField = new JTextField(10);
			
			idTextField.setBounds(250, 150, 300, 40);
			idTextField.setText("Your account id...");
			idTextField.setOpaque(false);
			add(idTextField);
			
			passwordTextField.setBounds(250, 250, 300, 40);
			passwordTextField.setText("Your account password...");
			passwordTextField.setOpaque(false);
			add(passwordTextField);
			
			btnConfirm.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					listener.onConfirmClicked(idTextField.getText(), passwordTextField.getText(), payAmount);
				}
				
			});
			add(btnConfirm);
		}else if("ask interface".equals(whichHandle)){
			btnConfirm.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					listener.onConfirmClicked(null, null, payAmount);
				}
				
			});
			add(btnConfirm);
		}
		
	}
	/**
	 * Override the method of void paint(Graphics g).
	 */
	@Override
	public void paint(Graphics g) {
		
		Graphics2D graphics2d = (Graphics2D) g;
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2d.drawImage(movImg, 21, 37, 140, 207, null);
		
		if("enter interface".equals(whichHandle)){
			graphics2d.setFont(new Font("Arial", Font.BOLD, 20));
			graphics2d.drawString("Bank System", 330, 80);
		}else if("error interface".equals(whichHandle)){
			graphics2d.setFont(new Font("Arial", Font.BOLD, 21));
			graphics2d.drawString("Your account name or password is error!", 200, 220);
		}else if("not sufficient funds interface".equals(whichHandle)){
			graphics2d.setFont(new Font("Arial", Font.BOLD, 23));
			graphics2d.drawString("Your balance is insufficient!", 245, 220);
		}else if("ask interface".equals(whichHandle)){
			graphics2d.setFont(new Font("Arial", Font.BOLD, 23));
			graphics2d.drawString("Confirm the payment ($ " + String.format("%.2f",payAmount) + ")?", 230, 220);
		}else if("successful payment interface".equals(whichHandle)){
			graphics2d.setFont(new Font("Arial", Font.BOLD, 23));
			graphics2d.drawString("Payment Status: pay success", 245, 220);
		}
		
		
		super.paint(graphics2d);
	}
	
	
	
}
