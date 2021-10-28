package regularExpression;

/*
 * Singleton.
 */
public class EmptyExp implements RegExp {

	private static EmptyExp emptySet;
	
	/**
	 * 
	 */
	private EmptyExp () {}
	
	/**
	 * 
	 */
	public static EmptyExp getEmptySet() {
		if (emptySet == null) {
			emptySet = new EmptyExp();
		}
		
		return emptySet;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "{}";
	}
	
	
}
