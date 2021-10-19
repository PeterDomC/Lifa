import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import automata.*;
import automataAlgorithms.*;
import automatonParser.AutomParser;
import automatonParser.AutomatonBuildListener;
import automatonParser.AutomatonLexer;
import automatonParser.AutomatonParser;
import automatonPrinter.*;
import examples.ExampleCollection;
import examples.Test;

public class main {

	public static void main(String[] args) {
		/*
		Test.runOperationTests();
		Test.runLanguageTests();
		*/
		
		Autom A = AutomParser.parseFromFile("src/examples/parsetest.txt");
		AutomPrinter.createVisual(A,"testprint");
	}
}