package regularExpression;

/*
 * Singleton.
 */
public class EmptyExp extends RegExp {
	
	private static EmptyExp emptySet;
	
	/**
	 * 
	 */
	private EmptyExp () {
		super(RegExpType.emptyExp);
	}
	
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
