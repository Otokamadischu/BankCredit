package com.adrian.bankcredit.creditdetails;

import java.util.List;
import java.util.Optional;


public interface CreditDetailsService {
	
	Optional<CreditDetails> findById(Long id);
	List<CreditDetails> findAll();
	CreditDetails save(CreditDetails creditDetails);
	void deleteById(Long id);


}
