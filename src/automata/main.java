package automata;

public class main {

	public static void main(String[] args) {
		
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
		Transition t5 = new Transition(p1,p2,a);
		Transition t6 = new Transition(p1,p1,a);
		
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
		A.addTransition(t5);
		A.addTransition(t6);
		
		A.print();
	}
}
