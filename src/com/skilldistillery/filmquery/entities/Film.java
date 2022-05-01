package com.skilldistillery.filmquery.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
	private List<String> categories;
	private List<String> inventoryCondition;
	
	{
		categories = new ArrayList<>();
		inventoryCondition = new ArrayList<>();
	}
	
	
	public List<String> getInventoryCondition() {
		return inventoryCondition;
	}

	public void setInventoryCondition(List<String> inventoryCondition) {
		this.inventoryCondition = inventoryCondition;
	}
	
	public void addInventoryCondition(String condition) {
		inventoryCondition.add(condition);
	}
	
	public String listInventoryCondition() {
		StringBuilder sb = new StringBuilder();
		Iterator<String> it = inventoryCondition.iterator();
		sb.append("Known Conditions: ");
		while(it.hasNext()) {
			sb.append(it.next() + ", ");
		}
		sb.replace(sb.length()-2, sb.length(), "");
		sb.append("\n\n");
		
		return sb.toString();
		
	}

	
	public List<String> getCategories() {
		return categories;
	}
	
	
	public void addCategory(String category) {
		categories.add(category);
	}

	
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	
	
	public String listCategories() {
		StringBuilder sb = new StringBuilder();
		sb.append("Categories: ");
		for(int i = 0; i < categories.size(); i++) {
			if(i != categories.size() -1) {
				sb.append(categories.get(i) + " ");
			} else {
				sb.append(categories.get(i) + "\n");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	
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
				+ specialFeatures + "]\n" + "[Description: " + description + "]\n\n" + listCategories() + listInventoryCondition() + listCast();
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

	@Override
	public int hashCode() {
		return Objects.hash(id, inventoryCondition, languageId, length, rating, releaseYear, rentalDuration, rentalRate,
				replaceCost, specialFeatures, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return id == other.id && Objects.equals(inventoryCondition, other.inventoryCondition)
				&& languageId == other.languageId && Objects.equals(length, other.length)
				&& Objects.equals(rating, other.rating) && releaseYear == other.releaseYear
				&& rentalDuration == other.rentalDuration
				&& Double.doubleToLongBits(rentalRate) == Double.doubleToLongBits(other.rentalRate)
				&& Double.doubleToLongBits(replaceCost) == Double.doubleToLongBits(other.replaceCost)
				&& Objects.equals(specialFeatures, other.specialFeatures) && Objects.equals(title, other.title);
	}
	
	
	
	

	
}
