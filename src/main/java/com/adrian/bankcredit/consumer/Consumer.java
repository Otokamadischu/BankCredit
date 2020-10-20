package com.adrian.bankcredit.consumer;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.adrian.bankcredit.creditdetails.CreditDetails;
import com.adrian.bankcredit.proposal.Proposal;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Consumer {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	@NotNull(message = "Login cannot be null")
	@Column(unique=true)
	@Size(min=6, max=15, message = "Login must be between 6 and 15 characters")
	String login;
	
	@NotNull(message = "Password cannot be null")
	String password;
	
	@NotNull(message = "Role cannot be null")
	@Size(min=4, max=15, message = "Role must be between 4 and 15 characters")
	String role;
	
	@NotNull(message = "Name cannot be null")
	@Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
	String name;
	
	@NotNull(message = "LastName cannot be null")
	@Size(min = 2, max = 100, message = "LastName must be between 2 and 100 characters")
	String lastName;
	
	@NotNull(message = "Pesel cannot be null")
	@Min(value = 10000000000L, message = "Pesel must have 11 digits")
	@Max(value = 99999999999L, message = "Pesel must have 11 digits")
	Long pesel;
	
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	boolean active;
	
	@OneToMany(mappedBy = "consumer", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
	@JsonIgnore
	Set<CreditDetails> creditsDetails;
	
	@OneToMany(mappedBy = "consumer", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
	@JsonIgnore
	Set<Proposal> proposals;
	
	
	public Consumer() {}


	public Consumer(Long id,
			@NotNull(message = "Login cannot be null") @Size(min = 6, max = 15, message = "Login must be between 6 and 15 characters") String login,
			@NotNull(message = "Password cannot be null") String password,
			@NotNull(message = "Role cannot be null") @Size(min = 4, max = 15, message = "Role must be between 4 and 15 characters") String role,
			@NotNull(message = "Name cannot be null") @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters") String name,
			@NotNull(message = "LastName cannot be null") @Size(min = 2, max = 100, message = "LastName must be between 2 and 100 characters") String lastName,
			@NotNull(message = "Pesel cannot be null") @Min(value = 10000000000L, message = "Pesel must have 11 digits") @Max(value = 99999999999L, message = "Pesel must have 11 digits") Long pesel,
			boolean active) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.role = role;
		this.name = name;
		this.lastName = lastName;
		this.pesel = pesel;
		this.active = active;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
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


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public Set<CreditDetails> getCreditsDetails() {
		return creditsDetails;
	}


	public void setCreditsDetails(Set<CreditDetails> creditsDetails) {
		this.creditsDetails = creditsDetails;
	}


	public Set<Proposal> getProposals() {
		return proposals;
	}


	public void setProposals(Set<Proposal> proposals) {
		this.proposals = proposals;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((creditsDetails == null) ? 0 : creditsDetails.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((pesel == null) ? 0 : pesel.hashCode());
		result = prime * result + ((proposals == null) ? 0 : proposals.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consumer other = (Consumer) obj;
		if (active != other.active)
			return false;
		if (creditsDetails == null) {
			if (other.creditsDetails != null)
				return false;
		} else if (!creditsDetails.equals(other.creditsDetails))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (pesel == null) {
			if (other.pesel != null)
				return false;
		} else if (!pesel.equals(other.pesel))
			return false;
		if (proposals == null) {
			if (other.proposals != null)
				return false;
		} else if (!proposals.equals(other.proposals))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}




	
}
