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
		Atom A = new Atom(a);
		System.out.println(A.getType());
		Atom B = new Atom(b);
		
		RegExp C = new SumExp(A,B);
		System.out.println(C.toString());
		System.out.println(C.getType());
		
		RegExp D = new StarExp(C);
		System.out.println(D.toString());
		System.out.println(D.getType());
		
		D = new SumExp(D,D);
		System.out.println(D.toString());
		System.out.println(D.getType());
		
		D = new StarExp(D);
		System.out.println(D.toString());
		System.out.println(D.getType());
		
		RegExp T = new SumExp(D,Epsilon.getEps());
		System.out.println(T.toString());
		System.out.println(T.getType());
		
		T = new ConExp(D,EmptyExp.getEmptySet());
		System.out.println(T.toString());
		System.out.println(T.getType());
		
		
		RegExp S = Kleene.add(A,B);
		System.out.println(S.toString());
		System.out.println(S.getType());
		*/
	}
}