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
	 * NOTE: The algorithm is a breadth-first exploration where only 
	 * the reachable states of the cross product are computed.
	 * NOTE: For better performance, we recommend to reduce A and B
	 * before computing the intersection.
	 * NOTE: Does not return a reference to A or B, does not return null.
	 */
	public static Autom intersect(Autom A, Autom B) {
		
		Autom Cross = new Autom();
		
		// A or B (or both) do not have an initial state / final states - intersection is empty
		if (!A.hasInit() || !B.hasInit() || !A.hasFinal() || !B.hasFinal()) return Cross;
		
		// If A and B share a state (name), then we give A and B different state names
		if (A.containsState(B.getStates())) {
			A.renewStateNames("A");
			B.renewStateNames("B");
		}
		
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
		
		while(!workQ.isEmpty()) {
			
			// Take current statepair off the queue and add it to the set of considered pairs
			Statepair cur = workQ.poll();
			consSet.add(cur);
			
			// For each letter a, go over all states of Post_a(left) and Post_a(right),
			// add the correpsonding transition to Cross,
			// and add the resulting statepairs to the queue
			State left = cur.getLeft();
			State right = cur.getRight();
			
			for (Letter a : Sigma) {
				// Determine the post sets - can be null
				HashSet<State> leftPost = postA.get(left,a);
				HashSet<State> rightPost = postB.get(right,a);
				
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
							Cross.addTransition(prod_trans);
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
		
		boolean A_final = A.hasFinal();
		boolean B_final = B.hasFinal();
		
		// If A and B do not have final states - union is empty
		if (!A_final && !B_final) return new Autom();
		// If A does not have final states but B has - union is L(B)
		if (!A_final && B_final) return B.copy();
		// If A has final states but B does not - union is L(A)
		if (A_final && !B_final) return A.copy();
		
		// If A and B share a state (name), then we give A and B different state names
		if (A.containsState(B.getStates())) {
			A.renewStateNames("A");
			B.renewStateNames("B");
		}
		
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
			Union.addTransition(t);
			
			if (t.getSource().equals(A_init_state)) {
				Transition t_add = new Transition(new_init_state, t.getTarget(), t.getLabel());
				Union.addTransition(t_add);
			}
		}
		
		// Add the transitions of B and the additional transitions from the new initial state
		// to the post initial states of B
		Trans = B.getTransitions();
		for (Transition t : Trans) {
			Union.addTransition(t);
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
	 * NOTE: Does not return a reference to A or B, does not return null.
	 */
	public static Autom concat(Autom A, Autom B) {
		
		Autom Concat = new Autom();
		
		// A or B does not have final or initial state - concatenation is empty
		if (!A.hasInit() || !B.hasInit() || !A.hasFinal() || !B.hasFinal()) return Concat;
		
		// If A and B share a state (name), then we give A and B different state names
		if (A.containsState(B.getStates())) {
			A.renewStateNames("A");
			B.renewStateNames("B");
		}
		
		// Copy the transitions of A
		HashSet<Transition> Trans = A.getTransitions();
		for (Transition t : Trans) {
			Concat.addTransition(t);
		}
		
		// Take the initial state of A as new initial state
		Concat.setInit(A.getInit());
		State B_init_state = B.getInit();
		
		// Copy the transitions of B
		// For each post initial state of B, add a transition from all final states of A to that state
		Trans = B.getTransitions();
		HashSet<State> Final = A.getFinal();
		for (Transition t : Trans) {
			Concat.addTransition(t);
			
			if (t.getSource().equals(B_init_state)) {
				for (State q : Final) {
					Transition t_add = new Transition(q,t.getTarget(),t.getLabel());
					Concat.addTransition(t_add);
				}
			}
		}
		
		// Final states of Concat contain the final states of B (in each case)
		// as well as the final states of A if L(B) contains epsilon.
		Concat.addFinal(B.getFinal());
		if (B.isFinal(B.getInit())) Concat.addFinal(A.getFinal());
		
		return Concat;
	}
	
	/**
	 * Takes an automaton and returns the automaton that accepts the Kleene star of the original language.
	 * @param A is the given automaton.
	 * @return Kleene, the automata accepting L(A)^*.
	 * 
	 * NOTE: Kleene is constructed without epsilon transitions.
	 * NOTE: Does not return a reference on A, does not return null.
	 */
	public static Autom kleene(Autom A) {
		
		Autom Kleene = new Autom();
		
		// If A does not have an initial state or final states, the Kleene star language is empty
		if (!A.hasInit() || !A.hasFinal()) return Kleene;
		
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
			Kleene.addTransition(t);
			
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
	 * NOTE: Determinizations may grow large! Consider your input carefully.
	 * NOTE: For better performance, we recommend to reduce A.
	 * NOTE: Does not return a reference to A, does not return null.
	 */
	public static Autom determinize(Autom A) {
		
		Autom Power = new Autom();
		
		// If A does not have an initial state, the determinization is empty
		if (!A.hasInit() || !A.hasFinal()) return Power;
		
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
		State init_state = init.toState();
		
		// Add the state to Power and to the queue - it can be final
		Power.addState(init_state);
		Power.setInit(init_state);
		if (A.isFinal(init_set)) Power.addFinal(init_state);
		workQ.add(init);
		
		while(!workQ.isEmpty()) {
			
			// Take the current powerstate and add it to the set of considered powerstates
			Powerstate cur = workQ.poll();
			HashSet<State> cur_content = cur.getSetContent();
			consSet.add(cur);
			
			for (Letter a : Sigma) {
				
				// Determine the post of cur under a in the determinization
				HashSet<State> det_post_content = new HashSet<State>();
				
				for (State q : cur_content) {
					// Get the post of q under a and add it to det_post_content
					HashSet<State> post = postA.get(q,a);
					if (post != null) det_post_content.addAll(post);
				}
				
				// Add the corresponding state to the determinization along with the corresponding transition
				Powerstate det_post = new Powerstate(det_post_content);
				State det_post_state = det_post.toState();
				
				Transition det_trans = new Transition(cur.toState(), det_post_state, a);
				Power.addTransition(det_trans);
				
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
	 * NOTE: If A does not have an initial state or final states, 
	 * the complement is universal - the whole set of words.
	 */
	public static Autom complement(Autom A) {
		
		Autom Comp = new Autom();
		
		if (!A.hasInit() || !A.hasFinal()) {
			// A does not have an initial state or no final state, so its language is empty
			// Construct a universal automaton
			State only = new State("{}");
			Comp.addFinal(only);
			Comp.setInit(only);
			HashSet<Letter> Sigma = A.getAlphabet();
			for (Letter a : Sigma) {
				Transition t = new Transition(only,only,a);
				Comp.addTransition(t);
			}
			
			return Comp;
		}
		
		// A has an initial state - determinize and flip final states
		Comp = determinize(A);
		HashSet<State> new_final = new HashSet<State>();
		new_final.addAll(Comp.getStates());
		new_final.removeAll(Comp.getFinal());
		Comp.clearFinal();
		Comp.addFinal(new_final);
		
		return Comp;
	}
	
	/**
     * Method for reducing the given automaton to one that accepts the same language
     * and that has only reachable states/states that can reach a final state.
     * @param A is the given automaton.
     * @return Red, the reduced automaton.
     * 
     * NOTE: Does not return a reference to A, does not return null.
     * NOTE: This is a breadth-first algorithm into two directions:
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
		
		// Begin the first breadth first algorithm with the initial state
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
		
		// Begin the second breadth-first search
		// We directly work on Red since the automaton is smaller than A
		HashSet<State> canReachFinal = new HashSet<State>();
		
		// Get the premap of Red - this is the postmap of the reversed automaton
		// Note that the workQ is empty at this point!
		Postmap preRed = new Postmap(reverseTransitions(Red));
		
		// Begin the search from all final states of Red
		HashSet<State> finalRed = Red.getFinal();
		for (State q : finalRed) {
			workQ.add(q);
		}
		
		while (!workQ.isEmpty()) {
			
			// Take the current state from the queue and add its pre states 
			// to the queue if they have not been considered yet
			State cur = workQ.poll();
			canReachFinal.add(cur);
			
			for (Letter a : Sigma) {
				HashSet<State> pre = preRed.get(cur,a);
				
				if (pre != null) {	
					for (State q : pre) {
						// Continue exploration from q if not already considered
						if (!canReachFinal.contains(q)) workQ.add(q);
					}
				}
			}
		}
		
		// Delete the states that cannot reach a final state in Red
		// Note that the corresponding transitions are also removed from Red
		Red.retainStates(canReachFinal);
    	return Red;
    }
    
    /**
     * Method takes an automaton and outputs one that accepts the reverse language.
     * @param A is the given automaton.
     * @return Rev, the automaton that accepts reverse(L(A)).
     * 
     * NOTE: Does not return a reference to A, does not return null.
     */
    public static Autom reverse(Autom A) {
    	
    	Autom Rev = new Autom();
    	// If A does not have an initial state or a final state, the reversed language is empty
    	if (!A.hasInit() || !A.hasFinal()) return Rev;
    	
    	// Turn around the transitions of A and store it in Rev
    	Rev = reverseTransitions(A);
    	
    	// Add a new initial state that points to pre-final (now post-final) states
    	State init_A = A.getInit();
    	State init = new State(init_A.getName() + "!");
    	Rev.addState(init);
    	Rev.setInit(init);
    	if (A.isFinal(init_A)) Rev.addFinal(init);
    	
    	HashSet<Transition> Trans = Rev.getTransitions();
    	HashSet<Transition> Trans_new = new HashSet<Transition>();
    	for (Transition t : Trans) {
    		if (A.isFinal(t.getSource())) {
    			Transition t_new = new Transition(init,t.getTarget(),t.getLabel());
    			Trans_new.add(t_new);
    		}
    	}
    	Rev.addTransition(Trans_new);
    	
    	// Add the initial state of A as final in Rev
    	Rev.addFinal(init_A);
    	return Rev;
    }
    
    /**
     * Method that turns around the direction of the transitions in the given automaton.
     * @param A is the given automaton.
     * @return Rev, the automaton with turned around transitions.
     * 
     * NOTE: The method cuts isolated states of A.
     * NOTE: Does not return a reference to A, does not return null.
     */
    private static Autom reverseTransitions(Autom A) {
    	
    	Autom Rev = new Autom();
    	HashSet<Transition> Trans = A.getTransitions();
    	for (Transition t : Trans) {
    		Rev.addTransition(new Transition(t.getTarget(), t.getSource(), t.getLabel()));
    	}
    	
    	return Rev;
    }
}
