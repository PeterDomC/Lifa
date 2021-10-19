package automatonParser;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import automata.Autom;
import automata.Letter;
import automata.State;
import automata.Transition;

/*
 * Listener that walks on the abstract syntax tree of the given input.
 */
public class AutomatonBuildListener implements AutomatonListener {
	
	private Autom parsedAutom;
	
	/**
	 * Constructor
	 */
	public AutomatonBuildListener() {
		parsedAutom = new Autom();
	}
	
	public Autom getParsedAutomaton() {
		return this.parsedAutom;
	}
	
	/**
	 * 
	 */
	@Override
	public void enterAutomaton(AutomatonParser.AutomatonContext ctx) {
		//System.out.println("Parsing started.");
	}
	
	/**
	 * 
	 */
	@Override
	public void exitAutomaton(AutomatonParser.AutomatonContext ctx) {
		//System.out.println("Parsing completed.");
	}
	
	/**
	 * Method parses a transition from the given context and adds it to the automaton.
	 * TODO: Modify the strings
	 */
	@Override
	public void enterTransitions(AutomatonParser.TransitionsContext ctx) {
		
		String source = ctx.STATENAME(0).getText();
		String label = ctx.LABELNAME().getText();
		String target = ctx.STATENAME(1).getText();
		Transition t = new Transition(new State(source), new State(target), new Letter(label));
		
		parsedAutom.addTransition(t);
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitTransitions(AutomatonParser.TransitionsContext ctx) {
		
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterInitial(AutomatonParser.InitialContext ctx) {
		String init = ctx.STATENAME().getText();
		parsedAutom.setInit(new State(init));
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitInitial(AutomatonParser.InitialContext ctx) {
		
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterFinals(AutomatonParser.FinalsContext ctx) {
		String fin = ctx.STATENAME().getText();
		parsedAutom.addFinal(new State(fin));
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitFinals(AutomatonParser.FinalsContext ctx) {
		
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterEveryRule(ParserRuleContext ctx) {
		
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitEveryRule(ParserRuleContext ctx) {
		
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void visitTerminal(TerminalNode node) {
		
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void visitErrorNode(ErrorNode node) {
		
	}
}