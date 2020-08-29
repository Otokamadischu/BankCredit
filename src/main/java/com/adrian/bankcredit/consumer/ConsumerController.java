package com.adrian.bankcredit.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
public class ConsumerController {
	
	@Autowired
	ConsumerService consumerService;
	
	
	@GetMapping("/bank/consumer/{id}")
	public Optional<Consumer> getConsumer(@PathVariable Long id){
		
		if(!consumerService.findById(id).isPresent()) {
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "Consumer not found"
					);
		}
		
		return consumerService.findById(id);
	}
	
	@GetMapping("/bank/consumers")
	public List<Consumer> getConsumers(){
		return consumerService.findAll();
	}
	
	@PostMapping("/bank/consumer")
	public ResponseEntity<Void> createConsumer(@RequestBody Consumer consumer){
		
		consumerService.save(consumer);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(consumer.getId())
		        .toUri();
	
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/bank/consumer/{id}")
	public ResponseEntity<Void> updateConsumerAdvance(@RequestBody Consumer consumer, @PathVariable Long id){
		
		Optional<Consumer> existConsumer = consumerService.findById(id);
		
		if(!existConsumer.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		consumer.setId(id);
		consumerService.save(consumer);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(consumer.getId())
		        .toUri();
	
		return ResponseEntity.ok().location(uri).build();
	}
	
	@DeleteMapping("/bank/consumer/{id}")
	public void deleteConsumer(@PathVariable Long id) {
		consumerService.deleteById(id);
	}
}
