package com.adrian.bankcredit.proposal;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProposalRepository extends CrudRepository<Proposal, Long> {

	List<Proposal> findByCheck(boolean check);

	List<Proposal> findByCheckAndVerify(boolean check, boolean verify);

}
