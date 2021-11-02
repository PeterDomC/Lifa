package regularExpression;

import java.util.HashSet;

/*
 * Class for the clauses of a regular expression.
 * Clauses of a certain type extend this class.
 * The type is stored in the field 'type'.
 * @Immutable
 */
public abstract class Clause extends RegExp {
	
	private final ClauseType type;
	
	/**
	 * Constructor with the given type.
	 */
	public Clause(ClauseType type) {
		super(new HashSet<Clause>());
		super.summands.add(this);
		this.type = type;
	}

	/**
	 * Getter for the type.
	 */
	public ClauseType getType() {
		return this.type;
	}
}
