import java.util.ArrayList;

public class checker {
	public static String OR[] = { "v", "V", "or", "ou", "||", "|", "+", "∨" };
	public static String AND[] = { "^", "&", "&&", "*", ".", "∧", "and", "e" };
	public static String EQUIVALENCE[] = { "<>", "↔", "<->", "≡", "⇔", "se, somente se" };
	public static String IMPLICATION[] = { "->", ">", "→", "⇒", "⊃" };
	public static String NEGATION[] = { "¬", "~", "!" };
	public static String TRUE[] = { "t", "1", "⊤", "true" };
	public static String FALSE[] = { "f", "0", "⊥", "false" };
	public static String PROP[];
	public static String CONECTIVE[] = { "v", "V", "or", "ou", "||", "|", "+", "∨", "^", "&", "&&", "*", ".", "∧", "and",
			"e", "<>", "↔", "≡", "⇔", "se, somente se", "->", ">", "→", "⇒", "⊃", "¬", "~", "!", "<" };

	public static String OPENPARENTESE = "(";
	public static String CLOSEPARENTESE = ")";

	public static String Or = "+";
	public static String And = ".";
	public static String Equivalence = "-";
	public static String Implication = ">";
	public static String Negation = "!";
	public static String True = "1";
	public static String False = "0";

	/* STATUS */
	public static int INITIAL_STATE = 0;
	public static int CONECTIVE_STATE = 1;
	public static int NEGATION_STATE = 2;
	public static int OPEN_PARENTESE_STATE = 3;
	public static int CLOSE_PARENTESE_STATE = 4;
	public static int PROPOSITION_STATE = 5;
	public static int INVALID_STATE = 6;

	public ArrayList<String> formula;
	// !(p{asdajsdlskdfjsaldf} ^ !p{dasdasd})

	checker() {
		formula = new ArrayList<String>();
	}

	public boolean simpleValidation(String formula) {
		return simpleChecker(Converter(formula, 0), 0, 0);
	}

	public String Converter(String formula, int toffset) {
		ValidValue Result;
		if (toffset > formula.length() || toffset == formula.length())
			return "";

		String subFormula = formula.substring(toffset);

		if (!this.startWithConective(subFormula).isValid && Character.isUpperCase(subFormula.charAt(0))) {
			int count = 0;
			char[] ArrayChar = subFormula.toCharArray();
			for (char char1 : ArrayChar) {
				if (!Character.isLetter(char1)) {
					if (count == 0) {
						return Converter(formula, toffset + 1);
					} else {
						if (!Character.isDigit(char1)) {
							this.formula.add(subFormula.substring(0, count));
							return "P{" + subFormula.substring(0, count) + "}" + Converter(formula, toffset + count);
						}
					}
				}
				count++;
			}
			this.formula.add(subFormula.substring(0, count));
			return "P{" + subFormula.substring(0, count) + "}" + Converter(formula, toffset + count);
		}

		Result = startWithNegation(subFormula);
		if (Result.isValid) {
			this.formula.add("!");
			return "!" + Converter(formula, toffset + Result.value);
		}
		Result = startWithAnd(subFormula);
		if (Result.isValid) {
			this.formula.add(".");

			return "." + Converter(formula, toffset + Result.value);
		}
		Result = startWithOr(subFormula);
		if (Result.isValid) {
			this.formula.add("+");

			return "+" + Converter(formula, toffset + Result.value);
		}
		Result = startWithImplication(subFormula);
		if (Result.isValid) {

			this.formula.add(">");

			return ">" + Converter(formula, toffset + Result.value);
		}
		Result = startWithEquivalence(subFormula);
		if (Result.isValid) {

			this.formula.add("-");

			return "-" + Converter(formula, toffset + Result.value);
		}
		Result = startWithFalse(subFormula);
		if (Result.isValid) {
			this.formula.add("⊥");
			return "⊥" + Converter(formula, toffset + Result.value);
		}
		Result = startWithTrue(subFormula);
		if (Result.isValid) {
			this.formula.add("⊤");

			return "⊤" + Converter(formula, toffset + Result.value);
		}
		return Converter(formula, toffset + 1);
	}

	public ValidValue startWithOr(String str) {
		for (String strB : OR) {
			if (str.startsWith(strB))
				return new ValidValue(true, strB.length());
		}
		return new ValidValue(false);
	}

	public ValidValue startWithAnd(String str) {
		for (String strB : AND) {
			if (str.startsWith(strB))
				return new ValidValue(true, strB.length());
		}
		return new ValidValue(false);
	}

	public ValidValue startWithEquivalence(String str) {
		for (String strB : EQUIVALENCE) {
			if (str.startsWith(strB))
				return new ValidValue(true, strB.length());
		}
		return new ValidValue(false);
	}

	public ValidValue startWithImplication(String str) {
		for (String strB : IMPLICATION) {
			if (str.startsWith(strB))
				return new ValidValue(true, strB.length());
		}
		return new ValidValue(false);
	}

	public ValidValue startWithNegation(String str) {
		for (String strB : NEGATION) {
			if (str.startsWith(strB))
				return new ValidValue(true, strB.length());
		}
		return new ValidValue(false);
	}

	public ValidValue startWithTrue(String str) {
		for (String strB : TRUE) {
			if (str.startsWith(strB))
				return new ValidValue(true, strB.length());
		}
		return new ValidValue(false);
	}

	public ValidValue startWithFalse(String str) {
		for (String strB : FALSE) {
			if (str.startsWith(strB))
				return new ValidValue(true, strB.length());
		}
		return new ValidValue(false);
	}

	public ValidValue startWithConective(String str) {
		for (String strB : CONECTIVE) {
			if (str.startsWith(strB))
				return new ValidValue(true, strB.length());
		}
		return new ValidValue(false);
	}

	public boolean isOr(String str) {
		for (String strB : OR) {
			if (str.equals(strB))
				return true;
		}
		return false;
	}

	public boolean isAnd(String str) {
		for (String strB : AND) {
			if (str.equals(strB))
				return true;
		}
		return false;
	}

	public boolean isEquivalence(String str) {
		for (String strB : EQUIVALENCE) {
			if (str.equals(strB))
				return true;
		}
		return false;
	}

	public boolean isImplication(String str) {
		for (String strB : IMPLICATION) {
			if (str.equals(strB))
				return true;
		}
		return false;
	}

	public boolean isNegation(String str) {
		for (String strB : NEGATION) {
			if (str.equals(strB))
				return true;
		}
		return false;
	}

	public boolean isTrue(String str) {
		for (String strB : TRUE) {
			if (str.equals(strB))
				return true;
		}
		return false;
	}

	public boolean isFalse(String str) {
		for (String strB : FALSE) {
			if (str.equals(strB))
				return true;
		}
		return false;
	}

	public boolean isConective(String str) {
		for (String strB : CONECTIVE) {
			if (str.equals(strB))
				return true;
		}
		return false;
	}

	public boolean isProposition(String str) {
		char[] ArrayChar = str.toCharArray();
		int count = 0;
		for (char char1 : ArrayChar) {
			if (!Character.isLetter(char1))
				if (count == 0)
					return false;
				else if (!Character.isDigit(char1))
					return false;
			count++;
		}
		return true;
	}

	public boolean simpleChecker(String formula, int status, int parenteseCount) {
		if (parenteseCount < 0) {
			return false;
		}
		if (formula.isEmpty()) {
			System.out.println("fim");
			if (parenteseCount != 0)
				return false;

			return true;
		}
		if (formula.startsWith(Or)) {
			System.out.println("or");
			if (status != CLOSE_PARENTESE_STATE && status != PROPOSITION_STATE) {
				return false;
			}
			return true && simpleChecker(formula.substring(1), CONECTIVE_STATE, parenteseCount);
		}
		if (formula.startsWith(And)) {
			System.out.println("and");
			if (status != CLOSE_PARENTESE_STATE && status != PROPOSITION_STATE) {
				return false;
			}
			return true && simpleChecker(formula.substring(1), CONECTIVE_STATE, parenteseCount);
		}
		if (formula.startsWith(Equivalence)) {
			System.out.println("equi");
			if (status != CLOSE_PARENTESE_STATE && status != PROPOSITION_STATE) {
				return false;
			}
			return true && simpleChecker(formula.substring(1), CONECTIVE_STATE, parenteseCount);
		}
		if (formula.startsWith(Implication)) {
			System.out.println("impl");
			if (status != CLOSE_PARENTESE_STATE && status != PROPOSITION_STATE) {
				return false;
			}
			return true && simpleChecker(formula.substring(1), CONECTIVE_STATE, parenteseCount);
		}
		if (formula.startsWith(Negation)) {
			System.out.println("negat");
			if (status == CLOSE_PARENTESE_STATE || status == PROPOSITION_STATE) {
				return false;
			}
			return true && simpleChecker(formula.substring(1), NEGATION_STATE, parenteseCount);
		}
		if (formula.startsWith(True)) {
			System.out.println("true");
			if (status == CLOSE_PARENTESE_STATE || status == PROPOSITION_STATE) {
				return false;
			}
			return true && simpleChecker(formula.substring(1), PROPOSITION_STATE, parenteseCount);
		}
		if (formula.startsWith(False)) {
			System.out.println("false");
			if (status == CLOSE_PARENTESE_STATE || status == PROPOSITION_STATE) {
				return false;
			}
			return true && simpleChecker(formula.substring(1), PROPOSITION_STATE, parenteseCount);
		}
		if (formula.startsWith("P{")) {
			System.out.println("prop");
			if (status == CLOSE_PARENTESE_STATE || status == PROPOSITION_STATE) {
				return false;
			}
			return true && simpleChecker(formula.substring(formula.indexOf("}") + 1), PROPOSITION_STATE, parenteseCount);
		}

		if (formula.startsWith(OPENPARENTESE)) {
			System.out.println("OpenParen");
			if (status == CLOSE_PARENTESE_STATE || status == PROPOSITION_STATE) {
				return false;
			}
			return true && simpleChecker(formula.substring(1), PROPOSITION_STATE, parenteseCount++);
		}
		if (formula.startsWith(CLOSEPARENTESE)) {
			System.out.println("CloseParen");
			if (status != CLOSE_PARENTESE_STATE && status != PROPOSITION_STATE) {
				return false;
			}
			return true && simpleChecker(formula.substring(1), PROPOSITION_STATE, parenteseCount--);
		}

		System.out.println("fim errado");
		return false;
	}
}
