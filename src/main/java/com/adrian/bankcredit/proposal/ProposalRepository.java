package com.adrian.bankcredit.proposal;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;



public interface ProposalRepository extends CrudRepository<Proposal, Long> {
	
	Optional<Proposal> findById(Long id);
	
	List<Proposal> findAll();
	
	List<Proposal> findByChecked(boolean check);

	List<Proposal> findByCheckedAndVerify(boolean check, boolean verify);
	
	@Modifying
	@Query("update Proposal p set p.checked = ?1 where p.id = ?2")
	void setChecked(Boolean checked, Long id);

	@Modifying
	@Query("update Proposal p set p.verify = ?1 where p.id = ?2")
	void setVerify(Boolean verify, Long id);
}

