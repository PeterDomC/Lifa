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
		/*
		Autom A = ExampleCollection.exampleSmall_3();
		AutomTranslator.createVisual(A,"test");
		Autom C = Language.reduce(A);
		AutomTranslator.createVisual(C,"reduced");
		*/
		
		Autom A = ExampleCollection.exampleRM_A();
		Autom B = ExampleCollection.exampleRM_B();
		AutomTranslator.createVisual(A,"testA");
		AutomTranslator.createVisual(B,"testB");
		
		/* Intersection test */
		Autom C = Language.reduce(Operations.intersect(A,B));
		AutomTranslator.createVisual(C,"inter");
		
		//Test.runOperationTests();
		//Test.runLanguageTests();
	}
}