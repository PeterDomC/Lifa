package automataAlgorithms;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;

import automata.Autom;
import automata.Letter;
import automata.State;

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
	
	//TODO: reduce
}
