package com.adrian.bankcredit.credit;

import java.util.List;
import java.util.Optional;

public class CreditServiceImpl implements CreditService {

	private CreditRepository creditRepository;
	
	@Override
	public Optional<Credit> findById(Long id) {
		// TODO Auto-generated method stub
		return creditRepository.findById(id);
	}

	@Override
	public List<Credit> findAll() {
		// TODO Auto-generated method stub
		return (List<Credit>) creditRepository.findAll();
	}

	@Override
	public Credit save(Credit credit) {
		// TODO Auto-generated method stub
		return creditRepository.save(credit);
	}

}
