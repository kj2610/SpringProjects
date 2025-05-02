package com.infy.validator;

import java.time.LocalDate;

import com.infy.dto.BookDTO;
import com.infy.exception.InfyBookException;

public class Validator {

	public static void validate(BookDTO bookDTO) throws InfyBookException {
		Boolean result = validateYear(bookDTO.getPublishedYear());
		if(!result) {
			throw new InfyBookException("Validator.INVALID_YEAR");
		}
	}

	public static boolean validateYear(LocalDate year) {
		return year.isBefore(LocalDate.now()) ;
	}

}
