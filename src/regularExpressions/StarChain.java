package regularExpressions;

import java.util.ArrayList;
import java.util.HashSet;

/*
 * Class for clauses of star type that have a special form - they are star chains:
 * (r_1* ... r_n*)* = (r_1 + ... + r_n)*.
 * The class is needed to remember that a clause of star chain type can be written as a sum.
 * This significantly simplifies the output when writing a regular expression.
 * @Immutable
 */
public class StarChain extends StarExp {
	
	/**
	 * Constructor for star chain expressions.
	 */
	StarChain (Clause inner) {
		super(inner);
	}
	
	/**
	 * Getter for the inner star factors of the star chain: r_1* ... r_n*.
	 */
	public HashSet<Clause> getInnerStarFactors() {
		Clause inner = super.getInner();
		ArrayList<Clause> star_factors = ((ConExp) inner).getFactors();
		return new HashSet<Clause>(star_factors);
	}
	
	/**
	 * Getter for the inner summands r_1, ..., r_n as a set of clauses with n elements.
	 */
	@Override
	public HashSet<Clause> getInnerAsSet() {
		Clause inner = super.getInner();
		HashSet<Clause> summands = new HashSet<Clause>();
		ArrayList<Clause> star_factors = ((ConExp) inner).getFactors();
		for (Clause c : star_factors) summands.add(((StarExp) c).getInner());
		
		return summands;
	}
	
	/**
	 * Generate a string that represents the star chain expression.
	 */
    @Override
	public String toString() {
    	// Star chain expressions (r_1*r_2* ... r_n*)* are visualized as (r_1 + r_2 + ... + r_n)*.
    	Clause inner = super.getInner();
		StringBuilder builder = new StringBuilder();
		builder.append("(");
		
		ArrayList<Clause> factors = ((ConExp) inner).getFactors();
		boolean first = true;
		for (Clause c : factors) {
			if (!first) {
				builder.append(" + ");
			} else {
				first = false;
			}
			
			builder.append(((StarExp) c).getInner().toString());
		}
		
		builder.append(")*");
		return builder.toString();
    }
}
