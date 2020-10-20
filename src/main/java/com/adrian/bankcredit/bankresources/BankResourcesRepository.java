package com.adrian.bankcredit.bankresources;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BankResourcesRepository extends CrudRepository<BankResources, Long> {
	
	@Modifying
	@Query("update BankResources b set b.money = ?1 where b.id = ?2")
	void useMoney(Long amount, Long id);
	
	List<BankResources> findAll();

}
