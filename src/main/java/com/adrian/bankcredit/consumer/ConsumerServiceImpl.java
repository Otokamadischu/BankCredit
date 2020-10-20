package com.adrian.bankcredit.consumer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl implements ConsumerService {
	
	@Autowired
	private ConsumerRepository consumerRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Optional<Consumer> findById(Long id) {
		// TODO Auto-generated method stub
		return consumerRepository.findById(id);
	}

	@Override
	public List<Consumer> findAll() {
		return (List<Consumer>) consumerRepository.findAll();
	}

	@Override
	public Consumer save(Consumer consumer) {
		
		consumer.setPassword(bCryptPasswordEncoder.encode(consumer.getPassword()));
		consumer.setActive(true);
		return consumerRepository.save(consumer);
	}

	@Override
	public void deleteById(Long id) {
		consumerRepository.deleteById(id);
	}

}
