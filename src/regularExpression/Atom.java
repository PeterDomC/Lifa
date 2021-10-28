package regularExpression;

import java.util.Objects;

import automata.Letter;

/*
 * Class for modeling a regular expression that consists of a single letter.
 * @Immutable
 */
public class Atom extends RegExp {
	
	private final String symb;
	
	/**
     * Constructor.
     */
	public Atom (Letter a) {
		super(RegExpType.atom);
		this.symb = a.getSymb();
	}
	
	/**
     * Getter: Symbol.
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
        if (!(o instanceof Atom)) return false;
        
        Atom C = (Atom) o;
        return symb.equals(C.getSymb());
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
