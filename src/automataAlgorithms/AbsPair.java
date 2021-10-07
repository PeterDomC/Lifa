package automataAlgorithms;

import java.util.Objects;

/*
 * Class to model an abstract immutable pair
 * @Immutable
 */
public class AbsPair<T,K> {
	
	private final T left;
	private final K right;
	
	/**
	 * Constructor that builds the pair of states (left,right)
	 */
	protected AbsPair(T left, K right) {
		this.left = left;
		this.right = right;
	}
	
	/**
	 * Getter for the left component
	 */
	public T getLeft() {
		return left;
	}
	
	/**
	 * Getter for the right component
	 */
	public K getRight() {
		return right;
	}
	
	/**
     * Override of equals
     */
    @Override
    public boolean equals(Object o){
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof AbsPair<?,?>)) return false;
        
        AbsPair<?,?> q = (AbsPair<?, ?>) o;
        return (this.left.equals(q.getLeft()) && this.right.equals(q.getRight()));
    }
    
    /**
     * Override of hashCode
     */
    @Override
    public int hashCode(){
        return Objects.hash(left,right);
    }
}
