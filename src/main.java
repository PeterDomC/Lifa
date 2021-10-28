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
		
		
	}
}