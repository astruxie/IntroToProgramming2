/**
 * Class that evaluates a mathematical expression
 * @author - Samantha J. Noggle (With help from Nick Savage, Andrew Stocker, and Logan Santee)
 **/
import java.util.Stack;

public class PostfixEvaluator {

	/**
	 * Evaluates a postfix mathematical expression.
	 *
	 * @param input A String containing the expression.
	 * 
	 * @return A String containing the value of the expression, or an error message
	 *         if there was a problem.
	 */

	public static String evaluate(String input) {
		Stack<Double> s = new Stack<Double>();
		TokenScanner scanner = new TokenScanner(input);

		// check if there is input
		if (!scanner.hasNextToken()) {
			return ("No input.");
		}

		// for each token
		while (scanner.hasNextToken()) {
			// take the token
			Token toke = scanner.nextToken();

			// token = number
			if (toke.isNumber()) {
				Double num = toke.getNumberValue();
				s.push(num);
			} else if (toke.isOperator()) { // token = operator
				// create char
				char symbol = toke.getSymbol();

				// check there are enough operands
				if (s.size() < 2) {
					return ("Stack underflow.  Not enough operands on stack.");
				}

				// get the operand
				Double num1 = s.pop();
				Double num2 = s.pop();

				// doing what the operator says & pushing back onto the stack
				if (symbol == '-') {
					s.push(num2 - num1);

				} else if (symbol == '+') {
					s.push(num1 + num2);

				} else if (symbol == ('/')) {
					s.push(num2 / num1);

				} else if (symbol == ('*')) {
					s.push(num1 * num2);
				}
			} else if (toke.isLeftParen()) { // check for parentheses in input
				return ("( has no meaning here.");
			} else if (toke.isRightParen()) {
				return (") has no meaning here.");
			}

		}
		//check for invalid char?
		if(!scanner.reachedEnd()) {
			return ("Computed answer, but not all input used.");
		}
		
		// check if the string isnt empty
		if (scanner.hasNextToken()) {
			return ("Computed answer, but not all input used.");
		}
		// check if more than one in stack
		if (s.size() > 1) {
			return ("Computed answer, but values remain on stack.");
		}
		if (s.isEmpty()) {
			return ("Stack underflow. Not enough operands on stack.");
		}
		Double value = s.pop();
		String val = value.toString();
		return val;

	}

}
