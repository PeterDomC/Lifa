package regularExpression;

/*
 * Class that implements the operations of the Kleene algebra over the regular expressions.
 */
public class Kleene {
	
	public static RegExp add(RegExp a, RegExp b) {
		return new SumExp(a,b);
	}
	
	public static RegExp concat(RegExp a, RegExp b) {
		return new ConExp(a,b);
	}
	
	public static RegExp star(RegExp a) {
		return new StarExp(a);
	}
}
