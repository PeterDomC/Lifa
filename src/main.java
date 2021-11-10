import java.io.IOException;
import java.util.ArrayList;

import automata.Autom;
import automata.Letter;
import automatonPrinter.AutomPrinter;
import examples.ExampleCollection;
import examples.Test;
import regularExpressionEngine.Kleene;
import regularExpressionEngine.RegSystem;
import regularExpressions.Atom;
import regularExpressions.Clause;
import regularExpressions.EmptyExp;
import regularExpressions.Epsilon;
import regularExpressions.RegExp;

public class main {
	
	public static void main(String[] args) throws IOException {
		
		/*
		Test.runOperationTests();
		Test.runLanguageTests();
		Test.runRealWorldTests();
		*/
		
		Autom A = ExampleCollection.arden_1();
		RegSystem AEQ = new RegSystem(A);
		AEQ.print();
		AutomPrinter.createVisual(A,"arden1");
		
		AEQ.plugIn(1,0);
		AEQ.print();
		
	}
}
