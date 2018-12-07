package com.aditya.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import com.aditya.calculator.exception.InvalidExpressionException;
import com.aditya.calculator.model.MathExpression;

/**
 * JUnit 5 Test class to test the expressions
 * @author Aditya Barjatya
 *
 */
public class CalculatorTest {

	/**
	 * Tests expression 7 +  (6 * 5 ^2 +3 -4 /2 )
	 * @throws InvalidExpressionException
	 */
    @Test
    public void expressionOne() {
        try{
        	//Test following expressions
            MathExpression expression = new MathExpression("7 +  (6 * 5 ^2 +3 -4 /2 )");
            double result = expression.evaluate();
            
            assertEquals(result, 158);        	

            //Print test case details in logs
            this.print("1", "7 +  (6 * 5 ^2 +3 -4 /2 )", String.valueOf(158.0), String.valueOf(result));
        }catch (Exception e) {
        	fail("Exception thrown while evaluating 1st expression, stay careful !!");
		}
    }

	/**
	 * Tests expression 7+67(56*2)
	 * @throws InvalidExpressionException
	 */
    @Test
    public void expressionTwo() {
    	//Test following expressions
		try {
			MathExpression expression = new MathExpression("7+67(56*2)");

	        //Should throw exception
	        InvalidExpressionException exception = assertThrows(InvalidExpressionException.class, () -> {expression.evaluate();});
	        
	        assertEquals("Invalid expression!!", exception.getLocalizedMessage());
	        
	        //Print test case details in logs
	        print("2", "7+67(56*2)", "Invalid expression!!", exception.getLocalizedMessage());
		} catch (InvalidExpressionException e) {
			fail("Exception thrown while evaluating 2nd expression, stay careful !!");
		}
    }

	/**
	 * Tests expression 8*+7
	 * @throws InvalidExpressionException
	 */
    @Test
    public void expressionThree() {
        try{
			MathExpression expression = new MathExpression("8*+7");

	        //Should throw exception
	        InvalidExpressionException exception = assertThrows(InvalidExpressionException.class, () -> {expression.evaluate();});
	        
	        assertEquals("Invalid expression!!", exception.getLocalizedMessage());
	        
	        //Print test case details to logs
	        print("3", "8*+7", "Invalid expression!!", exception.getLocalizedMessage());
        }catch (Exception e) {
        	fail("Exception thrown while evaluating 3rd expression, stay careful !!");
		}
    }

	/**
	 * Tests expression (8*5/8) - (3/1) - 5
	 * @throws InvalidExpressionException
	 */
    @Test
    public void expressionFour() {
        try{
        	//Test following expressions
            MathExpression expression = new MathExpression("(8*5/8) - (3/1) - 5");
            double result = expression.evaluate();
            
            assertEquals(result, -3);
            
            //Print test case details to logs
            this.print("4", "(8*5/8) - (3/1) - 5", String.valueOf(-3.0), String.valueOf(result));
        }catch (Exception e) {
        	fail("Exception thrown while evaluating 1st expression, stay careful !!");
		}
    }
    
    /**
     * Print the test case details for ease of analyzing results
     * Take 4 parameters of type String
     * @param parameters
     */
    private void print(String... parameters){
    	System.out.print("Test Case #" + parameters[0]);
    	System.out.println(": Executing expression = " + parameters[1]);
    	System.out.println("Expected result = " + parameters[2]);
    	System.out.println("Actual result = " + parameters[3]);
    }

}