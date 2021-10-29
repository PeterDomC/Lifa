package regularExpression;

import automata.Autom;

/*
 * Base abstract class for regular expressions.
 * Regular Expressions of a certain type extend the class.
 * @Immutable
 */
public abstract class RegExp {
	
	private RegExpType type;
	
	/**
	 * Constructor that specifies the type of the regular expression.
	 */
	public RegExp(RegExpType type) {
		this.type = type;
	}
	
	/**
	 * Getter for the type.
	 */
	public RegExpType getType() {
		return this.type;
	}
	
	/**
	 * 
	 */
	//public abstract Autom toAutom();
}
