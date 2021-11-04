package regularExpression;

import java.util.ArrayList;
import java.util.Objects;

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
public class StarExp extends Clause {
	
	private final Clause inner;
	
	/**
	 * Constructor for clauses of star type with given clause/expression that is stared.
	 */
	protected StarExp (Clause inner) {
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
	 * Method that checks whether the expression is of the form (r_1* r_2* ... r_n*)* = (r_1 + ... + r_n)*.
	 * The method is needed to simplify the printed expression.
	 */
	public boolean isStarChain() {
		
		if (inner.getType() == ClauseType.conExp) {
			// The inner is of concatenation type with at least two factors.
			// And all these factors need to be star expressions.
			ArrayList<Clause> factors = ((ConExp) inner).getFactors();
			if (factors.size() >= 1) {
				for (Clause c : factors) {
					if (c.getType() != ClauseType.starExp) return false;
				}
				
				return true;
			}
		}
		
		return false;
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
		
		if (isStarChain()) {
			// Star chain expressions (r_1*r_2* ... r_n*)* are visualized as (r_1 + r_2 + ... + r_n)*.
			// The formula stays readable this way.
			StringBuilder builder = new StringBuilder();
			builder.append("(");
			
			ArrayList<Clause> factors = ((ConExp) inner).getFactors();
			boolean first = true;
			for (Clause c : factors) {
				if (!first) {
					builder.append(" + ");
				} else {
					first = false;
				}
				
				builder.append(((StarExp) c).inner.toString());
			}
			
			builder.append(")*");
			return builder.toString();
		}
		
		return ("(" + inner.toString() + ")*");
	}
}
