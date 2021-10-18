import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import automata.*;
import automataAlgorithms.*;
import automatonParser.AutomatonBuildListener;
import automatonParser.AutomatonLexer;
import automatonParser.AutomatonParser;
import automatonPrinter.*;
import examples.ExampleCollection;
import examples.Test;

public class main {

	public static void main(String[] args) {
		
		String A = "trans\n"
				+ "[p0](x)[p1],\n"
				+ "[p1] (b)[p3],\n"
				+ "[p3](a)[p2],\n"
				+ "[p2](a) [p1],\n"
				+ "[p1](b)[p3];\n"
				+ "\n"
				+ "init\n"
				+ "[p0];\n"
				+ "\n"
				+ "final\n"
				+ "[p0],\n"
				+ "[p3];\n";
		
		AutomatonLexer lex = new AutomatonLexer(CharStreams.fromString(A));
		AutomatonParser parser = new AutomatonParser(new CommonTokenStream(lex));
		AutomatonBuildListener listener = new AutomatonBuildListener();
        ParseTree tree = parser.automaton();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener,tree);
        
        Autom NFA = listener.getParsedAutomaton();
        AutomTranslator.createVisual(NFA, "test-nfa");
		
		//Test.runOperationTests();
		//Test.runLanguageTests();
	}
}