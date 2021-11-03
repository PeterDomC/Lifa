import java.io.IOException;
import java.util.ArrayList;

import automata.Letter;
import examples.Test;
import regularExpression.Atom;
import regularExpression.Clause;
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
		
		StarExp Bstar = new StarExp(B);
		ArrayList<Clause> split_factors = new ArrayList<Clause>();
		split_factors.add(B);
		split_factors.add(Bstar);
		ConExp split = new ConExp(split_factors);
		System.out.println(split.toString());
		
		RegExp simple = Kleene.add(Epsilon.getEps(),split);
		System.out.println(simple.toString());
		*/
		
	}
}