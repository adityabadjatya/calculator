package com.aditya.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.aditya.calculator.exception.InvalidExpressionException;
import com.aditya.calculator.model.MathExpression;

/**
 * This class is the main class of Calculator
 * 
 * @author Aditya Barjatya
 *
 */
public class Calculator {
	/**
	 * Main method. Command line should be used to provide number of expression
	 * in 1st line and rest of the lines should contains individual expression
	 * Each line can have only one expression. Below is an example of input 
	 * Total number of expressions to be evaluated 4
	 * Expression 1 7+(6*5^2+3-4/2)
	 * Expression 2 7+(67(56*2)) 
	 * Expression 3 8*+7 
	 * Expression 4 (8*5/8)-(3/1)-5 
	 * 
	 * The output for above expressions will be
	 * Case #1: 158
	 * Case #2: Invalid expression!!
	 * Case #3: Invalid expression!!
	 * Case #4: -3
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// List to hold expressions entered by user
		List<String> expressions = new ArrayList<String>();

		System.out.print("Please specify how many expressions you want to evaluate: ");
		// Read number of expressions to be evaluated
		Scanner scanner = new Scanner(System.in);
		String numberOfExpressions = scanner.nextLine();
		int totalNumberOfTestCases = 0;

		// Keep asking user to enter valid number of expressions to be evaluated
		while (true) {
			try {
				totalNumberOfTestCases = Integer.parseInt(numberOfExpressions);
				// Check if number of expressions to be evaluated is within
				// specified limit or not
				if (totalNumberOfTestCases < 1 || totalNumberOfTestCases > 100) {
					System.out.println("Please enter number of test cases between 1 and 100");
					numberOfExpressions = scanner.nextLine();
				} else {
					// Stay happy and proceed if user enters valid number
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Please enter valid number of test cases.");
			}
		}

		int expressionsIndex = 0;
		try {
			//Read the expressions from user
			while (expressionsIndex < totalNumberOfTestCases) {
				expressions.add(scanner.nextLine());
				expressionsIndex++;
			}
			scanner.close();
			
			System.out.println("Output for all the entered expression is:");
			StringBuffer result = new StringBuffer();
			//Track the expression number
			int[] processingIndex = {1};
			
			//Process each expression
			expressions.stream().forEach(testCase -> {

				result.append("Case #").append(processingIndex[0]).append(": ");
				processingIndex[0]++;
				try {
					//Build the expression
					MathExpression exp = new MathExpression(testCase);
					//Format output to 2 places of decimal
					result.append(String.format("%.2f", exp.evaluate()));
				} catch (InvalidExpressionException e) {
					result.append(e.getLocalizedMessage());
				} catch (Exception e) {
					result.append(e.getLocalizedMessage());
				}
				//Print result of each expression in a new line
				result.append(System.getProperty("line.separator"));

			});

			System.out.println(result.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}