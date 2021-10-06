package examples;

import automata.Autom;
import automataAlgorithms.Language;
import automataAlgorithms.Operations;
import automatonPrinter.AutomTranslator;

public class Test {
	
	public static void runOperationTests() {
	
		/* Readme Example */
		Autom A = ExampleCollection.exampleRM();
		AutomTranslator.createVisual(A, "exampleRM");
		
		/* Load examples */
		A = ExampleCollection.exampleRM_A();
		Autom B = ExampleCollection.exampleRM_B();
		AutomTranslator.createVisual(A,"testA");
		AutomTranslator.createVisual(B,"testB");
		
		/* Intersection test */
		Autom C = Operations.intersect(A,B);
		AutomTranslator.createVisual(C,"inter");
		
		/* Union test */
		C = Operations.union(A,B);
		AutomTranslator.createVisual(C,"union");
		
		/* Concat test */
		C = Operations.concat(A,B);
		AutomTranslator.createVisual(C,"concat");
		
		/* Kleene test */
		C = Operations.kleene(A);
		AutomTranslator.createVisual(C, "kleene");
		
		/* Determinization test */
		C = Operations.determinize(A);
		AutomTranslator.createVisual(C, "det");
		
		/* Complement test */
		C = Operations.complement(A);
		AutomTranslator.createVisual(C, "compl");
		
		/* Reduction test */
		A = ExampleCollection.exampleMed_3();
		AutomTranslator.createVisual(A, "unreduced");
		C = Operations.reduce(A);
		AutomTranslator.createVisual(C, "reduced");
	}
	
	public static void runLanguageTests() {
		
		/* Emptiness Check */
		Autom A = ExampleCollection.exampleMed_2();
		assert (Language.isEmpty(A)); // Language is empty
		Autom B = ExampleCollection.exampleMed_1();
		assert (!Language.isEmpty(B)); // Language not empty
		
	}
}
