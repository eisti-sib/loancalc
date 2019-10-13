package org.bouche.training.bank.loancalc;

import java.math.BigDecimal;

public class LoanCalculationResult {

	private int loanDuration;
	private BigDecimal lastInstallment;	
	private BigDecimal apr;

	public int getLoanDuration() {
		return loanDuration;
	}

	public void setLoanDuration(int loanDuration) {
		this.loanDuration = loanDuration;
	}

	public BigDecimal getLastInstallment() {
		return lastInstallment;
	}

	public void setLastInstallment(BigDecimal lastInstallment) {
		this.lastInstallment = lastInstallment;
	}

	public BigDecimal getApr() {
		return apr;
	}

	public void setApr(BigDecimal apr) {
		this.apr = apr;
	}

	public LoanCalculationResult(int loanDuration, BigDecimal lastInstallment, BigDecimal apr) {
		super();
		this.loanDuration = loanDuration;
		this.lastInstallment = lastInstallment;
		this.apr = apr;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof LoanCalculationResult) {
			LoanCalculationResult result = (LoanCalculationResult) obj;
			if (
					result.loanDuration == this.loanDuration &&
					result.lastInstallment.equals(this.lastInstallment) &&
					result.apr.equals(this.apr)
					) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
}
