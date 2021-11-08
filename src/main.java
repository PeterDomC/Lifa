import java.io.IOException;
import java.util.ArrayList;

import automata.Autom;
import automata.Letter;
import automatonPrinter.AutomPrinter;
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
		Atom a = new Atom("a");
		Atom b = new Atom("b");
		Atom c = new Atom("c");
		
		RegExp as = Kleene.star(a);
		RegExp bs = Kleene.star(b);
		RegExp cs = Kleene.star(c);
		
		RegExp out = Kleene.concat(as,bs);
		out = Kleene.concat(out,cs);
		out = Kleene.add(out,Kleene.concat(a,bs));
		out = Kleene.add(out,Kleene.concat(a,b));
		
		System.out.println(out.toString());
		
		
		Autom A = out.toAutom();
		AutomPrinter.createVisual(A,"outy");
		*/
		/*
		RegExp AS = Kleene.star(A);
		RegExp BS = Kleene.star(B);
		RegExp CS = Kleene.star(C);
		RegExp ASBS = Kleene.concat(AS,BS);
		System.out.println(ASBS.toString());
		
		// Larger expression.
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
		*/
	}
}
