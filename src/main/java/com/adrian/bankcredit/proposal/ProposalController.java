package com.adrian.bankcredit.proposal;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.adrian.bankcredit.consumer.Consumer;
import com.adrian.bankcredit.consumer.ConsumerService;

public class ProposalController {
	/*
	 * 	List<Proposal> findAllNotChecked();
	List<Proposal> findAllCheckedAndNotVerified();
	List<Proposal> findAllCheckedAndVerified();
	 */
	@Autowired
	ProposalService proposalService;
	
	@Autowired
	ConsumerService consumerService;
	
	@GetMapping("/bank/proposal/{id}")
	public Optional<Proposal> getProposal(@PathVariable Long id){
		return proposalService.findById(id);
	}
	
	@GetMapping("/bank/proposal")
	public List<Proposal> getAllProposals(){
		return proposalService.findAll();
	}
	
	@GetMapping("/bank/proposal/notchecked")
	public List<Proposal> getAllNotChecked(){
		return proposalService.findAllByCheck(false);
	}
	
	@GetMapping("/bank/proposal/notverified")
	public List<Proposal> findAllCheckedAndNotVerified(){
		return proposalService.findAllByCheckAndVerify(true, false);
	}
	
	@GetMapping("/bank/proposal/verified")
	public List<Proposal> findAllCheckedAndVerified(){
		return proposalService.findAllByCheckAndVerify(true, true);
	}

	@PostMapping("/bank/proposal")
	public ResponseEntity<Void> createProposal(@RequestBody Proposal proposal, @RequestParam String creditName, @RequestParam Long consumerId){
		
		Optional<Consumer> consumer = consumerService.findById(consumerId);
		if(!consumer.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Consumer consumerToEntity = consumer.get();
		proposal.setConsumer(consumerToEntity);
		proposalService.save(proposal);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(proposal.getId())
		        .toUri();
	
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/bank/proposal/{id}")
	public ResponseEntity<Void> updateProposal(@RequestBody Proposal proposal, @PathVariable Long id){
		
		Optional<Proposal> existProposal = proposalService.findById(id);
		
		if(!existProposal.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Proposal proposalToEntity = existProposal.get();
		proposalToEntity.setLoanAmount(proposal.getLoanAmount());
		proposalToEntity.setLoanStart(proposal.getLoanStart());
		proposalToEntity.setMonths(proposal.getMonths());
		
		proposalService.save(proposalToEntity);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(proposalToEntity.getId())
		        .toUri();
	
		return ResponseEntity.ok().location(uri).build();
	}
	
	@PutMapping("/bank/proposal/{id}/check")
	public ResponseEntity<Void> setCheck(@PathVariable Long id, boolean check){
		
		Optional<Proposal> existProposal = proposalService.findById(id);
		
		if(!existProposal.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		proposalService.setCheck(check, id);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id)
		        .toUri();
	
		return ResponseEntity.ok().location(uri).build();
	}
	
	@PutMapping("/bank/proposal/{id}/verify")
	public ResponseEntity<Void> setVerify(@PathVariable Long id, boolean verify){
		
		Optional<Proposal> existProposal = proposalService.findById(id);
		
		if(!existProposal.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		proposalService.setVerify(verify, id);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id)
		        .toUri();
	
		return ResponseEntity.ok().location(uri).build();
	}
	
	@DeleteMapping("/bank/proposal/{id}")
	public void deleteProposal(@PathVariable Long id) {
		proposalService.deleteById(id);
	}

}
