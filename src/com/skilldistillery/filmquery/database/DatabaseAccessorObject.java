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
import com.skilldistillery.filmquery.entities.FilmCategory;
import com.skilldistillery.filmquery.entities.Language;

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

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					film = new Film();
					film.setId(rs.getInt(1));
					film.setTitle(rs.getString(2));
					film.setDescription(rs.getString(3));
					film.setReleaseYear(rs.getInt(4));
					film.setLanguageId(rs.getInt(5));
					film.setRentalDuration(rs.getInt(6));
					film.setRentalRate(rs.getDouble(7));
					film.setLength(rs.getInt(8));
					film.setReplacementCost(rs.getDouble(9));
					film.setRating(rs.getString(10));
					film.setSpecialFeatures(rs.getString(11));
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

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					actor = new Actor();
					actor.setId(rs.getInt(1));
					actor.setFirstName(rs.getString(2));
					actor.setLastName(rs.getString(3));
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
		String sql = "SELECT * FROM film WHERE title LIKE ? OR description LIKE ?";

		try (Connection conn = DriverManager.getConnection(URL, user, pwd);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, "%" + filmKey + "%");
			ps.setString(2, "%" + filmKey + "%");

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					film = new Film();
					film.setId(rs.getInt(1));
					film.setTitle(rs.getString(2));
					film.setDescription(rs.getString(3));
					film.setReleaseYear(rs.getInt(4));
					film.setLanguageId(rs.getInt(5));
					film.setRentalDuration(rs.getInt(6));
					film.setRentalRate(rs.getDouble(7));
					film.setLength(rs.getInt(8));
					film.setReplacementCost(rs.getDouble(9));
					film.setRating(rs.getString(10));
					film.setSpecialFeatures(rs.getString(11));
					filmList.add(film);
				}
				rs.close();
			}
		} catch (SQLException e) {

		}
		return filmList;
	}

	@Override
	public Language filmByLanguage(int filmId) {
		Language language = null;
		String sql = "SELECT f.id, f.language_id, l.id, l.name FROM film f JOIN language l ON l.id = f.language_id WHERE f.id = ?;";

		try (Connection conn = DriverManager.getConnection(URL, user, pwd);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, filmId);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					language = new Language();
					language.setName(rs.getString(4));

				}

				rs.close();
			}
		} catch (SQLException e) {

		}
		return language;
	}

	@Override
	public FilmCategory filmByCategory(int filmId) {
		// TODO Auto-generated method stub
		return null;
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