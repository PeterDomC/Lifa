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
		
		Test.runOperationTests();
		Test.runLanguageTests();
		Test.runRealWorldTests();
		
		/*
		Letter a = new Letter("a");
		Letter b = new Letter("b");
		Letter c = new Letter("c");
		Atom A = new Atom(a);
		Atom B = new Atom(b);
		Atom C = new Atom(c);
		
		RegExp AB = Kleene.add(A,B);
		RegExp BC = Kleene.add(B,C);
		RegExp out = Kleene.concat(AB,BC);
		System.out.println(out.toString());
		
		out = Kleene.concat(Kleene.star(AB),BC);
		System.out.println(out.toString());
		*/
	}
}