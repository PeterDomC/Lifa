package regularExpression;

/*
 * Singleton pattern.
 */
public class Epsilon extends RegExp {
	
	private static Epsilon eps;
	
	/**
	 * 
	 */
	private Epsilon () {
		super(RegExpType.epsilon);
	}
	
	/**
	 * 
	 */
	public static Epsilon getEps() {
		if (eps == null) {
			eps = new Epsilon();
		}
		
		return eps;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "@eps";
	}
}
