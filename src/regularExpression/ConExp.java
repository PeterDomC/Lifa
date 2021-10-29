package regularExpression;

import java.util.Objects;

/*
 * Class for regular expressions of concatenation type: R = A.B.
 * @Immutable
 */
public class ConExp extends RegExp {

	private final RegExp leftFactor;
	private final RegExp rightFactor;
	
	/**
	 * Constructor for expressions of concat type.
	 * @param leftFactor, rightFactor are the expressions that are concatenated.
	 * The resulting expression is lefftFactor . rightFactor.
	 */
	public ConExp (RegExp leftFactor, RegExp rightFactor) {
		super(RegExpType.conExp);
		this.leftFactor = leftFactor;
		this.rightFactor = rightFactor;
	}
	
	/**
	 * Getter for the left factor.
	 */
	public RegExp getLeftFactor() {
		return this.leftFactor;
	}

	/**
	 * Getter for the right factor.
	 */
	public RegExp getRightFactor() {
		return this.rightFactor;
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
        return leftFactor.equals(C.getLeftFactor()) && rightFactor.equals(C.getRightFactor());
    }
    
    /**
     * Override of hashCode.
     */
    @Override
    public int hashCode(){
        return Objects.hash(leftFactor,rightFactor);
    }
	
    /**
	 * Generate a string that represents the concatenation expression.
	 */
	@Override
	public String toString() {
		RegExpType left_type = leftFactor.getType();
		RegExpType right_type = rightFactor.getType();
		
		String left = leftFactor.toString();
		if (left_type == RegExpType.sumExp) left = "(" + left + ")";
		
		String right = rightFactor.toString();
		if (right_type == RegExpType.sumExp) right = "(" + right + ")";
		
		return left + "." + right;
	}
}
