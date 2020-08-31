package com.adrian.bankcredit.credit;

import java.util.List;
import java.util.Optional;

public interface CreditService {
	
	Optional<Credit> findById(Long id);
	List<Credit> findAll();
	Credit save(Credit credit);

}
