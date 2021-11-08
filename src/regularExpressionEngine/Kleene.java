package regularExpressionEngine;

import java.util.ArrayList;
import java.util.HashSet;

import regularExpressions.Clause;
import regularExpressions.ClauseFactory;
import regularExpressions.ClauseType;
import regularExpressions.ConExp;
import regularExpressions.EmptyExp;
import regularExpressions.Epsilon;
import regularExpressions.RegExp;
import regularExpressions.StarChain;
import regularExpressions.StarExp;

/*
 * Class that implements the operations of the Kleene algebra over regular expressions.
 */
public abstract class Kleene {
	
	/**
	 * Method for adding two regular expressions in disjunctive normalform.
	 * This yields a new regular expression in DNF.
	 * @param a = r_1 + ... + r_n, b = s_1 + ... + s_k are the given regular expressions.
	 * @return the resulting sum r_1 + ... + r_n + s_1 + ... + s_k modulo simplifications.
	 * 
	 * NOTE: The method simplifies the resulting sum by calling the method simplifySum.
	 * NOTE: Does not return a reference to a or b.
	 */
	public static RegExp add(RegExp a, RegExp b) {
		
		// Building the new set of summands of the resulting regular expression.
		// Note that syntactically equivalent clauses are already discarded.
		HashSet<Clause> summands = new HashSet<Clause>();
		summands.addAll(a.getSummands());
		summands.addAll(b.getSummands());
		
		summands = simplifySum(summands);
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
	 * TODO: Refactor
	 * Method for concatenating two regular expressions in disjunctive normalform.
	 * This yields a new regular expression in DNF.
	 * @param a = r_1 + ... + r_n, b = s_1 + ... + s_k are the given regular expressions.
	 * @return the resulting expression r_1.s_1 + r_2.s_1 + ... + r_n.s_n modulo simplifications.
	 * 
	 * NOTE: The method simplifies the resulting DNF by calling the method simplify.
	 * NOTE: Does not return a reference to a or b.
	 */
	public static RegExp concat(RegExp a, RegExp b) {
		
		HashSet<Clause> a_summands = a.getSummands();
		HashSet<Clause> b_summands = b.getSummands();
		HashSet<Clause> concat_summands = new HashSet<Clause>();
		
		// Construct the summands of the resulting DNF.
		// Note that syntactically equivalent summands are already discarded.
		for (Clause r : a_summands) {
			for (Clause s : b_summands) {
				Clause rs = clauseConcat(r,s);
				concat_summands.add(rs);
			}
		}
		
		// Then call simplify
		concat_summands = simplifySum(concat_summands);
		return new RegExp(concat_summands);
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
	 * Method for applying Kleene star to a regular expression in DNF.
	 * This yields a new regular expression in DNF.
	 * @param a = r_1 + ... + r_n is the given regular expression.
	 * @return the resulting expression (r_1 + ... + r_n)* = (r_1* ... r_n*)* modulo simplifications.
	 * 
	 * NOTE: The resulting expression is in DNF with one clause only.
	 * This clause is a star chain if the given DNF contains more than one summand.
	 * NOTE: The method simplifies the expression.
	 * NOTE: May return a reference to a.
	 */
	public static RegExp star(RegExp a) {
		
		HashSet<Clause> a_summands = a.getSummands();
		ArrayList<Clause> inner_factors = new ArrayList<Clause>();
		
		// Construct the factors r_i* and add them to the list of inner_factors.
		// Syntactically equivalent factors do not appear since a does not contain syntactically equivalent summands.
		for (Clause r : a_summands) {
			Clause r_star = clauseStar(r);
			inner_factors.add(r_star);
		}
		
		// Then simplify the resulting star chain expression.
		//TODO: inner_factors = simplifyStarChain(inner_factors);
		Clause starChain = ClauseFactory.createStarChain(inner_factors);
		return new RegExp(starChain);
	}

	/**
	 * Further Kleene star method allowing to apply the operation to clauses as well.
	 */
	public static RegExp star(Clause a) {
		return star(new RegExp(a));
	}

	/**
	 * Method checks whether one regular expression is contained in the other.
	 * @param a, b are the given expressions.
	 * @return true, if a is contained in b - false, otherwise.
	 */
	public static boolean isContained(RegExp a, RegExp b) {
		// TODO: Currently not implemented - build up automaton.
		// along the expression and run corresponding automaton method.
		return false;
	}

	/**
	 * Further addition method allowing to check containment for clause/regexp and clause/regexp.
	 */
	public static boolean isContained(Clause a, RegExp b) {
		return isContained(new RegExp(a),b);
	}

	/**
	 * Further addition method allowing to check containment for clause/regexp and clause/regexp.
	 */
	public static boolean isContained(RegExp a, Clause b) {
		return isContained(a,new RegExp(b));
	}

	/**
	 * Further addition method allowing to check containment for clause/regexp and clause/regexp.
	 */
	public static boolean isContained(Clause a, Clause b) {
		return isContained(new RegExp(a),new RegExp(b));
	}

	/**
	 * Method checks whether the given regular expression is universal.
	 * @param a is the given expressions.
	 * @return true, if a is universal - false, otherwise.
	 */
	public static boolean isUniversal(RegExp a) {
		// Check whether a given regular expression is universal.
		// TODO: implement!
		return false;
	}
	
	/**
	 * Further addition method allowing to check universality for a clause.
	 */
	public static boolean isUniversal(Clause a) {
		return isUniversal(new RegExp(a));
	}
	
	/**
	 * Method that takes a set of clauses of a regular DNF expression and simplifies them.
	 * @param clauses is the given set of clauses.
	 * @return simpl, the simplified set of clauses.
	 * 
	 * NOTE: The method simplifies in three steps.
	 * 1st: It checks for addition with the empty expression (zero) - these are discarded.
	 * 2nd: It looks for hardcoded pattern that allow to erase summands.
	 * 3rd: It successively checks for semantic inclusion and erases summands that are not needed.
	 * NOTE: Does not return a reference to clauses nor does it manipulate clauses.
	 */
	private static HashSet<Clause> simplifySum(HashSet<Clause> clauses) {
		
		// Set of simplified clauses.
		HashSet<Clause> simpl = new HashSet<Clause>(clauses);
		
		// First simplification.
		// Remove the summand zero/empty set.
		simpl = simplifyZero(simpl);
		
		// Second simplification.
		// Simplify hardcoded pattern.
		simpl = simplifyPattern(simpl);
		
		// Third simplification.
		simpl = simplifySemantic(simpl);
		
		// Return simplified clauses.
		return simpl;
	}

	/**
	 * Method that removes the empty expression from the given set of clauses.
	 * @param clauses is the given set.
	 * @return new_clauses, the set with removed empty expression.
	 */
	private static HashSet<Clause> simplifyZero(HashSet<Clause> clauses) {
		HashSet<Clause> new_clauses = new HashSet<Clause>(clauses);
		new_clauses.remove(EmptyExp.getEmptySet());
		return new_clauses;
	}

	/**
	 * Method that simplifies the given set of clauses via checking for hardcoded pattern.
	 * @param clauses is the given set of clauses.
	 * @return reducedClause, the reduced set of clauses after the simplifications.
	 * 
	 * NOTE: Currently, the method applies two simplifications:
	 * Step 1: it applies the rules eps + c.c* = c* and eps + c*.c = c*.
	 * Step 2: it applies the rule that c can be discarded when it is contained in a 
	 * star chain expression (r_1 + ... + r_n)* where c = r_i for some i.
	 * This also covers the case that c is contained in c*.
	 * Step 3: it syntactically compares star chain expressions.
	 * If there are c = (r_1 + ... + r_n)* and d = (s_1 + ... s_k)* and each s_i appears as an r_i, d can be discarded.
	 */
	private static HashSet<Clause> simplifyPattern(HashSet<Clause> clauses) {
		
		// The simplified set of clauses.
		HashSet<Clause> reducedClauses = new HashSet<Clause>(clauses);
		
		// Simplification pattern one.
		reducedClauses = simplifyEpsilonRule(reducedClauses);
		
		// Simplification pattern two.
		reducedClauses = simplifyStarContainment(reducedClauses);
		
		// Simplification pattern three.
		reducedClauses = simplifyStarChainContainment(reducedClauses);
		
		// Return the simplified set of clauses.
		return reducedClauses;
	}
	
	/**
	 * Apply the rule 1 + c.c* = c* = 1 + c*.c to a given set of clauses and simplifies it.
	 * @param clauses is the given set of clauses.
	 * @return the simplified set of clauses reducedClauses.
	 */
	private static HashSet<Clause> simplifyEpsilonRule(HashSet<Clause> clauses) {
		// Reduced set of clauses.
		HashSet<Clause> reducedClauses = new HashSet<Clause>(clauses);
		
		// Applicable if the set of clauses contains epsilon or any star expression (then we can add epsilon for free).
		boolean containsStar = false;
		for (Clause c : clauses) {
			if (c.getType() == ClauseType.starExp) {
				containsStar = true; 
				break;
			}
		}
		
		if (clauses.contains(Epsilon.getEps()) || containsStar) {
			// Iterate over all clauses and check whether they satisfy the split form c = d.d* or c = d*.d
			// and if so, simplify them to c = d*.
			boolean simplified = false;
			
			for (Clause c : clauses) {	
				Clause split = getSplitForm(c);
				
				if (!split.equals(EmptyExp.getEmptySet())) {
					// Clause c = d.d* = d*.d is in split form - simplify to c = d*.
					reducedClauses.remove(c);
					reducedClauses.add(ClauseFactory.createStarExp(split));
					simplified = true;
				}
			}
			
			// Remove epsilon as explicit summand if at least one expression got simplified.
			if (simplified) reducedClauses.remove(Epsilon.getEps());
		}
		
		return reducedClauses;
	}

	/**
	 * Method that checks whether the given clause is in split form.
	 * This means it can be written as c = w.w* or c = w*.w, where w is some expression.
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
			
			// For left split form c = w.w*, the last concatenated factor needs to be a star expression.
			Clause star = factors.get(n-1);
			if (star.getType() == ClauseType.starExp) {
				
				// Clause c is in left split form if the expression up to the last star expression
				// coincides (syntactically) with the star expression's inner.
				ArrayList<Clause> prefix_factors = new ArrayList<Clause>(factors);
				prefix_factors.remove(n-1);
				Clause prefix = ClauseFactory.createConExp(prefix_factors);
				Clause star_inner = ((StarExp) star).getInner();
				if (prefix.equals(star_inner)) return star_inner;
			}
			
			// For right split form c = w*.w, the first concatenated factor needs to be a star expression.
			star = factors.get(0);
			if (star.getType() == ClauseType.starExp) {
				
				// Clause c is in right split form if the expression from the first star expression
				// coincides (syntactically) with the star expression's inner.
				ArrayList<Clause> postfix_factors = new ArrayList<Clause>(factors);
				postfix_factors.remove(0);
				Clause postfix = ClauseFactory.createConExp(postfix_factors);
				Clause star_inner = ((StarExp) star).getInner();
				if (postfix.equals(star_inner)) return star_inner;
			}
		}
		
		// If the given clause is not in split form, return the empty clause.
		return EmptyExp.getEmptySet();
	}

	/**
	 * Method that simplifies according to the rule: c is contained in (r_1 + ... + r_n)* when r_i = c for an i.
	 * @param clauses is the given set of clauses.
	 * @return reducedClauses, the simplified set of clauses.
	 */
	private static HashSet<Clause> simplifyStarContainment(HashSet<Clause> clauses) {
		// Reduced set of clauses.
		HashSet<Clause> reducedClauses = new HashSet<Clause>(clauses);
		
		// Obtain the inner of all star (chain) clauses.
		HashSet<Clause> innerStarClauses = new HashSet<Clause>();
		clauses.forEach(c -> { if (c.getType() == ClauseType.starExp) innerStarClauses.addAll(((StarExp) c).getInnerAsSet()); });
		
		// Remove those clauses from the reduced clause set that appear as inner of a star (chain) clause.
		reducedClauses.removeAll(innerStarClauses);
		return reducedClauses;
	}
	
	/**
	 * Method that simplifies according to the rule:
	 * if there are two star (chain) clauses c = (r_1 + ... + r_n)* and d = (s_1 + ... s_k)*
	 * and each s_i appears as one of the r_i, then we can discard d.
	 * @param clauses is the given set of clauses.
	 * @return reducedClauses, the simplified set of clauses.
	 */
	private static HashSet<Clause> simplifyStarChainContainment(HashSet<Clause> clauses) {
		// Reduced set of clauses.
		HashSet<Clause> reducedClauses = new HashSet<Clause>(clauses);
		
		// Get the star (chain) clauses.
		HashSet<Clause> starChainClauses = new HashSet<Clause>();
		clauses.forEach(c -> { if (c.getType() == ClauseType.starExp) starChainClauses.add(c); });
		
		// Compare each two star (chain) clauses for containment.
		// Those that vanish are added to deletableSC.
		HashSet<Clause> deletableSC = new HashSet<Clause>();
		
		for (Clause current : starChainClauses) {
			for (Clause other : starChainClauses) {
				
				// current and other are not allowed to be syntactically equal since then each star (chain) gets deleted.
				if (!current.equals(other) && syntacticStarChainContains((StarExp) current, (StarExp) other)) {
					// Add as deletable star (chain).
					deletableSC.add(other);
				}
			}
		}
		
		// Remove the deletable star (chains).
		reducedClauses.removeAll(deletableSC);
		return reducedClauses;
	}
	
	/**
	 * Method that checks whether one given star (chain) is syntactically contained in the other.
	 * @param a,b are the given star (chain) expressions.
	 * @return true, if b is contained in a - false, otherwise.
	 * 
	 * NOTE: This is syntactic containment.
	 * This means when a = (r_1 + ... + r_n)* and b = (s_1 + ... + s_k)*, each s_j has to appear as an r_i.
	 */
	private static boolean syntacticStarChainContains(StarExp a, StarExp b) {
		
		// Get the set of inner summands of the two star (chains).
		HashSet<Clause> summands_a = a.getInnerAsSet();
		HashSet<Clause> summands_b = b.getInnerAsSet();
		
		// Check whether the inner summands of a contain all inner summands of b.
		return summands_a.containsAll(summands_b);
	}

	/**
	 * Method for concatenating two clauses.
	 * @param r,s are the given clauses.
	 * @return r.s modulo simplifications.
	 * 
	 * NOTE: The method constructs a new concatenation expression r.s only if needed.
	 * It first tries to simplify the expression r.s in a three step procedure:
	 * 1st: It checks for a multiplication with zero (the empty expression).
	 * 2nd: It checks for a multiplication with one (epsilon).
	 * 3rd: It checks whether a factor vanishes due to monotonicity among star expressions.
	 * NOTE: May return a reference to r or s.
	 */
	private static Clause clauseConcat(Clause r, Clause s) {
		
		// Simple multiplication cases.
		ClauseType type_r = r.getType();
		ClauseType type_s = s.getType();
		
		// Multiplication with the empty expression (zero).
		// If r or s is empty, we return the empty expression.
		if (type_r == ClauseType.emptyExp || type_s == ClauseType.emptyExp) return EmptyExp.getEmptySet();
		
		// One of the factors (or both) is epsilon - multiplication with one.
		if (type_r == ClauseType.epsilon) return s;
		if (type_s == ClauseType.epsilon) return r;
		
		// Non-obvious simplifications.
		// TODO: outsource!
		// If r = R* is a star expression and s = S* is a star expression and we have
		// R is contained in S (S is contained in R) then we get r.s = s (r.s = r). 
		if (type_r == ClauseType.starExp && type_s == ClauseType.starExp) {
			
			// Clause r = R* and s = S*.
			Clause R = ((StarExp) r).getInner();
			Clause S = ((StarExp) s).getInner();
			
			// R and S are syntactically equal - return one factor.
			if (R.equals(S)) return r;
			
			// If S is contained in R, return r: R* . S* = R* = r.
			if (isContained(S,R)) return r;
			
			// If R is contained in S, return s: R* . S* = S* = s.
			if (isContained(R,S)) return s;
		}
		
		// No simplification applicable.
		// Build new concatenation expression.
		ArrayList<Clause> factors = new ArrayList<Clause>();
		if (type_r == ClauseType.conExp) {
			// If r contains more than one factor, we add them all to the factors of the new expression.
			factors.addAll(((ConExp) r).getFactors());
		} else {
			// If r contains only one factor (it is not a concatenation expression), we add r as a new factor.
			factors.add(r);
		}
		
		if (type_s == ClauseType.conExp) {
			// If s contains more than one factor, we add them all to the factors of the new expression.
			factors.addAll(((ConExp) s).getFactors());
		} else {
			// If s contains only one factor (it is not a concatenation expression), we add s as a new factor.
			factors.add(s);
		}
		
		return ClauseFactory.createConExp(factors);
	}
	
	/**
	 * Method for applying Kleene star to a single clause.
	 * @param r is the given clause.
	 * @return r*, the stared clause.
	 * 
	 * NOTE: The method constructs a new star expression r* only if needed.
	 * It first checks whether r is already of star type and if so, returns r.
	 * Then it checks whether the resulting expression is a star chain:
	 * r* = (r_1*...r_n*)* = (r_1 + ... + r_n)*.
	 * If so, it returns an expression of type StarChain.
	 * If not, it may return a star expression, based on the simplifications 
	 * built into the factory method createStarExp.
	 * NOTE: May return a reference to r.
	 */
	private static Clause clauseStar(Clause r) {
		
		// If r is already a star expression, it returns r.
		if (r.getType() == ClauseType.starExp) return r;
		
		ArrayList<Clause> inner_factors = getInnerOfStarChain(r);
		// Case of a star chain - construct one and return it.
		if (!inner_factors.isEmpty()) return ClauseFactory.createStarChain(inner_factors);
		
		// Not a star chain.
		return ClauseFactory.createStarExp(r);
	}
	
	/**
	 * Method that checks whether the given clause is of the form a = r_1* r_2* ... r_n*.
	 * This is the inner of a star chain.
	 * @param a is the given clause.
	 * @return the list of star expressions r_1*, ..., r_n*.
	 * The list is empty, if a is not a star expression.
	 */
	//TODO: do we need this function?
	private static ArrayList<Clause> getInnerOfStarChain(Clause a) {
		
		if (a.getType() == ClauseType.conExp) {	
			// Clause a must have at least two factors and
			// all factors of a need to be star expressions.
			ArrayList<Clause> factors = ((ConExp) a).getFactors();
			if (factors.size() >= 2) {
				for (Clause c : factors) {
					if (c.getType() != ClauseType.starExp) return new ArrayList<Clause>();
				}
				
				return factors;
			}
		}
		
		return new ArrayList<Clause>();
	}
	
	private static HashSet<Clause> simplifySemantic(HashSet<Clause> simpl) {
		// TODO Auto-generated method stub
		return simpl;
	}
	
}
