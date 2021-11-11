package regularExpressions;

import java.util.HashSet;

import automataAlgorithms.AbsPair;

/*
 * A special class of regular expressions that supports factoring out parts of the expression.
 * A factor expression is of the form F_1 . R_1 + F_2 . R_2 + ... + F_n . R_n.
 * Note that this is not a DNF since F_i and R_i can be arbitrary regular expressions in DNF or clauses.
 */
public class FactorExp extends RegExp {
	
	HashSet<AbsPair<RegExp,RegExp>> factorClauses;
	
	FactorExp(HashSet<Clause> summands) {
		super(summands);
	}
	
	//TODO: Factor out recursively.
	public RegExp factorOut() {
		return null;
	}
	
	public RegExp factorOutLeft() {
		return null;
	}
	
	public RegExp factorOutRight() {
		return null;
	}
	
	// Find common clause at a time - star expression or atom only!
	public RegExp findCommonLeft() {
		return null;
	}
	
	public RegExp findCommonRight() {
		return null;
	}
}
