package com.infy.test;

import com.infy.exception.InfyAcademyException;
import com.infy.model.Candidate;
import com.infy.service.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;


public class CandidateServiceTest {
	private CandidateService candidateService = new CandidateServiceImpl(); 
	
	@Test
	public void addCandidateInvalidCandidateID() throws InfyAcademyException {
		//Code here
		Candidate candidate = new Candidate();
		candidate.setCandidateName("John");
        candidate.setCandidateId(123);  // Invalid: ID length < 5
        candidate.setDepartment("CSE");
        candidate.setExamDate(LocalDate.of(2024, 3, 1));
        candidate.setMark1(60);
        candidate.setMark2(70);
        candidate.setMark3(80);
        candidate.setResult('P');
		assertThrows(InfyAcademyException.class , () -> candidateService.addCandidate(candidate));
		
	}
	
	@Test
	public void addCandidateInvalidDepartment() throws InfyAcademyException {
		//Code here
		Candidate candidate = new Candidate();
		 candidate.setCandidateName("Alice");
	        candidate.setCandidateId(12345);
	        candidate.setDepartment("BIO");  // Invalid department
	        candidate.setExamDate(LocalDate.of(2024, 3, 1));
	        candidate.setMark1(60);
	        candidate.setMark2(70);
	        candidate.setMark3(80);
	        candidate.setResult('P');
		
		assertThrows(InfyAcademyException.class, () -> candidateService.addCandidate(candidate));
		
	}
	
	@Test
	public void addCandidateInvalidResult() throws InfyAcademyException {
		//Code here
		Candidate candidate = new Candidate();
        candidate.setCandidateName("David");
        candidate.setCandidateId(12345);
        candidate.setDepartment("IT");
        candidate.setExamDate(LocalDate.of(2024, 3, 1));
        candidate.setMark1(30);  // Should result in 'F'
        candidate.setMark2(70);
        candidate.setMark3(80);
        candidate.setResult('P');  // Incorrectly set to 'P'

        assertThrows(InfyAcademyException.class, () -> {
            candidateService.addCandidate(candidate);
        });
	}
	
}
