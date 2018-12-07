package com.aditya.calculator.mathlogic;

import com.aditya.calculator.constant.MathConstant;;

/**
 * This class implements Math rule like precedence and evaluates basic expressions
 * @author Aditya Barjatya
 *
 */
public class GeneralMath {

	/**
	 * Returns true if 'op2' has higher or same precedence as 'op1',
	 * otherwise returns false.
	 * @param op1
	 * @param op2
	 * @return
	 */
	public static boolean hasPrecedence(char operator1, char operator2) {
		//Brackets have highest precedence process them first
		if (operator2 == '(' || operator2 == ')')
			return false;
		//Defer power calculation until next operand comes
		if (operator1 == '^')
			return false;
		//Process power if both operands are available
		if (operator2 == '^')
			return true;
		if ((operator1 == '*' || operator1 == '/') && (operator2 == '+' || operator2 == '-'))
			return false;
		else
			return true;
	}

	/**
	 * A utility method to apply an operator 'op' on operands 'a'
	 * and 'b'. Return the result.
	 * @param op
	 * @param b
	 * @param a
	 * @return
	 */
	public static double calculate(char operator, double b, double a) {
		switch (operator) {
			case MathConstant.POWER_SYMBOL:
				return (int) Math.pow(a, b);
			case MathConstant.ADD_SYMBOL:
				return a + b;
			case MathConstant.SUBTRACT_SYMBOL:
				return a - b;
			case MathConstant.MULTIPLY_SYMBOL:
				return a * b;
			case MathConstant.DIVISON_SYMBOL:
				if (b == 0)
					throw new UnsupportedOperationException("Cannot divide by zero");
				return a / b;
		}
		return 0;
	}
}
