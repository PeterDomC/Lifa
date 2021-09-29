import java.io.File;

import automata.*;
import automataAlgorithms.*;
import automatonPrinter.*;

public class main {

	public static void main(String[] args) {
		
		/* Readme example */
		/*
		Letter a = new Letter("a");
		Letter b = new Letter("b");
		
		State p0 = new State("p0", true, true);
		State p1 = new State("p1", false, false);
		State p2 = new State("p2", false, true);
		State p3 = new State("p3", false, false);
		
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
		
		AutomTranslator.createVisual(A, "exampleRM");
		*/
		
		/* Larger example */
		/*
		Letter a = new Letter("a");
		Letter b = new Letter("b");
		
		State p0 = new State("p0", true, true);
		State p1 = new State("p1", false, false);
		State p2 = new State("p2", false, true);
		State p3 = new State("p3", false, false);
		State p4 = new State("p4", false, false);
		State p5 = new State("p5", false, false);
		State p6 = new State("p6", false, true);
		State p7 = new State("p7", false, false);
		
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
		
		AutomTranslator.createVisual(A, "out");
		*/
		
		/* Intersection test */
		Letter a = new Letter("a");
		Letter b = new Letter("b");
		
		State p0 = new State("p0", true, true);
		State p1 = new State("p1", false, false);
		State p2 = new State("p2", false, true);
		State p3 = new State("p3", false, false);
		Transition t1 = new Transition(p0,p1,a);
		Transition t2 = new Transition(p1,p2,b);
		Transition t3 = new Transition(p2,p3,a);
		Transition t4 = new Transition(p3,p0,b);
		
		Autom A = new Autom();
		A.forceAddTransition(t1);
		A.forceAddTransition(t2);
		A.forceAddTransition(t3);
		A.forceAddTransition(t4);
		
		State q0 = new State("q0", true, true);
		State q1 = new State("q1", false, false);
		State q2 = new State("q2", false, false);
		State q3 = new State("q3", false, false);
		State q4 = new State("q4", false, true);
		
		Transition z1 = new Transition(q0,q0,b);
		Transition z2 = new Transition(q0,q1,a);
		Transition z3 = new Transition(q0,q2,a);
		Transition z4 = new Transition(q0,q3,b);
		Transition z5 = new Transition(q1,q4,b);
		Transition z6 = new Transition(q2,q4,a);
		Transition z7 = new Transition(q3,q4,a);
		Transition z8 = new Transition(q4,q0,a);
		
		Autom B = new Autom();
		B.forceAddTransition(z1);
		B.forceAddTransition(z2);
		B.forceAddTransition(z3);
		B.forceAddTransition(z4);
		B.forceAddTransition(z5);
		B.forceAddTransition(z6);
		B.forceAddTransition(z7);
		B.forceAddTransition(z8);
		
		AutomTranslator.createVisual(A,"testA");
		AutomTranslator.createVisual(B,"testB");
		
		Autom C = BooleanOp.intersect(A, B);
		AutomTranslator.createVisual(C,"inter");
	}
}