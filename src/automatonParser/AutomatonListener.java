// Generated from Automaton.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AutomatonParser}.
 */
public interface AutomatonListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AutomatonParser#automaton}.
	 * @param ctx the parse tree
	 */
	void enterAutomaton(AutomatonParser.AutomatonContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomatonParser#automaton}.
	 * @param ctx the parse tree
	 */
	void exitAutomaton(AutomatonParser.AutomatonContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomatonParser#transitions}.
	 * @param ctx the parse tree
	 */
	void enterTransitions(AutomatonParser.TransitionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomatonParser#transitions}.
	 * @param ctx the parse tree
	 */
	void exitTransitions(AutomatonParser.TransitionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomatonParser#initial}.
	 * @param ctx the parse tree
	 */
	void enterInitial(AutomatonParser.InitialContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomatonParser#initial}.
	 * @param ctx the parse tree
	 */
	void exitInitial(AutomatonParser.InitialContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomatonParser#finals}.
	 * @param ctx the parse tree
	 */
	void enterFinals(AutomatonParser.FinalsContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomatonParser#finals}.
	 * @param ctx the parse tree
	 */
	void exitFinals(AutomatonParser.FinalsContext ctx);
}