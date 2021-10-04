package automata;

import java.util.Objects;

/*
 * State of a finite automaton
 * Basically a wrapper of a String
 * @Immutable
 */
public class State implements Comparable<State>{
	
    private final String name;

    /**
     * Constructor generating a state with the given name
     */
    public State(String name) {
    	this.name = name;
    }
    
    /**
     * Getter: Name
     */
    public String getName(){
        return name;
    }
    
    /**
     * Override of equals
     */
    @Override
    public boolean equals(Object o){
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof State)) return false;

        State Q = (State) o;
        return name.equals(Q.getName());
    }
    
    /**
     * Override of hashCode
     */
    @Override
    public int hashCode(){
        return Objects.hash(name);
    }
    
    /**
     * Override of compareTo
     */
	@Override
	public int compareTo(State q) {
		return name.compareTo(q.getName());
	}
}