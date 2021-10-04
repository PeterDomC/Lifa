package automata;

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
     * Method adds a given transition to the automaton if
     * the source, the target state, and the label of the transition are already stored in the automaton
     */
    public void addTransition(Transition T) {
        if (!Stateset.contains(T.getSource())|| !Stateset.contains(T.getTarget()) || !Sigma.contains(T.getLabel())) {
        	System.out.println("Source or target state / label of transition not in set of states / alphabet of the automaton.");
        	System.out.println("You may consider using the method 'forceAddTransition' instead.");
        } else {
        	Trans.add(T);
        }
    }
    
    /**
     * Method adds a given transition to the automaton.
     * If source state or target state are not in the current set of states, they get added.
     * If the label is not in the current alphabet, it gets added.
     * Only use this method if you know what you are doing!
     */
    public void forceAddTransition(Transition T) {
    	Stateset.add(T.getSource());
    	Stateset.add(T.getTarget());
    	Sigma.add(T.getLabel());
    	Trans.add(T);
    }
    
    /**
     * Method adds a given state to the final states.
     * If it is not in the current set of states, it does not get added.
     */
    public void addFinal(State q) {
    	if (!Stateset.contains(q)) {
    		System.out.println("State is not in the current set of states.");
    		System.out.println("You may consider using the method 'forceAdFinal' instead.");
    	} else {
    		Final.add(q);
    	}
    }
    
    /**
     * Method that adds the given states to the final states.
     * If one of the states is not in the set of states, it does not get added.
     */
    public void addFinal(HashSet<State> Final) {
    	for (State q : Final) {
    		this.addFinal(q);
    	}
    }
    
    /**
     * Method adds a given state as final state.
     * If the state is not in the current stateset of the automaton, if gets added.
     * Only use this method if you know what you are doing!
     */
    public void forceAddFinal(State q) {
    	Stateset.add(q);
    	Final.add(q);
    }
    
    /**
     * Clears the set of final states to be empty
     */
    public void clearFinal()  {
    	this.Final.clear();
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
     * Method that removes a given state q.
     * Note that then also all transitions involving q, as source or target, vanish.
     */
    public void removeState(State q){
    	Stateset.remove(q);
    	
    	for (Transition T : Trans) {
    		if (T.getSource().equals(q) || T.getTarget().equals(q)) {
    			Trans.remove(T);
    		}
    	}
    }
    
    /**
     * Method that removes a given letter a.
     * Note that then also all transitions involving a as label vanish.
     */
    public void removeLetter(Letter a){
    	Sigma.remove(a);
    	
    	for (Transition T : Trans) {
    		if (T.getLabel().equals(a)) {
    			Trans.remove(T);
    		}
    	}
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
     * Method that cuts away the isolated states of the automaton
     */
    public void cutIsolated() {
    	
    }
    
    /**
     * TODO: Method that renews the names of the states of the automaton to something simple
     */
    public void renewStateNames() {
    	
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