package regularExpression;

/*
 * Class that implements the operations of the Kleene algebra over the regular expressions.
 */
public class Kleene {
	
	public static Atom add(Atom a, Atom b) {
		System.out.println("Atom method");
		return a;
	}
	
	public static RegExp add(RegExp a, RegExp b) {
		System.out.println("RegExp method");
		return b;
	}

	
	
}
