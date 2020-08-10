package com.adrian.bankcredit.consumer;

import java.util.List;
import java.util.Optional;

public class ConsumerServiceImpl implements ConsumerService {
	
	private ConsumerRepository consumerRepository;

	public ConsumerServiceImpl(ConsumerRepository consumerRepository) {
		this.consumerRepository = consumerRepository;
	}

	@Override
	public Optional<Consumer> findById(Long id) {
		// TODO Auto-generated method stub
		return consumerRepository.findById(id);
	}

	@Override
	public List<Consumer> findAll() {
		// TODO Auto-generated method stub
		return (List<Consumer>) consumerRepository.findAll();
	}

	@Override
	public Consumer save(Consumer consumer) {
		// TODO Auto-generated method stub
		return consumerRepository.save(consumer);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		consumerRepository.deleteById(id);
	}

}
