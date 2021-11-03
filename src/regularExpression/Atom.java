package regularExpression;

import java.util.Objects;

import automata.Letter;

/*
 * Class for modeling a clause that consists of a single letter - an atom.
 * @Immutable
 */
public class Atom extends Clause {
	
	private final String symb;
	
	/**
     * Constructor that takes the symbol from a given letter.
     */
	public Atom (Letter a) {
		super(ClauseType.atom);
		this.symb = a.getSymb();
	}
	
	/**
     * Constructor that takes the symbol from a given string.
     */
	public Atom (String a) {
		super(ClauseType.atom);
		this.symb = a;
	}
	
	/**
     * Getter for the symbol.
     */
	public String getSymb() {
		return this.symb;
	}
	
	/**
     * Override of equals.
     */
    @Override
    public boolean equals(Object o){
        if (o == null) return false;
        if (o == this) return true;
        
        // If input is not a clause - discard.
        if (!(o instanceof Clause)) return false;
        Clause C = (Clause) o;
        
        return ClauseType.isSyntaxEquivalent(this,C);
    }
    
    /**
     * Override of hashCode.
     */
    @Override
    public int hashCode(){
        return Objects.hash(symb);
    }
    
    /**
     * Method for translating the Atom to a string.
     */
    @Override
    public String toString() {
    	return this.symb;
    }
}
