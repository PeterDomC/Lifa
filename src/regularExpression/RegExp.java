package regularExpression;

import java.util.HashSet;
import java.util.Objects;

/*
 * Base class for regular expressions.
 * 
 * NOTE: Regular Expressions are always assumed to be in disjunctive normal form (DNF):
 * R = r_1 + r_2 + ... r_n, where the r_i are constructed out of concatenation and Kleene star.
 * The r_i are clauses.
 * Expressions that are built without addition are seen as DNF with only one clause.
 * The clauses extends this class and carries a tree structure for its interior expression,
 * based on the clause type (concatenation or star expression).
 * Base cases are atoms (letters), epsilon, and the empty expression.
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
	 * Constructor for a single clause.
	 */
	public RegExp(Clause summand) {
		this.summands = new HashSet<Clause>();
		summands.add(summand);
	}
	
	/**
	 * Getter for the summands.
	 */
	public HashSet<Clause> getSummands() {
		return this.summands;
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
}
