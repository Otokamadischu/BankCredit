package com.adrian.bankcredit.creditcard;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class CreditCardController {
	
	@Autowired
	CreditCardService creditCardService;
	
	@GetMapping("/bank/creditcard/{id}")
	public Optional<CreditCard> getCreditCard(@PathVariable Long id){
		return creditCardService.findById(id);
	}
	
	@GetMapping("/bank/creditcard")
	public List<CreditCard> getAllCreditCard(){
		return creditCardService.findAll();
	}
	
	@PutMapping("/bank/creditcard/{id}/addmoney")
	public ResponseEntity<Void> addMoney(@PathVariable Long id, @RequestParam Long money){
		
		Optional<CreditCard> existCreditCard = creditCardService.findById(id);
		
		if(!existCreditCard.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		CreditCard creditCard = existCreditCard.get();
		
		creditCardService.useMoney(creditCard.getAmount()+money, id);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id)
		        .toUri();
	
		return ResponseEntity.ok().location(uri).build();
	}
	
	@PutMapping("/bank/creditcard/{id}/usemoney")
	public ResponseEntity<Void> useMoney(@PathVariable Long id, @RequestParam Long money){
		
		Optional<CreditCard> existCreditCard = creditCardService.findById(id);
		
		if(!existCreditCard.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		CreditCard creditCard = existCreditCard.get();
		if(creditCard.getAmount()-money>0)
			creditCardService.useMoney(creditCard.getAmount()-money, id);
		else {
			ResponseEntity.badRequest().build();
		}
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id)
		        .toUri();
	
		return ResponseEntity.ok().location(uri).build();
	}
}
