package regularExpression;

import java.util.Objects;

public class ConExp extends RegExp {

	private final RegExp leftFactor;
	private final RegExp rightFactor;
	
	public ConExp (RegExp leftFactor, RegExp rightFactor) {
		super(RegExpType.conExp);
		this.leftFactor = leftFactor;
		this.rightFactor = rightFactor;
	}

	public RegExp getLeftFactor() {
		return this.leftFactor;
	}

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
	
    /*
     * TODO: binding of .
     * Check what left and right factor are and whether we need a bracket (case of sum expression)
     */
	@Override
	public String toString() {
		return (leftFactor.toString() + " . "  + rightFactor.toString());
	}
}
