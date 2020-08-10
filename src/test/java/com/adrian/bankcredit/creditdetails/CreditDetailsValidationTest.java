package com.adrian.bankcredit.creditdetails;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.adrian.bankcredit.consumer.Consumer;

@SpringBootTest
class CreditDetailsValidationTest {

	private static Validator validator;

    @BeforeAll
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    @Nested
	class LoanAmountValidationTest{
    	
    	@Test //Test loanAmount null validation
        public void testLoanNullValidation() {
            CreditDetails creditDetails = new CreditDetails(1L, null, new Date(), 21, "541234567890125", new Consumer());

            Set<ConstraintViolation<CreditDetails>> constraintViolations =
                    validator.validate(creditDetails);

            assertEquals(1, constraintViolations.size());
            assertEquals("LoanAmount cannot be null",
            		constraintViolations.iterator().next().getMessage());
        }
    	
    	@Test //Test loanAmount less than 1,000 validation
        public void testLoanAmountMinValidation() {
            CreditDetails creditDetails = new CreditDetails(1L, 999L, new Date(), 21, "541234567890125", new Consumer());

            Set<ConstraintViolation<CreditDetails>> constraintViolations =
                    validator.validate(creditDetails);

            assertEquals(1, constraintViolations.size());
            assertEquals("LoanAmount must be more than 1,000",
            		constraintViolations.iterator().next().getMessage());
        }
    	
    	@Test //Test loanAmount more than 100,000,000 validation
        public void testLoanAmountMaxValidation() {
            CreditDetails creditDetails = new CreditDetails(1L, 9999999999L, new Date(), 21, "541234567890125", new Consumer());

            Set<ConstraintViolation<CreditDetails>> constraintViolations =
                    validator.validate(creditDetails);

            assertEquals(1, constraintViolations.size());
            assertEquals("LoanAmount must be less than 100,000,000",
            		constraintViolations.iterator().next().getMessage());
        }
    }
    
    @Nested
    class LoanStartValidationTest{
    	
    	@Test //Test loanStart null validation
    	public void testLoanStartNullValidation() {
    		CreditDetails creditDetails = new CreditDetails(1L, 10000L, null, 21, "541234567890125", new Consumer());

    		Set<ConstraintViolation<CreditDetails>> constraintViolations =
                    validator.validate(creditDetails);

            assertEquals(1, constraintViolations.size());
            assertEquals("LoanStart cannot be null",
            		constraintViolations.iterator().next().getMessage());
    	}
    }
    
    @Nested
	class MonthsValidationTest{
    	
    	@Test //Test Months less than 1 validation
        public void testMonthsMinValidation() {
            CreditDetails creditDetails = new CreditDetails(1L, 9999L, new Date(), 0, "541234567890125", new Consumer());

            Set<ConstraintViolation<CreditDetails>> constraintViolations =
                    validator.validate(creditDetails);

            assertEquals(1, constraintViolations.size());
            assertEquals("Months must be more than 0",
            		constraintViolations.iterator().next().getMessage());
        }
    	
    	@Test //Test loanAmount more than 360 validation
        public void testLoanAmountMaxValidation() {
            CreditDetails creditDetails = new CreditDetails(1L, 9999L, new Date(), 400, "541234567890125", new Consumer());

            Set<ConstraintViolation<CreditDetails>> constraintViolations =
                    validator.validate(creditDetails);

            assertEquals(1, constraintViolations.size());
            assertEquals("Months must be less than 361",
            		constraintViolations.iterator().next().getMessage());
        }
    }
    
    @Nested
    class CreditCardNumberValidationTest{
    	
    	@Test //Test CreditCardNumber single digit error validation
    	public void testCreditCardSingleDigitErrorValidation() {
    		CreditDetails creditDetails = new CreditDetails(1L, 10000L, new Date(), 21, "441234567890125", new Consumer());

    		Set<ConstraintViolation<CreditDetails>> constraintViolations =
                    validator.validate(creditDetails);

            assertEquals(1, constraintViolations.size());
            assertEquals("CreditAccountNumber is wrong",
            		constraintViolations.iterator().next().getMessage());
    	}
    	
    	@Test //Test CreditCardNumber null validation
    	public void testCreditCardNullValidation() {
    		CreditDetails creditDetails = new CreditDetails(1L, 10000L, new Date(), 21, null, new Consumer());

    		Set<ConstraintViolation<CreditDetails>> constraintViolations =
                    validator.validate(creditDetails);

            assertEquals(1, constraintViolations.size());
            assertEquals("CreditCardNumber cannot be null",
            		constraintViolations.iterator().next().getMessage());
    	}
    }
    
    @Nested
    class CustomerValidationTest{
    	
    	@Test //Test CreditCardNumber single digit error validation
    	public void testCustomerNullValidation() {
    		CreditDetails creditDetails = new CreditDetails(1L, 10000L, new Date(), 21, "541234567890125", null);

    		Set<ConstraintViolation<CreditDetails>> constraintViolations =
                    validator.validate(creditDetails);

            assertEquals(1, constraintViolations.size());
            assertEquals("Customer cannot be null",
            		constraintViolations.iterator().next().getMessage());
    	}
    }

}
