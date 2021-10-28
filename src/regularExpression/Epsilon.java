package regularExpression;

/*
 * Singleton pattern.
 */
public class Epsilon implements RegExp {
	
	private static Epsilon eps;
	
	/**
	 * 
	 */
	private Epsilon () {}
	
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
