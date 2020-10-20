package com.adrian.bankcredit.proposal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.adrian.bankcredit.consumer.Consumer;
import com.adrian.bankcredit.credit.Credit;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Proposal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@NotNull(message = "LoanAmount cannot be null")
	@Min(value=1000, message="LoanAmount must be more than 1,000")
	@Max(value=100000000, message="LoanAmount must be less than 100,000,000")
	Long loanAmount;
	
	@NotNull(message = "LoanStart cannot be null")
	@Temporal(TemporalType.TIMESTAMP)
	Date loanStart;

	@Min(value=1, message="Months must be more than 0")
	@Max(value=720, message="Months must be less than 721")
	int months;
	
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	boolean checked;
	
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	boolean verify;
	
	@NotNull(message = "Consumer cannot be null")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "consumerId", nullable = false)
	@JsonIgnore
    Consumer consumer;
	
	@NotNull(message = "Credit cannot be null")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "creditId", nullable = false)
	@JsonIgnore
    Credit credit;
	
	public Proposal() {}

	public Proposal(Long id,
			@NotNull(message = "LoanAmount cannot be null") @Min(value = 1000, message = "LoanAmount must be more than 1,000") @Max(value = 100000000, message = "LoanAmount must be less than 100,000,000") Long loanAmount,
			@NotNull(message = "LoanStart cannot be null") Date loanStart,
			@Min(value = 1, message = "Months must be more than 0") @Max(value = 720, message = "Months must be less than 721") int months,
			boolean checked, boolean verify, @NotNull(message = "Consumer cannot be null") Consumer consumer,
			@NotNull(message = "Credit cannot be null") Credit credit) {
		super();
		this.id = id;
		this.loanAmount = loanAmount;
		this.loanStart = loanStart;
		this.months = months;
		this.checked = checked;
		this.verify = verify;
		this.consumer = consumer;
		this.credit = credit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Long loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Date getLoanStart() {
		return loanStart;
	}

	public void setLoanStart(Date loanStart) {
		this.loanStart = loanStart;
	}

	public int getMonths() {
		return months;
	}

	public void setMonths(int months) {
		this.months = months;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setCheck(boolean checked) {
		this.checked = checked;
	}

	public boolean isVerify() {
		return verify;
	}

	public void setVerify(boolean verify) {
		this.verify = verify;
	}

	public Consumer getConsumer() {
		return consumer;
	}

	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}

	public Credit getCredit() {
		return credit;
	}

	public void setCredit(Credit credit) {
		this.credit = credit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (checked ? 1231 : 1237);
		result = prime * result + ((consumer == null) ? 0 : consumer.hashCode());
		result = prime * result + ((credit == null) ? 0 : credit.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((loanAmount == null) ? 0 : loanAmount.hashCode());
		result = prime * result + ((loanStart == null) ? 0 : loanStart.hashCode());
		result = prime * result + months;
		result = prime * result + (verify ? 1231 : 1237);
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
		Proposal other = (Proposal) obj;
		if (checked != other.checked)
			return false;
		if (consumer == null) {
			if (other.consumer != null)
				return false;
		} else if (!consumer.equals(other.consumer))
			return false;
		if (credit == null) {
			if (other.credit != null)
				return false;
		} else if (!credit.equals(other.credit))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (loanAmount == null) {
			if (other.loanAmount != null)
				return false;
		} else if (!loanAmount.equals(other.loanAmount))
			return false;
		if (loanStart == null) {
			if (other.loanStart != null)
				return false;
		} else if (!loanStart.equals(other.loanStart))
			return false;
		if (months != other.months)
			return false;
		if (verify != other.verify)
			return false;
		return true;
	}

	
}
