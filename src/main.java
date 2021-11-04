import java.io.IOException;
import java.util.ArrayList;

import automata.Letter;
import examples.Test;
import regularExpression.Atom;
import regularExpression.Clause;
import regularExpression.ClauseFactory;
import regularExpression.ClauseType;
import regularExpression.ConExp;
import regularExpression.EmptyExp;
import regularExpression.Epsilon;
import regularExpression.Kleene;
import regularExpression.RegExp;
import regularExpression.StarExp;

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
		//System.out.println(BB.toString());
		RegExp AB = Kleene.concat(A,B);
		//System.out.println(AB.toString());
		
		RegExp BBAB = Kleene.concat(BB,AB);
		//System.out.println(BBAB.toString());
		
		RegExp more = Kleene.add(BBAB,A);
		//System.out.println(more.toString());
		more = Kleene.add(more,A);
		//System.out.println(more.toString());
		more = Kleene.add(more,Epsilon.getEps());
		//System.out.println(more.toString());
		more = Kleene.add(more,EmptyExp.getEmptySet());
		System.out.println(more.toString());
		more = Kleene.concat(more,more);
		System.out.println(more.toString());
		*/
	}
}
