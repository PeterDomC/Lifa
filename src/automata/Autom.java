package automata;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Automaton class.
 * Automata consist of an alphabet Sigma, a set of states Stateset, and a set of Transitions Trans.
 */
public class Autom{
	
    private HashSet<Letter> Sigma;
    private HashSet<State> Stateset;
    private HashSet<Transition> Trans;
    
    /*
     * Constructor - generates an empty NFA
     */
    public Autom() {
    	this.Sigma = new HashSet<Letter>();
    	this.Stateset = new HashSet<State>();
        this.Trans = new HashSet<Transition>();
    }
    
    /*
     * Constructor with given alphabet
     */
    public Autom(HashSet<Letter> Sigma){
        this.Sigma = Sigma;
        this.Stateset = new HashSet<State>();
        this.Trans = new HashSet<Transition>();
    }
    
    /*
     * Constructor with given alphabet and set of states
     */
    public Autom(HashSet<Letter> Sigma, HashSet<State> Stateset){
        this.Sigma = Sigma;
        this.Stateset = Stateset;
        this.Trans = new HashSet<Transition>();
    }
    
    /*
     * Getter: Alphabet
     */
    public HashSet<Letter> getAlphabet(){
        return Sigma;
    }

    /*
     * Getter: Set of states
     */
    public HashSet<State> getStates(){
        return Stateset;
    }

    /*
     * Getter: Set of Transitions
     */
    public HashSet<Transition> getTransitions(){
        return Trans;
    }

    /*
     * Method adds a letter to the underlying alphabet
     */
    public void addLetter(Letter a){
        Sigma.add(a);
    }
    
    /*
     * Method adds a given state to the set of states
     */
    public void addState(State q){
        Stateset.add(q);
    }
    
    /*
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
    
    /*
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
    
    /*
     * Method that removes the given transition
     */
    public void removeTransition(Transition T){
        Trans.remove(T);
    }
    
    /*
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
    
    /*
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
    
    

    public void setFinal(State P){
        // Set the given state to be final
        for (State Q : Stateset) {
            if (Q.equals(P)) Q.setFinal();
        }
    }

    public void setInit(State P){
        // Set the given state to be initial
        for (State Q : Stateset) {
            if (Q.equals(P)) Q.setInit();
        }
    }
    
    public void print(){
        String out = String.format("%-5s", "");
        String out_row = "";
        String out_initFin = "";
        int N = 5*Sigma.size() + 1;

        for (State Q : Stateset) {
            out_initFin = "";
            if (Q.isInit()) out_initFin = "(I)";
            if (Q.isFinal()) out_initFin = out_initFin + "(F)";
            out = out + String.format("%-" + N + "s", Q.getName() + out_initFin);
        }

        out = out + "\n";

        for (State Q : Stateset) {

            out = out + String.format("%-5s", Q.getName());

            for (State P : Stateset) {

                for (Transition T : Trans) {
                    if (T.getSource().equals(Q) && T.getTarget().equals(P)) out_row = out_row  + T.getLabel().getSymb() + " ";
                }

                out_row = String.format("%-" + N + "s", out_row);
                out = out + out_row;
                out_row = "";
            }

            out = out + "\n";
        }

        System.out.println(out);
    }

    public Autom copy(){
        // Creates a copy of the automaton
        HashSet<Letter> Gamma = new HashSet<Letter>(Sigma);
        HashSet<State> Q = new HashSet<State>(Stateset);
        Autom B = new Autom(Gamma,Q);
        for (Transition T : Trans) {
            B.addTransition(T);
        }

        return B;
    }
    
    /* Maybe change input to HashMap(State,Integer) to avoid confusion
    public void permuteStates(ArrayList<Integer> Perm){
        // Permutes the states by the given encoding
        int i = 0;
        for (State Q : Stateset) {
            Q.setIndex(Perm.get(i));
            i++;
        }
    } */
}