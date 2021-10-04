package automataAlgorithms;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;

import automata.Autom;
import automata.Letter;
import automata.State;
import automata.Transition;

/*
 * Class which holds methods around basic operations on automata
 * such as intersection, union, determinization, complement, concatenation, and Kleene star.
 */
public class Operations {
	
	/**
	 * Takes two automata and computes their cross product.
	 * The cross product generates the intersection of the languages .
	 * @param A, B are the given automata.
	 * They need to have distinct sets of states.
	 * @return Cross, the cross product of A and B.
	 * It generates the language L(A) intersected L(B).
	 * 
	 * NOTE: Only the reachable states of the cross product are computed.
	 * NOTE: If A or B (or both) do not have exactly one initial state,
	 * the method returns the empty automaton.
	 * NOTE: For better performance, we recommend to cut isolated states of A and B
	 * before computing the intersection.
	 * NOTE: Does not return a reference to A or B, does not return null.
	 */
	public static Autom intersect(Autom A, Autom B) {
		
		Autom Cross = new Autom();
		
		// A or B (or both) do not have an initial state - intersection is empty
		if (!A.hasInit() || !B.hasInit()) return Cross;
		
		// Prepare the postmaps of A and B
		Postmap postA = new Postmap(A);
		Postmap postB = new Postmap(B);
		
		// The underlying alphabet of Cross - the intersection of both alphabets
		HashSet<Letter> Sigma = new HashSet<Letter>(A.getAlphabet());
		Sigma.retainAll(B.getAlphabet());
		
		// A queue to store the statepairs that need to be investigated
		// A set to store the statepairs that have already been investigated
		Queue<Statepair> workQ = new ArrayDeque<Statepair>();
		HashSet<Statepair> consSet = new HashSet<Statepair>();
		
		// First add the pair of initial states to Cross and to the queue
		Statepair init = new Statepair(A.getInit(),B.getInit());
		State init_state = init.toState();
		
		Cross.addState(init_state);
		Cross.setInit(init_state);
		if (A.isFinal(A.getInit()) && B.isFinal(B.getInit())) Cross.addFinal(init_state);
		workQ.add(init);
		
		Statepair cur;
		State left,right;
		HashSet<State> leftPost,rightPost;
		while(!workQ.isEmpty()) {
			
			// Take current statepair off the queue and add it to the set of considered pairs
			cur = workQ.poll();
			consSet.add(cur);
			
			// For each letter a, go over all states of Post_a(left) and Post_a(right),
			// add the correpsonding transition to Cross,
			// and add the resulting statepairs to the queue
			left = cur.getLeft();
			right = cur.getRight();
			
			for (Letter a : Sigma) {
				// Determine the post sets - can be null
				leftPost = postA.get(left,a);
				rightPost = postB.get(right,a);
				
				if (leftPost != null && rightPost != null) {
					// Continue only if both sets are not null (means non-empty)
					
					for (State q : leftPost) {
						for (State p : rightPost) {
							Statepair prod = new Statepair(q,p);
							
							// Construct the transition to prod and add to cross product
							// Note that equivalent transitions are ignored automatically
							// Add transition (and label + states)
							State prod_state = prod.toState();
							Transition prod_trans = new Transition(cur.toState(),prod_state,a);
							Cross.forceAddTransition(prod_trans);
							if (A.isFinal(q) && B.isFinal(p)) Cross.addFinal(prod_state);
							
							// Add the pair (q,p) to the worklist to go on with the exploration from there
							// But only if it has not already been considered!
							if (!consSet.contains(prod)) { 
								workQ.add(prod);
							}
						}
					}
				}
			}
		}
		
		return Cross;
	}
	
	/**
	 * Takes two automata and returns an automaton that accepts the union of their languages.
	 * @param A, B are the given automata.
	 * They need to have distinct sets of states.
	 * @return Union, the automaton that accepts L(A) union L(B).
	 * 
	 * NOTE: The union is constructed by adding a new initial state.
	 * and without epsilon transitions.
	 * NOTE: If A and B do not have exactly one initial state, the method returns the empty automaton.
	 * NOTE: Does not return a reference to A or B, does not return null.
	 */
	public static Autom union(Autom A, Autom B) {
		
		boolean A_init = A.hasInit();
		boolean B_init = B.hasInit();
		
		// If A and B do not have an initial state - union is empty
		if (!A_init && !B_init) return new Autom();
		// If A does not have an initial state but B has - union is L(B)
		if (!A_init && B_init) return B.copy();
		// If A has an initial state but B does not - union is L(A)
		if (A_init && !B_init) return A.copy();
		
		Autom Union = new Autom();
		
		// If A and B have a unique initial state
		State A_init_state = A.getInit();
		State B_init_state = B.getInit();
		State new_init_state = new Statepair(A_init_state,B_init_state).toState();
		Union.addState(new_init_state);
		Union.setInit(new_init_state);
		if (A.isFinal(A_init_state) || B.isFinal(B_init_state)) Union.addFinal(new_init_state);
		
		// Add the transitions of A and the additional transitions from the new initial state
		// to the post initial states of A
		HashSet<Transition> Trans = A.getTransitions();
		for (Transition t : Trans) {
			Union.forceAddTransition(t);
			
			if (t.getSource().equals(A_init_state)) {
				Transition t_add = new Transition(new_init_state, t.getTarget(), t.getLabel());
				Union.addTransition(t_add);
			}
		}
		
		// Add the transitions of B and the additional transitions from the new initial state
		// to the post initial states of B
		Trans = B.getTransitions();
		for (Transition t : Trans) {
			Union.forceAddTransition(t);
			if (t.getSource().equals(B_init_state)) {
				Transition t_add = new Transition(new_init_state, t.getTarget(), t.getLabel());
				Union.addTransition(t_add);
			}
		}
		
		// Add final states to the union
		Union.addFinal(A.getFinal());
		Union.addFinal(B.getFinal());
		
		return Union;
	}
	
	/**
	 * Takes two automata and returns the automata that accepts the concatenation of their languages.
	 * @param A, B are the given automata.
	 * They need to have distinct sets of states.
	 * @return Concat, the automata accepting L(A).L(B).
	 * 
	 * NOTE: The concatenation is constructed without epsilon transitions.
	 * NOTE: If either A or B does not have an initial state, the concatenation is empty.
	 * NOTE: Does not return a reference on A or B, does not return null.
	 */
	public static Autom concat(Autom A, Autom B) {
		
		Autom Concat = new Autom();
		
		// A or B (or both) do not have an initial state - concatenation is empty by definition
		if (!A.hasInit() || !B.hasInit()) return Concat;
		
		// Copy the transitions of A
		HashSet<Transition> Trans = A.getTransitions();
		for (Transition t : Trans) {
			Concat.forceAddTransition(t);
		}
		
		// Take the initial state of A as new initial state
		Concat.setInit(A.getInit());
		State B_init_state = B.getInit();
		
		// Copy the transitions of B
		// For each post initial state of B, add a transition from all final states of A to that state
		Trans = B.getTransitions();
		HashSet<State> Final = A.getFinal();
		for (Transition t : Trans) {
			Concat.forceAddTransition(t);
			
			if (t.getSource().equals(B_init_state)) {
				for (State q : Final) {
					Transition t_add = new Transition(q,t.getTarget(),t.getLabel());
					Concat.addTransition(t_add);
				}
			}
		}
		
		// Final states of Concat are the final states of B
		Concat.addFinal(B.getFinal());
		return Concat;
	}
	
	/**
	 * Takes an automaton and returns the automaton that accepts the Kleene star of the original language.
	 * @param A is the given automaton.
	 * @return Kleene, the automata accepting L(A)^*.
	 * 
	 * NOTE: Kleene is constructed without epsilon transitions.
	 * NOTE: If A does not have an initial state, the Kleene star language is empty.
	 * NOTE: Does not return a reference on A, does not return null.
	 */
	public static Autom kleene(Autom A) {
		
		Autom Kleene = new Autom();
		
		// If A does not have an initial state, the Kleene star language is empty
		if (!A.hasInit()) return Kleene;
		
		// Add a new initial state that is also final
		State A_init_state = A.getInit();
		State new_init = new State(A_init_state.getName() + "!");
		Kleene.addState(new_init);
		Kleene.addFinal(new_init);
		Kleene.setInit(new_init);
		
		// Copy the transitions of A
		// For each post initial state of A, add a transition from all final states to that state
		// and from the new initial state as well
		HashSet<Transition> Trans = A.getTransitions();
		HashSet<State> Final = A.getFinal();
		for (Transition t : Trans) {
			Kleene.forceAddTransition(t);
			
			if (t.getSource().equals(A_init_state)) {
				for (State q : Final) {
					Transition t_add_interior = new Transition(q,t.getTarget(),t.getLabel());
					Kleene.addTransition(t_add_interior);
					
					Transition t_add_ext = new Transition(new_init,t.getTarget(),t.getLabel());
					Kleene.addTransition(t_add_ext);
				}
			}
		}
		
		// Add the final states of A as final to Kleene
		Kleene.addFinal(A.getFinal());
		return Kleene;
	}
	
	/**
	 * Takes an automaton and determinizes it via the powerset construction.
	 * @param A is the given automaton.
	 * @return Power, the determinization of A.
	 * 
	 * NOTE: Only the reachable states of the powerset construction are computed.
	 * NOTE: If A does not have an initial state, the determinization is the empty automaton.
	 * NOTE: Determinizations may grow large! Consider your input carefully.
	 * NOTE: For better performance, we recommend to cut the isolated states of A.
	 * NOTE: Does not return a reference to A, does not return null.
	 */
	public static Autom determinize(Autom A) {
		
		Autom Power = new Autom();
		
		// If A does not have an initial state, the determinization is empty
		if (!A.hasInit()) return Power;
		
		// The powerset construction consists of sets of states - so called powerstates
		// A queue to store the powerstates that need to be investigated
		// A set to store the powerstates that have already been investigated
		Queue<Powerstate> workQ = new ArrayDeque<Powerstate>();
		HashSet<Powerstate> consSet = new HashSet<Powerstate>();
		
		// The underlying alphabet of Power - the alphabet of A
		HashSet<Letter> Sigma = A.getAlphabet();
		
		// Prepare the postmap of A
		Postmap postA = new Postmap(A);
		
		// Construct the initial state - the powerstate consisting only of the initial state of A
		HashSet<State> init_set = new HashSet<State>();
		init_set.add(A.getInit());
		Powerstate init = new Powerstate(init_set);
		State init_state =  init.toState();
		
		// Add the state to Power and to the queue - it can be final
		Power.addState(init_state);
		Power.setInit(init_state);
		if (A.isFinal(init_set)) Power.addFinal(init_state);
		workQ.add(init);
		
		Powerstate cur;
		HashSet<State> cur_content;
		HashSet<State> post;
		while(!workQ.isEmpty()) {
			
			// Take the current powerstate and add it to the set of considered powerstates
			cur = workQ.poll();
			cur_content = cur.getSetContent();
			consSet.add(cur);
			
			for (Letter a : Sigma) {
				
				// Determine the post of cur under a in the determinization
				HashSet<State> det_post_content = new HashSet<State>();
				
				for (State q : cur_content) {
					// Get the post of q under a and add it to power_post
					post = postA.get(q,a);
					if (post != null) det_post_content.addAll(post);
				}
				
				// Add the corresponding state to the determinization along with the corresponding transition
				Powerstate det_post = new Powerstate(det_post_content);
				State det_post_state = det_post.toState();
				
				Transition det_trans = new Transition(cur.toState(), det_post_state, a);
				Power.forceAddTransition(det_trans);
				
				// If the constructed state contains a final state of A, 
				// set it to be final in the determinization
				if (A.isFinal(det_post_content)) Power.addFinal(det_post_state);
				
				// If the constructed state has not already been considered,
				// add it to the worklist to continue the exploration from there
				if (!consSet.contains(det_post)) workQ.add(det_post);
			}
		}
		
		return Power;
	}

	/**
	 * Takes an automaton and returns the automaton that accepts the complement language.
	 * @param A is the given automaton.
	 * @return Comp, the automaton that accepts the complement of A.
	 * 
	 * NOTE: The algorithm determinizes A and flips the final states.
	 * NOTE: Does not return a reference on A, does not return null.
	 * NOTE: If A does not have an initial state, the complement is universal - the whole set of words!
	 */
	public static Autom complement(Autom A) {
		return null;
	}
}