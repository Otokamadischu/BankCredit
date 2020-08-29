package com.adrian.bankcredit.consumer;

public class ConsumerDtoAdvance {
	
	Long id;
	String name;
	String lastName;
	Long pesel;
	
	
	public ConsumerDtoAdvance() {}
	
	public ConsumerDtoAdvance(Long id, String name, String lastName, Long pesel) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.pesel = pesel;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Long getPesel() {
		return pesel;
	}
	public void setPesel(Long pesel) {
		this.pesel = pesel;
	}
	
	

}
