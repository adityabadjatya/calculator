package com.aditya.calculator.model;

import java.util.Stack;
import java.util.regex.Pattern;

import com.aditya.calculator.constant.MathConstant;
import com.aditya.calculator.exception.InvalidExpressionException;
import com.aditya.calculator.mathlogic.GeneralMath;;

/**
 * This class build, validate and evaluate a mathematical expression
 * @author Aditya Barjatya
 *
 */
public class MathExpression {
	
	/**
	 * Validates expression starting, should not have any operator except (
	 */
	private final Pattern startingPattern = Pattern.compile("^[\\d\\(]$");
	/**
	 * Validates expression ending pattern, should not have any operator except )
	 */
	private final Pattern endingPattern = Pattern.compile("^[\\d\\)]$");
	/**
	 * Expression to be evaluated
	 */
	private String expression;
	
	/**
	 * Allowed operators array
	 */
	private final String OPERATORS = String.valueOf(MathConstant.OPERATOR_ARR);
	
	/**
	 * Default constructor provided as we have a parameterized constructor
	 */
	public MathExpression(){
		
	}
	
	/**
	 * Initializes the expression if it is valid otherwise throws exception
	 * @param expression
	 * @throws InvalidExpressionException
	 */
	public MathExpression(String expression) throws InvalidExpressionException{
		if(expression != null && validate(expression)){
			this.expression = expression;
		}else{
			throw new InvalidExpressionException("Invalid expression!!");
		}
	}
	
	/**
	 * Get current expression
	 * @return
	 */
	public String getExpression() {
		return expression;
	}
	/**
	 * Set current expression after validating it otherwise throw exception
	 * @param expression
	 */
	public void setExpression(String expression) throws InvalidExpressionException{
		if(this.validate(expression)){
			this.expression = expression;
		}else{
			throw new InvalidExpressionException("Invalid expression!!");
		}
		
	}
	/**
	 * Validate the expression entered by user, returns true if expression is valid otherwise false
	 * Returns true if expression starts with digit or (, any other operator is not allowed at start AND
	 * 				if expressions ends with digit or ), any other operator is not allowed at end
	 * Returns false otherwise
	 * @param expression
	 * @return
	 */
	public boolean validate(String expression){
		if(expression != null){
			//Consider empty expression as valid
			//Match starting and ending pattern 
			String trimmedExp = expression.trim();
			if(expression.length() == 0 || 
						(startingPattern.matcher(trimmedExp.substring(0, 1)).matches() && 
								endingPattern.matcher(trimmedExp.substring(trimmedExp.length()-1, trimmedExp.length())).matches())){
				return true;
			}
			return false;
			
		}else{
			return false;
		}
	}
	/**
	 * Evaluates current expression using BODMAS precedence rule
	 * @return
	 * @throws InvalidExpressionException
	 */
    public double evaluate() throws InvalidExpressionException
    {
        try{
	    	char[] tokens = this.expression.toCharArray();
	
	         // Stack for Operands
	        Stack<Double> operands = new Stack<Double>();
	
	        // Stack for Operators
	        Stack<Character> operators = new Stack<Character>();
	        
	        // Build the number
	        StringBuffer sbuf = new StringBuffer();
	
	        //Process each and every token from expression
	        for (int i = 0; i < tokens.length; i++)
	        {
	            // Current token is a number, push it to stack for operands
	        	// There may be more than one digits in number, so cache it for the next token
	        	if (tokens[i] >= '0' && tokens[i] <= '9')
	            {
	            	sbuf.append(tokens[i]);
	            	continue;
	            }
	
	            //If no more digits, append all the digits to build number
	        	if(sbuf.length() > 0){
	        		operands.push(Double.parseDouble(sbuf.toString()));
	            	sbuf = new StringBuffer();
	            }
	            
	            // Current token is a whitespace, skip it
	            if (tokens[i] == ' ')
	               continue;
	            
	            // Current token is an opening brace, push it to 'operators'
	            if (tokens[i] == '(')
	            	operators.push(tokens[i]);
	
	            // Closing brace encountered, solve entire brace
	            else if (tokens[i] == ')')
	            {
	                while (operators.peek() != '('){
	                	operands.push(GeneralMath.calculate(operators.pop(), operands.pop(), operands.pop()));	                	
	                }
	                operators.pop();
	            }
	
	            // Current token is an operator.
	            else if (OPERATORS.indexOf(tokens[i]) != -1)
	            {
	                // While top of 'operators' has same or greater precedence to current
	                // token, which is an operator. Apply operator on top of 'operators'
	                // to top two elements in values stack
	                while (!operators.empty() && GeneralMath.hasPrecedence(tokens[i], operators.peek()))
	                	operands.push(GeneralMath.calculate(operators.pop(), operands.pop(), operands.pop()));
	
	                // Push current token to 'operators'.
	                operators.push(tokens[i]);
	            }
	        }
	        
	        //Combine left over digits at the end to build number
	        if(sbuf.length() > 0){
	        	operands.push(Double.parseDouble(sbuf.toString()));
	        	sbuf = new StringBuffer();
	        }
	
	        // Entire expression has been parsed, process remaining operators
	        while (!operators.empty())
	        	operands.push(GeneralMath.calculate(operators.pop(), operands.pop(), operands.pop()));
	
	        //If there are no more operators but we still have more that 1 value than expression is not valid
	        if(operands.size() > 1){
	        	throw new InvalidExpressionException("Invalid expression!!");
	        }
	        
	        // Top of 'operands' contains result, return it
	        return operands.pop();
        }catch(Throwable t){
        	throw new InvalidExpressionException("Invalid expression!!");
        }
    }
    
}
