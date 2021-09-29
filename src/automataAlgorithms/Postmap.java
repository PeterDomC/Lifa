package automataAlgorithms;

import java.util.HashMap;
import java.util.HashSet;

import automata.Autom;
import automata.Letter;
import automata.State;
import automata.Transition;

/*
 * Class for handling the transitions of an automaton
 * as a fast accessible hash map construction
 * The class makes it obsolete to construct single posts for state/letter combinations
 * Instead, ALL possible posts are constructed by iterating over the transitions of the automaton ONCE
 * This saves lots of time in automata algorithms
 */
public class Postmap {
	
	private final HashMap<State,HashMap<Letter,HashSet<State>>> postmap;
	
	/**
	 * Constructor for the hash map.
	 */
	protected Postmap (Autom A) {
		
		// Initialize the postmap
		this.postmap = new HashMap<State,HashMap<Letter,HashSet<State>>>();
		
		// Iterate over all transitions of the automaton and fill the map accordingly
		HashSet<Transition> trans = A.getTransitions();
		State sour,tar;
		Letter lab;
		
		for (Transition t : trans) {
			
			sour = t.getSource();
			tar = t.getTarget();
			lab = t.getLabel();
			
			if (postmap.containsKey(sour)) {
				// Postmap already has an entry for the source state of this transition
				
				if (postmap.get(sour).containsKey(lab)) {
					// Postmap already has an entry for sour,lab
					// Add the target state to the entry
					postmap.get(sour).get(lab).add(tar);
					
				} else {
					// Postmap does not have an entry for sour,lab
					// Create one with the source state as first state
					HashSet<State> entry = new HashSet<State>();
					entry.add(tar);
					postmap.get(sour).put(lab,entry);
				}
				
			} else {
				// Postmap does not have an entry for the source state
				// Create one according to the transition
				HashSet<State> entry = new HashSet<State>();
				entry.add(tar);
				HashMap<Letter,HashSet<State>> letmap = new HashMap<Letter,HashSet<State>>();
				letmap.put(lab,entry);
				postmap.put(sour,letmap);
			}
		}	
	}
	
	/**
	 * Returns the set of post states of q under the letter a
	 * Returns null if the entry is not set
	 * NOTE: It returns null if the corresponding post is empty
	 */
	public HashSet<State> get(State q, Letter a) {
		if (postmap.get(q) != null) {
			return postmap.get(q).get(a);
		} else {
			return null;
		}
	}

	/**
	 * DEBUG print method
	 */
	public void print() {
		
		for (State q : postmap.keySet()) {
			for (Letter a : postmap.get(q).keySet()) {
				for (State p : postmap.get(q).get(a)) {
					System.out.println("[" + q.getName() + ", " + a.getSymb() + " -> " + p.getName() + "]");
				}
			}
		}
	}
}