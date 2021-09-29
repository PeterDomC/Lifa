package automataAlgorithms;

import java.util.ArrayDeque;
import java.util.Queue;

import automata.Autom;
import automata.State;

/*
 * Class which holds methods around Boolean operations on automata
 * such as intersection, union, determinization, and complement.
 */
public class BooleanOp {
	
	/**
	 * Takes two automata and computes their cross product
	 * The cross product generates the intersection of the languages 
	 * @param A, B are the given automata
	 * @return Cross, the cross product of A and B
	 * It generates the language L(A) intersected L(B)
	 * 
	 * NOTE: Only the reachable states of the cross product are computed
	 * NOTE: If A or B (or both) do not have exactly one initial state,
	 * the method returns the empty automaton
	 * NOTE: For better performance, we recommend to cut isolated states of A and B
	 * before computing the intersection
	 */
	public static Autom intersect(Autom A, Autom B) {
		
		Autom Cross = new Autom();
		
		// A or B (or both) do not have exactly one initial state
		if (!A.hasInit() || !B.hasInit()) {
			return Cross;
		}
		
		Queue<Statepair> blubb = new ArrayDeque<Statepair>();
		// TODO
		
		return null;
	}
	
	
	
	
	
	
}