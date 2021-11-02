package regularExpression;

import java.util.ArrayList;
import java.util.Objects;

/*
 * Class for clauses of concatenation type: R = r_1 ... r_n.
 * @Immutable
 */
public class ConExp extends Clause {

	private final ArrayList<Clause> factors;
	
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
		for (Clause c : factors) {
			builder.append(c.toString());
		}
		
		return builder.toString();
	}
}
