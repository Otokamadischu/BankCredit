package com.adrian.bankcredit.credit;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.adrian.bankcredit.consumer.Consumer;

public class CreditController {

	@Autowired
	CreditService creditService;
	
	@GetMapping("/bank/credit/{id}")
	public Optional<Credit> getCredit(@PathVariable Long id){
		
		if(!creditService.findById(id).isPresent()) {
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "Credit not found"
					);
		}
		
		return creditService.findById(id);
	}
	
	@GetMapping("/bank/credit")
	public List<Credit> getCredits(){
		return creditService.findAll();
	}
	
	@PostMapping("/bank/credit")
	public ResponseEntity<Void> createConsumer(@RequestBody Credit credit){
		
		creditService.save(credit);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(credit.getId())
		        .toUri();
	
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/bank/credit/{id}")
	public ResponseEntity<Void> updateCredit(@RequestBody Credit credit, @PathVariable Long id){
		
		Optional<Credit> existCredit = creditService.findById(id);
		
		if(!existCredit.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		credit.setId(id);
		creditService.save(credit);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(credit.getId())
		        .toUri();
	
		return ResponseEntity.ok().location(uri).build();
	}

}
