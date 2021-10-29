package regularExpression;

/*
 * Base abstract class for regular expressions.
 * Regular Expressions of a certain type extend the class.
 * 
 * NOTE: We follow the mathematical definition of regular expressions.
 * They can be build from concatenation, summation, or by (Kleene-)staring other regular expressions.
 * The leaf cases are atoms (letters), epsilon, or the empty expression.
 * This is implemented via a tree structure.
 * The structure has to be respected when implementing algorithms over regular expressions.
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
}
