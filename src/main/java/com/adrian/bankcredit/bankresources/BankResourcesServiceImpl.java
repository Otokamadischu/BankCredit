package com.adrian.bankcredit.bankresources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankResourcesServiceImpl implements BankResourcesService{

	@Autowired
	BankResourcesRepository bankResourcesRepository;
	
	@Override
	public Optional<BankResources> findById(Long id) {
		// TODO Auto-generated method stub
		return bankResourcesRepository.findById(id);
	}

	@Override
	public List<BankResources> findAll() {
		// TODO Auto-generated method stub
		return bankResourcesRepository.findAll();
	}

	@Override
	public BankResources save(BankResources bankResources) {
		// TODO Auto-generated method stub
		return bankResourcesRepository.save(bankResources);
	}

	@Override
	public void useMoney(Long amount, Long id) {
		// TODO Auto-generated method stub
		bankResourcesRepository.useMoney(amount,id);
	}
	
	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		bankResourcesRepository.deleteById(id);
	}

}
