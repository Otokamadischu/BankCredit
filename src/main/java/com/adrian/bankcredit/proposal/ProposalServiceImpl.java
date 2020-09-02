package com.adrian.bankcredit.proposal;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

public class ProposalServiceImpl implements ProposalService {
	
	@Autowired
	ProposalRepository proposalRepository;

	@Override
	public Optional<Proposal> findById(Long id) {
		// TODO Auto-generated method stub
		return proposalRepository.findById(id);
	}

	@Override
	public List<Proposal> findAllByCheck(boolean check) {
		// TODO Auto-generated method stub
		return proposalRepository.findByCheck(check);
	}

	@Override
	public List<Proposal> findAllByCheckAndVerify(boolean check, boolean verify) {
		// TODO Auto-generated method stub
		return proposalRepository.findByCheckAndVerify(check, verify);
	}

	@Override
	public Proposal save(Proposal proposal) {
		// TODO Auto-generated method stub
		return proposalRepository.save(proposal);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		proposalRepository.deleteById(id);
	}

}
