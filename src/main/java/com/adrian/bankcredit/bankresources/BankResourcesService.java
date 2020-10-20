package com.adrian.bankcredit.bankresources;

import java.util.List;
import java.util.Optional;


public interface BankResourcesService {
	
	Optional<BankResources> findById(Long id);
	List<BankResources> findAll();
	BankResources save(BankResources bankResources);
	void useMoney(Long amount, Long id);
	void deleteById(Long id);

}
