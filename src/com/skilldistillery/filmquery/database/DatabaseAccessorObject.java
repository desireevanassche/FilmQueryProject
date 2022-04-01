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

	String user = "student";
	String pwd = "student";

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		String sql = "SELECT id, title, description, release_year, language_id,";
		sql += "rental_duration, rental_rate, length, replacement_cost, rating, special_features"
				+ "  FROM film WHERE id = ?";

		try (Connection conn = DriverManager.getConnection(URL, user, pwd);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, filmId);

			try (ResultSet filmResult = ps.executeQuery()) {
				if (filmResult.next()) {
					film = new Film();
					film.setId(filmResult.getInt(1));
					film.setTitle(filmResult.getString(2));
					film.setDescription(filmResult.getString(3));
					film.setReleaseYear(filmResult.getInt(4));
					film.setLanguageId(filmResult.getInt(5));
					film.setRentalDuration(filmResult.getInt(6));
					film.setRentalRate(filmResult.getDouble(7));
					film.setLength(filmResult.getInt(8));
					film.setReplacementCost(filmResult.getDouble(9));
					film.setRating(filmResult.getString(10));
					film.setSpecialFeatures(filmResult.getString(11));
				}
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

			try (ResultSet actorResult = ps.executeQuery()) {
				if (actorResult.next()) {
					actor = new Actor();
					actor.setId(actorResult.getInt(1));
					actor.setFirstName(actorResult.getString(2));
					actor.setLastName(actorResult.getString(3));
				}
			}
		} catch (SQLException e) {

		}
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actor = new ArrayList<>();

		String sql = "SELECT actor_id, first_name, last_name FROM actor "
				+ "JOIN film_actor ON actor.id = film_actor.actor_id " + "WHERE film_id = 16";

		try (Connection conn = DriverManager.getConnection(URL, user, pwd);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, filmId);

			try (ResultSet arrayResult = ps.executeQuery()) {

				while (arrayResult.next()) {
					int actorId = arrayResult.getInt(1);
					String firstName = arrayResult.getString(2);
					String lastName = arrayResult.getString(3);

					Actor actorDetails = new Actor(actorId, firstName, lastName);

					actor.add(actorDetails);
				}
			}
		} catch (SQLException e) {

		}
		return actor;

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
