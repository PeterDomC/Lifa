package regularExpression;

import automata.Autom;

/*
 * Class for the clauses of a regular expression.
 * Clauses of a certain type extend this class:
 * This can be concatenation or star expressions or one of the base cases:
 * atoms (letters), epsilon, and the empty expression.
 * The type is stored in the field 'type'.
 * 
 * NOTE: Clause is not a subclass of RegExp due to self-referencing reasons.
 * @Immutable
 */
public abstract class Clause {
	
	private final ClauseType type;
	
	/**
	 * Constructor with the given type.
	 */
	public Clause(ClauseType type) {
		this.type = type;
	}

	/**
	 * Getter for the type.
	 */
	public ClauseType getType() {
		return this.type;
	}

	/**
	 * Method that translates the clause at hand into an automaton
	 * the language of which consists of the clause.
	 */
	public abstract Autom toAutom();
}
