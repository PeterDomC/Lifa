package automata;

import java.util.Objects;

/*
 * Class for transitions of a (finite) automaton
 * Consists of a source state, a target state, a label, and an index
 * @Immutable
 */
public class Transition{
	
    private final State source;
    private final State target;
    private final Letter label;
    
    /**
     * Constructor
     */
    public Transition(State source, State target, Letter label){
        this.source = source;
        this.target = target;
        this.label = label;
    }
    
    /**
     * Getter: Source State
     */
    public State getSource(){
        return source;
    }
    
    /**
     * Getter: Target State
     */
    public State getTarget(){
        return target;
    }
    
    /**
     * Getter: Label
     */
    public Letter getLabel(){
        return label;
    }
    
    /**
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
    
    /**
     * Override of hashCode
     */
    @Override
    public int hashCode(){
        return Objects.hash(source, target, label);
    }
}