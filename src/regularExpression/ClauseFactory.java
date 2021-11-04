package regularExpression;

import java.util.ArrayList;

/*
 * Factory for building clauses of type concatenation or star.
 * NOTE: Clauses of other types can either be called by their constructor or are singletons.
 * NOTE: This class is needed in order to make the clause types semantically mutually exclusive!
 */
public class ClauseFactory {

	/**
	 * Method tries to create a concatenation expression from the given input.
	 * @param factors is the given list of factors.
	 * @return a concatenation expression from the given list of factors.
	 * 
	 * NOTE: May return other types of clauses, depending on the list of factors.
	 * NOTE: We generally recommend to create clauses via the operations of the class Kleene
	 * since applying this method without deeper knowledge can lead to subtle structure violations.
	 */
	public static Clause createConExp(ArrayList<Clause> factors) {
		
		int n = factors.size();
		
		// If factors is empty or contains the empty expression, we return the empty expression.
		if (n == 0 || factors.contains(EmptyExp.getEmptySet())) return EmptyExp.getEmptySet();
		
		// If factors consists of one entry that is not the empty expression - we return it.
		// It is either epsilon or an atom.
		if (n == 1) return factors.get(0);
		
		// The list of factors has at least two entries.
		// We can create a standard concatenation expression.
		return new ConExp(factors);
	}
	
	/**
	 * Method tries to create a star expression from the given input.
	 * @param inner is a given clause.
	 * @return a star expression inner*.
	 * 
	 * NOTE: May return other types of clauses, depending on the given clause.
	 * NOTE: We generally recommend to create clauses via the operations of the class Kleene
	 * since applying this method without deeper knowledge can lead to subtle structure violations.
	 */
	public static Clause CreateStarExp(Clause inner) {
		
		ClauseType inner_type = inner.getType();
		
		// If the inner clause is empty or epsilon, we can return epsilon.
		if (inner_type == ClauseType.emptyExp || inner_type == ClauseType.epsilon) return Epsilon.getEps();
		
		// If the inner is a star expression, we can return it: a** = a*.
		if (inner_type == ClauseType.starExp) return inner;
		
		// The inner clause is an atom or a concatenation expression.
		// We construct a proper star expression.
		return new StarExp(inner);
	}
}
