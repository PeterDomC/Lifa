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
	atom, conExp, starExp, epsilon, emptyExp;
	
	/**
	 * Method that checks whether two given clauses are syntactically equivalent.
	 * @param a, b are the given clauses.
	 * @return true, if a and b are syntactically equivalent - false, if not.
	 * 
	 * NOTE: The above types can sometimes represent the same expression.
	 * For instance can a concatenation contain only a single factor and actually represent a star expression.
	 * The method at hand uses these relations and performs limited semantic simplifications.
	 * 
	 * TODO: Build factory for regular expressions that makes this method obsolete.
	 */
	public static boolean isSyntaxEquivalent(Clause a, Clause b) {
		
		ClauseType type_a = a.getType();
		ClauseType type_b = b.getType();
		
		switch (type_a) {
			case conExp:
				// a is a concatenation expression.
				ConExp a_con = (ConExp) a;
				
				if (type_b == conExp) {
					// b is also a concatenation expression.
					ConExp b_con = (ConExp) b;
					// a and b coincide if their factors all coincide.
					return a_con.getFactors().equals(b_con.getFactors());
				}
				
				if (type_b == emptyExp) {
					// b is the empty expression, then a needs to have empty as a factor.
					return a_con.getFactors().contains(EmptyExp.getEmptySet());
				}
				
				// b is of some other clause type: atom, epsilon, or a star expression.
				// Then, a needs to have a single factor that coincides with b.
				if (a_con.getFactors().size() == 1) return a_con.getFactors().get(0).equals(b);
				
				break;
				
			case starExp:
				// a is a star expression.
				StarExp a_star = (StarExp) a;
				
				if (type_b == starExp) {
					// b is also a star expression.
					StarExp b_star = (StarExp) b;
					// Inner clauses of a and b need to coincide.
					return a_star.getInner().equals(b_star.getInner());
				}
				
				if (type_b == conExp) {
					// b is a concatenation expression.
					ConExp b_con = (ConExp) b;
					// b must have one factor that is equivalent to a.
					if (b_con.getFactors().size() == 1) return a_star.equals(b_con.getFactors().get(0));
				}
		        
				if (type_b == epsilon) {
					// b is epsilon - the inner of a has to be epsilon or the empty expression.
					return (a_star.getInner().equals(EmptyExp.getEmptySet()) || a_star.getInner().equals(Epsilon.getEps()));
				}
				
		        break;
				
			case atom:
				// a is an atom.
				Atom a_atom = (Atom) a;
				
				if (type_b == atom) {
					// b is also an atom.
					Atom b_atom = (Atom) b;
					// Symbols of a and b need to be equal.
					return a_atom.getSymb().equals(b_atom.getSymb());
				}
				
				if (type_b == conExp) {
					// b is a concatenation expression.
					ConExp b_con = (ConExp) b;
					// b must have one factor that is equivalent to a.
					if (b_con.getFactors().size() == 1) return a_atom.equals(b_con.getFactors().get(0));
				}
				
				break;
				
			case emptyExp:
				// a is the empty expression.
				// Since it exists only once we don't need to test the case where b is empty as well.
				// This is handled by equals.
				
				if (type_b == conExp) {
					// b is a concatenation expression.
					ConExp b_con = (ConExp) b;
					// b must have empty as a factor.
					return b_con.getFactors().contains(EmptyExp.getEmptySet());
				}
				
				break;
				
			case epsilon:
				// a is epsilon.
				// Since it exists only once we don't need to test the case where b is epsilon as well.
				// This is handled by equals.
				
				if (type_b == starExp) {
					// b is a star expression - the inner of b has to be epsilon or the empty expression.
					StarExp b_star = (StarExp) b;
					return (b_star.getInner().equals(EmptyExp.getEmptySet()) || b_star.getInner().equals(Epsilon.getEps()));
				}
				
				if (type_b == conExp) {
					// b is a concatenation expression.
					ConExp b_con = (ConExp) b;
					// b must have one factor that is equivalent to epsilon.
					if (b_con.getFactors().size() == 1) return Epsilon.getEps().equals(b_con.getFactors().get(0));
				}
				
				break;
		}
		
		// a and b are not syntactically equivalent.
		return false;
	}
}
