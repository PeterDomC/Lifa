package automataAlgorithms;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;

import automata.Autom;
import automata.Letter;
import automata.State;
import automata.Transition;

/*
 * Class for the language of a finite automaton.
 * It features basic algorithms for checking whether a langauge is empty or
 * whether the languages of two automata are equivalent.
 */
public class Language {
	
	/**
	 * Method takes an automaton and checks whether its language is empty.
	 * @param A is the given automaton.
	 * @return true, if L(A) is empty - false, otherwise.
	 * 
	 * NOTE: The method is a breadth-first search for a final state.
	 */
	public static boolean isEmpty(Autom A) {
		
		// If A does not have an initial or final state, L(A) is empty
		if (!A.hasInit() || !A.hasFinal()) return true;
		
		// Store a set of already reached states and a work queue
		HashSet<State> reachable = new HashSet<State>();
		Queue<State> workQ = new ArrayDeque<State>();
		
		// Prepare the postmap of A
		HashSet<Letter> Sigma = A.getAlphabet();
		Postmap postA = new Postmap(A);
		
		// Begin the breadth first search with the initial state
		workQ.add(A.getInit());
		
		while(!workQ.isEmpty()) {
			
			// Take the current state from the queue and add its post states 
			// to the queue if they are not already marked as reachable
			State cur = workQ.poll();
			reachable.add(cur);
			
			for (Letter a : Sigma) {
				HashSet<State> post = postA.get(cur,a);
				if (post != null) {
					
					for (State q : post) {
						if (A.isFinal(q)) return false;
						// Final state found - language not empty
						
						if (!reachable.contains(q)) workQ.add(q);
						// State not final - continue exploration from here
					}
				}
			}
		}
		
		return true;
	}
	
    /**
     * Method for reducing the given automaton to one that accepts the same language
     * and that has only reachable states/states that can reach a final state.
     * @param A is the given automaton.
     * @return Red, the reduced automaton.
     * 
     * NOTE: Does not return a reference to A, does not return null.
     * NOTE: This is a breadth-first search into two directions:
     *     - it determines the states that can be reached from the initial state, and 
     *     - it determines the states that can reach a final state,
     *     then it intersects both.
     */
    public static Autom reduce(Autom A) {
    	
    	Autom Red = new Autom();
    	
    	// If A does not have an initial state or no final states, the reduction is empty
    	if (!A.hasInit() || !A.hasFinal()) return Red;
    	
    	// Begin with the breadth-first search for reachable states
    	// Store a set of already considered (reachable) states and a work queue
		HashSet<State> consSet = new HashSet<State>();
		Queue<State> workQ = new ArrayDeque<State>();
    	
		// Prepare the postmap of A
		HashSet<Letter> Sigma = A.getAlphabet();
		Postmap postA = new Postmap(A);
		
		// Begin the breadth first search with the initial state
		State Ainit = A.getInit();
		Red.addState(Ainit);
		Red.setInit(Ainit);
		if (A.isFinal(Ainit)) Red.addFinal(Ainit);
		workQ.add(Ainit);
		
		while (!workQ.isEmpty()) {
			
			// Take the current state from the queue and add its post states 
			// to the queue if they have not been considered yet
			State cur = workQ.poll();
			consSet.add(cur);
			
			for (Letter a : Sigma) {
				HashSet<State> post = postA.get(cur,a);
				if (post != null) {
					
					for (State q : post) {
						Transition t = new Transition(cur,q,a);
						Red.addTransition(t);
						if (A.isFinal(q)) Red.addFinal(q);
						
						// Continue exploration from q if not already considered
						if (!consSet.contains(q)) workQ.add(q);
					}
				}
			}
		}
		
		// Do we need a data structure premap?
		// Apply it to Red since this is already reduced
    	
    	return Red;
    }
}
