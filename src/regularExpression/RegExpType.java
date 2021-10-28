package regularExpression;

/*
 * Different types of regular expressions.
 * Note that these types are mutually exclusive,
 * an expression can only be of one type at a time.
 */
public enum RegExpType {
	
	/**
	 * Regular expressions can be summations, concatenations, or stars of other regular expressions.
	 * They can as well be atomic (letters), be epsilon, or represent the empty set.
	 */
	atom, sumExp, conExp, starExp, epsilon, emptyExp
	
}
