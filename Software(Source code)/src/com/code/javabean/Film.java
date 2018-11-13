package com.group99.javabean;
/**
 * The entity class of film.
 * @author group 99
 *
 */
public class Film {
	
	private int filmId;
	private String filmName;
	private int filmDuration;
	private String filmDirector;
	private String filmStars;
	private Float filmPrice;
	private String filmStoryline;
	private String filmType;
	private String filmScore;
	
	public Film(){
		
	}
	/**
	 * This is the constructor of Film.
	 * @param filmId The id of film.
	 * @param filmName The name of film.
	 * @param filmDuration The duration of film.
	 * @param filmDirector The director of film.
	 * @param filmStars The stars of film.
	 * @param filmPrice The price of film.
	 * @param filmStoryline The story line of film.
	 * @param filmType The type of film.
	 * @param filmScore The score of film.
	 */
	public Film(int filmId, String filmName, int filmDuration, String filmDirector, String filmStars, Float filmPrice,
			String filmStoryline, String filmType, String filmScore) {
		super();
		this.filmId = filmId;
		this.filmName = filmName;
		this.filmDuration = filmDuration;
		this.filmDirector = filmDirector;
		this.filmStars = filmStars;
		this.filmPrice = filmPrice;
		this.filmStoryline = filmStoryline;
		this.filmType = filmType;
		this.filmScore = filmScore;
	}

	public String getFilmType() {
		return filmType;
	}

	public void setFilmType(String filmType) {
		this.filmType = filmType;
	}

	public String getFilmScore() {
		return filmScore;
	}

	public void setFilmScore(String filmScore) {
		this.filmScore = filmScore;
	}

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public String getFilmName() {
		return filmName;
	}

	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}

	public int getFilmDuration() {
		return filmDuration;
	}

	public void setFilmDuration(int filmDuration) {
		this.filmDuration = filmDuration;
	}

	public String getFilmDirector() {
		return filmDirector;
	}

	public void setFilmDirector(String filmDirector) {
		this.filmDirector = filmDirector;
	}

	public String getFilmStars() {
		return filmStars;
	}

	public void setFilmStars(String filmStars) {
		this.filmStars = filmStars;
	}

	public Float getFilmPrice() {
		return filmPrice;
	}

	public void setFilmPrice(Float filmPrice) {
		this.filmPrice = filmPrice;
	}

	public String getFilmStoryline() {
		return filmStoryline;
	}

	public void setFilmStoryline(String filmStoryline) {
		this.filmStoryline = filmStoryline;
	}

	
}
