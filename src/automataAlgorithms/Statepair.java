package automataAlgorithms;

import automata.State;

/*
 * Class which models a pair of states
 * @Immutable
 */
public class Statepair extends AbsPair<State,State>{
	
	/**
	 * Constructor
	 */
	protected Statepair(State left, State right) {
		super(left, right);
	}
	
	/**
	 * Turns the statepair into a single state by combining the names
	 */
	public State toState() {
		State prod = new State("(" + getLeft().getName() + "," + getRight().getName() + ")");
		return prod;
	}
}