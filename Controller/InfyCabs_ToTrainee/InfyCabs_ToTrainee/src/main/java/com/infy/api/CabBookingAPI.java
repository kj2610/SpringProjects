package com.infy.api;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.dto.CabBookingDTO;
import com.infy.exception.InfyCabException;
import com.infy.service.BookingService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@RestController
@RequestMapping(value = "/bookings")
public class CabBookingAPI {
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private Environment environment;

/*This is a REST controller method to book a cab. It accepts booking details as part of HTTP request body.
It invokes the bookCab() method of BookingServiceImpl, which returns a booking id.
It returns an object of ResponseEntity created using success message and HTTP status code as CREATED.
If any exception occurs its catch them and throw ResponseStatusException with HTTP status code as BAD_REQUEST along with message and the exception. */
	@PostMapping(value = "/")
	public ResponseEntity<String> bookCab(@RequestBody CabBookingDTO cabBookingDTO) throws InfyCabException {
		try {
			Integer message = bookingService.bookCab(cabBookingDTO);
			String successMessage = environment.getProperty("API.BOOKING_SUCCESSFUL") + message;
			return new ResponseEntity<String>(successMessage, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			String errorMessage = "Booking failed" + e.getMessage();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
		}
	}

	/*This is a REST controller method to get cab booking details based on mobile number of user. 
	It accepts mobileNo as path variable.
	It invokes the getDetails() methods of BookingService which returns a List<CabBooking>.
	It returns an object of ResponseEntity created using List<CabBooking> and HTTP status code as OK.
	If any exception occurs it catches them and throw ResponseStatusException with HTTP status code as BAD_REQUEST along with message and the exception. */
		@GetMapping(value = "/{mobileNo}")
		public ResponseEntity<List<CabBookingDTO>> getBookingDetails(@PathVariable Long mobileNo) throws InfyCabException {
			try {
				List<CabBookingDTO> bookings = bookingService.getBookingDetails(mobileNo);
				return new ResponseEntity<List<CabBookingDTO>>(bookings, HttpStatus.OK);
			} catch (Exception e) {
				String errorMessage = "Booking Details cant found " + e.getMessage();
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
			}
		
		}

/*This is a REST controller method to cancel cab booking based on bookingId. 
It accepts bookingId as path variable.
It invokes cancelBooking() method of BookingService.
It returns an object of ResponseEntity created using success message and HTTP status code as OK. 
If any exception occurs it catches them and throw ResponseStatusException with HTTP status code as BAD_REQUEST along with message and the exception.*/
		@PutMapping(value = "/{bookingId}")
	public ResponseEntity<String> cancelBooking(@PathVariable Integer bookingId) throws InfyCabException {
			try {
				Integer message = bookingService.cancelBooking(bookingId);
				String successMessage = environment.getProperty("API.BOOKING_CANCELLED") + message;
				return new ResponseEntity<String>(successMessage, HttpStatus.OK);
			} catch (Exception e) {
				String errorMessage = "Booking cannot cancel " + e.getMessage();
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
			}
			
	}

}
