package automata;

import java.util.Objects;

/**
 * State of a finite automaton.
 * Consists of a name, flags for initial or final state, an index.
 */
public class State{
	
    private final String name;
    private boolean init;
    private boolean fin;
    private int index;

    /*
     * Constructor which sets fin and init to false
     */
    public State(String name) {
    	this.name = name;
    	this.init = false;
    	this.fin = false;
    	this.index = 0;
    }
    
    /*
     * Constructor with booleans for being initial and final
     */
    public State(String name, boolean init, boolean fin){
        this.name = name;
        this.init = init;
        this.fin = fin;
        this.index = 0;
    }
    
    /*
     * Getter: Name
     */
    public String getName(){
        return name;
    }

    /*
     * Getter: Initial state
     */
    public boolean isInit(){
        return init;
    }

    /*
     * Getter: Final State
     */
    public boolean isFinal(){
        return fin;
    }

    /*
     * Getter: Index
     */
    public int getIndex(){
        return index;
    }

    /*
     * Setter: Initial state
     */
    public void setInit(boolean init){
        this.init = init;
    }

    /*
     * Setter: Final state
     */
    public void setFinal(boolean fin){
        this.fin = fin;
    }

    /*
     * Setter: Index
     */
    public void setIndex(int index){
        this.index = index;
    }
    
    /*
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
    
    /*
     * Override of hashCode
     */
    @Override
    public int hashCode(){
        return Objects.hash(name);
    }
}