package regularExpressions;

import java.util.ArrayList;
import java.util.Objects;

import automata.Autom;
import automataAlgorithms.Operations;

/*
 * Class for clauses of concatenation type: R = r_1 ... r_n.
 * 
 * NOTE: Concatenation expressions are always assumed to be flat:
 * If a factor r_i = a.b is itself a concatenation, then a,b appear as factors instead of r_i.
 * Hence, the factors r_i cannot be concatenation expressions themselves.
 * This is not checked by the constructor due to performance - this has to be ensured by the user
 * or by constructing concatenation expressions solely via the factory provided by the class Kleene.
 * NOTE: Concatenation expressions are always assumed to have at least two factors.
 * @Immutable
 */
public class ConExp extends Clause {
	
	private final ArrayList<Clause> factors;
	
	/**
	 * Constructor for clauses of concatenation type.
	 * The given list contains the factors.
	 */
	ConExp (ArrayList<Clause> factors) {
		super(ClauseType.conExp);
		this.factors = factors;
	}
	
	/**
	 * Getter for the factors.
	 */
	public ArrayList<Clause> getFactors() {
		return this.factors;
	}
	
	/**
	 * Create an automaton A representing the concatenation expression.
	 * This means the language of A is the expression.
	 */
	@Override
	public Autom toAutom() {
		// Take the automaton representation of the factors and concatenate them successively.
		// Note that a concatenation expression has at least two factors.
		Autom A = new Autom();
		boolean first = true;
		for (Clause c : factors) {
			if (!first) {
				// Concatenate with further factors.
				A = Operations.concat(A,c.toAutom());
			} else {
				// First factor.
				A = factors.get(0).toAutom();
				first = false;
			}
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
        if (!(o instanceof ConExp)) return false;

        ConExp C = (ConExp) o;
        return factors.equals(C.getFactors());
    }
    
    /**
     * Override of hashCode.
     */
    @Override
    public int hashCode(){
        return Objects.hash(factors);
    }
	
    /**
	 * Generate a string that represents the concatenation expression.
	 */
	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		boolean first = true;
		for (Clause c : factors) {
			if (!first) {
				builder.append(".");
			} else {
				first = false;
			}
			
			builder.append(c.toString());
		}
		
		return builder.toString();
	}
}
