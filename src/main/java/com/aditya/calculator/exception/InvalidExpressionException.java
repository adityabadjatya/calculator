package com.aditya.calculator.exception;

/**
 * Exception for invalid expression
 * @author Aditya Barjatya
 *
 */
public class InvalidExpressionException extends Exception 
{
	/**
	 * Serial id
	 */
	private static final long serialVersionUID = -8079037944828318499L;

	/**
	   Creates an empty InvalidExpressionException.
	 **/
    public InvalidExpressionException() {
        super();
    }

    /**
	   Creates a InvalidExpressionException from an exception.
	   @param  e Exception to get a message from.
     **/
    public InvalidExpressionException(Exception e) {
        super("" + e);
    }
    
    /**
	   Creates a InvalidExpressionException from a message.
	   @param s Exception message.
	**/
    public InvalidExpressionException(String s) {
        super(s);
    }
}
