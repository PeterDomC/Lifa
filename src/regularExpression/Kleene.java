package regularExpression;

import java.util.ArrayList;
import java.util.HashSet;

/*
 * Class that implements the operations of the Kleene algebra over regular expressions.
 */
public class Kleene {
	
	/**
	 * Method for adding two regular expressions in disjunctive normalform.
	 * This yields a new regular expression in DNF.
	 * @param a = r_1 + ... + r_n, b = s_1 + ... + s_k are the given regular expressions.
	 * @return the resulting sum r_1 + ... + r_n + s_1 + ... + s_k modulo simplifications.
	 * 
	 * NOTE: The method simplifies the resulting sum in a four step procedure.
	 * 1st: It looks for syntactically equivalent summands and discards copies.
	 * 2nd: It checks for additions with zero - these are abbreviated.
	 * 3rd: It looks for some hardcoded pattern that allow to erase summands.
	 * 4th: It successively checks for semantic inclusion and erases summands that are not needed.
	 * NOTE: Does not return a reference to a or b.
	 */
	public static RegExp add(RegExp a, RegExp b) {
		
		// First simplification.
		// This is implicit in building the new set of summands for the regular expression.
		// Syntactically equivalent clauses are discarded.
		HashSet<Clause> summands = new HashSet<Clause>();
		summands.addAll(a.getSummands());
		summands.addAll(b.getSummands());
		
		// Second simplification.
		// Remove the summand zero/empty set.
		summands = removeZero(summands);
		
		// Third simplification.
		// Simplify hardcoded pattern.
		summands = simplifyPattern(summands);
		
		// Fourth simplification
		// TODO
		
		return new RegExp(summands);
	}
	
	/**
	 * Further addition method allowing to add clause/regexp and clause/regexp.
	 */
	public static RegExp add(Clause a, RegExp b) {
		return add(new RegExp(a),b);
	}
	
	/**
	 * Further addition method allowing to add clause/regexp and clause/regexp.
	 */
	public static RegExp add(RegExp a, Clause b) {
		return add(a,new RegExp(b));
	}
	
	/**
	 * Further addition method allowing to add clause/regexp and clause/regexp.
	 */
	public static RegExp add(Clause a, Clause b) {
		return add(new RegExp(a),new RegExp(b));
	}
	
	/**
	 * Method for concatenating two regular expressions in disjunctive normalform.
	 * This yields a new regular expression in DNF.
	 * @param a = r_1 + ... + r_n, b = s_1 + ... + s_k are the given regular expressions.
	 * @return the resulting expression r_1.s_1 + r_2.s_1 + ... + r_n.s_n modulo simplifications.
	 * 
	 * NOTE: The method simplifies the resulting expression in a x step procedure.
	 * 1st: If a or b is the empty expression, we can return the empty expression.
	 * 2nd: If a or b is epsilon, we can return a or b correspondingly.
	 * -----------------
	 * 1st: It looks for syntactically equivalent summands and discards copies.
	 * 2nd: It checks for additions with zero - these are abbreviated.
	 * 3rd: It looks for some hardcoded pattern that allow to erase summands.
	 * 4th: It successively checks for semantic inclusion and erases summands that are not needed.
	 * NOTE: Does not return a reference to a or b.
	 */
	public static RegExp concat(RegExp a, RegExp b) {
		
		HashSet<Clause> a_summands = a.getSummands();
		HashSet<Clause> b_summands = b.getSummands();
		HashSet<Clause> concat_summands = new HashSet<Clause>();
		
		for (Clause r : a_summands) {
			for (Clause s : b_summands) {
				// Concatenate r and s in extra method and add to concat_summands.
			}
		}
		
		// Then call the sum-simplification method consisting of 2nd, 3rd, and 4th component.
		
		/*
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
		return new ConExp(a,b);*/
		
		return null;
	}
	
	/**
	 * Further concatenation method allowing to concatenate clause/regexp and clause/regexp.
	 */
	public static RegExp concat(Clause a, RegExp b) {
		return concat(new RegExp(a),b);
	}
	
	/**
	 * Further concatenation method allowing to concatenate clause/regexp and clause/regexp.
	 */
	public static RegExp concat(RegExp a, Clause b) {
		return concat(a,new RegExp(b));
	}
	
	/**
	 * Further concatenation method allowing to concatenate clause/regexp and clause/regexp.
	 */
	public static RegExp concat(Clause a, Clause b) {
		return concat(new RegExp(a),new RegExp(b));
	}
	
	/**
	 * Add description!
	 * @param r
	 * @param s
	 * @return
	 */
	private static Clause clauseConcat(Clause r, Clause s) {
		return null;
	}
	
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
	
	//TODO: add body - need public method for regular expressions?
	private static RegExp simplify(HashSet<Clause> clauses) {
		return null;
	}
	
	/**
	 * Method that removes the empty expression from the given set of clauses.
	 * @param clauses is the given set.
	 * @return new_clauses, the set with removed empty expression.
	 */
	private static HashSet<Clause> removeZero(HashSet<Clause> clauses) {
		HashSet<Clause> new_clauses = new HashSet<Clause>(clauses);
		new_clauses.remove(EmptyExp.getEmptySet());
		return new_clauses;
	}
	
	/**
	 * Method that simplifies the given set of clauses via checking for hardcoded pattern.
	 * @param clauses is the given set of clauses.
	 * @return reducedClause, the reduced set of clauses after the simplifications.
	 * 
	 * NOTE: Currently, the method applies two simplifications, 
	 * namely 1+ c.c* = c* and c is contained in c*.
	 */
	private static HashSet<Clause> simplifyPattern(HashSet<Clause> clauses) {
		
		// The simplified set of clauses
		HashSet<Clause> reducedClause = new HashSet<Clause>(clauses);
		
		// Simplification pattern one: eps + c.c* = c* for each clause c.
		// Applicable if the set of clauses contains epsilon or any star expression (then we can add epsilon for free).
		boolean containsStar = false;
		for (Clause c : clauses) {
			if (c.getType() == ClauseType.starExp) {
				containsStar = true; 
				break;
			}
		}
		
		if (clauses.contains(Epsilon.getEps()) || containsStar) {
			// Iterate over all clauses and check whether they satisfy the split form c = d.d* 
			// and if so, simplify them to c = d*.
			boolean simplified = false;
			for (Clause c : clauses) {
				
				Clause split = getSplitForm(c);
				if (!split.equals(EmptyExp.getEmptySet())) {
					// Clause c = d.d* is in split form - simplify to d*.
					reducedClause.remove(c);
					reducedClause.add(ClauseFactory.CreateStarExp(split));
					simplified = true;
				}
			}
			
			// Remove epsilon as explicit summand if at least one expression got simplified.
			if (simplified) reducedClause.remove(Epsilon.getEps());
		}
		
		// Simplification pattern two: c is contained in c* for each clause c, so c can be discarded.
		// Obtain the inner of the star clauses.
		HashSet<Clause> innerStarClause = new HashSet<Clause>();
		for (Clause c : reducedClause) {
			if (c.getType() == ClauseType.starExp) {
				innerStarClause.add(((StarExp) c).getInner());
			}
		}
		
		// Remove those clauses from the reduced clause set that appear as inner of a star clause.
		reducedClause.removeAll(innerStarClause);
		
		// Return the reduced set of clauses.
		return reducedClause;
	}
	
	/**
	 * Method that checks whether the given clause is in split form.
	 * This means it can be written as c = w.w*, where w is some expression.
	 * @param c is the given clause.
	 * @return the inner of the split form: w.
	 * 
	 * NOTE: Returns the empty expression if the given clause is not in split form.
	 */
	private static Clause getSplitForm(Clause c) {
		
		// Clause c can only be in split form if it is a concatenation expression.
		if (c.getType() == ClauseType.conExp) {
			
			ConExp con = (ConExp) c;
			ArrayList<Clause> factors = con.getFactors();
			int n = factors.size();
			
			// For split form, the last concatenated factor needs to be a star expression.
			Clause star = factors.get(n-1);
			if (star.getType() == ClauseType.starExp) {
				
				// Clause c is in split form if the expression up to the last star expression
				// coincides (syntactically) with the star expression's inner.
				ArrayList<Clause> prefix_factors = new ArrayList<Clause>(factors);
				prefix_factors.remove(n-1);
				Clause prefix = ClauseFactory.createConExp(prefix_factors);
				Clause star_inner = ((StarExp) star).getInner();
				if (prefix.equals(star_inner)) return star_inner;
			}
		}
		
		// If the given clause is not in split form, return the empty clause.
		return EmptyExp.getEmptySet();
	}
}
