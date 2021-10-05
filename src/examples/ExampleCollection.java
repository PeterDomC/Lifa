package examples;

import automata.*;
/*
 * A collection of automata examples to test the implementation of Lifa
 */
public class ExampleCollection {

	/**
	 * First simple example from the readme file.
	 * Alphabet is a,b.
	 */
	public static Autom exampleRM() {
		Letter a = new Letter("a");
		Letter b = new Letter("b");
		
		State p0 = new State("p0");
		State p1 = new State("p1");
		State p2 = new State("p2");
		State p3 = new State("p3");
		
		Transition t1 = new Transition(p0,p1,a);
		Transition t2 = new Transition(p1,p2,b);
		Transition t3 = new Transition(p2,p3,a);
		Transition t4 = new Transition(p3,p0,b);
		
		Autom A = new Autom();
	
		A.addLetter(a);
		A.addLetter(b);
	
		A.addState(p0);
		A.addState(p1);
		A.addState(p2);
		A.addState(p3);
	
		A.addTransition(t1);
		A.addTransition(t2);
		A.addTransition(t3);
		A.addTransition(t4);
		
		A.addFinal(p0);
		A.addFinal(p2);
		
		A.setInit(p0);
		
		return A;
	}
	
	/**
	 * Readme example A.
	 * Alphabet is  a,b.
	 */
	public static Autom exampleRM_A() {
		Letter a = new Letter("a");
		Letter b = new Letter("b");
		
		State p0 = new State("p0");
		State p1 = new State("p1");
		State p2 = new State("p2");
		State p3 = new State("p3");
		
		Transition t1 = new Transition(p0,p1,a);
		Transition t2 = new Transition(p0,p2,a);
		Transition t3 = new Transition(p1,p2,b);
		Transition t4 = new Transition(p1,p3,b);
		Transition t5 = new Transition(p2,p3,a);
		Transition t6 = new Transition(p3,p3,a);
		
		Autom A = new Autom();
		A.addTransition(t1);
		A.addTransition(t2);
		A.addTransition(t3);
		A.addTransition(t4);
		A.addTransition(t5);
		A.addTransition(t6);
		
		A.setInit(p0);
		A.addFinal(p3);
		
		return A;
	}
	
	/**
	 * Readme example B.
	 * Alphabet is  a,b.
	 */
	public static Autom exampleRM_B() {
		Letter a = new Letter("a");
		Letter b = new Letter("b");
		
		State q0 = new State("q0");
		State q1 = new State("q1");
		State q2 = new State("q2");
		
		Transition t1 = new Transition(q0,q1,a);
		Transition t2 = new Transition(q0,q2,b);
		Transition t3 = new Transition(q1,q2,b);
		Transition t4 = new Transition(q2,q2,a);
		
		Autom B = new Autom();
		B.addTransition(t1);
		B.addTransition(t2);
		B.addTransition(t3);
		B.addTransition(t4);
		
		B.setInit(q0);
		B.addFinal(q0);
		B.addFinal(q2);
		
		return B;
	}
	
	/**
	 * Small example 1.
	 * Alphabet is a,b.
	 */
	public static Autom exampleSmall_1() {
		Letter a = new Letter("a");
		Letter b = new Letter("b");
		
		State p0 = new State("p0");
		State p1 = new State("p1");
		State p2 = new State("p2");
		
		Transition t1 = new Transition(p0,p1,a);
		Transition t2 = new Transition(p1,p2,b);
		Transition t3 = new Transition(p2,p0,a);
		Transition t4 = new Transition(p2,p2,b);
		
		Autom A = new Autom();
		
		A.addTransition(t1);
		A.addTransition(t2);
		A.addTransition(t3);
		A.addTransition(t4);
		
		A.addFinal(p0);
		A.addFinal(p2);
		
		A.setInit(p0);
		
		return A;
	}
	
	/**
	 * Small example 2.
	 * Alphabet is a,b.
	 */
	public static Autom exampleSmall_2() {
		Letter a = new Letter("a");
		Letter b = new Letter("b");
		
		State q0 = new State("q0");
		State q1 = new State("q1");
		State q2 = new State("q2");
		State q3 = new State("q3");
		State q4 = new State("q4");
		
		Transition z1 = new Transition(q0,q0,b);
		Transition z2 = new Transition(q0,q1,a);
		Transition z3 = new Transition(q0,q2,a);
		Transition z4 = new Transition(q0,q3,b);
		Transition z5 = new Transition(q1,q4,b);
		Transition z6 = new Transition(q2,q4,a);
		Transition z7 = new Transition(q3,q4,a);
		Transition z8 = new Transition(q4,q0,a);
		
		Autom A = new Autom();
		
		A.addTransition(z1);
		A.addTransition(z2);
		A.addTransition(z3);
		A.addTransition(z4);
		A.addTransition(z5);
		A.addTransition(z6);
		A.addTransition(z7);
		A.addTransition(z8);
		
		A.addFinal(q0);
		A.addFinal(q4);
		
		A.setInit(q0);
		
		return A;
	}
	
	/**
	 * Small example 3.
	 * Alphabet is a,b,c,d.
	 * Contains states that are not reachable.
	 */
	public static Autom exampleSmall_3() {
		Letter a = new Letter("a");
		Letter b = new Letter("b");
		Letter c = new Letter("c");
		Letter d = new Letter("d");
		
		State p0 = new State("p0");
		State p1 = new State("p1");
		State p2 = new State("p2");
		State p3 = new State("p3");
		State p4 = new State("p4");
		
		Transition t1 = new Transition(p0,p0,a);
		Transition t2 = new Transition(p0,p1,b);
		Transition t3 = new Transition(p1,p1,c);
		Transition t4 = new Transition(p2,p1,d);
		Transition t5 = new Transition(p1,p3,c);
		Transition t6 = new Transition(p3,p3,d);
		Transition t7 = new Transition(p4,p3,a);
		
		Autom A = new Autom();
		
		A.addTransition(t1);
		A.addTransition(t2);
		A.addTransition(t3);
		A.addTransition(t4);
		A.addTransition(t5);
		A.addTransition(t6);
		A.addTransition(t7);
		A.addFinal(p3);
		A.addFinal(p4);
		A.setInit(p0);
		
		return A;
	}
	
	/**
	 * A simple example, medium size.
	 * Alphabet is a,b.
	 */
	public static Autom exampleMed_1() {
		Letter a = new Letter("a");
		Letter b = new Letter("b");
		
		State p0 = new State("p0");
		State p1 = new State("p1");
		State p2 = new State("p2");
		State p3 = new State("p3");
		State p4 = new State("p4");
		State p5 = new State("p5");
		State p6 = new State("p6");
		State p7 = new State("p7");
		
		Transition t1 = new Transition(p0,p1,a);
		Transition t2 = new Transition(p1,p2,b);
		Transition t3 = new Transition(p2,p3,a);
		Transition t4 = new Transition(p3,p0,b);
		Transition t5 = new Transition(p1,p2,a);
		Transition t6 = new Transition(p1,p1,a);
		Transition t7 = new Transition(p1,p3,a);
		Transition t8 = new Transition(p7,p5,a);
		Transition t9 = new Transition(p5,p1,a);
		Transition t10 = new Transition(p0,p5,a);
		Transition t11 = new Transition(p0,p6,b);
		Transition t12 = new Transition(p6,p7,b);
		
		Autom A = new Autom();
		
		A.addLetter(a);
		A.addLetter(b);
		
		A.addState(p0);
		A.addState(p1);
		A.addState(p2);
		A.addState(p3);
		A.addState(p4);
		A.addState(p5);
		A.addState(p6);
		A.addState(p7);
		
		A.addTransition(t1);
		A.addTransition(t2);
		A.addTransition(t3);
		A.addTransition(t4);
		A.addTransition(t5);
		A.addTransition(t6);
		A.addTransition(t7);
		A.addTransition(t8);
		A.addTransition(t9);
		A.addTransition(t10);
		A.addTransition(t11);
		A.addTransition(t12);
		
		A.addFinal(p0);
		A.addFinal(p2);
		A.addFinal(p6);
		
		A.setInit(p0);
		
		return A;
	}
	
	/**
	 * A simple example, medium size.
	 * Alphabet is a,b,c.
	 * NOTE: The automaton has an unreachable final state.
	 */
	public static Autom exampleMed_2() {
		Letter a = new Letter("a");
		Letter b = new Letter("b");
		Letter c = new Letter("c");
		
		State p0 = new State("p0");
		State p1 = new State("p1");
		State p2 = new State("p2");
		State p3 = new State("p3");
		State p4 = new State("p4");
		State p5 = new State("p5");
		State p6 = new State("p6");
		State p7 = new State("p7");
		State p8 = new State("p8");
		State p9 = new State("p9");
		State p10 = new State("p10");
		
		Transition t1 = new Transition(p0,p0,a);
		Transition t2 = new Transition(p1,p3,b);
		Transition t3 = new Transition(p2,p3,c);
		Transition t4 = new Transition(p1,p2,b);
		Transition t5 = new Transition(p2,p8,a);
		Transition t6 = new Transition(p8,p9,c);
		Transition t7 = new Transition(p10,p9,c);
		Transition t8 = new Transition(p3,p4,a);
		Transition t9 = new Transition(p4,p5,a);
		Transition t10 = new Transition(p5,p3,a);
		Transition t11 = new Transition(p5,p6,b);
		Transition t12 = new Transition(p6,p7,c);
		Transition t13 = new Transition(p7,p8,c);
		Transition t14 = new Transition(p8,p9,c);
		Transition t15 = new Transition(p9,p3,a);
		Transition t16 = new Transition(p0,p3,a);
		Transition t17 = new Transition(p0,p2,c);
		
		Autom A = new Autom();
		
		A.addTransition(t1);
		A.addTransition(t2);
		A.addTransition(t3);
		A.addTransition(t4);
		A.addTransition(t5);
		A.addTransition(t6);
		A.addTransition(t7);
		A.addTransition(t8);
		A.addTransition(t9);
		A.addTransition(t10);
		A.addTransition(t11);
		A.addTransition(t12);
		A.addTransition(t13);
		A.addTransition(t14);
		A.addTransition(t15);
		A.addTransition(t16);
		A.addTransition(t17);
		
		A.addFinal(p10);
		A.setInit(p0);
		
		return A;
	}
}