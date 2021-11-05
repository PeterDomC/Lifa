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
		
		RegExp AS = Kleene.star(A);
		RegExp BS = Kleene.star(B);
		RegExp ASBS = Kleene.concat(AS,BS);
		System.out.println(ASBS.toString());
		
		ASBS = Kleene.star(ASBS);
		System.out.println(ASBS.toString());
		
		RegExp res = Kleene.add(ASBS,Epsilon.getEps());
		System.out.println(res.toString());
		
		res = Kleene.star(res);
		System.out.println(res.toString());
		
		res = Kleene.add(res,AS);
		System.out.println(res.toString());
		res = Kleene.star(res);
		System.out.println(res.toString());
		
		res = Kleene.concat(res,BS);
		res = Kleene.add(res,BS);
		res = Kleene.add(res,A);
		res = Kleene.concat(res,B);
		System.out.println(res.toString());
		
		res = Kleene.add(res, Epsilon.getEps());
		System.out.println(res.toString());
		//TODO: check for split form on the other side as well!
		// The rule can be applied from left and right.
		 */
	}
}
