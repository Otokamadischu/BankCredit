package com.adrian.bankcredit.creditdetails;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditDetailsServiceImpl implements CreditDetailsService {

	@Autowired
	CreditDetailsRepository creditDetailsRepository;
	
	@Override
	public Optional<CreditDetails> findById(Long id) {
		// TODO Auto-generated method stub
		return creditDetailsRepository.findById(id);
	}

	@Override
	public List<CreditDetails> findAll() {
		// TODO Auto-generated method stub
		return creditDetailsRepository.findAll();
	}

	@Override
	public CreditDetails save(CreditDetails creditDetails) {
		// TODO Auto-generated method stub
		return creditDetailsRepository.save(creditDetails);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		creditDetailsRepository.deleteById(id);
	}

}
