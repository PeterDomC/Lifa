package automatonParser;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import automata.Autom;

/*
 * Class for initializing the ANTLR parser and tree structure 
 * as well as for interacting with the implemented automaton build listener.
 */
public class AutomParser {
	
	/**
	 * Parse an automaton from a given CharStream.
	 * @param stream is the given stream.
	 * @return the automaton encoded by the stream.
	 * 
	 * NOTE: The method initializes the lexer, the parser, and the corresponding listener.
	 */
	public static Autom parse(CharStream stream) {
		// Initialize the lexer
		AutomatonLexer lex = new AutomatonLexer(stream);
		// Initialize the parser
		AutomatonParser parser = new AutomatonParser(new CommonTokenStream(lex));
		// Initialize the listener
		AutomatonBuildListener listener = new AutomatonBuildListener();
		
		// A parse tree is created with the root "automaton" - this is the start rule
        ParseTree tree = parser.automaton();
        // The tree is walked with the listener
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener,tree);
        
        // Return the created automaton
        return listener.getParsedAutomaton();
	}
	
	/**
	 * Parse an automaton from a given string.
	 * @param inputStr is the given input string.
	 * @return the automaton encoded by the string.
	 * 
	 * NOTE: The method generates a CharStream and passes it to the corresponding parse method.
	 */
	public static Autom parse(String inputStr) {
		return parse(CharStreams.fromString(inputStr));
	}
	
	/**
	 * Parse an automaton from a given file.
	 * @param filename is the name of the given file.
	 * @return the automaton contained in the file.
	 * 
	 * NOTE: The method generates a CharStream and passes it to the corresponding parse method.
	 * NOTE: If the method fails to load the file, it returns an empty automaton.
	 */
	public static Autom parseFromFile(String filename) {
		try {
			return parse(CharStreams.fromFileName(filename));
			
		} catch (IOException e) {
			System.out.println("Error loading file " + filename);
			return new Autom();
		}
	}
}
