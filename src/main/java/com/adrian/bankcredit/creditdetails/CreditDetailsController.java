package com.adrian.bankcredit.creditdetails;

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

import com.adrian.bankcredit.consumer.Consumer;
import com.adrian.bankcredit.consumer.ConsumerService;
import com.adrian.bankcredit.creditcard.CreditCard;
import com.adrian.bankcredit.proposal.Proposal;
import com.adrian.bankcredit.proposal.ProposalService;

@RestController
public class CreditDetailsController {
	
	@Autowired
	CreditDetailsService creditDetailsService;
	
	@Autowired
	ProposalService proposalService;
	
	@Autowired
	ConsumerService consumerService;
	
	@GetMapping("/bank/creditdetails/{id}")
	public Optional<CreditDetails> getCreditDetails(@PathVariable Long id){
		return creditDetailsService.findById(id);
	}
	
	@GetMapping("/bank/creditdetails")
	public List<CreditDetails> getAllCreditDetails(){
		return creditDetailsService.findAll();
	}
	
	@PostMapping("/bank/creditdetails")
	public ResponseEntity<Void> createCreditDetails(@RequestParam Long proposalId){
		
		Optional<Proposal> proposal = proposalService.findById(proposalId);
		if(!proposal.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Proposal proposalToCD = proposal.get();
		
		
		CreditDetails creditDetails = new CreditDetails();
		creditDetails.setConsumer(proposalToCD.getConsumer());
		creditDetails.setCredit(proposalToCD.getCredit());
		creditDetails.setLoanAmount(proposalToCD.getLoanAmount());
		creditDetails.setLoanStart(proposalToCD.getLoanStart());
		creditDetails.setMonths(proposalToCD.getMonths());
		creditDetails.setCreditCard(new CreditCard(creditDetails.getId(),"4531537617686580",0.0,creditDetails));
		
		creditDetailsService.save(creditDetails);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(creditDetails.getId())
		        .toUri();
		System.out.println("dzialaPost");
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/bank/creditdetails/{id}")
	public void deleteCreditDetails(@PathVariable Long id) {
		creditDetailsService.deleteById(id);
	}
	
}
