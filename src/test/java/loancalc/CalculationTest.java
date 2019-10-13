package loancalc;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.bouche.training.bank.loancalc.LoanCalculationResult;
import org.bouche.training.bank.loancalc.LoanConditions;
import org.junit.Test;

public class CalculationTest {

	@Test
	public void testCalculation() {
		
		LoanConditions loanA = new LoanConditions(new BigDecimal("100000.00"), new BigDecimal("2000.00"), new BigDecimal("0.0200"), new BigDecimal("500.00"));
		LoanCalculationResult resultA = new LoanCalculationResult(53, new BigDecimal("500.93"), new BigDecimal("0.0226"));
		
		assertEquals(resultA.getLoanDuration(), loanA.getResult().getLoanDuration());
		assertEquals(resultA.getLastInstallment(), loanA.getResult().getLastInstallment());
		assertEquals(resultA.getApr(), loanA.getResult().getApr());
	}
	
}
