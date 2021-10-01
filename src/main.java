import automata.*;
import automataAlgorithms.*;
import automatonPrinter.*;
import examples.ExampleCollection;

public class main {

	public static void main(String[] args) {
		
		//TODO: Adjust the readme
		
		/* Readme Example */
		Autom A = ExampleCollection.exampleRM();
		AutomTranslator.createVisual(A, "exampleRM");
		
		/* Load examples */
		A = ExampleCollection.exampleSmall_1();
		Autom B = ExampleCollection.exampleSmall_2();
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
	}
}