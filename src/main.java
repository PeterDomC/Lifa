import java.io.File;

import automata.*;
import automataAlgorithms.*;
import automatonPrinter.*;

public class main {

	public static void main(String[] args) {
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
/*
		A.addLetter(a);
		A.addLetter(b);

		A.addState(p0);
		A.addState(p1);
		A.addState(p2);
		A.addState(p3);

		A.addTransition(t1);
		A.addTransition(t2);
		A.addTransition(t3);
		A.addTransition(t4); */
		
		Postmap map = new Postmap(A);
		map.print();
		
		//AutomTranslator.createVisual(A, "exampleRM");
	}
}