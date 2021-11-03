import java.io.IOException;
import java.util.ArrayList;

import automata.Letter;
import examples.Test;
import regularExpression.Atom;
import regularExpression.Clause;
import regularExpression.ClauseType;
import regularExpression.ConExp;
import regularExpression.EmptyExp;
import regularExpression.Epsilon;
import regularExpression.Kleene;
import regularExpression.RegExp;
import regularExpression.StarExp;

public class main {

	public static void main(String[] args) throws IOException {
		
		/*
		Test.runOperationTests();
		Test.runLanguageTests();
		Test.runRealWorldTests();
		*/
		
		Letter a = new Letter("a");
		Letter b = new Letter("b");
		Letter c = new Letter("c");
		Atom A = new Atom(a);
		Atom B = new Atom(b);
		Atom C = new Atom(c);
		
		ArrayList<Clause> BBlist = new ArrayList<Clause>();
		BBlist.add(B);
		BBlist.add(B);
		ConExp BB = new ConExp(BBlist);
		
		StarExp BBstar = new StarExp(BB);
		
		ArrayList<Clause> splitTestList = new ArrayList<Clause>();
		splitTestList.add(B);
		splitTestList.add(B);
		splitTestList.add(BBstar);
		ConExp splitTest = new ConExp(splitTestList);
		System.out.println(splitTest.toString());
		
		RegExp simple = Kleene.add(Epsilon.getEps(),splitTest);
		System.out.println(simple.toString());
		
		
	}
}