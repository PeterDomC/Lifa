package automataAlgorithms;

import java.util.Objects;

import automata.State;

/*
 * Class which models a pair of states
 */
public class Statepair {
	
	private final State left;
	private final State right;
	
	/**
	 * Constructor that builds the statepair (left,right)
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
     * TODO: Override of equals
     */
	/*
    @Override
    public boolean equals(Object o){
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof State)) return false;

        State Q = (State) o;
        return name.equals(Q.getName());
    }
    */
    /**
     * TODO: Override of hashCode
     */
	/*
    @Override
    public int hashCode(){
        return Objects.hash(name);
    }
    */
}