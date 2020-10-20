package com.adrian.bankcredit.creditcard;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CreditCardRepository extends CrudRepository<CreditCard, Long>{

	//Optional<CreditCard> findById(Long id);
	
	@Modifying
	@Query("update CreditCard c set c.amount = ?1 where c.id = ?2")
	void useMoney(double money, Long id);
	
	List<CreditCard> findAll();
	
	void deletetById(Long id);
	
}
