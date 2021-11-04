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
		
		ArrayList<Clause> BBlist = new ArrayList<Clause>();
		BBlist.add(B);
		BBlist.add(B);
		Clause BB = ClauseFactory.createConExp(BBlist);
		
		Clause BBstar = ClauseFactory.CreateStarExp(BB);
		Clause Astar = ClauseFactory.CreateStarExp(A);
		
		ArrayList<Clause> split_list = new ArrayList<Clause>();
		split_list.addAll(BBlist);
		split_list.add(BBstar);
		Clause bbBB = ClauseFactory.createConExp(split_list);
		
		RegExp one = Kleene.add(C,BB);
		System.out.println(one.toString());
		RegExp two = Kleene.add(one,Astar);
		System.out.println(two.toString());
		RegExp three = Kleene.add(two,bbBB);
		System.out.println(three.toString());
		*/
	}
}
