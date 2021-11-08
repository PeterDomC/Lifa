package regularExpressions;

import automata.Autom;
import automata.State;

/*
 * Class for modeling a clause that consists of epsilon.
 * Note that this is a singleton pattern.
 * This means there is no need for overriding equals/hash code since there is only a single instance of this class.
 * @Immutable
 */
public class Epsilon extends Clause {
	
	private static Epsilon eps;
	
	/**
	 * Private Constructor.
	 */
	private Epsilon () {
		super(ClauseType.epsilon);
	}
	
	/**
	 * Getter for the instance.
	 */
	public static Epsilon getEps() {
		if (eps == null) {
			eps = new Epsilon();
		}
		
		return eps;
	}
	
	/**
	 * Create an automaton A representing epsilon.
	 * The language of A contains only epsilon.
	 */
	@Override
	public Autom toAutom() {
		
		// The automaton consists of a single state that is initial and final at the same time.
		State eps = new State("eps");
		Autom A = new Autom();
		A.addState(eps);
		A.setInit(eps);
		A.addFinal(eps);
		
		return A;
	}
	
	/**
	 * Method for representing epsilon as a string.
     */
	@Override
	public String toString() {
		return "[@eps]";
	}
}
