package examples;

import java.util.HashSet;

import automata.Autom;
import automata.State;
import automataAlgorithms.Language;
import automataAlgorithms.Operations;
import automatonParser.AutomParser;
import automatonPrinter.AutomPrinter;

public class Test {
	
	public static void runOperationTests() {
	
		/* Readme Example */
		Autom A = ExampleCollection.exampleRM();
		AutomPrinter.createVisual(A, "exampleRM");
		
		/* Load examples */
		A = ExampleCollection.exampleRM_A();
		Autom B = ExampleCollection.exampleRM_B();
		AutomPrinter.createVisual(A,"testA");
		AutomPrinter.createVisual(B,"testB");
		
		/* Intersection test */
		Autom C = Operations.intersect(A,B);
		AutomPrinter.createVisual(C,"inter");
		
		/* Union test */
		C = Operations.union(A,B);
		AutomPrinter.createVisual(C,"union");
		
		/* Concat test */
		C = Operations.concat(A,B);
		AutomPrinter.createVisual(C,"concat");
		
		/* Kleene test */
		C = Operations.kleene(A);
		AutomPrinter.createVisual(C, "kleene");
		
		/* Determinization test */
		C = Operations.determinize(A);
		AutomPrinter.createVisual(C, "det");
		
		/* Complement test */
		C = Operations.complement(A);
		AutomPrinter.createVisual(C, "compl");
		
		/* Reduction test */
		A = ExampleCollection.exampleMed_3();
		AutomPrinter.createVisual(A, "unreduced");
		C = Operations.reduce(A);
		AutomPrinter.createVisual(C, "reduced");
		
		/* Reverse test */
		C = Operations.reverse(A);
		AutomPrinter.createVisual(C,"reverse");
	}
	
	public static void runLanguageTests() {
		
		/* Emptiness check */
		Autom A = ExampleCollection.exampleMed_2();
		assert (Language.isEmpty(A)); // Language is empty
		Autom B = ExampleCollection.exampleMed_1();
		assert (!Language.isEmpty(B)); // Language not empty
		
		/* Universality check */
		A = ExampleCollection.exampleMed_4();
		assert (Language.isUniversal(A));
		
		/* Containment check */
		A = ExampleCollection.exampleRM_A();
		B = ExampleCollection.exampleRM_B();
		assert (!Language.isContained(A,B));
		
		B = Operations.determinize(A);
		assert (Language.isContained(A,B));
		
		/* Equivalence check */
		assert (Language.isEquivalent(A,B));
		B = ExampleCollection.exampleRM_B();
		assert (!Language.isEquivalent(A,B));
	}
	
	/**
	 * Real world test scenarios, taken from http://www.languageinclusion.org
	 */
	public static void runRealWorldTests() {
		
		/* Peterson */
		Autom A = AutomParser.parseFromFile("src/examples/realworld/petersonA.txt");
		Autom B = AutomParser.parseFromFile("src/examples/realworld/petersonB.txt");
		assert (Language.isContained(A,B));
		
		/* Phils */
		A = AutomParser.parseFromFile("src/examples/realworld/philsA.txt");
		B = AutomParser.parseFromFile("src/examples/realworld/philsB.txt");
		assert (Language.isContained(A,B));
		
		/* Bakery */
		A = AutomParser.parseFromFile("src/examples/realworld/bakeryA.txt");
		B = AutomParser.parseFromFile("src/examples/realworld/bakeryB.txt");
		assert (!Language.isContained(A,B));
		
		/* Fischer V5 (this test may take a while) */
		A = AutomParser.parseFromFile("src/examples/realworld/fischerV5A.txt");
		HashSet<State> finA = new HashSet<State>(A.getStates());
		A.addFinal(finA);
		
		B = AutomParser.parseFromFile("src/examples/realworld/fischerV5B.txt");
		HashSet<State> finB = new HashSet<State>(B.getStates());
		B.addFinal(finB);
		
		assert (!Language.isContained(A,B));
	}
}
