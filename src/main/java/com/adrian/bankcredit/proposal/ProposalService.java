package com.adrian.bankcredit.proposal;

import java.util.List;
import java.util.Optional;


public interface ProposalService {
	
	Optional<Proposal> findById(Long id);
	List<Proposal> findAll();
	List<Proposal> findAllByCheck(boolean check);
	List<Proposal> findAllByCheckAndVerify(boolean check, boolean verify);
	Proposal save(Proposal proposal);
	void setCheck(Boolean check, Long id);
	void setVerify(Boolean verify, Long id);
	void deleteById(Long id);

}
