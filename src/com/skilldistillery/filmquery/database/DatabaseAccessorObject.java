package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private String user = "student";
	private String pass = "student";
	
	static {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM film WHERE film.id = ?;");
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String desc = rs.getString("description");
				short releaseYear = rs.getShort("release_year");
				int langId = rs.getInt("language_id");
				int rentDur = rs.getInt("rental_duration");
				double rate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double repCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String features = rs.getString("special_features");
				List<Actor> actors = findActorsByFilmId(conn, filmId);
				film = new Film(id, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating, features, actors);
				setFilmLanguage(conn, film);
			}
			conn.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;

	}

	
	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement stmt = prepStatementActor(conn, actorId);
				ResultSet rs = stmt.executeQuery();) {
			ResultSet actorResult = stmt.executeQuery();
			if (actorResult.next()) {
				actor = new Actor(); // Create the object
				// Here is our mapping of query columns to our object fields:
				actor.setId(actorResult.getInt(1));
				actor.setFirstName(actorResult.getString(2));
				actor.setLastName(actorResult.getString(3));
				actor.setFilms(findFilmsByActorId(actorId)); // An Actor has Films
			}
			// ...

		} catch (Exception e) {
			e.printStackTrace();
		}

		return actor;

	}


	public List<Film> findFilmsByActorId(int actorId) {
		List<Film> films = new ArrayList<>();
		String user = "student";
		String pass = "student";
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement stmt = prepStatementFilmList(conn, actorId);
				ResultSet rs = stmt.executeQuery();) {

			while (rs.next()) {
				int filmId = rs.getInt("id");
				String title = rs.getString("title");
				String desc = rs.getString("description");
				short releaseYear = rs.getShort("release_year");
				int langId = rs.getInt("language_id");
				int rentDur = rs.getInt("rental_duration");
				double rate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double repCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String features = rs.getString("special_features");
				List<Actor> actors = findActorsByFilmId(conn, filmId);
				Film film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
						features, actors);
				films.add(film);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}
	
	
	private List<Film> findFilmsByActorId(Connection conn, int id) {
		List<Film> films = new ArrayList<>();
		try (PreparedStatement stmt = prepStatementFilmList(conn, id);
			ResultSet rs = stmt.executeQuery();) {

			while (rs.next()) {
				int filmId = rs.getInt("id");
				String title = rs.getString("title");
				String desc = rs.getString("description");
				short releaseYear = rs.getShort("release_year");
				int langId = rs.getInt("language_id");
				int rentDur = rs.getInt("rental_duration");
				double rate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double repCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String features = rs.getString("special_features");
				Film film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
						features);
				setFilmLanguage(conn, film);
				films.add(film);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}



	
	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement stmt = prepStatementActorList(conn, filmId);
				ResultSet rs = stmt.executeQuery();) {
			ResultSet actorResult = stmt.executeQuery();
			while (actorResult.next()) {
				// Create the object
				// Here is our mapping of query columns to our object fields:
				int id = actorResult.getInt("id");
				String firstName = actorResult.getString("first_name");
				String lastName = actorResult.getString("last_name");
				List<Film> films = findFilmsByActorId(conn, id); // An Actor has Films
				actors.add(new Actor(id, firstName, lastName, films));

			}
			// ...

		} catch (Exception e) {
			e.printStackTrace();
		}

		return actors;

	}
	
	
	private List<Actor> findActorsByFilmId(Connection conn, int filmId) {
		List<Actor> actors = new ArrayList<>();
		try (PreparedStatement stmt = prepStatementActorList(conn, filmId);
			 ResultSet rs = stmt.executeQuery();) {
			 ResultSet actorResult = stmt.executeQuery();
			while (actorResult.next()) {
				
				int id = actorResult.getInt("id");
				String firstName = actorResult.getString("first_name");
				String lastName = actorResult.getString("last_name");
				actors.add(new Actor(id, firstName, lastName));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return actors;

	}

	
	private void setFilmLanguage(Connection conn, Film film) {
		
		try (PreparedStatement stmt = prepStatementLanguage(conn, film.getLangId());
			 ResultSet rs = stmt.executeQuery();) {
			
			if(rs.next()) {
				film.setLanguage(rs.getString("name"));
			} 
			
			}catch (Exception e) {
				e.printStackTrace();
				// Create the object
				// Here is our mapping of query columns to our object fields:
		

			}
	}
	
	

	
	private PreparedStatement prepStatementLanguage(Connection conn, int langId) throws SQLException {
		String sql = "SELECT name FROM language WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, langId);
		return stmt;
	}


	private PreparedStatement prepStatementActorList(Connection conn, int filmId) throws SQLException {
		String sql = "SELECT actor.id, actor.first_name, actor.last_name FROM actor JOIN film_actor ON film_actor.actor_id = actor.id JOIN film ON film_actor.film_id = film.id WHERE film.id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		return stmt;

	}
	
	
	
	
	private PreparedStatement prepStatementActor(Connection conn, int actorId) throws SQLException {
		String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);
		return stmt;

	}
	
	
	
	
	private PreparedStatement prepStatementFilmList(Connection conn, int actorId) throws SQLException {

		String sql = "SELECT id, title, description, release_year, language_id, rental_duration, ";
		sql += " rental_rate, length, replacement_cost, rating, special_features "
				+ " FROM film JOIN film_actor ON film.id = film_actor.film_id " + " WHERE actor_id = ?";
		PreparedStatement tempStmt = conn.prepareStatement(sql);
		tempStmt.setInt(1, actorId);
		return tempStmt;

	}
	
	private PreparedStatement prepStatementFilmList(Connection conn, String keyPhrase) throws SQLException {
		String sql = "SELECT id, title, description, release_year, language_id, rental_duration, ";
		sql += " rental_rate, length, replacement_cost, rating, special_features "
				+ " FROM film" + " WHERE title LIKE ? or description LIKE ?";
		PreparedStatement tempStmt = conn.prepareStatement(sql);
		tempStmt.setString(1, "%" + keyPhrase + "%");
		tempStmt.setString(2, "%" + keyPhrase + "%");
		return tempStmt;
	}
	
	
	public List<Film> findFilmsByKeyword(String keyPhrase) {
		List<Film> films = new ArrayList<>();
		String user = "student";
		String pass = "student";
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement stmt = prepStatementFilmList(conn, keyPhrase);
				ResultSet rs = stmt.executeQuery();) {

			while (rs.next()) {
				int filmId = rs.getInt("id");
				String title = rs.getString("title");
				String desc = rs.getString("description");
				short releaseYear = rs.getShort("release_year");
				int langId = rs.getInt("language_id");
				int rentDur = rs.getInt("rental_duration");
				double rate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double repCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String features = rs.getString("special_features");
				List<Actor> actors = findActorsByFilmId(conn, filmId);
				Film film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
						features, actors);
				films.add(film);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}


	

}
