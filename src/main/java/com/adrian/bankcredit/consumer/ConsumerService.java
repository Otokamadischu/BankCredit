package com.adrian.bankcredit.consumer;

import java.util.List;
import java.util.Optional;

public interface ConsumerService {
	
    Optional<Consumer> findById(Long id);
    List<Consumer> findAll();
    Consumer save(Consumer consumer);
    void deleteById(Long id);

}
