package regularExpressionEngine;

import automata.Autom;
import regularExpressions.Clause;

/*
 * Class for handling automata as linear equations over regular expressions.
 * Contains basic methods that are needed to transform an automaton into a regular expression.
 */
public class RegExpMatrix {

	private Clause[][] entries;
	
	public RegExpMatrix(Autom A) {
		//TODO:
		// Transform Automaton to Postmap and eed matrix with corresponding entries.
	}
	
	public void arden (int row) {
		// TODO: apply arden's lemma in the given row.
	}
	
	public void plugIn (int inrow, int outrow) {
		// TODO: plug inrow into outrow.
	}
}
