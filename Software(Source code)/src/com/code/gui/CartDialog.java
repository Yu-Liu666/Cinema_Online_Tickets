package com.group99.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import com.group99.dom.BankAccountDomParser;
import com.group99.dom.ScreenSeatDomParser;
import com.group99.dom.TicketDomParser;
import com.group99.gui.CartDialogPanel.CartDialogPanelListener;
import com.group99.gui.PaymentDialogPanel.PaymentDialogPanelListener;
import com.group99.javabean.BankAccount;
import com.group99.javabean.Ticket;
/**
 * This is the control class of cart payment.
 * @author group 99
 *
 */
public class CartDialog extends JFrame {

	private ClientBackgroundPanel contentPane;
	private int mouseX, mouseY, jFrameX, jFrameY;
	private CartDialogListener listener;
	private int superFrameX, superFrameY;
	private BufferedImage movImg;
	private List<Ticket> tickets;
	/**
	 * This is the callback interface of cart dialog.
	 * @author group 99.
	 *
	 */
	public interface CartDialogListener {
		public void onCancelClicked();
	}
	/**
	 * Set CartDialog's listener.
	 * @param listener A listener of CartDialog.
	 */
	public void setCartDialogListener(CartDialogListener listener) {
		this.listener = listener;
	}
	/**
	 * This is the constructor of CartDialog.
	 * @param tickets A List of Ticket.
	 * @param movImg The Buffered Image of current movie.
	 * @param superFrameX The x location of frame.
	 * @param superFrameY The y location of frame.
	 * @throws HeadlessException
	 */
	public CartDialog(List<Ticket> tickets, BufferedImage movImg, int superFrameX, int superFrameY)
			throws HeadlessException {

		this.tickets = tickets;
		this.movImg = movImg;
		this.superFrameX = superFrameX;
		this.superFrameY = superFrameY;

		setUndecorated(true);
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(313 + superFrameX, 80 + superFrameY, 640, 400);

		contentPane = new ClientBackgroundPanel("Cart");
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				setLocation(jFrameX + (e.getXOnScreen() - mouseX), jFrameY + (e.getYOnScreen() - mouseY));
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
		contentPane.setLayout(null);
		setContentPane(contentPane);

		CartDialogPanel cartDialogPanel = new CartDialogPanel(tickets, movImg);
		cartDialogPanel.setBounds(0, 0, 640, 400);
		cartDialogPanel.setOpaque(false);
		cartDialogPanel.setCartDialogPanelListener(new CartDialogPanelListener() {

			@Override
			public void onReelectClicked() {
				listener.onCancelClicked();
			}

			@Override
			public void onPayClicked(float payAmount) {
				// TODO Auto-generated method stub
				contentPane.remove(cartDialogPanel);
				contentPane.repaint();

				PaymentDialogPanel paymentDialogPanel = new PaymentDialogPanel(movImg, payAmount,
						PaymentDialogPanel.ENTER_HANDLE);
				paymentDialogPanel.setBounds(0, 0, 640, 400);
				paymentDialogPanel.setOpaque(false);
				paymentDialogPanel.setPaymentDialogPanelListener(new PaymentDialogPanelListener() {

					@Override
					public void onConfirmClicked(String accountNum, String accountPassword, float allPayAmount) {
						List<BankAccount> bankAccounts = null;
						try {
							bankAccounts = BankAccountDomParser.getBankAccounts();
						} catch (ParserConfigurationException | SAXException | IOException e) {
							e.printStackTrace();
						}
						int count = 0;
						for (BankAccount bankAccount : bankAccounts) {
							if (accountNum.equals(bankAccount.getAccountNum())) {
								if (accountPassword.equals(bankAccount.getAccountPassword())) {
									if (bankAccount.getBalance() >= allPayAmount) {
										contentPane.remove(paymentDialogPanel);
										contentPane.repaint();
										PaymentDialogPanel askDialogPanel = new PaymentDialogPanel(movImg, allPayAmount,
												PaymentDialogPanel.ASK_HANDLE);
										askDialogPanel.setBounds(0, 0, 640, 400);
										askDialogPanel.setOpaque(false);
										askDialogPanel.setPaymentDialogPanelListener(new PaymentDialogPanelListener() {

											@Override
											public void onConfirmClicked(String accountNum, String accountPassword,
													float payAmount) {
												try {
													BankAccountDomParser.updateAccounts(bankAccount.getAccountNum(),
															"balance",
															String.valueOf(bankAccount.getBalance() - payAmount));
												} catch (ParserConfigurationException | SAXException | IOException
														| TransformerException e) {
													e.printStackTrace();
												}
												String fileName = tickets.get(0).getScreenName().toLowerCase().replace(" ", "") + "At"
														+ tickets.get(0).getTimeStr().split(":")[0] + "_" + tickets.get(0).getTimeStr().split(":")[1] + ".xml";
												try {
													for (Ticket ticket : tickets) {
														
														TicketDomParser.createTicket(ticket);
														
														ScreenSeatDomParser.updateScreenSeat(fileName,
																ticket.getSeatLocation(), "false");
													}
												} catch (ParserConfigurationException | SAXException | IOException
														| TransformerException e) {
													e.printStackTrace();
												}

												contentPane.remove(askDialogPanel);
												contentPane.repaint();
												
												for(Ticket ticket : tickets){
													FileWriter fw;
													try {
														fw = new FileWriter("./TICKET_ID " + ticket.getTicketId() + ".txt");
														BufferedWriter bufw = new BufferedWriter(fw);
														bufw.write("Ticket ID: " + "	|	" + ticket.getTicketId());
														bufw.newLine();
														bufw.write("Film Name: " + "	|	" + ticket.getFilmName());
														bufw.newLine();
														bufw.write("Screen Name: " + "	|	" + ticket.getScreenName());
														bufw.newLine();
														bufw.write("Seat Location: " + "	|	" + ticket.getSeatLocation());
														bufw.newLine();
														bufw.write("Time: " + "		|	" + ticket.getTimeStr());
														bufw.newLine();
														bufw.write("Tiket Type: " + "	|	" + ticket.getTiketType());
														bufw.newLine();
														if("student".equals(ticket.getTiketType())){
															bufw.write("Student ID: " + "	|	" + ticket.getStudentId());
															bufw.newLine();
														}
														bufw.write("Ticket Price: " + "	|	" + ticket.getTicketPrice());
														bufw.newLine();
														bufw.flush();
														bufw.close();
													} catch (IOException e) {
														e.printStackTrace();
													}
													
												}
												
												PaymentDialogPanel successPaymentDialogPanel = new PaymentDialogPanel(
														movImg, payAmount, PaymentDialogPanel.SUCCESS_HANDLE);
												successPaymentDialogPanel.setBounds(0, 0, 640, 400);
												successPaymentDialogPanel.setOpaque(false);
												successPaymentDialogPanel.setPaymentDialogPanelListener(
														new PaymentDialogPanelListener() {

															@Override
															public void onConfirmClicked(String accountNum,
																	String accountPassword, float payAmount) {
															}

															@Override
															public void onCancelClicked() {
																listener.onCancelClicked();
															}
														});
												contentPane.add(successPaymentDialogPanel);
												contentPane.repaint();
											}

											@Override
											public void onCancelClicked() {
												listener.onCancelClicked();
											}
										});
										contentPane.add(askDialogPanel);
										contentPane.repaint();
									} else {
										contentPane.remove(paymentDialogPanel);
										contentPane.repaint();
										PaymentDialogPanel nsfDialogPanel = new PaymentDialogPanel(movImg, allPayAmount,
												PaymentDialogPanel.NSF_HANDLE);
										nsfDialogPanel.setBounds(0, 0, 640, 400);
										nsfDialogPanel.setOpaque(false);
										nsfDialogPanel.setPaymentDialogPanelListener(new PaymentDialogPanelListener() {

											@Override
											public void onConfirmClicked(String accountNum, String accountPassword,
													float payAmount) {

											}

											@Override
											public void onCancelClicked() {
												listener.onCancelClicked();
											}
										});
										contentPane.add(nsfDialogPanel);
										contentPane.repaint();
									}
									return;
								}
							}
							if (count == bankAccounts.size() - 1) {
								JLabel errorLabel = new JLabel("Your account number or password is error !");
								errorLabel.setFont(new Font("Arial", Font.PLAIN, 11));
								errorLabel.setForeground(Color.RED);
								errorLabel.setOpaque(false);
								errorLabel.setBounds(330, 300, 300, 20);
								paymentDialogPanel.add(errorLabel);
								paymentDialogPanel.repaint();
							}
							count++;
						}
					}

					@Override
					public void onCancelClicked() {
						listener.onCancelClicked();
					}
				});
				contentPane.add(paymentDialogPanel);
				contentPane.repaint();
			}
		});

		contentPane.add(cartDialogPanel);
		contentPane.repaint();
	}

}
