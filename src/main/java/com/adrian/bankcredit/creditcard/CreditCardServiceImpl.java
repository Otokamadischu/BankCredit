package com.adrian.bankcredit.creditcard;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditCardServiceImpl implements CreditCardService{

	@Autowired
	CreditCardRepository creditCardRepository;
	
	@Override
	public Optional<CreditCard> findById(Long id) {
		// TODO Auto-generated method stub
		return creditCardRepository.findById(id);
	}

	@Override
	public List<CreditCard> findAll() {
		// TODO Auto-generated method stub
		return creditCardRepository.findAll();
	}

	@Override
	public CreditCard save(CreditCard creditCard) {
		// TODO Auto-generated method stub
		return creditCardRepository.save(creditCard);
	}
	
	@Override
	public void useMoney(double amount, Long id) {
		
		creditCardRepository.useMoney(amount, id);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		creditCardRepository.deleteById(id);
	}

}
