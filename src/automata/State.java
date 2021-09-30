package automata;

import java.util.Objects;

/*
 * State of a finite automaton
 * Consists of a name and flags for initial or final state
 * @Immutable
 */
public class State{
	
    private final String name;
    private final boolean init;
    private final boolean fin;

    /**
     * Constructor which sets fin and init to false
     */
    public State(String name) {
    	this.name = name;
    	this.init = false;
    	this.fin = false;
    }
    
    /**
     * Constructor with booleans for being initial and final
     */
    public State(String name, boolean init, boolean fin){
        this.name = name;
        this.init = init;
        this.fin = fin;
    }
    
    /**
     * Getter: Name
     */
    public String getName(){
        return name;
    }

    /**
     * Getter: Initial state
     */
    public boolean isInit(){
        return init;
    }

    /**
     * Getter: Final State
     */
    public boolean isFinal(){
        return fin;
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
}