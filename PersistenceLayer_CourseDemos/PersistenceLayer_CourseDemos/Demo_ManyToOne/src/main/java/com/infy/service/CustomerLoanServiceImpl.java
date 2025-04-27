package com.infy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.dto.CustomerDTO;
import com.infy.dto.LoanDTO;
import com.infy.entity.Customer;
import com.infy.entity.Loan;
import com.infy.exception.InfyBankException;
import com.infy.repository.CustomerRepository;
import com.infy.repository.LoanRepository;

@Service(value = "customerLoanService")
@Transactional
public class CustomerLoanServiceImpl implements CustomerLoanService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private LoanRepository loanRepository;
	/**
	 This method calls findById method of loanRepository passing loanId received in parameter
	 @param Integer loanId
	 @return Loan Object returned from loanId method of loanRepository
	 @throws Service.LOAN_UNAVAILABLE exception if Object returned from findById method of loanRepository is null
	 
	 */
	@Override
	public LoanDTO getLoanDetails(Integer loanId) throws InfyBankException {
		Optional<Loan> optional = loanRepository.findById(loanId);
		Loan loan = optional.orElseThrow(()->new InfyBankException("Service.LOAN_UNAVAILABLE"));
		LoanDTO loanDTO = new LoanDTO();
		loanDTO.setAmount(loan.getAmount());
		loanDTO.setLoanId(loan.getLoanId());
		loanDTO.setLoanIssueDate(loan.getIssueDate());
		loanDTO.setStatus(loan.getStatus());
		Customer customer = loan.getCustomer();
		if (customer != null) {
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setCustomerId(customer.getCustomerId());
			customerDTO.setDateOfBirth(customer.getDateOfBirth());
			customerDTO.setEmailId(customer.getEmailId());
			customerDTO.setName(customer.getName());
			loanDTO.setCustomer(customerDTO);
		}
	
		return loanDTO;
	}
	/**
	 This method calls save method of loanRepository passing Loan object received in parameter
	 @param LoanDTO  object
	 @return Integer loanId after calling save method of loanRepository
	
	 */

	@Override
	public Integer addLoanAndCustomer(LoanDTO loanDTO) throws InfyBankException {
		Loan loan = new Loan();
		loan.setAmount(loanDTO.getAmount());
		loan.setIssueDate(loanDTO.getLoanIssueDate());
		loan.setStatus("open");
		CustomerDTO customerDTO = loanDTO.getCustomer();
		Customer customer = new Customer();
		customer.setCustomerId(customerDTO.getCustomerId());
		customer.setDateOfBirth(customerDTO.getDateOfBirth());
		customer.setEmailId(customerDTO.getEmailId());
		customer.setName(customerDTO.getName());
		loan.setCustomer(customer);
		loanRepository.save(loan);
		return loan.getLoanId();
	}
	/**
	 This method first calls findById method of customerRepository passing customerId received in parameter
	 then calls save method of loanRepository passing loan entity feed by LoanDTO Object received in parameter
	 
	 @param Integer customerId, LoanDTO object
	 @return Integer loanId after calling save method of loanRepository
	@throws Service.CUSTOMER_UNAVAILABLE exception if findById method of customerRepository returns null
	 */
	@Override
	public Integer sanctionLoanToExistingCustomer(Integer customerId, LoanDTO loanDTO) throws InfyBankException {
		
		Loan loan = new Loan();
		loan.setAmount(loanDTO.getAmount());
		loan.setIssueDate(loanDTO.getLoanIssueDate());
		loan.setStatus(loanDTO.getStatus());
		Optional<Customer> optional = customerRepository.findById(customerId);
		Customer customer = optional.orElseThrow(()->new InfyBankException("Service.CUSTOMER_UNAVAILABLE"));
		loan.setCustomer(customer);
		loanRepository.save(loan);
		return loan.getLoanId();

	}
	/**
	 This method first calls findById method of loanRepository passing loanId received in parameter
	 then set the status of the loan entity received as closed.
	 
	 @param Integer loanId
	 @throws Service.LOAN_UNAVAILABLE exception if findById method of loanRepository returns null
	 */
	@Override
	public void closeLoan(Integer loanId) throws InfyBankException {
		Optional<Loan> optional = loanRepository.findById(loanId);
		Loan loan = optional.orElseThrow(()->new InfyBankException("Service.LOAN_UNAVAILABLE"));
		loan.setStatus("Closed");
	}
	/**
	 This method first calls findById method of CustomerLoanDAOImpl passing loanId received in parameter
	 then calls delete method of loanRepository passing loan entity object.
	 
	 @param Integer loanId
	 @throws Service.LOAN_UNAVAILABLE exception if findById method of loanRepository returns null
	 */
	public void deleteLoan(Integer loanId) throws InfyBankException{
		Optional<Loan> optional = loanRepository.findById(loanId);
		Loan loan = optional.orElseThrow(()->new InfyBankException("Service.LOAN_UNAVAILABLE"));
		loan.setCustomer(null);
		loanRepository.delete(loan);
	}

}