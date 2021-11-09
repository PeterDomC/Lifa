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
	
	/**
	 * Method takes an automaton and checks whether its language is universal.
	 * @param A is the given automaton.
	 * @return true, if L(A) is the whole set of words - false, otherwise.
	 * 
	 * NOTE: The method is a breadth-first search for a non-final state in the determinization of A
	 * without explicitly constructing the determinization.
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
		
		// Add the state to the queue
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
	
	/**
	 * Method takes two automata and checks whether the language of the first is contained in the language 
	 * of the second one.
	 * @param A,B are the given automaton.
	 * @return true, if L(A) is contained in L(B) - false, otherwise.
	 * 
	 * NOTE: The method is a breadth-first search on A x det(B) without explicitly constructing the automaton.
	 * NOTE: L(A) is contained in L(B) if and only if the language of A x compl(B) is empty.
	 * This language is empty if and only if A x det(B) cannot reach a state of the form (q,Q),
	 * where q is a final state of A and Q is a non-final state of det(B).
	 * We search for such a state in a breadth-first manner.
	 * NOTE: Constructing the complete automaton A x compl(B) is costly - we try to avoid this step!
	 */
	public static boolean isContained(Autom A, Autom B) {
		
		boolean A_init = A.hasInit();
		boolean A_final = A.hasFinal();
		boolean B_init = B.hasInit();
		boolean B_final = B.hasFinal();
		
		// If A does not have an initial or final state, L(A) is empty 
		// Then, the inclusion is true independent of B
		if (!A_init || !A_final) return true;
		
		// If B does not have an initial or final state, L(B) is empty
		// Then the inclusion is only true if A is empty as well
		if (!B_init || !B_final) return Language.isEmpty(A);
		
		// If A and B share a state (name), then we give A and B different state names
		if (A.containsState(B.getStates())) {
			A.renewStateNames("A");
			B.renewStateNames("B");
		}
		
		// Begin the breadth-first search without memorizing A x det(B)
		// Work queue of pair = state x powerstate, set of already considered pairs, postmaps of A and B
		Queue<AbsPair<State,Powerstate>> workQ = new ArrayDeque<AbsPair<State,Powerstate>>();
		HashSet<AbsPair<State,Powerstate>> consSet = new HashSet<AbsPair<State,Powerstate>>();
		Postmap postA = new Postmap(A);
		Postmap postB = new Postmap(B);
		
		// The underlying alphabet of the construction is the union of alphabets
		HashSet<Letter> Sigma = new HashSet<Letter>(A.getAlphabet());
		Sigma.addAll(B.getAlphabet());
		
		// Initial state of A x det(B)
		HashSet<State> init_set_B = new HashSet<State>();
		init_set_B.add(B.getInit());
		Powerstate init_det_B = new Powerstate(init_set_B);
		AbsPair<State,Powerstate> init = new AbsPair<State,Powerstate>(A.getInit(),init_det_B);
		
		// Add the pair to the queue and check if it fulfils the required form
		if (A.isFinal(A.getInit()) && !B.isFinal(init_set_B)) return false;
		workQ.add(init);
		
		while(!workQ.isEmpty()) {
			
			// Take the current pair and add it to the set of considered pairs
			AbsPair<State,Powerstate> cur = workQ.poll();
			consSet.add(cur);
			
			// Content of the current pair
			State left = cur.getLeft();
			Powerstate right = cur.getRight();
			
			for (Letter a : Sigma) {
				// Post set of A
				HashSet<State> leftPost = postA.get(left,a);
				
				HashSet<State> rightPost_content = new HashSet<State>();
				// Post powerstate of det(B) - definitely exists!
				for (State q : right.getSetContent()) {
					HashSet<State> post = postB.get(q,a);
					if (post != null) rightPost_content.addAll(post);
				}
				Powerstate rightPost = new Powerstate(rightPost_content);
				
				if (leftPost != null) {
					// Continue if A has a post state (det(B) always has one!)
					
					for (State q : leftPost) {
						// Newly discovered pair
						AbsPair<State,Powerstate> newPair = new AbsPair<State,Powerstate>(q,rightPost);
						
						// If the pair satisfies the form - return false
						if (A.isFinal(q) && !B.isFinal(rightPost_content)) return false;
						
						// If the constructed pair has not been considered, add it
						if (!consSet.contains(newPair)) workQ.add(newPair);
					}
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Method takes two automata and checks whether their languages are equal.
	 * @param A,B are the given automaton.
	 * @return true, if L(A) = L(B) - false, otherwise.
	 * 
	 * NOTE: The method calls isContained() two times to check whether L(A) is contained in L(B) and vice versa.
	 */
	public static boolean isEquivalent(Autom A, Autom B) {
		return (isContained(A,B) && isContained(B,A));
	}
}
