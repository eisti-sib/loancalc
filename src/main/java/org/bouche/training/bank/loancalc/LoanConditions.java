package org.bouche.training.bank.loancalc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoanConditions {

	private BigDecimal amount;
	private BigDecimal installment;
	private BigDecimal interestRate;
	private BigDecimal fees;
	
	private List<AmortizationLine> amortizationTable;
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public BigDecimal getInstallment() {
		return installment;
	}
	
	public BigDecimal getInterestRate() {
		return interestRate;
	}
	
	public BigDecimal getFees() {
		return fees;
	}
	
	public LoanConditions(BigDecimal amount, BigDecimal installment, BigDecimal interestRate, BigDecimal fees) {
		super();
		this.amount = amount;
		this.installment = installment;
		this.interestRate = interestRate;
		this.fees = fees;
	}
	
	public List<AmortizationLine> getAmortizationTable() {
		if(amortizationTable == null) {
			this.amortizationTable = calculateAmortizationTable();
		} 
		return amortizationTable;
	}
	
	public LoanCalculationResult getResult() {
		Iterator<AmortizationLine> amortizationTableIterator = getAmortizationTable().iterator();
		AmortizationLine lastLine = null;
		while(amortizationTableIterator.hasNext()) {
			lastLine = amortizationTableIterator.next();
		}
		
		return new LoanCalculationResult(lastLine.getNbMonths(), lastLine.getRepayment(), calculateAPR(getAmortizationTable()));
	}

	private BigDecimal calculateAPR(List<AmortizationLine> amortizationTable) {
		double minApr = 0d;
		double maxApr = 1d;
		while (maxApr - minApr > 0.000005) {
			double testApr = (maxApr + minApr) / 2;
			double test = calculateRightHand(amortizationTable, testApr);
			if (this.amount.doubleValue() - test > 0) {
				maxApr = testApr;
			} else {
				minApr = testApr;
			}
		}
		return new BigDecimal(maxApr).setScale(4, RoundingMode.CEILING);
	}

	private double calculateRightHand(List<AmortizationLine> amortizationTable, double apr) {
		double result = 0;
		Iterator<AmortizationLine> iterator = amortizationTable.iterator();
		while(iterator.hasNext()) {
			AmortizationLine line = iterator.next();
			result += calculateActualizedValue(line.getRepayment(), apr, line.getNbMonths());
		}
		
		return result;
	}
	
	private double calculateActualizedValue(BigDecimal amount, double apr, int nbMonths) {
		return amount.doubleValue() / Math.pow(1 + apr, nbMonths / 12d);
	}
	
	private List<AmortizationLine> calculateAmortizationTable() {
		List<AmortizationLine> amortizationTable = new ArrayList<AmortizationLine>();
		BigDecimal remainingAmount = this.amount;

		amortizationTable.add(new AmortizationLine(0, null, this.fees, null));		
		
		int nbMonths = 1;
		
		while(remainingAmount.compareTo(BigDecimal.ZERO) > 0) {
			
			BigDecimal interest = remainingAmount.multiply(this.interestRate).divide(new BigDecimal(12), 2, RoundingMode.HALF_UP);
			
			BigDecimal repayment; 
			if(interest.add(remainingAmount).compareTo(this.installment) > 0) {
				repayment = this.installment;
			} else {
				repayment = interest.add(remainingAmount);
			}
			
			AmortizationLine line = new AmortizationLine(nbMonths, remainingAmount, repayment, interest);
			amortizationTable.add(line);
			
			remainingAmount = remainingAmount.subtract(repayment.subtract(interest));
			nbMonths++;

		}
		
		return amortizationTable;
	}
	
}
