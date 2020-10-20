package com.adrian.bankcredit.proposal;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProposalServiceImpl implements ProposalService {
	
	@Autowired
	ProposalRepository proposalRepository;

	@Override
	public Optional<Proposal> findById(Long id) {
		// TODO Auto-generated method stub
		return proposalRepository.findById(id);
	}
	
	@Override
	public List<Proposal> findAll() {
		// TODO Auto-generated method stub
		return proposalRepository.findAll();
	}

	@Override
	public List<Proposal> findAllByCheck(boolean check) {
		// TODO Auto-generated method stub
		return proposalRepository.findByChecked(check);
	}

	@Override
	public List<Proposal> findAllByCheckAndVerify(boolean check, boolean verify) {
		// TODO Auto-generated method stub
		return proposalRepository.findByCheckedAndVerify(check, verify);
	}

	@Override
	public Proposal save(Proposal proposal) {
		// TODO Auto-generated method stub
		return proposalRepository.save(proposal);
	}
	
	@Override
	public void setCheck(Boolean check, Long id) {
		// TODO Auto-generated method stub
		proposalRepository.setChecked(check, id);
	}
	
	@Override
	public void setVerify(Boolean verify, Long id) {
		// TODO Auto-generated method stub
		proposalRepository.setVerify(verify, id);
	}	

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		proposalRepository.deleteById(id);
	}



}
