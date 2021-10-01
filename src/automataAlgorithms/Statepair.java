package automataAlgorithms;

import java.util.Objects;

import automata.State;

/*
 * Class which models a pair of states
 * @Immutable
 */
public class Statepair {
	
	private final State left;
	private final State right;
	
	/**
	 * Constructor that builds the pair of states (left,right)
	 */
	protected Statepair (State left, State right) {
		this.left = left;
		this.right = right;
	}
	
	/**
	 * Getter for the left component
	 */
	public State getLeft() {
		return left;
	}
	
	/**
	 * Getter for the right component
	 */
	public State getRight() {
		return right;
	}
	
	/**
	 * Turns the statepair into a single state by combining the names
	 */
	public State toState() {
		State prod = new State("(" + left.getName() + "," + right.getName() + ")");
		return prod;
	}
	
	/**
     * Override of equals
     */
    @Override
    public boolean equals(Object o){
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof Statepair)) return false;
        
        Statepair q = (Statepair) o;
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