package automataAlgorithms;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;

import automata.Autom;
import automata.Letter;
import automata.State;
import automata.Transition;

/*
 * Class which holds methods around Boolean operations on automata
 * such as intersection, union, determinization, and complement.
 */
public class BooleanOp {
	
	/**
	 * Takes two automata and computes their cross product
	 * The cross product generates the intersection of the languages 
	 * @param A, B are the given automata
	 * They need to have distinct sets of states
	 * @return Cross, the cross product of A and B
	 * It generates the language L(A) intersected L(B)
	 * 
	 * NOTE: Only the reachable states of the cross product are computed
	 * NOTE: If A or B (or both) do not have exactly one initial state,
	 * the method returns the empty automaton
	 * NOTE: For better performance, we recommend to cut isolated states of A and B
	 * before computing the intersection
	 * 
	 * Details on the algorithm can be found in the readme
	 */
	public static Autom intersect(Autom A, Autom B) {
		
		Autom Cross = new Autom();
		
		// A or B (or both) do not have exactly one initial state
		if (!A.hasInit() || !B.hasInit()) {
			return Cross;
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
		init_state.setInit(true);
		if (A.getInit().isFinal() && B.getInit().isFinal()) init_state.setFinal(true);
		
		Cross.addState(init_state);
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
							State prod_state = prod.toState();
							if (q.isFinal() && p.isFinal()) prod_state.setFinal(true);
							
							// Add transition (and label + states)
							Transition prod_trans = new Transition(cur.toState(),prod_state,a);
							Cross.forceAddTransition(prod_trans);
							
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
	 * Takes two automata and returns an automaton that accepts the union of their languages
	 * @param A, B are the given automata
	 * @return Union, the automaton that accepts L(A) union L(B)
	 * 
	 * NOTE: The union is constructed by adding a new initial state
	 * and without epsilon transitions.
	 * NOTE: If A and B do not have exactly one initial state, the method returns the empty automaton
	 * NOTE: The method ignores isolated states
	 */
	public static Autom union(Autom A, Autom B) {
		
		Autom Union = new Autom();
		
		boolean A_init = A.hasInit();
		boolean B_init = B.hasInit();
		
		// If A and B do not have an initial state - union is empty
		if (!A_init && !B_init) return Union;
		// If A does not have an initial state but B has - union is L(B)
		if (!A_init && B_init) return B;
		// If A has an initial state but B does not - union is L(A)
		if (A_init && !B_init) return A;
		
		// If A and B have a unique initial state
		State A_init_state = A.getInit();
		State B_init_state = B.getInit();
		State new_init_state = new Statepair(A_init_state,B_init_state).toState();
		new_init_state.setInit(true);
		Union.addState(new_init_state);
		
		// Add the transitions of A and the additional transitions from the new initial state
		// to the post initial states of A
		HashSet<Transition> A_trans = A.getTransitions();
		for (Transition t : A_trans) {
			Union.forceAddTransition(t);
			if (t.getSource().equals(A_init_state)) {
				Transition t_add = new Transition(new_init_state, t.getTarget(), t.getLabel());
				Union.addTransition(t_add);
			}
		}
		
		// Add the transitions of A and the additional transitions from the new initial state
		// to the post initial states of A
		HashSet<Transition> A_trans = A.getTransitions();
		for (Transition t : A_trans) {
			Union.forceAddTransition(t);
			if (t.getSource().equals(A_init_state)) {
				Transition t_add = new Transition(new_init_state, t.getTarget(), t.getLabel());
				Union.addTransition(t_add);
			}
		}
		
		return Union;
	}
}