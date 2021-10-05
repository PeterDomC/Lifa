package automataAlgorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;

import automata.State;

/*
 * Class which models a set of states - namely a state of the powerset construction.
 * It is on purpose not to use this class in the definition of automata.
 * This is only meant for interior use within algorithms for automata.
 * @Immutable
 */
public class Powerstate {

	private final HashSet<State> setContent;
	
	/**
	 * Constructor that builds the stateset
	 */
	protected Powerstate(HashSet<State> setContent) {
		this.setContent = setContent;
	}
	
	/**
	 * Getter for the set of states
	 */
	public HashSet<State> getSetContent() {
		return this.setContent;
	}
	
	/**
	 * Turns the stateset into a single state by combining the names of the entries.
	 * NOTE: We need the lexigraphical order here in order to identify two states as equal that stem from the
	 * same powerstate but the below iteration over the elements is different.
	 * For sets, order does not matter, for string comparison it does.
	 * NOTE: States are comparable.
	 */
	public State toState() {
		ArrayList<State> statelist = new ArrayList<State>(setContent);
		Collections.sort(statelist);
		
		StringBuilder stateName = new StringBuilder();
		int i = 0;
		int n = statelist.size();
		
		stateName.append("{");
		for (State q : statelist) {
			stateName.append(q.getName());
			
			if (i < n-1) stateName.append(",");
			i++;
		}
		
		stateName.append("}");
		return new State(stateName.toString());
	}
	
	/**
     * Override of equals
     */
    @Override
    public boolean equals(Object o){
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof Powerstate)) return false;
        
        Powerstate Q = (Powerstate) o;
        return (this.setContent.equals(Q.getSetContent()));
    }
    
    /**
     * Override of hashCode
     */
    @Override
    public int hashCode(){
        return Objects.hash(setContent);
    }
}