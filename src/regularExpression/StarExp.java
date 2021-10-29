package regularExpression;

import java.util.Objects;

/*
 * Class for regular expressions of star type: R = A*.
 * @Immutable
 */
public class StarExp extends RegExp {
	
	private final RegExp inner;
	
	/**
	 * Constructor for expressions of star type.
	 * @param inner is the expression that is stared.
	 */
	public StarExp (RegExp inner) {
		super(RegExpType.starExp);
		this.inner = inner;
	}
	
	/**
	 * Getter for the inner expression.
	 */
	public RegExp getInner() {
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
		RegExpType inner_type = inner.getType();
		if (inner_type == RegExpType.atom) return (inner.toString() + "*");
		return ("(" + inner.toString() + ")*");
	}
}
