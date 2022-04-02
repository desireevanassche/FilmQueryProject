package com.skilldistillery.filmquery.database;

public class mess {

}
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