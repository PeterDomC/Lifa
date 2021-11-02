package regularExpression;

import java.util.Objects;

/*
 * Class for clauses of star type: R = A*.
 * @Immutable
 */
public class StarExp extends Clause {
	
	private final Clause inner;
	
	/**
	 * Constructor for clauses of star type with given clause/expression that is stared.
	 */
	public StarExp (Clause inner) {
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
		ClauseType inner_type = inner.getType();
		if (inner_type == ClauseType.atom) return (inner.toString() + "*");
		return ("(" + inner.toString() + ")*");
	}
}
