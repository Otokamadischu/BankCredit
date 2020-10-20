package com.adrian.bankcredit.bankresources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.adrian.bankcredit.creditdetails.CreditDetails;
import com.adrian.bankcredit.proposal.Proposal;

@RestController
public class BankResourcesController {
	
	@Autowired
	BankResourcesService bankResourcesService;
	
	@GetMapping("/bank/bankresources/{id}")
	public Optional<BankResources> getBankResources(@PathVariable Long id) {
		return bankResourcesService.findById(id);
	}
	
	@GetMapping("/bank/bankresources")
	public List<BankResources> getAllBankResources(){
		return bankResourcesService.findAll();
	}
	
	@PostMapping("/bank/bankresources")
	public ResponseEntity<Void> createBankResources(@RequestBody BankResources bankResources){
		
		bankResourcesService.save(bankResources);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(bankResources.getId())
		        .toUri();
	
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/bank/bankresources/{id}/addmoney")
	public ResponseEntity<Void> addMoney(@PathVariable Long id, @RequestParam Long money){
		
		Optional<BankResources> existBankResources = bankResourcesService.findById(id);
		
		if(!existBankResources.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		BankResources bankResources = existBankResources.get();
		
		bankResourcesService.useMoney(bankResources.getMoney()+money,id);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id)
		        .toUri();
	
		return ResponseEntity.ok().location(uri).build();
	}
	
	@PutMapping("/bank/bankresources/{id}/usemoney")
	public ResponseEntity<Void> useMoney(@PathVariable Long id, @RequestParam Long money){
		
		Optional<BankResources> existBankResources = bankResourcesService.findById(id);
		
		if(!existBankResources.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		BankResources bankResources = existBankResources.get();
		
		if(bankResources.getMoney()-money>0) {
			bankResourcesService.useMoney(bankResources.getMoney()-money,id);
		}
		else {
			ResponseEntity.badRequest().build();
		}
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id)
		        .toUri();
	
		return ResponseEntity.ok().location(uri).build();
	}
	
	@DeleteMapping("/bank/bankresources/{id}")
	public void deleteBankResources(@PathVariable Long id) {
		
		Optional<BankResources> bankResources = bankResourcesService.findById(id);
		if(!bankResources.isPresent()) {
			
		}
		else {
			BankResources checkBankResources = bankResources.get();
			if(checkBankResources.getMoney()==0) {
				bankResourcesService.deleteById(id);
			}
		}
		
		
	}

}
