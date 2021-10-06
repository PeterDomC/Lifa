import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import automata.*;
import automataAlgorithms.*;
import automatonPrinter.*;
import examples.ExampleCollection;
import examples.Test;

public class main {

	public static void main(String[] args) {
		
		Autom A = ExampleCollection.exampleMed_3();
		AutomTranslator.createVisual(A,"testA");
		
		/* Reduction test */
		Autom C = Operations.reduce(A);
		AutomTranslator.createVisual(C,"reduced");
		
		Test.runOperationTests();
		//Test.runLanguageTests();
	}
}