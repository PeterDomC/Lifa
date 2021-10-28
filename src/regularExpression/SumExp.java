package regularExpression;

import java.util.Objects;

public class SumExp implements RegExp {
	
	private RegExp leftSumand;
	private RegExp rightSumand;
	
	public SumExp (RegExp leftSumand, RegExp rightSumand) {
		this.leftSumand = leftSumand;
		this.rightSumand = rightSumand;
	}
	
	public RegExp getLeftSumand() {
		return this.leftSumand;
	}
	
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
	
	@Override
	public String toString() {
		return (leftSumand.toString() + " + "  + rightSumand.toString());
	}
}
