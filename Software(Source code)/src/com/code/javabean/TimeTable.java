package com.group99.javabean;
/**
 * The entity class of timetable.
 * @author group 99
 *
 */
public class TimeTable {
	
	private String id;
	private String screen;
	private String time;
	private String movie;
	
	public TimeTable(){
		
	}
	/**
	 * This is the constructor of TimeTable.
	 * @param id ID of timetable.
	 * @param screen Current screen.
	 * @param time Current time.
	 * @param movie current movie name.
	 */
	public TimeTable(String id, String screen, String time, String movie) {
		super();
		this.id = id;
		this.screen = screen;
		this.time = time;
		this.movie = movie;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	@Override
	public String toString() {
		return "TimeTable [id=" + id + ", screen=" + screen + ", time=" + time + ", movie=" + movie + "]";
	}
	
}
