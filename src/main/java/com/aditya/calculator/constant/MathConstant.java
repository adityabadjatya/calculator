package com.aditya.calculator.constant;

/**
 * This class defines the constants used for calculator
 */
public class MathConstant {
	public static final char DIVISON_SYMBOL = '/';
	public static final char MULTIPLY_SYMBOL = '*';
	public static final char ADD_SYMBOL = '+';
	public static final char SUBTRACT_SYMBOL = '-';
	public static final char POWER_SYMBOL = '^';
	
	public static final char OPEN_BRACKET_SYMBOL = '(';
	public static final char CLOSED_BRACKET_SYMBOL = ')';
	
	public static final char[] OPERATOR_ARR = { DIVISON_SYMBOL, MULTIPLY_SYMBOL, ADD_SYMBOL, SUBTRACT_SYMBOL, POWER_SYMBOL };
	public static final char[] BRACKETS = { OPEN_BRACKET_SYMBOL, CLOSED_BRACKET_SYMBOL };
	
	public static final String REGEX_MATH_OPERATION = "[-+*/]";
	
}