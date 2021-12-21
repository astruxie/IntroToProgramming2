import java.util.NoSuchElementException;

/**
 * Class that evaluates a mathematical expression.
 * 
 * @author - Samantha J. Noggle (With help from Nick Savage and Andrew Stocker)
 **/
public class PrefixEvaluator {
	/**
	 * Evaluates a prefix mathematical expression.
	 *
	 * @param input a String containing the expression.
	 * @return A String containing the value of the expression, or an error message
	 *         if there was a problem.
	 */
	public static String evaluate(String input) {
		TokenScanner scanner = new TokenScanner(input);
		// check if empty
		if (!scanner.hasNextToken()) {
			return ("No input.");
		}
		Double answer = new Double(0);
		try {
			answer = evaluateSub(scanner);
		} catch (IllegalArgumentException exception) {
			return exception.getMessage();
		} catch (NoSuchElementException exception) {
			return ("Not enough operands.");
		}
		
		if(!scanner.reachedEnd()) {
			return ("Computed answer, but not all input used.");
		}
		return answer.toString();
	}

	/**
	 * Evaluates the next sub-expression found in a scanner.
	 *
	 * @param scanner A TokenScanner containing at least one prefix
	 *                (sub)-expression.
	 * 
	 * @return The result of the first sub-expression found in the scanner.
	 */
	private static Double evaluateSub(TokenScanner scanner) {
		Token token = scanner.nextToken();
		if (token.isNumber()) {
			Double num = token.getNumberValue();
			return num;
		} else if (token.isOperator()) {
			char symbol = token.getSymbol();
			double oper1 = evaluateSub(scanner);
			double oper2 = evaluateSub(scanner);

			if (symbol == ('+')) {
				return (oper1 + oper2);

			} else if (symbol == ('-')) {
				return (oper1 - oper2);

			} else if (symbol == ('*')) {
				return (oper1 * oper2);

			} else if (symbol == ('/')) {
				return (oper1 / oper2);
			} else {
				throw new IllegalArgumentException("Invalid operand.");
			}
		} else if (token.isLeftParen()) {
			throw new IllegalArgumentException("( has no meaning here.");
		} else if (token.isRightParen()) {
			throw new IllegalArgumentException(") has no meaning here.");
		} else {
			throw new IllegalArgumentException("Invalid input.");
		}
	}
}
