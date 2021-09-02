package automata;

import java.util.Objects;

/**
 * Class for transitions of a (finite) automaton.
 * Consists of a source state, a target state, a label, and an index.
 */
public class Transition{
	
    private final State source;
    private final State target;
    private final Letter label;
    int index;
    
    /*
     * Constructor
     */
    public Transition(State source, State target, Letter label){
        this.source = source;
        this.target = target;
        this.label = label;
        this.index = 0;
    }
    
    /*
     * Getter: Source State
     */
    public State getSource(){
        return source;
    }
    
    /*
     * Getter: Target State
     */
    State getTarget(){
        return target;
    }
    
    /*
     * Getter: Label
     */
    Letter getLabel(){
        return label;
    }

    /*
     * Getter: Index
     */
    int getIndex(){
        return index;
    }
    
    /*
     * Setter: Index
     */
    void setIndex(int in_index){
        index = in_index;
    }

    /*
     * Override of equals
     */
    @Override
    public boolean equals(Object o){
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof Transition)) return false;

        Transition T = (Transition) o;
        return source.equals(T.getSource()) && target.equals(T.getTarget()) && label.equals(T.getLabel());
    }

    /*
     * Override of hashCode
     */
    @Override
    public int hashCode(){
        return Objects.hash(source, target, label);
    }
}