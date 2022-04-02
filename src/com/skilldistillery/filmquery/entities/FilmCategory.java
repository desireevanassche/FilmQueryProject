package com.skilldistillery.filmquery.entities;

import java.util.Objects;

public class FilmCategory {
	private String name;

	public FilmCategory() {
	}

	public FilmCategory(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilmCategory other = (FilmCategory) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "FilmCategory [name=" + name + "]";
	} 

}
