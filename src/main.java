import java.io.IOException;

import automata.Letter;
import examples.Test;
import regularExpression.Atom;
import regularExpression.ConExp;
import regularExpression.EmptyExp;
import regularExpression.Epsilon;
import regularExpression.Kleene;
import regularExpression.RegExp;
import regularExpression.StarExp;
import regularExpression.SumExp;

public class main {

	public static void main(String[] args) throws IOException {
		/*
		Test.runOperationTests();
		Test.runLanguageTests();
		Test.runRealWorldTests();
		*/
		
		Letter a = new Letter("a");
		Letter b = new Letter("b");
		Atom A = new Atom(a);
		Atom B = new Atom(b);
		
		RegExp C = Kleene.add(A,B);
		System.out.println(C.toString());
		System.out.println(C.getType());
		
		C = Kleene.add(C,EmptyExp.getEmptySet());
		System.out.println(C.toString());
		System.out.println(C.getType());
		
		C = Kleene.star(C);
		System.out.println(C.toString());
		System.out.println(C.getType());
		
	}
}