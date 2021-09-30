package automata;

import java.util.HashSet;

/*
 * Automaton class.
 * Automata consist of an alphabet Sigma, a set of states Stateset, and a set of Transitions Trans.
 */
public class Autom{
	
    private HashSet<Letter> Sigma;
    private HashSet<State> Stateset;
    private HashSet<Transition> Trans;
    
    /**
     * Constructor - generates an empty NFA
     */
    public Autom() {
    	this.Sigma = new HashSet<Letter>();
    	this.Stateset = new HashSet<State>();
        this.Trans = new HashSet<Transition>();
    }
    
    /**
     * Constructor with given alphabet
     */
    public Autom(HashSet<Letter> Sigma){
        this.Sigma = Sigma;
        this.Stateset = new HashSet<State>();
        this.Trans = new HashSet<Transition>();
    }
    
    /**
     * Constructor with given alphabet and set of states
     */
    public Autom(HashSet<Letter> Sigma, HashSet<State> Stateset){
        this.Sigma = Sigma;
        this.Stateset = Stateset;
        this.Trans = new HashSet<Transition>();
    }
    
    /**
     * Getter: Alphabet
     */
    public HashSet<Letter> getAlphabet(){
        return Sigma;
    }

    /**
     * Getter: Set of states
     */
    public HashSet<State> getStates(){
        return Stateset;
    }

    /**
     * Getter: Set of Transitions
     */
    public HashSet<Transition> getTransitions(){
        return Trans;
    }

    /**
     * Method adds a letter to the underlying alphabet
     */
    public void addLetter(Letter a){
        Sigma.add(a);
    }
    
    /**
     * Method adds a given state to the set of states
     */
    public void addState(State q){
        Stateset.add(q);
    }
    
    /**
     * Method adds a given transition to the automaton if
     * the source, the target state, and the label of the transition are already stored in the automaton
     */
    public void addTransition(Transition T){
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
     * Method that removes the given transition
     */
    public void removeTransition(Transition T){
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
     * Getter for the set of final states
     * Note that it returns an empty set if there is no final state, but never null
     */
    public HashSet<State> getFinal() {
    	HashSet<State> finals = new HashSet<State>();
    	for (State q : Stateset) {
    		if (q.isFinal()) finals.add(q);
    	}
    	
    	return finals;
    }
    
    /**
     * Checks if the automaton has a final state
     */
    public boolean hasFinal() {
    	for (State q : Stateset) {
    		if (q.isFinal()) return true;
    	}
    	
    	return false;
    }
    
    /**
     * Getter for the initial state
     * NOTE: We typically assume that automata have a unique initial state
     * NOTE: It returns null if there is no initial state 
     * NOTE: It returns some initial state if the automaton has more than one initial state
     * Use hasInit() before invoking this method to check if the automaton has exactly one initial state
     */
    public State getInit() {
    	State init = null;
    	
    	for (State q : Stateset) {
    		if (q.isInit()) {
    			init = q;
    			break;
    		}
    	}
    	
    	return init;
    }
    
    /**
     * Checks if the automaton has a unique initial state
     */
    public boolean hasInit() {
    	boolean singleInit = false;
    	
    	for (State q : Stateset) {
    		if (q.isInit()) {
    			if (!singleInit) {
    				singleInit = true;
    			} else {
    				singleInit = false;
    			}
    		}
    	}
    	
    	return singleInit;
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
     * TODO: Method that cuts away the isolated states of the automaton
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
    public void print(){
        String out = String.format("%-5s","");
        String out_row = "";
        String out_initFin = "";
        int N = 5*Sigma.size() + 1;

        for (State q : Stateset) {
            out_initFin = "";
            if (q.isInit()) out_initFin = "(I)";
            if (q.isFinal()) out_initFin = out_initFin + "(F)";
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
}