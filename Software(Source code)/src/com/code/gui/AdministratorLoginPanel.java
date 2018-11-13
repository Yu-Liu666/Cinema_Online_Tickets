package com.group99.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicButtonUI;
/**
 * This is the boundary class of panel of logging in by administrator.
 * @author group 99.
 *
 */
public class AdministratorLoginPanel extends JPanel {
	
	private AdministratorLoginPanelListener listener;
	private boolean isErrorHint;
	private String id;
	private String password;
	/**
	 * This is the callback interface of administrator's logging in panel.
	 * @author group 99
	 *
	 */
	public interface AdministratorLoginPanelListener{
		public void onBackClicked();
		public void onLoginCicked(String id, String password);
	}
	/**
	 * Set AdministratorLoginPanel's listener.
	 * @param listener
	 */
	public void setAdministratorLoginPanelListener(AdministratorLoginPanelListener listener){
		this.listener = listener;
	}
	/**
	 * This is the constructor of AdministratorLoginPanel.
	 * @param id The id of administrator.
	 * @param password The password of administrator's account.
	 * @param isErrorHint Whether the panel generates error message or not.
	 */
	public AdministratorLoginPanel(String id, String password, boolean isErrorHint) {
		this.id = id;
		this.password = password;
		this.isErrorHint = isErrorHint;
		initData();
	}
	/**
	 * Initialize the data of AdministratorLoginPanel.
	 */
	private void initData() {
		
		setLayout(null);
		
		JTextField idTextField = new JTextField(15);
		idTextField.setBounds(310, 190, 330, 55);
		if(id == null){
			idTextField.setText("Your id...");
		}else{
			idTextField.setText(id);
		}
		idTextField.setFont(new Font("Arial", Font.PLAIN, 18));
		idTextField.setOpaque(false);
		add(idTextField);
		
		JTextField passwordTextField = new JTextField(15);
		passwordTextField.setBounds(310, 270, 330, 55);
		if(password == null){
			passwordTextField.setText("Your password...");
		}else{
			passwordTextField.setText(password);
		}
		passwordTextField.setFont(new Font("Arial", Font.PLAIN, 18));
		idTextField.setOpaque(false);
		add(passwordTextField);

		JButton backButton = new JButton("Back");
		backButton.setUI(new ClientNormalButtonUI());
		backButton.setForeground(Color.WHITE);
		backButton.setFont(new Font("Arial", Font.BOLD, 20));
		backButton.setBounds(270, 410, 155, 50);
		backButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				listener.onBackClicked();
			}
			
		});
		add(backButton);
		
		JButton loginButton = new JButton("Login");
		loginButton.setUI(new ClientNormalButtonUI());
		loginButton.setForeground(Color.WHITE);
		loginButton.setFont(new Font("Arial", Font.BOLD, 20));
		loginButton.setBounds(530, 410, 155, 50);
		loginButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				listener.onLoginCicked(idTextField.getText(), passwordTextField.getText());
			}
			
		});
		add(loginButton);
		
	}
	/**
	 * Override the method of void paint(Graphics g).
	 */
	@Override
	public void paint(Graphics g) {
		
		Graphics2D graphics2d = (Graphics2D) g;
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		graphics2d.setFont(new Font("Arial", Font.BOLD, 35));
		graphics2d.drawString("Administrator Login", 310, 80);
		
		if(isErrorHint){
			graphics2d.setColor(Color.RED);
			graphics2d.setFont(new Font("Arial", Font.PLAIN, 15));
			graphics2d.drawString("Your id or password is error !", 450, 350);
		}
		
		super.paint(graphics2d);
	}
	
}
