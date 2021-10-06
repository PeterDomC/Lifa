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
	 * Method takes an automaton and checks whether its language is universal.
	 * @param A is the given automaton.
	 * @return true, if L(A) is the whole set of words - false, otherwise.
	 * 
	 * NOTE: The method is a breadth-first search for a non-final state in the determinization of A.
	 * NOTE: L(A) is not universal if and only if the determinization of A can reach a non-final state.
	 * NOTE: First constructing the determinization of A and then calling isEmpty() is slower
	 * since the whole determinization needs to be constructed.
	 */
	public static boolean isUniversal(Autom A) {
		
		// If A does not have a final state or an initial state, 
		// its language is empty and hence, not universal
		if (!A.hasInit() || !A.hasFinal()) return false;
		
		// Now we follow the breadth-first powerset construction but without memorizing the determinization
		// Work queue of powerstates, set of already considered powerstates, alphabet of A, postmap of A
		Queue<Powerstate> workQ = new ArrayDeque<Powerstate>();
		HashSet<Powerstate> consSet = new HashSet<Powerstate>();
		HashSet<Letter> Sigma = A.getAlphabet();
		Postmap postA = new Postmap(A);
		
		// Initial state
		HashSet<State> init_set = new HashSet<State>();
		init_set.add(A.getInit());
		Powerstate init = new Powerstate(init_set);
		
		// Add the state to Power and to the queue
		// If it is not final, return false
		if (!A.isFinal(init_set)) return false;
		workQ.add(init);
		
		while(!workQ.isEmpty()) {
			
			// Take the current powerstate and add it to the set of considered powerstates
			Powerstate cur = workQ.poll();
			HashSet<State> cur_content = cur.getSetContent();
			consSet.add(cur);
			
			for (Letter a : Sigma) {
				HashSet<State> det_post_content = new HashSet<State>();
				
				for (State q : cur_content) {
					// Get the post of q under a and add it to det_post_content
					HashSet<State> post = postA.get(q,a);
					if (post != null) det_post_content.addAll(post);
				}
				Powerstate det_post = new Powerstate(det_post_content);
				
				// If the constructed state does not contain a final state of A, 
				// the determinization can reach a non-final state - return false
				if (!A.isFinal(det_post_content)) return false;
				
				// If the constructed state has not already been considered,
				// add it to the worklist to continue the exploration from there
				if (!consSet.contains(det_post)) workQ.add(det_post);
			}
		}
		
		// The determinization can only reach final states - the language is universal
		return true;
	}
}
