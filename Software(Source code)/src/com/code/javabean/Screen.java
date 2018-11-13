package com.group99.javabean;
/**
 * The entity class of screen.
 * @author group 99
 *
 */
public class Screen {
	
	private String seatId;
	private String seatMov;
	private String seatIsEmpty;

	public Screen() {
	}
	/**
	 * This is the constructor of Screen.
	 * @param seatId The seat ID.
	 * @param seatMov The current movie name.
	 * @param seatIsEmpty Whether the seat is empty.
	 */
	public Screen(String seatId, String seatMov, String seatIsEmpty) {
		this.seatId = seatId;
		this.seatMov = seatMov;
		this.seatIsEmpty = seatIsEmpty;
	}

	public String getSeatId() {
		return seatId;
	}

	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}

	public String getSeatMov() {
		return seatMov;
	}

	public void setSeatMov(String seatMov) {
		this.seatMov = seatMov;
	}

	public String getSeatIsEmpty() {
		return seatIsEmpty;
	}

	public void setSeatIsEmpty(String seatIsEmpty) {
		this.seatIsEmpty = seatIsEmpty;
	}

	@Override
	public String toString() {
		return "Screen [seatId=" + seatId + ", seatMov=" + seatMov + ", seatIsEmpty=" + seatIsEmpty + "]";
	}
	
}
