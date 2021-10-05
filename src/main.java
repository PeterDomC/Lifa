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
		
		Autom A = ExampleCollection.exampleRM();
		AutomTranslator.createVisual(A, "A");
		Autom B = ExampleCollection.exampleRM_A();
		AutomTranslator.createVisual(B, "B");
		Autom C = Operations.intersect(A,B);
		AutomTranslator.createVisual(A, "Anew");
		AutomTranslator.createVisual(B, "Bnew");
		AutomTranslator.createVisual(C, "AcapB");
		
		Test.runTests();
	}
}