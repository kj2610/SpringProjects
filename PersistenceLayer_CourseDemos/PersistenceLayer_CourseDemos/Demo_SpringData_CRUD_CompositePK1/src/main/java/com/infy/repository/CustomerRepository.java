package com.infy.repository;

import org.springframework.data.repository.CrudRepository;

import com.infy.entity.Customer;
import com.infy.entity.CustomerCompositeId;

public interface CustomerRepository extends CrudRepository<Customer, CustomerCompositeId>{

}

