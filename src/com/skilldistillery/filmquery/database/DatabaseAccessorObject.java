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
import com.skilldistillery.filmquery.entities.MainMenu;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";

	String user = "student";
	String pwd = "student";

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		String sql = "SELECT f.id, f.title, f.description, f.release_year, language_id ,rental_duration, f.rental_rate, f.length, f.replacement_cost, f.rating, f.special_features, l.name FROM film f JOIN language l ON f.language_id = l.id WHERE f.id = ?";

		try (Connection conn = DriverManager.getConnection(URL, user, pwd);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, filmId);
			boolean hasFilm = false;
			MainMenu menuCall = new MainMenu();

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					hasFilm = true;
					film = new Film();
					film.setId(rs.getInt("id"));
					film.setTitle(rs.getString("title"));
					film.setDescription(rs.getString("description"));
					film.setReleaseYear(rs.getInt("release_year"));
					film.setLanguageId(rs.getInt("language_id"));
					film.setRentalDuration(rs.getInt("rental_duration"));
					film.setRentalRate(rs.getDouble("rental_rate"));
					film.setLength(rs.getInt("length"));
					film.setReplacementCost(rs.getDouble("replacement_cost"));
					film.setRating(rs.getString("rating"));
					film.setSpecialFeatures(rs.getString("special_features"));
					film.setLanguage(rs.getString("name"));

					List<Actor> cast = findActorsByFilmId(filmId);
					film.setCast(cast);
				

				}
				if (!hasFilm) {
					System.out.println("hmm.. doesn't look like we have that one..");
					System.out.println("Please Try again");
					System.out.println();
					menuCall.subMenu(film.getId());

				}

				rs.close();
			}
		} catch (SQLException e) {

		}
		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		String sql = "SELECT id, first_name, last_name,  FROM actor WHERE id = ?";

		try (Connection conn = DriverManager.getConnection(URL, user, pwd);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, actorId);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					actor = new Actor();
					actor.setId(rs.getInt(1));
					actor.setFirstName(rs.getString(2));
					actor.setLastName(rs.getString(3));
				}
				
				rs.close();
				ps.close();
				conn.close();
			}
		} catch (SQLException e) {

		}
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actor = new ArrayList<>();

		String sql = "SELECT actor_id, first_name, last_name FROM actor "
				+ "JOIN film_actor ON actor.id = film_actor.actor_id " + "WHERE film_id = ?";

		try (Connection conn = DriverManager.getConnection(URL, user, pwd);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, filmId);

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					int actorId = rs.getInt(1);
					String firstName = rs.getString(2);
					String lastName = rs.getString(3);

					Actor actorDetails = new Actor(actorId, firstName, lastName);

					actor.add(actorDetails);
				}
				rs.close();
				ps.close();
				conn.close();
			}
		} catch (SQLException e) {

		}
		return actor;

	}

	@Override
	public List<Film> findFilmByKey(String filmKey) {
		List<Film> filmList = new ArrayList<Film>();

		Film film = null;
		// check to see if you need qutations around the ?
		String sql = "SELECT f.id, f.title, f.description, f.release_year, f.language_id, f.rental_duration, f.rental_rate, f.length, f.replacement_cost, f.rating, f.special_features, l.name FROM film f JOIN language l ON f.language_id = l.id WHERE (f.title LIKE ?) OR (f.description LIKE ?)";

		try (Connection conn = DriverManager.getConnection(URL, user, pwd);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, "%" + filmKey + "%");
			ps.setString(2, "%" + filmKey + "%");
			boolean hasFilm = false;
			MainMenu menuCall = new MainMenu();

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					hasFilm = true;
					film = new Film();
					int id = rs.getInt("id");
					String title = rs.getString("title");
					String description = rs.getString("description");
					int releaseYear = rs.getInt("release_year");
					int languageId = rs.getInt("language_id");
					int rentalDuration = rs.getInt("rental_duration");
					double rentalRate = rs.getDouble("rental_rate");
					int length = rs.getInt("length");
					double replacementCost = rs.getDouble("replacement_cost");
					String rating = rs.getString("rating");
					String specialFeatures = rs.getString("special_features");
					String language = rs.getString("name");

					film = new Film(id, title, description, releaseYear, languageId, rentalDuration, rentalRate, length,
							replacementCost, rating, specialFeatures, language);
					filmList.add(film);
				

				}
				if (!hasFilm) {
					System.out.println("Hm.. doesn't look like we have that film..");
					System.out.println("Please Try again");
					System.out.println();
					
				}
				rs.close();
				ps.close();
				conn.close();
			}
		} catch (SQLException e) {

		}
		return filmList;
	}

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Driver not found.");
			throw new RuntimeException("Unable to load MySQL driver class");
		}
	}

}