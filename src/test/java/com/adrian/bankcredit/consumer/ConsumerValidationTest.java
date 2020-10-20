package com.adrian.bankcredit.consumer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConsumerValidationTest {

	private static Validator validator;

    @BeforeAll
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    @Nested
	class NameValidationTest{
    
    	@Test //Test null Name validation
        public void testNameNullValidation() {
            Consumer consumer = new Consumer( 1L, "login", "password", "USER",
            								null, "Le", 12312312312L, true);

            Set<ConstraintViolation<Consumer>> constraintViolations =
                    validator.validate(consumer);

            assertEquals(1, constraintViolations.size());
            assertEquals("Name cannot be null",
            		constraintViolations.iterator().next().getMessage());
        }
    	
    	@Test //Test less than 3 characters Name validation
        public void testNameMinValidation() {
            Consumer consumer = new Consumer( 1L, "login", "password", "USER",
            								"Ri", "Le", 12312312312L, true);

            Set<ConstraintViolation<Consumer>> constraintViolations =
                    validator.validate(consumer);

            assertEquals(1, constraintViolations.size());
            assertEquals("Name must be between 3 and 100 characters",
            		constraintViolations.iterator().next().getMessage());
        }
    	
    	@Test //Test more than 100 characters Name validation
        public void testNameMaxValidation() {
            Consumer consumer = new Consumer( 1L, "login", "password", "USER", "Richarddqwdqwdqwdqwdqwqdwqwdqwdqwdqwdqwdqwdqwdqwdqwdqwqwdqwdqwdqwdqwdqwdwqdqwdqwdqwdqwdqwdwqdqwdqwdwqdqwdqwdqwdqwdqwdqwdqwdqwdqwddqwdqwdqdqwwwwqwewqweqweqweqweqweqweqwe",
            		"Le", 12312312312L, true);

            Set<ConstraintViolation<Consumer>> constraintViolations =
                    validator.validate(consumer);

            assertEquals(1, constraintViolations.size());
            assertEquals("Name must be between 3 and 100 characters",
            		constraintViolations.iterator().next().getMessage());
        }
    
    }
    
    @Nested
	class LastNameValidationTest{
    
    	@Test //Test LastName null validation
        public void testLastNameNullValidation() {
    		
            Consumer consumer = new Consumer( 1L, "login", "password", "USER", "Richard", null, 12312312312L, true);

            Set<ConstraintViolation<Consumer>> constraintViolations =
                    validator.validate(consumer);

            assertEquals(1, constraintViolations.size());
            assertEquals("LastName cannot be null",
            		constraintViolations.iterator().next().getMessage());
        }
    	
    	@Test //Test less than 2 characters LastName validation
        public void testLastNameMinValidation() {
    		
            Consumer consumer = new Consumer( 1L, "login", "password", "USER", "Richard", "L", 12312312312L, true);

            Set<ConstraintViolation<Consumer>> constraintViolations =
                    validator.validate(consumer);

            assertEquals(1, constraintViolations.size());
            assertEquals("LastName must be between 2 and 100 characters",
            		constraintViolations.iterator().next().getMessage());
        }
    	
    	@Test //Test more than 100 characters LastName validation
        public void testLastNameMaxValidation() {
            Consumer consumer = new Consumer( 1L, "login", "password", "USER", "Richard", "Leeeeeeerddqwdqwdqwdqwdqwqdwqwdqwdqwdqwdqwdqwdqwdqwdqwdqwqwdqwdqwdqwdqwdqwdwqdqwdqwdqwdqwdqwdwqdqwdqwdwqdqwdqwdqwdqwdqwdqwdqwdqwdqwddqwdqwdqdqwwwwqwewqweqweqweqweqweqweqwe",
            		12312312312L, true);

            Set<ConstraintViolation<Consumer>> constraintViolations =
                    validator.validate(consumer);

            assertEquals(1, constraintViolations.size());
            assertEquals("LastName must be between 2 and 100 characters",
            		constraintViolations.iterator().next().getMessage());
        }
    
    }
    
    @Nested
	class PeselValidationTest{
    	
    	@Test //Test Pesel null validation
        public void testPeselNullValidation() {
        	
            Consumer consumer = new Consumer( 1L, "login", "password", "USER", "Richard", "Le", null, true);

            Set<ConstraintViolation<Consumer>> constraintViolations =
                    validator.validate(consumer);

            assertEquals(1, constraintViolations.size());
            assertEquals("Pesel cannot be null",
            		constraintViolations.iterator().next().getMessage());
        }
    	
    	@Test //Test 1 digit Pesel validation
        public void testPeselMinValidation() {
        	
            Consumer consumer = new Consumer( 1L, "login", "password", "USER", "Richard", "Le", 9L, true);

            Set<ConstraintViolation<Consumer>> constraintViolations =
                    validator.validate(consumer);

            assertEquals(1, constraintViolations.size());
            assertEquals("Pesel must have 11 digits",
            		constraintViolations.iterator().next().getMessage());
        }
    	
    	@Test //Test 13 digits Pesel validation
        public void testPeselMaxValidation() {
        	
            Consumer consumer = new Consumer( 1L, "login", "password", "USER", "Richard", "Le", 9999999999999L, true);

            Set<ConstraintViolation<Consumer>> constraintViolations =
                    validator.validate(consumer);

            assertEquals(1, constraintViolations.size());
            assertEquals("Pesel must have 11 digits",
            		constraintViolations.iterator().next().getMessage());
        }
    }
}
