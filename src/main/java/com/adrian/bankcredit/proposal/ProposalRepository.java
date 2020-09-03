package com.adrian.bankcredit.proposal;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProposalRepository extends CrudRepository<Proposal, Long> {

	List<Proposal> findAll();
	
	List<Proposal> findByCheck(boolean check);

	List<Proposal> findByCheckAndVerify(boolean check, boolean verify);
	
	@Modifying
	@Query("update Proposal p set p.check = ?1 where p.id = ?2")
	void setCheck(boolean check, Long id);

	@Modifying
	@Query("update Proposal p set p.verify = ?1 where p.id = ?2")
	void setVerify(boolean verify, Long id);
}
