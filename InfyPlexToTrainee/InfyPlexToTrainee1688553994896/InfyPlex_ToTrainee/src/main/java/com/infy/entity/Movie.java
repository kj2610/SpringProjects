package com.infy.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Movie {
	@Id
	private Integer movieId;
	private String movieName;
	private String directorName;
	@Column(name="releaseYear")
	private Integer year;
	private float imdbRating;
	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Movie(Integer movieId, String movieName, String directorName, Integer year,float imdbRating) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.directorName = directorName;
		this.year = year;
		this.imdbRating = imdbRating;
	}
	public Integer getMovieId() {
		return movieId;
	}
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getDirectorName() {
		return directorName;
	}
	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	
	public float getImdbRating() {
		return imdbRating;
	}
	public void setImdbRating(float imdbRating) {
		this.imdbRating = imdbRating;
	}
	@Override
	public int hashCode() {
		return Objects.hash(directorName, imdbRating, movieId, movieName, year);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		return Objects.equals(directorName, other.directorName)
				&& Float.floatToIntBits(imdbRating) == Float.floatToIntBits(other.imdbRating)
				&& Objects.equals(movieId, other.movieId) && Objects.equals(movieName, other.movieName)
				&& Objects.equals(year, other.year);
	}
	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", movieName=" + movieName + ", directorName=" + directorName + ", year="
				+ year + ", imdbRating=" + imdbRating + "]";
	}
	
	
	
	

}
