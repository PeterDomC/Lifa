package automatonPrinter;

import java.io.File;
import java.util.Set;

import automata.Autom;
import automata.State;
import automata.Transition;

/*
 * Class for graphical visualization of automata.
 * It has methods for translating an Autom object 
 * into the language DOT (https://graphviz.org/doc/info/lang.html)
 * which is supported by GraphViz - then it creates the corresponding output picture.
 * Standard format is .gif
 */
public class AutomTranslator {
	
	/**
	 * Standard file_format of output picture
	 */
	private static final String file_format = "gif";
	
	/**
	 * Automation is formulates in terms of language DOT
	 */
	private static final String repr_type = "dot";
	
	/**
	 * Prints the given automaton into a file with the given filename
	 * The file format is taken from file_format
	 * Standard is .gif
	 */
	public static void createVisual(Autom A, String filename) {
		
		// Access the API to GraphViz
		GraphViz viz = new GraphViz();
		viz.increaseDpi();
		viz.addln(viz.start_graph());
		viz.addln("rankdir = LR;");
		
		// Go over all states and add as circle node
		Set<State> Stateset = A.getStates();
		for (State q : Stateset) {
			viz.addln(q.getName() + "[shape = circle];");
		}
		
		// Add the isolated states in red
		Set<State> isol = A.getIsolated();
		for (State q : isol) {
			viz.addln(q.getName() + " [color = \"red\"];");
		}
		
		// Add the final states as double circles
		Set<State> fin = A.getFinal();
		for (State q : fin) {
			viz.addln(q.getName() + " [shape = doublecircle];");
		}
		
		// Add the initial transition
		if (A.hasInit()) {
			State initial = A.getInit();
			viz.addln("init [shape = point];");
			viz.addln("init -> " + initial.getName() + ";");
		}
		
		// Go over each transition of the automaton, formulate it as a string, and add it to the output graph
		Set<Transition> Trans = A.getTransitions();
		for (Transition t : Trans) {
			viz.addln(t.getSource().getName() + " -> " + t.getTarget().getName() + " [label = \"" + t.getLabel().getSymb() + "\"];");
		}
		
		// Create the file
		viz.addln(viz.end_graph());
		File out = new File(filename + "." + file_format);
		viz.writeGraphToFile(viz.getGraph(viz.getDotSource(), file_format, repr_type), out);
	}
}