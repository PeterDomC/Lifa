package regularExpression;

import java.util.Objects;

public class StarExp extends RegExp {
	
	private RegExp inner;
	
	public StarExp (RegExp inner) {
		super(RegExpType.starExp);
		this.inner = inner;
	}
	
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
	
	@Override
	public String toString() {
		return ("(" + inner.toString() + ")*");
	}
}
