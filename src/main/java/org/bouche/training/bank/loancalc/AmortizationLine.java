package org.bouche.training.bank.loancalc;

import java.math.BigDecimal;

public class AmortizationLine {

	private int nbMonths;
	private BigDecimal remainingAmount;
	private BigDecimal repayment;
	private BigDecimal interest;

	public int getNbMonths() {
		return nbMonths;
	}

	public void setNbMonths(int nbMonths) {
		this.nbMonths = nbMonths;
	}

	public BigDecimal getRemainingAmount() {
		return remainingAmount;
	}

	public void setRemainingAmount(BigDecimal remainingAmount) {
		this.remainingAmount = remainingAmount;
	}

	public BigDecimal getRepayment() {
		return repayment;
	}

	public void setRepayment(BigDecimal repayment) {
		this.repayment = repayment;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public AmortizationLine(int nbMonths, BigDecimal remainingAmount, BigDecimal repayment, BigDecimal interest) {
		super();
		this.nbMonths = nbMonths;
		this.remainingAmount = remainingAmount;
		this.repayment = repayment;
		this.interest = interest;
	}
	
}
