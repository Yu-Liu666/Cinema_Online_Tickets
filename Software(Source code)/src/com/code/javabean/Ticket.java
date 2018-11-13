package com.group99.javabean;
/**
 * The entity class of ticket.
 * @author group 99
 *
 */
public class Ticket {
	
	private String filmName;
	private String ticketId;
	private String timeStr;
	private String screenName;
	private String tiketType;
	private String seatLocation;
	private float ticketPrice;
	private String studentId;
	
	public Ticket() {
	}
	/**
	 * This is the constructor of Ticket.
	 * @param filmName The name of film.
	 * @param ticketId The ID of ticket.
	 * @param timeStr The time of ticket.
	 * @param screenName The screen of ticket.
	 * @param tiketType The type of film.
	 * @param seatLocation The location of seat.
	 * @param ticketPrice The price of ticket.
	 * @param studentId The student ID.
	 */
	public Ticket(String filmName, String ticketId, String timeStr, String screenName, String tiketType,
			String seatLocation, float ticketPrice, String studentId) {
		super();
		this.filmName = filmName;
		this.ticketId = ticketId;
		this.timeStr = timeStr;
		this.screenName = screenName;
		this.tiketType = tiketType;
		this.seatLocation = seatLocation;
		this.ticketPrice = ticketPrice;
		this.studentId = studentId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getSeatLocation() {
		return seatLocation;
	}

	public void setSeatLocation(String seatLocation) {
		this.seatLocation = seatLocation;
	}

	public String getFilmName() {
		return filmName;
	}

	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getTimeStr() {
		return timeStr;
	}

	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getTiketType() {
		return tiketType;
	}

	public void setTiketType(String tiketType) {
		this.tiketType = tiketType;
	}

	public float getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(float ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	@Override
	public String toString() {
		return "Ticket [filmName=" + filmName + ", ticketId=" + ticketId + ", timeStr=" + timeStr + ", screenName="
				+ screenName + ", tiketType=" + tiketType + ", seatLocation=" + seatLocation + ", ticketPrice="
				+ ticketPrice + ", studentId=" + studentId + "]";
	}

	
}
