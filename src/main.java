import java.io.IOException;
import java.util.ArrayList;

import automata.Letter;
import examples.Test;
import regularExpression.Atom;
import regularExpression.Clause;
import regularExpression.EmptyExp;
import regularExpression.Epsilon;
import regularExpression.Kleene;
import regularExpression.RegExp;

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
		
		RegExp BB = Kleene.concat(B,B);
		RegExp AB = Kleene.concat(A,B);
		
		RegExp BBAB = Kleene.concat(BB,AB);
		System.out.println(BBAB.toString());
		
		RegExp res = Kleene.add(BBAB,BB);
		System.out.println(res.toString());
		res = Kleene.star(res);
		System.out.println(res.toString());
		
		res = Kleene.add(res,B);
		System.out.println(res.toString());
		
		res = Kleene.add(res,BB);
		System.out.println(res.toString());
		
		res = Kleene.star(res);
		System.out.println(res.toString());
		*/
	}
}
