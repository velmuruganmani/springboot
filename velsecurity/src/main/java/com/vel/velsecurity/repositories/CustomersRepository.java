package com.vel.velsecurity.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.vel.velsecurity.entities.Customers;

@Repository
public interface CustomersRepository extends CrudRepository<Customers, String>{

	Customers findByCustomerloginid(String username);
}
