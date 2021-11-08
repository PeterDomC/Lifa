package regularExpression;

import java.util.HashSet;
import java.util.Objects;

import automata.Autom;
import automataAlgorithms.Operations;

/*
 * Base class for regular expressions.
 * 
 * NOTE: Regular Expressions are always assumed to be in disjunctive normal form (DNF):
 * R = r_1 + r_2 + ... r_n, where the r_i are constructed out of concatenation and Kleene star.
 * The r_i are clauses.
 * Expressions that are built without addition are seen as DNF with only one clause.
 * @Immutable
 */
public class RegExp {
	
	final HashSet<Clause> summands;
	
	/**
	 * Constructor with given set of clauses.
	 */
	public RegExp(HashSet<Clause> summands) {
		this.summands = summands;
	}
	
	/**
	 * Constructor with given clause.
	 */
	public RegExp(Clause c) {
		HashSet<Clause> summands = new HashSet<Clause>();
		summands.add(c);
		this.summands = summands;
	}
	
	/**
	 * Getter for the summands.
	 */
	public HashSet<Clause> getSummands() {
		return this.summands;
	}
	
	/**
	 * Create an automaton A representing the regular expression.
	 * This means the language of A is the expression.
	 */
	public Autom toAutom() {
		// Take the automaton representation of each summand and unify them successively.
		Autom A = new Autom();
		for (Clause c : summands) {
			A = Operations.union(A,c.toAutom());
		}
		
		// Eliminate unnecessary states.
		return Operations.reduce(A);
	}
	
	/**
     * Override of equals.
     * NOTE: This is only for syntactic equivalence!
     */
    @Override
    public boolean equals(Object o){
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof RegExp)) return false;
        
        RegExp C = (RegExp) o;
        return summands.equals(C.getSummands());
    }
    
    /**
     * Override of hashCode.
     */
    @Override
    public int hashCode(){
        return Objects.hash(summands);
    }
    
    /**
	 * Generate a string that represents the regular expression.
	 */
	@Override
	public String toString() {
		
		// At least one summand: generate the expression, where summands are separated by a +.
		if (summands.size() >= 1) {
			StringBuilder builder = new StringBuilder();
			boolean first = true;
			for (Clause c : summands) {
				if (!first) {
					builder.append(" + ");
				} else {
					first = false;
				}
				
				builder.append(c.toString());
			}
			
			return builder.toString();
		}
		
		// No summands: the expression represents the empty expression.
		return "{}";
	}
}
