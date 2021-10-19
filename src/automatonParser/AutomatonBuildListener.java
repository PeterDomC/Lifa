package automatonParser;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import automata.Autom;
import automata.Letter;
import automata.State;
import automata.Transition;

/*
 * Listener that walks over the abstract syntax tree of the given input.
 */
public class AutomatonBuildListener implements AutomatonListener {
	
	private Autom parsedAutom;
	
	/**
	 * Constructor.
	 * Creates an empty automaton.
	 */
	public AutomatonBuildListener() {
		this.parsedAutom = new Autom();
	}
	
	/**
	 * Getter for the parsed automaton.
	 */
	public Autom getParsedAutomaton() {
		return this.parsedAutom;
	}
	
	/**
	 * Begin parsing - enter root node of tree.
	 */
	@Override
	public void enterAutomaton(AutomatonParser.AutomatonContext ctx) {}
	
	/**
	 * Finish parsing - leave root node of tree.
	 */
	@Override
	public void exitAutomaton(AutomatonParser.AutomatonContext ctx) {}
	
	/**
	 * Method parses a transition from the given context and adds it to the automaton.
	 */
	@Override
	public void enterTransitions(AutomatonParser.TransitionsContext ctx) {
		
		// Get the source state and remove the []-brackets from its name
		String source = cutSqBrackets(ctx.STATENAME(0).getText());
		
		// Get the label and remove the ()-brackets from its name
		String label = cutBrackets(ctx.LABELNAME().getText());
		
		// Get the target state and remove the []-brackets from its name
		String target = cutSqBrackets(ctx.STATENAME(1).getText());
		
		// Build the transition and add it to the automaton
		Transition t = new Transition(new State(source), new State(target), new Letter(label));
		parsedAutom.addTransition(t);
	}
	
	/**
	 * Finished parsing of the transition context.
	 */
	@Override 
	public void exitTransitions(AutomatonParser.TransitionsContext ctx) {}
	
	/**
	 * Method parses the initial state from the given context and sets it as initial in the automaton.
	 */
	@Override 
	public void enterInitial(AutomatonParser.InitialContext ctx) {
		
		// Get the name of the initial state
		String init = cutSqBrackets(ctx.STATENAME().getText());
		
		// Set it as initial in the parsed automaton
		parsedAutom.setInit(new State(init));
	}
	
	/**
	 * Finished parsing of the initial context.
	 */
	@Override 
	public void exitInitial(AutomatonParser.InitialContext ctx) {}
	
	/**
	 * Method parses a state from the given context and sets it as final in the automaton.
	 */
	@Override 
	public void enterFinals(AutomatonParser.FinalsContext ctx) {
		
		// Get the name of the final state
		String fin = cutSqBrackets(ctx.STATENAME().getText());
		
		// Set it as final in the parsed automaton.
		parsedAutom.addFinal(new State(fin));
	}
	
	/**
	 * Finished parsing of the finals context.
	 */
	@Override 
	public void exitFinals(AutomatonParser.FinalsContext ctx) {}

	/**
	 * Action to be performed while entering each context - empty in our case.
	 */
	@Override 
	public void enterEveryRule(ParserRuleContext ctx) {}
	
	/**
	 * Action to be performed while leaving each context - empty in our case.
	 */
	@Override 
	public void exitEveryRule(ParserRuleContext ctx) {}
	
	/**
	 * Action to be performed while visiting a leaf of the parse tree - empty in our case.
	 */
	@Override 
	public void visitTerminal(TerminalNode node) {}
	
	/**
	 * Action to be performed while visiting an error - empty in our case.
	 */
	@Override 
	public void visitErrorNode(ErrorNode node) {}
	
	/**
	 * Method for replacing brackets () from a label's name.
	 * @param inStr is the name of the label read by the parser.
	 * @return inStr with () replaced by "".
	 */
	private String cutBrackets(String inStr) {
		inStr = inStr.replace("(", "");
		inStr = inStr.replace(")", "");
		return inStr;
	}
	
	/**
	 * Method for replacing the square brackets [] from a state's name.
	 * @param inStr is the name of the state read by the parser.
	 * @return inStr with [] replaced by "".
	 */
	private String cutSqBrackets(String inStr) {
		inStr = inStr.replace("[", "");
		inStr = inStr.replace("]", "");
		return inStr;
	}
}