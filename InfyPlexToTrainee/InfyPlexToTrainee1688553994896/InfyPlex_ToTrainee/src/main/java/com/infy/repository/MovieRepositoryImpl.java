package com.infy.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import com.infy.dto.MovieDTO;
import com.infy.entity.Movie;

@Repository(value="movieRepository")

public class MovieRepositoryImpl implements MovieRepository {

	@PersistenceContext
	private EntityManager entityManager;
	private static final Logger LOGGER = LogManager.getLogger(MovieRepository.class);
	@Override
	public List<MovieDTO> getMovieByRating(Double fromRating) {
		List<MovieDTO> movieDTOs = new ArrayList<>();
		Query query = entityManager.createQuery("select m from Movie m where m.imdbRating >= :fromRating");
		query.setParameter("fromRating", fromRating);
		List<Movie> movies = query.getResultList();
		movies.forEach(LOGGER::info);
		movies.forEach(m ->{
			MovieDTO movieDTO = new MovieDTO();
			movieDTO.setMovieId(m.getMovieId());
			movieDTO.setMovieName(m.getMovieName());
			movieDTO.setDirectorName(m.getDirectorName());
			movieDTO.setYear(m.getYear());
			movieDTO.setImdbRating(m.getImdbRating());
			movieDTOs.add(movieDTO);
		});
		
		return movieDTOs;
	}

	@Override
	public List<MovieDTO> getHighestRatedMovie(String directorName) {
		Query query = entityManager.createQuery("SELECT m FROM Movie m " +
		        "WHERE m.directorName = :directorName " +
		        "AND m.imdbRating = (" +
		            "SELECT MAX(m2.imdbRating) FROM Movie m2 WHERE m2.directorName = :directorName" +
		        ")");
		query.setParameter("directorName", directorName);
		List<MovieDTO>movieDTOs = new ArrayList<>();
		List<Movie> movies = query.getResultList();
		movies.forEach(m ->{
			MovieDTO movieDTO = new MovieDTO();
			movieDTO.setMovieId(m.getMovieId());
			movieDTO.setMovieName(m.getMovieName());
			movieDTO.setDirectorName(m.getDirectorName());
			movieDTO.setYear(m.getYear());
			movieDTO.setImdbRating(m.getImdbRating());
			movieDTOs.add(movieDTO);
		});
		return movieDTOs;
	}

	@Override
	public Float getAverageDirectorRating(String directorName) {
		TypedQuery<Double> query = (TypedQuery<Double>) entityManager.createQuery("select AVG(m.imdbRating) from Movie m where m.directorName=:directorName");
		query.setParameter("directorName", directorName);
		return query.getSingleResult().floatValue();
	}

	@Override
	public Long getNumberOfMoviesReleased(Integer fromYear, Integer toYear) {
		Query query = entityManager.createQuery("select COUNT(m) from Movie m where m.year BETWEEN:fromYear AND :toYear");
		query.setParameter("fromYear", fromYear);
		query.setParameter("toYear", toYear);
		return (Long) query.getSingleResult();
	}

	

}
