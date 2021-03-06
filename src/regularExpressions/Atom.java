package regularExpressions;

import java.util.Objects;

import automata.Autom;
import automata.Letter;
import automata.State;
import automata.Transition;

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
	 * Create an automaton A representing the atom.
	 * The language of A contains only this atom.
	 */
	@Override
	public Autom toAutom() {
		
		// The automaton consists of one transition that is labeled with the atom.
		// The source is the initial state, the target is final.
		State a0 = new State(symb + "0");
		State a1 = new State(symb + "1");
		Transition t0 = new Transition(a0,a1, new Letter(symb));
		
		Autom A = new Autom();
		A.addTransition(t0);
		A.setInit(a0);
		A.addFinal(a1);
		
		return A;
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
