package com.infy.validator;

import com.infy.dto.DirectorDTO;
import com.infy.dto.MovieDTO;
import com.infy.exception.DreamMakersException;

public class Validator {

	public static void validate(MovieDTO movieDTO) throws DreamMakersException {
		Boolean result = validateMovie(movieDTO);
		if(result == false) {
			throw new DreamMakersException("Validator.INVALID_NAMES");
		}
	}

	public static Boolean validateMovie(MovieDTO movieDTO) {
		String movieName = movieDTO.getMovieName();
		DirectorDTO directorDTO = movieDTO.getDirector();
		String DirectorName = directorDTO.getDirectorName();
		
		return (movieName == null || DirectorName == null) ? false : true;
	}
}
