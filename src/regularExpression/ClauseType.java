package regularExpression;

/*
 * Different types of clauses in regular expressions.
 * Note that these types are mutually exclusive,
 * a clause can only be of one type at a time.
 */
public enum ClauseType {
	
	/**
	 * Clauses can be concatenations or stars of concatenations and star expressions.
	 * They can as well be atomic (letters), be epsilon, or represent the empty set.
	 */
	atom, conExp, starExp, epsilon, emptyExp
	
}
