package regularExpression;

import java.util.Objects;

/*
 * Class for regular expressions of summation type: R = A + B.
 * @Immutable
 */
public class SumExp extends RegExp {
	
	private final RegExp leftSumand;
	private final RegExp rightSumand;
	
	/**
	 * Constructor for expressions of sum type.
	 * @param leftSumand, rightSumand are the expressions that are summed up.
	 * The resulting expression is lefftSumand + rightSumand.
	 */
	public SumExp (RegExp leftSumand, RegExp rightSumand) {
		super(RegExpType.sumExp);
		this.leftSumand = leftSumand;
		this.rightSumand = rightSumand;
	}
	
	/**
	 * Getter for the left summand.
	 */
	public RegExp getLeftSumand() {
		return this.leftSumand;
	}
	
	/**
	 * Getter for the right summand.
	 */
	public RegExp getRightSumand() {
		return this.rightSumand;
	}
	
	/**
     * Override of equals.
     * NOTE: This is only for syntactic equivalence!
     */
    @Override
    public boolean equals(Object o){
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof SumExp)) return false;
        
        SumExp C = (SumExp) o;
        return leftSumand.equals(C.getLeftSumand()) && rightSumand.equals(C.getRightSumand());
    }
    
    /**
     * Override of hashCode.
     */
    @Override
    public int hashCode(){
        return Objects.hash(leftSumand,rightSumand);
    }
    
    /**
	 * Generate a string that represents the summation expression.
	 */
	@Override
	public String toString() {
		return (leftSumand.toString() + " + "  + rightSumand.toString());
	}
}
