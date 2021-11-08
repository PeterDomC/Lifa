package regularExpressions;

import automata.Autom;

/*
 * Class for modeling a clause that represents the empty set.
 * Note that this is a singleton pattern.
 * This means there is no need for overriding equals/hash code since there is only a single instance of this class.
 * @Immutable
 */
public class EmptyExp extends Clause {
	
	private static EmptyExp emptySet;
	
	/**
	 * Private Constructor.
	 */
	private EmptyExp () {
		super(ClauseType.emptyExp);
	}
	
	/**
	 * Getter for the instance.
	 */
	public static EmptyExp getEmptySet() {
		if (emptySet == null) {
			emptySet = new EmptyExp();
		}
		
		return emptySet;
	}
	
	/**
	 * Create an automaton representing the empty expression.
	 * The language of the automaton is empty.
	 */
	@Override
	public Autom toAutom() {
		
		// Return the empty automaton.
		return new Autom();
	}
	
	/**
	 * Method for representing the empty set as a string.
     */
	@Override
	public String toString() {
		return "{}";
	}
}
