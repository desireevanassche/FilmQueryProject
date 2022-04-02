package com.skilldistillery.filmquery.database;

import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class mess {


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

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
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
						filmList.add(film);
						
						List<Actor> cast = findActorsByFilmId(filmId);
						film.setCast(cast);
						
				}
				rs.close();
			}
		} catch (SQLException e) {

		}
		return filmList;
	}
	
}
