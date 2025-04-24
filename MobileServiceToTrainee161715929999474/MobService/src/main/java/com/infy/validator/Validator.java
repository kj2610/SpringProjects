package com.infy.validator;

import java.util.List;

import org.apache.commons.logging.LogFactory;

import com.infy.exception.MobileServiceException;
import com.infy.model.ServiceRequest;

public class Validator {

	public void validate(ServiceRequest service) throws MobileServiceException{	
		//your code goes here
		String errorstatus = !isValidBrand(service.getBrand()) ? "Validator.INVALID_BRAND" : 
								(!isValidIssues(service.getIssues())) ? "Validator.INVALID_NO_OF_ISSUES"
										:(!isValidIMEINumber(service.getiMEINumber())) ? "Validator.INVALID_IMEI_NUMBER"
												:(!isValidContactNumber(service.getContactNumber())) ? "Validator.INVALID_CONTACT_NUMBER"
														:(!isValidCustomerName(service.getCustomerName())) ? "Validator.INVALID_CUSTOMER_NAME"
																:null;
		if(errorstatus != null) {
			MobileServiceException mobileServiceException = new MobileServiceException(errorstatus);
			LogFactory.getLog(this.getClass()).error(mobileServiceException.getMessage(),mobileServiceException);
			throw mobileServiceException;
		}
	}	

	
	// validates the brand
	// brand should always start with a upper case alphabet 
	// and can be followed by one or more alphabets (lower case or upper case) 
	public Boolean isValidBrand(String brand){
		return brand != null && brand.matches("[A-Z][a-zA-Z]+");
	}
	
	
	// validates the list of issues
	// checks if the list is null or empty
	public Boolean isValidIssues(List<String> issues) {
		return issues != null && !issues.isEmpty();
	}

	// validates the IMEINumber
	// it should be a 16 digit number 
	public Boolean isValidIMEINumber(Long iMEINumber) {
		return iMEINumber != null && iMEINumber.toString().length() == 16;
	}
	
	// validates the contact number
	// should contain 10 numeric characters and should not contain 10 repetitive characters
	public Boolean isValidContactNumber(Long contactNumber) {
		if(contactNumber == null) {
			return false;
		}
		String contact = contactNumber.toString();
		if(contact.length() != 10) {
			return false;
		}
		Character ch = contact.charAt(0);
		return contact.chars().anyMatch(c -> c!=ch);
	}
	
	
	// validates the customer name
	// should contain at least one word and each word separated by a single space should contain at least one letter.
	// the first letter of every word should be an upper case character 
	public Boolean isValidCustomerName(String customerName) {
		
		return customerName != null && customerName.matches("([A-Za-z]*)( [A-Za-z]*)*");
	}
}
