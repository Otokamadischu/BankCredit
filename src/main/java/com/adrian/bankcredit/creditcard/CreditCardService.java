package com.adrian.bankcredit.creditcard;

import java.util.List;
import java.util.Optional;



public interface CreditCardService {
	
	Optional<CreditCard> findById(Long id);
	List<CreditCard> findAll();
	CreditCard save(CreditCard creditCard);
	void useMoney(double amount, Long id);
	void deleteById(Long id);

}
