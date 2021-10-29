package regularExpression;

/*
 * Class that implements the operations of the Kleene algebra over the regular expressions.
 */
public class Kleene {
	
	/**
	 * Method for adding two regular expressions.
	 * This yields a new regular sum expression.
	 * @param a, b are the given regular expressions.
	 * @return the resulting sum expression.
	 * 
	 * NOTE: The method simplifies the resulting sum.
	 * NOTE: May return a reference to a or b.
	 */
	public static RegExp add(RegExp a, RegExp b) {
		
		// Obvious simplification cases.
		// One of the given expressions is empty - addition with zero.
		RegExpType type_a = a.getType();
		RegExpType type_b = b.getType();
		if (type_a == RegExpType.emptyExp && type_b == RegExpType.emptyExp) return EmptyExp.getEmptySet();
		if (type_a == RegExpType.emptyExp) return b;
		if (type_b == RegExpType.emptyExp) return a;
		
		// Summands are syntactically equal - return one summand.
		if (a.equals(b)) return a;
		
		// Non-obvious simplification cases.
		// Summand b is contained in a - return a.
		if (isContained(b,a)) return a;
		
		// Summand a is contained in b - return b.
		if (isContained(a,b)) return b;
		
		// No simplification applicable.
		return new SumExp(a,b);
	}
	
	public static RegExp concat(RegExp a, RegExp b) {
		
		// Obvious Simplification cases.
		// One of the given factors is empty - multiplication with zero.
		RegExpType type_a = a.getType();
		RegExpType type_b = b.getType();
		if (type_a == RegExpType.emptyExp || type_b == RegExpType.emptyExp) return EmptyExp.getEmptySet();
		
		// One of the factors (or both) is epsilon - multiplication with one.
		if (type_a == RegExpType.epsilon && type_b == RegExpType.epsilon) return Epsilon.getEps();
		if (type_a == RegExpType.epsilon) return b;
		if (type_b == RegExpType.epsilon) return a;
		
		//TODO
		
		// No simplification applicable.
		return new ConExp(a,b);
	}
	
	/**
	 * Method for staring a regular expression.
	 * @param a is the given expression.
	 * @return the resulting star expression.
	 * 
	 * NOTE: The method simplifies the expression.
	 * NOTE: May return a reference to a.
	 */
	public static RegExp star(RegExp a) {
		
		// Obvious Simplification cases.
		RegExpType type_a = a.getType();
		// The expression is already a star expression: a** = a*.
		if (type_a == RegExpType.starExp) return a;
		// The expression is epsilon: eps* = eps.
		if (type_a == RegExpType.epsilon) return a;
		// The expression is empty: 0* = eps.
		if (type_a == RegExpType.emptyExp) return Epsilon.getEps();
		
		// Non-obvious simplification.
		// Check if the given expression is a chain of stared expressions and apply: (a*.b*)* = (a+b)*.
		// TODO - apply isStarChain().
		
		// No simplification applicable.
		return new StarExp(a);
	}
	
	/**
	 * Method checks whether one regular expression is contained in the other.
	 * @param a, b are the given expressions.
	 * @return true, if a is contained in b - false, otherwise.
	 */
	public static boolean isContained(RegExp a, RegExp b) {
		// Currently not implemented - build up automaton.
		// along the expression and run corresponding automaton method.
		return false;
	}
	
	public static boolean isUniversal(RegExp a) {
		// Check whether a given regular expression is universal.
		return false;
	}
	
	private boolean isStarChain(RegExp a) {
		//TODO 
		return false;
	}
}
