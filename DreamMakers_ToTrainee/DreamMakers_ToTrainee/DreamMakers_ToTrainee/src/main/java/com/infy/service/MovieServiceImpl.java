package com.infy.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.dto.MovieDTO;
import com.infy.exception.DreamMakersException;
import com.infy.repository.MovieRepository;
import com.infy.validator.Validator;

@Service(value = "movieService")
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	Validator validate = new Validator();

	public String addMovie(MovieDTO movieDTO) throws DreamMakersException {
		validate.validate(movieDTO);
		return movieRepository.addMovie(movieDTO);
	}

	public List<MovieDTO> getMovies(String directorName) throws DreamMakersException {
		List<MovieDTO> movieList = movieRepository.getAllMovies();
		
		return movieList.stream().filter(movie -> movie.getDirector().getDirectorName().equals(directorName)).collect(Collectors.toList());
	}
}
