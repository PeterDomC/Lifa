package automata;

import java.util.HashMap;
import java.util.HashSet;

/*
 * Automaton class.
 * Automata consist of an alphabet Sigma, a set of states Stateset, a set of Transitions Trans,
 * an initial state, and a set of final states.
 */
public class Autom{
	
    private HashSet<Letter> Sigma;
    private HashSet<State> Stateset;
    private HashSet<Transition> Trans;
    private State init;
    private HashSet<State> Final;
    
    /**
     * Constructor - generates an empty NFA
     */
    public Autom() {
    	this.Sigma = new HashSet<Letter>();
    	this.Stateset = new HashSet<State>();
        this.Trans = new HashSet<Transition>();
        this.init = null;
        this.Final = new HashSet<State>();
    }
    
    /**
     * Constructor with given alphabet
     */
    public Autom(HashSet<Letter> Sigma) {
        this.Sigma = Sigma;
        this.Stateset = new HashSet<State>();
        this.Trans = new HashSet<Transition>();
        this.init = null;
        this.Final = new HashSet<State>();
    }
    
    /**
     * Constructor with given alphabet and set of states
     */
    public Autom(HashSet<Letter> Sigma, HashSet<State> Stateset) {
        this.Sigma = Sigma;
        this.Stateset = Stateset;
        this.Trans = new HashSet<Transition>();
        this.init = null;
        this.Final = new HashSet<State>();
    }
    
    /**
     * Getter: Alphabet
     */
    public HashSet<Letter> getAlphabet() {
        return Sigma;
    }

    /**
     * Getter: Set of states
     */
    public HashSet<State> getStates() {
        return Stateset;
    }

    /**
     * Getter: Set of transitions
     */
    public HashSet<Transition> getTransitions(){
        return Trans;
    }
    
    /**
     * Getter: Set of final states
     */
    public HashSet<State> getFinal() {
    	return Final;
    }
    
    /**
     * Getter: Initial state
     * NOTE: Our automata need to have a unique initial state
     * The method returns null if there is no initial state
     * Use hasInit() before invoking this method to check if the automaton has a unique initial state
     */
    public State getInit() {
    	return init;
    }

    /**
     * Method adds a letter to the underlying alphabet
     */
    public void addLetter(Letter a) {
        Sigma.add(a);
    }
    
    /**
     * Method adds a given state to the set of states
     */
    public void addState(State q) {
        Stateset.add(q);
    }
    
    /**
     * Method adds a given transition to the automaton.
     * If source state or target state are not in the current set of states, they get added.
     * If the label is not in the current alphabet, it gets added.
     */
    public void addTransition(Transition T) {
    	Stateset.add(T.getSource());
    	Stateset.add(T.getTarget());
    	Sigma.add(T.getLabel());
    	Trans.add(T);
    }
    
    /**
     * Method that adds a given state as final state.
     * If the state is not in the current stateset of the automaton, it gets added.
     */
    public void addFinal(State q) {
    	Stateset.add(q);
    	Final.add(q);
    }
    
    /**
     * Method that adds the given states to the final states.
     * If one of the states is not in the set of states, it does not get added.
     */
    public void addFinal(HashSet<State> Final) {
    	for (State q : Final) {
    		addFinal(q);
    	}
    }
    
    /**
     * Clears the set of final states to be empty
     */
    public void clearFinal()  {
    	Final.clear();
    }
    
    /**
     * Method that sets the given state to be initial.
     * This is only possible if the state is in the set of states.
     */
    public void setInit(State q) {
    	if (Stateset.contains(q)) init = q;
    }
    
    /**
     * Returns whether the given state is a final state of this automaton
     */
    public boolean isFinal(State q) {
    	return Final.contains(q);
    }
    
    /**
     * Returns whether one of the states in the given set is final in this automaton
     */
    public boolean isFinal(HashSet<State> Q) {
    	for (State q : Q) {
    		if (isFinal(q)) return true;
    	}
    	
    	return false;
    }
    
    /**
     * Returns whether the given state is the initial state of this automaton
     */
    public boolean isInit(State q) {
    	return (init.equals(q));
    }
 
    /**
     * Method that removes the given transition
     */
    public void removeTransition(Transition T) {
        Trans.remove(T);
    }
    
    /**
     * Method that removes all transitions
     */
    private void clearTransition() {
    	Trans.clear();
    }
    
    /**
     * Method that removes a given state q.
     * Note that then also all transitions involving q, as source or target, vanish.
     */
    public void removeState(State q){
    	Stateset.remove(q);
    	Trans.removeIf(t -> (t.getSource().equals(q) || t.getTarget().equals(q)));
    }
    
    /**
     * Method that removes all states from the automaton but the given ones.
     * Note that then also all transitions involving a state outside of Q, as source or target, vanish.
     */
    public void retainStates(HashSet<State> Q){
    	Stateset.retainAll(Q);
    	Trans.removeIf(t -> (!Q.contains(t.getSource()) || !Q.contains(t.getTarget())));
    }
    
    /**
     * Method that removes all states including final ones and the initial
     */
    private void clearStates() {
    	Stateset.clear();
    	init = null;
    	Final.clear();
    }
    
    /**
     * Method that checks whether the automaton contains one of the given states.
     */
    public boolean containsState(HashSet<State> states) {
    	HashSet<State> temp = new HashSet<State>(Stateset);
    	temp.retainAll(states);
    	return !temp.isEmpty();
    }
    
    /**
     * Checks if the automaton has a final state
     */
    public boolean hasFinal() {
    	return !Final.isEmpty();
    }
    
    /**
     * Checks if the automaton has a unique initial state
     */
    public boolean hasInit() {
    	return (init != null);
    }
    
    /**
     * Returns the set of isolated states (states not incident to a transition)
     * Returns an empty set if no such state exists
     * Does never return null
     */
    public HashSet<State> getIsolated() {
    	
    	HashSet<State> conn = new HashSet<State>();
    	
    	// Collect all states that are incident to a transition 
    	for (Transition t : Trans) {
    		conn.add(t.getSource());
    		conn.add(t.getTarget());
    	}
    	
    	// Subtract the states that are incident to a transition from the set of all states
    	HashSet<State> isol = new HashSet<State>(Stateset);
    	isol.removeAll(conn);
    	return isol;
    }
    
    /**
     * Method that renews the names of the states of the automaton.
     * @param postfix is appended to the state names.
     */
    public void renewStateNames(String postfix) {
    	
    	// Mapping old state names to new ones
    	HashMap<State,State> translation = new HashMap<State,State>();
    	HashSet<State> newState = new HashSet<State>();
    	HashSet<State> newFinal = new HashSet<State>();
    	State newInit = null;
    	for (State q : Stateset) {
    		State new_q = new State(q.getName() + postfix);
    		newState.add(new_q);
    		if (isFinal(q)) newFinal.add(new_q);
    		if (isInit(q)) newInit = new_q;
    		
    		translation.put(q, new_q);
    	}
    	
    	// Lift the transitions to the new state names
    	HashSet<Transition> newTrans = new HashSet<Transition>();
    	for (Transition t : Trans) {
    		Transition new_t = new Transition(
    				translation.get(t.getSource()), 
    				translation.get(t.getTarget()), 
    				t.getLabel());
    		newTrans.add(new_t);
    	}
    	
    	// Replace the transitions and states
    	this.clearStates();
    	this.clearTransition();
    	this.Stateset = newState;
    	this.Trans = newTrans;
    	this.Final = newFinal;
    	this.init = newInit;
    }
    
    /**
     * DEBUG method for simple print on the console
     */
    public void print() {
        String out = String.format("%-5s","");
        String out_row = "";
        String out_initFin = "";
        int N = 5*Sigma.size() + 1;

        for (State q : Stateset) {
            out_initFin = "";
            if (q.equals(init)) out_initFin = "(I)";
            if (Final.contains(q)) out_initFin = out_initFin + "(F)";
            out = out + String.format("%-" + N + "s", q.getName() + out_initFin);
        }

        out = out + "\n";

        for (State q : Stateset) {
            out = out + String.format("%-5s", q.getName());
            
            for (State p : Stateset) {
                for (Transition t : Trans) {
                    if (t.getSource().equals(q) && t.getTarget().equals(p)) out_row = out_row  + t.getLabel().getSymb() + " ";
                }

                out_row = String.format("%-" + N + "s", out_row);
                out = out + out_row;
                out_row = "";
            }

            out = out + "\n";
        }
        
        System.out.println(out);
    }
    
    /**
     * Method that creates a copy of the automaton.
     * The new automaton references to a copy of the alphabet, stateset, transition set, and set of final states.
     * NOTE: States, Letters, and Transitions do not need to be newly constructed - they are immutable.
     */
    public Autom copy() {
    	
    	HashSet<Letter> Sigma_copy = new HashSet<Letter>();
    	Sigma_copy.addAll(Sigma);
    	
    	HashSet<State> Stateset_copy = new HashSet<State>();
    	Stateset_copy.addAll(Stateset);
    	
    	Autom copy = new Autom(Sigma_copy,Stateset_copy);
    	for (Transition t : Trans) {
    		copy.addTransition(t);
    	}
    	
    	for (State q : Final) {
    		copy.addFinal(q);
    	}
    	
    	copy.setInit(init);
    	return copy;
    }
}