package regularExpressions;

import java.util.ArrayList;

/*
 * Factory for building clauses of type concatenation or star.
 * NOTE: Clauses of other types can either be called by their constructor or are singletons.
 * NOTE: This class is needed in order to make the clause types semantically mutually exclusive!
 */
public abstract class ClauseFactory {
	
	/**
	 * Method tries to create a concatenation expression from the given input.
	 * @param factors is the given list of factors.
	 * @return a concatenation expression from the given list of factors.
	 * 
	 * NOTE: May return other types of clauses, depending on the list of factors.
	 * NOTE: We generally recommend to create clauses via the operations of the class Kleene
	 * since applying this method without deeper knowledge can lead to subtle structure violations.
	 * NOTE: This method already applies basic simplifications like multiplication with 0 and epsilon.
	 */
	public static Clause createConExp(ArrayList<Clause> factors) {
		
		int n = factors.size();
		
		// If factors is empty or contains the empty expression, we return the empty expression.
		if (n == 0 || factors.contains(EmptyExp.getEmptySet())) return EmptyExp.getEmptySet();
		
		// If factors consists of one entry that is not the empty expression - we return it.
		// It is either epsilon or an atom.
		if (n == 1) return factors.get(0);
		
		// The list of factors has at least two entries.
		// We remove the epsilon entries due to multiplication with 1.
		ArrayList<Clause> reduced_factors = new ArrayList<Clause>(factors);
		reduced_factors.removeIf(c -> (c.getType() == ClauseType.epsilon));
		n = reduced_factors.size();
		
		// If the list is empty now - all entries were epsilon.
		if (n == 0) return Epsilon.getEps();
		
		// If the list now consists of a single clause, we return it so that it keeps its type.
		if (n == 1) return reduced_factors.get(0);
		
		// The list still has at least two entries.
		// We can create a standard concatenation expression.
		return new ConExp(reduced_factors);
	}
	
	/**
	 * Method tries to create a star expression from the given input.
	 * @param inner is a given clause.
	 * @return a star expression inner*.
	 * 
	 * NOTE: May return other types of clauses, depending on the given clause.
	 * NOTE: We generally recommend to create clauses via the operations of the class Kleene
	 * since applying this method without deeper knowledge can lead to subtle structure violations.
	 * NOTE: This method already applies basic simplifications like r** = r*, 0* = eps, and eps* = eps.
	 */
	public static Clause createStarExp(Clause inner) {
		
		ClauseType inner_type = inner.getType();
		
		// If the inner clause is empty or epsilon, we can return epsilon.
		if (inner_type == ClauseType.emptyExp || inner_type == ClauseType.epsilon) return Epsilon.getEps();
		
		// If the inner is a star expression, we can return it: a** = a*.
		if (inner_type == ClauseType.starExp) return inner;
		
		// The inner clause is an atom or a concatenation expression.
		// We construct a proper star expression.
		return new StarExp(inner);
	}
	
	/**
	 * Method tries to create a star chain expression from the given list of stared factors.
	 * @param inner_factors = r_1*,...,r_n* is a list of already stared factors.
	 * @return The star chain expression (r_1*...r_n*)* = (r_1 + ... + r_n)*.
	 * 
	 * NOTE: May return other types of clauses, depending on the given list.
	 * NOTE: We generally recommend to create clauses via the operations of the class Kleene
	 * since applying this method without deeper knowledge can lead to subtle structure violations.
	 */
	public static Clause createStarChain(ArrayList<Clause> inner_factors) {
		
		Clause inner = createConExp(inner_factors);
		
		ClauseType inner_type = inner.getType();
		switch (inner_type) {
			// If the inner clause is empty or epsilon, we can return epsilon.
			// This happens if inner_factors is empty or consist only of epsilon.
			case emptyExp:
			case epsilon: return Epsilon.getEps();
			
			// If the inner is a star expression, we return it: a** = a*.
			case starExp: return inner;
			
			// If the inner is an atom, we return the resulting star expression.
			case atom: return new StarExp(inner);
			
			// The inner clause is a concatenation expression with at least two star factors.
			// We construct a proper star chain.
			default: return new StarChain(inner);
		}
	}
}
