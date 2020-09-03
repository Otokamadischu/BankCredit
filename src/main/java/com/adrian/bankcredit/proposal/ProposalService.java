package com.adrian.bankcredit.proposal;

import java.util.List;
import java.util.Optional;


public interface ProposalService {
	
	Optional<Proposal> findById(Long id);
	List<Proposal> findAllByCheck(boolean check);
	List<Proposal> findAllByCheckAndVerify(boolean check, boolean verify);
	Proposal save(Proposal proposal);
	void setCheck(boolean check, Long id);
	void setVerify(boolean verify, Long id);
	void delete(Long id);

}
