package com.adrian.bankcredit.bankresources;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class BankResources {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	@NotNull(message = "Name cannot be null")
	@Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
	String name;
	
	@NotNull(message = "Money cannot be null")
	Long money;
	
	@NotNull(message = "Active cannot be null")
	Boolean active;
}
