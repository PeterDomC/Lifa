package regularExpression;

import java.util.HashSet;

import automata.Autom;

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
	/*
	public static RegExp add(RegExp a, RegExp b) {
		
		// Obvious simplification cases.
		// One of the given expressions is empty - addition with zero.
		ClauseType type_a = a.getType();
		ClauseType type_b = b.getType();
		if (type_a == ClauseType.emptyExp && type_b == ClauseType.emptyExp) return EmptyExp.getEmptySet();
		if (type_a == ClauseType.emptyExp) return b;
		if (type_b == ClauseType.emptyExp) return a;
		
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
	*/
	
	/**
	 * Method for concatenating two regular expressions.
	 * This yields a new regular con expression.
	 * @param a, b are the given regular expressions.
	 * @return the resulting con expression.
	 * 
	 * NOTE: The method simplifies the resulting expression.
	 * NOTE: May return a reference to a or b.
	 */
	/*
	public static RegExp concat(RegExp a, RegExp b) {
		
		// Obvious Simplification cases.
		// One of the given factors is empty - multiplication with zero.
		ClauseType type_a = a.getType();
		ClauseType type_b = b.getType();
		if (type_a == ClauseType.emptyExp || type_b == ClauseType.emptyExp) return EmptyExp.getEmptySet();
		
		// One of the factors (or both) is epsilon - multiplication with one.
		if (type_a == ClauseType.epsilon && type_b == ClauseType.epsilon) return Epsilon.getEps();
		if (type_a == ClauseType.epsilon) return b;
		if (type_b == ClauseType.epsilon) return a;
		
		// Non-obvious simplification.
		if (type_a == ClauseType.starExp && type_b == ClauseType.starExp) {
			
			// Factor a = R* and b = E*.
			RegExp R = ((StarExp) a).getInner();
			RegExp E = ((StarExp) b).getInner();
			
			// R and E are syntactically equal - return one factor.
			if (R.equals(E)) return a;
			
			// If E is contained in R, return a: R* . E* = R* = a.
			if (isContained(E,R)) return a;
			
			// If R is contained in E, return b: R* . E* = E* = b.
			if (isContained(R,E)) return b;
		}
		
		// No simplification applicable.
		//TODO: find common prefix and pull factor out.
		return new ConExp(a,b);
	}
	*/
	
	/**
	 * Method for staring a regular expression.
	 * @param a is the given expression.
	 * @return the resulting star expression.
	 * 
	 * NOTE: The method simplifies the expression.
	 * NOTE: May return a reference to a.
	 */
	/*
	public static RegExp star(RegExp a) {
		
		// Obvious Simplification cases.
		ClauseType type_a = a.getType();
		// The expression is already a star expression: a** = a*.
		if (type_a == ClauseType.starExp) return a;
		// The expression is epsilon: eps* = eps.
		if (type_a == ClauseType.epsilon) return a;
		// The expression is empty: 0* = eps.
		if (type_a == ClauseType.emptyExp) return Epsilon.getEps();
		
		// Non-obvious simplification.
		// Check if the given expression is a chain of star expressions and shorten via: (R*.E*)* = (R+E)*.
		HashSet<RegExp> comp = getStarChainComp(a);
		// If comp contains at least 2 factors, we can simplify the expression.
		if (comp.size() >= 2) {
			return simplifyStarChain(comp);
		}
		
		// No simplification applicable.
		return new StarExp(a);
	}
	*/
	
	/**
	 * Method checks whether one regular expression is contained in the other.
	 * @param a, b are the given expressions.
	 * @return true, if a is contained in b - false, otherwise.
	 */
	/*
	public static boolean isContained(RegExp a, RegExp b) {
		// Currently not implemented - build up automaton.
		// along the expression and run corresponding automaton method.
		return false;
	}
	*/
	
	/*
	public static boolean isUniversal(RegExp a) {
		// Check whether a given regular expression is universal.
		return false;
	}
	*/
	
	/**
	 * Checks whether the given regular expression is a star chain of the form: a* . b* . c* ...
	 * If so, it returns the components a,b,c...
	 * @param a is the given regular expression.
	 * @return the components if a is a star chain and an empty set otherwise.
	 */
	/*
	private static HashSet<RegExp> getStarChainComp(RegExp a) {
		
		HashSet<RegExp> factors = new HashSet<RegExp>();
		
		if (a.getType() == ClauseType.conExp) {
			// The given expression is a concat expression.
			RegExp left = ((ConExp) a).getLeftFactor();
			RegExp right = ((ConExp) a).getRightFactor();
			
			// Split the search into the left and the right part.
			HashSet<RegExp> left_factors = getStarChainComp(left);
			HashSet<RegExp> right_factors = getStarChainComp(right);
			if (!left_factors.isEmpty() && !right_factors.isEmpty()) {
				// Add the found factors.
				factors.addAll(left_factors);
				factors.addAll(right_factors);
			}
		}
		
		if (a.getType() == ClauseType.starExp) {
			// The given expression is a star expression - this is the end of the recursion.
			RegExp inner = ((StarExp) a).getInner();
			factors.add(inner);
		}
		
		// If the expression has a subexpression that is not a star chain,
		// returns the empty set.
		return factors;
	}
	*/
	
	/**
	 * Simplifies a star chain to its sum representation, requires the list of components.
	 * @param comp is the list of components of the star chain.
	 * @return a regular expression that is the star of the sum of the components.
	 */
	/*
	private static StarExp simplifyStarChain(HashSet<RegExp> comp) {
		// Build the star expresssion out of the given components.
		RegExp sum = EmptyExp.getEmptySet();
		boolean init = true;
		
		for (RegExp E : comp) {
			if (init) {
				// The initial expression in the set.
				sum = E;
				init = false;
			} else {
				// Add the remaining expressions.
				sum = Kleene.add(sum,E);
			}
		}
		
		// Return star expression of the sum.
		return new StarExp(sum);
	}
	
	//TODO: Implement
	public static Autom regExpToAutom(RegExp a) {
		return null;
	}
	*/
}
