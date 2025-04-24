package com.infy.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.logging.LogFactory;

import com.infy.dao.MobileServiceDAO;
import com.infy.dao.MobileServiceDAOImpl;
import com.infy.exception.MobileServiceException;
import com.infy.model.ServiceReport;
import com.infy.model.ServiceRequest;
import com.infy.model.Status;
import com.infy.validator.Validator;

public class MobileServiceImpl implements MobileService{
	
	private MobileServiceDAO dao=new MobileServiceDAOImpl();
	
	private Validator validator=new Validator();
	
	@Override
	public ServiceRequest registerRequest(ServiceRequest service) throws MobileServiceException {
		try {
			validator.validate(service);
			float value = calculateEstimateCost(service.getIssues());
			if(value <= 0) {
				throw new MobileServiceException("Service.INVALID_ISSUES");
			}
			service.setServiceFee(value);
			service.setStatus(Status.ACCEPTED);
			service.setTimeOfRequest(LocalDateTime.now());
			ServiceRequest serviceRequest = dao.registerRequest(service);
			return serviceRequest;
			
		} catch (MobileServiceException exception) {
			if(exception.getMessage().contains("Validator")) {
				LogFactory.getLog(this.getClass()).debug(exception);
			}
			throw exception;
			
		}
	}

	@Override
	public Float calculateEstimateCost(List<String> issues) throws MobileServiceException {
		float totalvalue = 0;
		for(String issue:issues) {
			if(issue.equalsIgnoreCase("BATTERY"))  {
				totalvalue+=10;
			}
			else if(issue.equalsIgnoreCase("CAMERA")) {
				totalvalue+=5;
			}
			else if(issue.equalsIgnoreCase("SCREEN")) {
				totalvalue+=15;
			}
		}
		return totalvalue;
	}

	@SuppressWarnings("null")
	@Override
	public List<ServiceReport> getServices(Status status) throws MobileServiceException {
		List<ServiceRequest> serviceRequest =  dao.getServices();
		serviceRequest.stream().forEach(s-> System.out.println(s.getStatus()));
		  List<ServiceReport> reports = serviceRequest.stream()
			        .filter(req -> req.getStatus().equals(status))
			        .map(req -> {
			            ServiceReport report = new ServiceReport();
			            report.setServiceId(req.getServiceId());
			            report.setBrand(req.getBrand());
			            report.setIssues(req.getIssues());
			            report.setServiceFee(req.getServiceFee());
			            return report;
			        })
			        .collect(Collectors.toList());
//		for(ServiceRequest serviceRequest1: serviceRequest) {
//			if(serviceRequest1.getStatus() == status) {
//				ServiceReport ser = new ServiceReport();
//				ser.setServiceId(serviceRequest1.getServiceId());
//				ser.setIssues(serviceRequest1.getIssues());
//				ser.setBrand(serviceRequest1.getBrand());
//				ser.setServiceFee(serviceRequest1.getServiceFee());
//				serviceReport.add(ser);
//			}
//		}
		  reports.stream().forEach(s-> System.out.println(s.getServiceId()));
		return reports;
	}

}
