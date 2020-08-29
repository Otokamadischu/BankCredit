package com.adrian.bankcredit.credit;

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

import com.adrian.bankcredit.creditdetails.CreditDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Credit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	@NotNull(message = "Name cannot be null")
	String name;
	
	@Min(value=1, message="LoanMinDuration must be more than 0")
	@Max(value=720, message="LoanMinDuration must be less than 721")
	int loanMinDuration;
	
	@Min(value=1, message="LoanMaxDuration must be more than 0")
	@Max(value=720, message="LoanMaxDuration must be less than 721")
	int loanMaxDuration;
	
	@Column(precision=7, scale=2)
	@Min(value=0, message="Provision must be positive")
	@Max(value=10000, message="Provision must be less than 10001")
	double provision;
	
	@NotNull(message = "MinLoanAmount cannot be null")
	@Min(value=1000, message="MinLoanAmount must be more than 1,000")
	@Max(value=100000000, message="MinLoanAmount must be less than 100,000,000")
	Long minLoanAmount;
	
	@NotNull(message = "MaxLoanAmount cannot be null")
	@Min(value=1000, message="MaxLoanAmount must be more than 1,000")
	@Max(value=100000000, message="MaxLoanAmount must be less than 100,000,000")
	Long maxLoanAmount;

	@OneToMany(mappedBy = "credit", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
	@JsonIgnore
	Set<CreditDetails> creditsDetails;
	
	public Credit() {
		
	}
	
	public Credit(Long id, @NotNull(message = "Name cannot be null") String name,
			@Min(value = 1, message = "LoanMinDuration must be more than 0") @Max(value = 720, message = "LoanMinDuration must be less than 721") int loanMinDuration,
			@Min(value = 1, message = "LoanMaxDuration must be more than 0") @Max(value = 720, message = "LoanMaxDuration must be less than 721") int loanMaxDuration,
			@Min(value = 0, message = "Provision must be positive") @Max(value = 10000, message = "Provision must be less than 10001") double provision,
			@NotNull(message = "MinLoanAmount cannot be null") @Min(value = 1000, message = "MinLoanAmount must be more than 1,000") @Max(value = 100000000, message = "MinLoanAmount must be less than 100,000,000") Long minLoanAmount,
			@NotNull(message = "MaxLoanAmount cannot be null") @Min(value = 1000, message = "MaxLoanAmount must be more than 1,000") @Max(value = 100000000, message = "MaxLoanAmount must be less than 100,000,000") Long maxLoanAmount) {
		super();
		this.id = id;
		this.name = name;
		this.loanMinDuration = loanMinDuration;
		this.loanMaxDuration = loanMaxDuration;
		this.provision = provision;
		this.minLoanAmount = minLoanAmount;
		this.maxLoanAmount = maxLoanAmount;
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

	public int getLoanMinDuration() {
		return loanMinDuration;
	}

	public void setLoanMinDuration(int loanMinDuration) {
		this.loanMinDuration = loanMinDuration;
	}

	public int getLoanMaxDuration() {
		return loanMaxDuration;
	}

	public void setLoanMaxDuration(int loanMaxDuration) {
		this.loanMaxDuration = loanMaxDuration;
	}

	public double getProvision() {
		return provision;
	}

	public void setProvision(double provision) {
		this.provision = provision;
	}

	public Long getMinLoanAmount() {
		return minLoanAmount;
	}

	public void setMinLoanAmount(Long minLoanAmount) {
		this.minLoanAmount = minLoanAmount;
	}

	public Long getMaxLoanAmount() {
		return maxLoanAmount;
	}

	public void setMaxLoanAmount(Long maxLoanAmount) {
		this.maxLoanAmount = maxLoanAmount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creditsDetails == null) ? 0 : creditsDetails.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + loanMaxDuration;
		result = prime * result + loanMinDuration;
		result = prime * result + ((maxLoanAmount == null) ? 0 : maxLoanAmount.hashCode());
		result = prime * result + ((minLoanAmount == null) ? 0 : minLoanAmount.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(provision);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Credit other = (Credit) obj;
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
		if (loanMaxDuration != other.loanMaxDuration)
			return false;
		if (loanMinDuration != other.loanMinDuration)
			return false;
		if (maxLoanAmount == null) {
			if (other.maxLoanAmount != null)
				return false;
		} else if (!maxLoanAmount.equals(other.maxLoanAmount))
			return false;
		if (minLoanAmount == null) {
			if (other.minLoanAmount != null)
				return false;
		} else if (!minLoanAmount.equals(other.minLoanAmount))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(provision) != Double.doubleToLongBits(other.provision))
			return false;
		return true;
	}
	
	

}
