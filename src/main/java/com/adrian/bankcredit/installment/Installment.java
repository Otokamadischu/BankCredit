package com.adrian.bankcredit.installment;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.adrian.bankcredit.creditdetails.CreditDetails;

@Entity
public class Installment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@NotNull(message="AmountRequired cannot be null")
	@Column(precision=11, scale=2)
	@Min(value=0, message="AmountRequired must be positive")
	@Max(value=100000000, message="AmountRequired must be less than 100,000,000")
	double amountRequired;
	
	@Column(precision=11, scale=2)
	@Min(value=0, message="AmountPaid must be positive")
	@Max(value=100000000, message="AmountPaid must be less than 100,000,000")
	double amountPaid;
	
	@NotNull(message = "InstallmentDate cannot be null")
	@Temporal(TemporalType.TIMESTAMP)
	Date installmentDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	Date installmentPaid;
	
	@NotNull(message = "CreditDetails connot be null")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "creditDetailsId", nullable = false)
    CreditDetails creditDetails;
	
	public Installment() {
		
	}
	
	

	public Installment(Long id,
			@NotNull(message = "AmountRequired cannot be null") @Min(value = 0, message = "AmountRequired must be positive") @Max(value = 100000000, message = "AmountRequired must be less than 100,000,000") double amountRequired,
			@Min(value = 0, message = "AmountPaid must be positive") @Max(value = 100000000, message = "AmountPaid must be less than 100,000,000") double amountPaid,
			@NotNull(message = "InstallmentDate cannot be null") Date installmentDate, Date installmentPaid,
			@NotNull(message = "CreditDetails connot be null") CreditDetails creditDetails) {
		super();
		this.id = id;
		this.amountRequired = amountRequired;
		this.amountPaid = amountPaid;
		this.installmentDate = installmentDate;
		this.installmentPaid = installmentPaid;
		this.creditDetails = creditDetails;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getAmountRequired() {
		return amountRequired;
	}

	public void setAmountRequired(double amountRequired) {
		this.amountRequired = amountRequired;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public Date getInstallmentDate() {
		return installmentDate;
	}

	public void setInstallmentDate(Date installmentDate) {
		this.installmentDate = installmentDate;
	}

	public Date getInstallmentPaid() {
		return installmentPaid;
	}

	public void setInstallmentPaid(Date installmentPaid) {
		this.installmentPaid = installmentPaid;
	}

	public CreditDetails getCreditDetails() {
		return creditDetails;
	}

	public void setCreditDetails(CreditDetails creditDetails) {
		this.creditDetails = creditDetails;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amountPaid);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(amountRequired);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((creditDetails == null) ? 0 : creditDetails.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((installmentDate == null) ? 0 : installmentDate.hashCode());
		result = prime * result + ((installmentPaid == null) ? 0 : installmentPaid.hashCode());
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
		Installment other = (Installment) obj;
		if (Double.doubleToLongBits(amountPaid) != Double.doubleToLongBits(other.amountPaid))
			return false;
		if (Double.doubleToLongBits(amountRequired) != Double.doubleToLongBits(other.amountRequired))
			return false;
		if (creditDetails == null) {
			if (other.creditDetails != null)
				return false;
		} else if (!creditDetails.equals(other.creditDetails))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (installmentDate == null) {
			if (other.installmentDate != null)
				return false;
		} else if (!installmentDate.equals(other.installmentDate))
			return false;
		if (installmentPaid == null) {
			if (other.installmentPaid != null)
				return false;
		} else if (!installmentPaid.equals(other.installmentPaid))
			return false;
		return true;
	}
	
	
	
}
