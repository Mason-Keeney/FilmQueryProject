package com.skilldistillery.filmquery.entities;

import java.util.List;

public class Film {
	private int id;
	private String title;
	private String description;
	private short releaseYear;
	private int languageId;
	private int rentalDuration;
	private double rentalRate;
	private Integer length;
	private double replaceCost;
	private String rating;
	private String specialFeatures;
	private List<Actor> actors;
	private String language;
	
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	
	
	@Override
	public String toString() {
		return "[Title: " + title + " || ID: " + id + " || Release Year: " + releaseYear + " || Length: " + length + " minutes]\n"
				+ "[Replace Cost: " + replaceCost + " || Rental Duration: " + rentalDuration + " || Rental Rate: " + rentalRate + "]\n"
				+ "[Language: " + language + " || Rating: " + rating + " || Special Features: "
				+ specialFeatures + "]\n" + "[Description: " + description + "]\n\n"  + listCast();
	}

	public String listCast() {
		StringBuilder sb = new StringBuilder();
		sb.append("");
		if(!actors.isEmpty())
			sb.append("Cast:\n");
		for (int i = 0; i < actors.size(); i++) {
			sb.append(actors.get(i).getFirstName() + " " + actors.get(i).getLastName() + "\n");
		}
			
		return sb.toString();
	}
	
	public String quickDisplay() {
		StringBuilder sb = new StringBuilder();  
		sb.append("[Title: " + title +  " || Release Year: " + releaseYear + " || Rating: " + rating + " || Language: " + language + "]\n\n");
		sb.append(description + "\n\n");
		sb.append(listCast());
		return sb.toString();
		
	}
	

	public int getFilmId() {
		return id;
	}

	public void setFilmId(int filmId) {
		this.id = filmId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return description;
	}

	public void setDesc(String desc) {
		this.description = desc;
	}

	public short getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(short releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int getLangId() {
		return languageId;
	}

	public void setLangId(int langId) {
		this.languageId = langId;
	}

	public int getRentDur() {
		return rentalDuration;
	}

	public void setRentDur(int rentDur) {
		this.rentalDuration = rentDur;
	}

	public double getRate() {
		return rentalRate;
	}

	public void setRate(double rate) {
		this.rentalRate = rate;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public double getRepCost() {
		return replaceCost;
	}

	public void setRepCost(double repCost) {
		this.replaceCost = repCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getFeatures() {
		return specialFeatures;
	}

	public void setFeatures(String features) {
		this.specialFeatures = features;
	}

	public Film() {}

	public Film(int filmId, String title, String desc, short releaseYear, int langId, int rentDur, double rate,
			Integer length, double repCost, String rating, String features, List<Actor> actors) {
		super();
		this.id = filmId;
		this.title = title;
		this.description = desc;
		this.releaseYear = releaseYear;
		this.languageId = langId;
		this.rentalDuration = rentDur;
		this.rentalRate = rate;
		this.length = length;
		this.replaceCost = repCost;
		this.rating = rating;
		this.specialFeatures = features;
		this.actors = actors;
	}

	public Film(int filmId, String title, String desc, short releaseYear, int langId, int rentDur, double rate,
			Integer length, double repCost, String rating, String features) {
		super();
		this.id = filmId;
		this.title = title;
		this.description = desc;
		this.releaseYear = releaseYear;
		this.languageId = langId;
		this.rentalDuration = rentDur;
		this.rentalRate = rate;
		this.length = length;
		this.replaceCost = repCost;
		this.rating = rating;
		this.specialFeatures = features;
	}
	
	

	
}
