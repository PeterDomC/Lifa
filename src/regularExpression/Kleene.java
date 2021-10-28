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
		// Summands are syntactically equal - return one summand.
		if (a.equals(b)) return a;
		
		// One of the given expressions is empty - addition with zero.
		RegExpType type_a = a.getType();
		RegExpType type_b = b.getType();
		if (type_a == RegExpType.emptyExp && type_b == RegExpType.emptyExp) return EmptyExp.getEmptySet();
		if (type_a == RegExpType.emptyExp) return b;
		if (type_b == RegExpType.emptyExp) return a;
		
		// Non-obvious simplification cases.
		// Summand b is contained in a - return a.
		if (isContained(b,a)) return a;
		
		// Summand a is contained in b - return b.
		if (isContained(a,b)) return b;
		
		// No simplification applicable.
		return new SumExp(a,b);
	}
	
	public static RegExp concat(RegExp a, RegExp b) {
		
		// Simplification cases.
		// TODO: implement rules.
		
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
		
		// Simplification cases.
		RegExpType type_a = a.getType();
		// The expression is already a star expression: a** = a*.
		if (type_a == RegExpType.starExp) return a;
		// The expression is epsilon: eps* = eps
		if (type_a == RegExpType.epsilon) return a;
		// The expression is empty: 0* = eps
		if (type_a == RegExpType.emptyExp) return Epsilon.getEps();
		
		// No simplification applicable.
		return new StarExp(a);
	}
	
	// TODO: Currently not implemented - build up automaton
	// along the expression and run corresponding automaton method!
	
	/**
	 * Method checks whether one regular expression is contained in the other.
	 * @param a, b are the given expressions.
	 * @return true, if a is contained in b - false, otherwise.
	 */
	public static boolean isContained(RegExp a, RegExp b) {
		return false;
	}
	
	public static boolean isEmpty(RegExp a) {
		return false;
	}
	
	public static boolean isUniversal(RegExp a) {
		return false;
	}
}
