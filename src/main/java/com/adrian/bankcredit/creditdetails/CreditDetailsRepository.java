package com.adrian.bankcredit.creditdetails;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface CreditDetailsRepository extends CrudRepository<CreditDetails, Long> {

	List<CreditDetails> findAll();

}
