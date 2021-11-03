package regularExpression;

import java.util.ArrayList;
import java.util.Objects;

/*
 * Class for clauses of concatenation type: R = r_1 ... r_n.
 * 
 * NOTE: Concatenation expressions are always assumed to be flat:
 * If a factor r_i = a.b is itself a concatenation, then a,b appear as factors instead of r_i.
 * Hence, the factors r_i cannot be concatenation expressions themselves.
 * This is not checked by the constructor due to performance - this has to be ensured by the user
 * or by constructing concatenation expressions solely via the factory provided by the class Kleene.
 * @Immutable
 */
public class ConExp extends Clause {
	
	private final ArrayList<Clause> factors;
	//TODO: make constructor protected
	/**
	 * Constructor for clauses of concatenation type.
	 * The given list contains the factors.
	 */
	public ConExp (ArrayList<Clause> factors) {
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
     * Override of equals.
     * NOTE: This is only for syntactic equivalence!
     */
    @Override
    public boolean equals(Object o){
        if (o == null) return false;
        if (o == this) return true;
        
        // If input is not a clause - discard.
        if (!(o instanceof Clause)) return false;
        Clause C = (Clause) o;
        
        return ClauseType.isSyntaxEquivalent(this,C);
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
		for (Clause c : factors) {
			builder.append(c.toString());
		}
		
		return builder.toString();
	}
}
