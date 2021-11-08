package regularExpression;

import java.util.Objects;

import automata.Autom;
import automata.Letter;
import automata.State;
import automata.Transition;
import automataAlgorithms.Operations;
import automatonPrinter.AutomPrinter;

/*
 * Class for clauses of star type: R = A*.
 * 
 * NOTE: Star expressions are always assumed to be flat:
 * If A = E* is a star expression itself, then R = E** = E* and we store only E.
 * Hence, the inner A cannot be a star expression itself.
 * This is not checked by the constructor due to performance - this has to be ensured by the user
 * or by constructing star expressions solely via the factory provided by the class Kleene.
 * @Immutable
 */
class StarExp extends Clause {
	
	private final Clause inner;
	
	/**
	 * Constructor for clauses of star type with given clause/expression that is stared.
	 */
	StarExp (Clause inner) {
		super(ClauseType.starExp);
		this.inner = inner;
	}
	
	/**
	 * Getter for the inner expression.
	 */
	public Clause getInner() {
		return this.inner;
	}
	
	/**
	 * Create an automaton A representing the star expression.
	 * This means the language of A is the expression.
	 */
	@Override
	public Autom toAutom() {
		// Take the automaton representation of inner and apply kleene star to it.
		Autom A = Operations.kleene(inner.toAutom());
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
        if (!(o instanceof StarExp)) return false;
        
        StarExp C = (StarExp) o;
        return inner.equals(C.getInner());
    }
    
    /**
     * Override of hashCode.
     */
    @Override
    public int hashCode(){
        return Objects.hash(inner);
    }
	
	/**
	 * Generate a string that represents the star expression.
	 */
    @Override
	public String toString() {
		if (inner.getType() == ClauseType.atom) return (inner.toString() + "*");
		return ("(" + inner.toString() + ")*");
	}
}
