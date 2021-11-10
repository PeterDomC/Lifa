package regularExpressionEngine;

import java.util.HashMap;
import java.util.HashSet;

import automata.Autom;
import automata.Letter;
import automata.State;
import automata.Transition;
import regularExpressions.Atom;
import regularExpressions.Clause;
import regularExpressions.EmptyExp;
import regularExpressions.Epsilon;
import regularExpressions.RegExp;

/*
 * Class for handling automata as linear equations over regular expressions.
 * Contains basic methods that are needed to transform an automaton into a regular expression.
 */
public class RegExpEq {

	private RegExp[][] matrix;
	private RegExp[] vector;
	private HashMap<State,Integer> index;
	
	/**
	 * Constructor: it takes an automaton A and translates it into linear equations over regular expressions.
	 * The system is (X_1, ..., X_n)^T = matrix * (X_1, ..., X_n)^T + vector.
	 * @param A is the given automaton.
	 */
	public RegExpEq(Autom A) {
		
		// TODO: Call reduce and cut isolated states.
		
		// Construction of index.
		// Assign a particular order to the states of the given automaton.
		this.index = new HashMap<State,Integer>();
		
		// Index 0 is reserved for the initial state.
		State init = A.getInit();
		this.index.put(init,0);
		
		// Other states are assigned in the order dictated by the iterator, starting from 1.
		HashSet<State> states = new HashSet<State>(A.getStates());
		states.remove(init);
		int i = 1;
		for (State q : states) {
			this.index.put(q,i);
			i++;
		}
		
		// Construction of the matrix/equations.
		// Note that we need i many rows and columns so that we have one row and one column per state.
		initMatrix(i);
		initVector(i);
		
		// Go over each transition (p,a,q) and add a.X_q to the equation X_p.
		// This means we add the expression 'a' to the entry (p,q) of the matrix.
		HashSet<Transition> trans = A.getTransitions();
		for (Transition t : trans) {
			int source = index.get(t.getSource());
			int target = index.get(t.getTarget());
			Letter label = t.getLabel();
			
			add(source,target,new Atom(label));
			// If the source is final, we add epsilon to correpsonding entry of the vector.
			if (A.isFinal(t.getSource())) add(source, Epsilon.getEps());
		}
	}
	
	/**
	 * Method to initialize the matrix of regular expressions.
	 * It puts the empty expression in each entry.
	 * @param size is the given number of rows and columns.
	 */
	private void initMatrix(int size) {
		this.matrix = new RegExp[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				this.matrix[i][j] = new RegExp(EmptyExp.getEmptySet());
			}
		}
	}
	
	/**
	 * Method to initialize the vector of regular expressions.
	 * It puts the empty expression in each dimension.
	 * @param size is the given number of rows.
	 */
	private void initVector(int size) {
		this.vector = new RegExp[size];
		for (int i = 0; i < size; i++) {
			this.vector[i] = new RegExp(EmptyExp.getEmptySet());
		}
	}
	
	/**
	 * Add a given regular expression to the entry of the matrix described by row and column.
	 * @param row, col are indexes describing the precise entry.
	 * @param summand is the regular expression that is added.
	 * 
	 * NOTE: It is assumed that row and col are within the borders of matrix
	 * and therefore describe a value of it.
	 */
	private void add(int row, int col, RegExp summand) {
		matrix[row][col] = Kleene.add(matrix[row][col],summand);
	}
	
	/**
	 * Additional add method for improved handling.
	 */
	private void add(int row, int col, Clause summand) {
		add(row,col,new RegExp(summand));
	}
	
	/**
	 * Add a given regular expression to the entry of the vector described by row.
	 * @param row is the index of the chosen dimension.
	 * @param summand is the regular expression that is added.
	 * 
	 * NOTE: It is assumed that row is within the borders of vector
	 * and therefore describe a proper dimension of it.
	 */
	private void add(int row, RegExp summand) {
		vector[row] = Kleene.add(vector[row],summand);
	}
	
	/**
	 * Additional add method for improved handling.
	 */
	private void add(int row, Clause summand) {
		add(row,new RegExp(summand));
	}
	
	/**
	 * Method that applies Arden's rule to an equation (a row of matrix and vector).
	 * @param row identifies the equation.
	 * 
	 * NOTE: Arden's rule removes self references by turning L = U.L u V into L = U*.V. 
	 */
	public void arden (int row) {
		//TODO
	}
	
	public void plugIn (int inrow, int outrow) {
		// TODO: plug inrow into outrow.
	}
	
	/**
	 * Method that multiplies an equation (a row of matrix and vector) 
	 * with a given regular expression from the left.
	 * @param factor is the given regular expression.
	 * @param row determines which row is multiplied.
	 * 
	 * NOTE: It is assumed that row is within the borders of vector and matrix
	 * and therefore describe a proper row of both.
	 */
	private void scalarMult(RegExp factor, int row) {
		
		// First multiply the row of the vector.
		vector[row] = Kleene.concat(factor,vector[row]);
		
		// Multiply each entry of the corresponding row of the matrix.
		int n = matrix.length;
		for (int i = 0; i < n; i++) {
			matrix[row][i] = Kleene.concat(factor,matrix[row][i]);
		}
	}
	
	/**
	 * DEBUG method that prints the matrix.
	 */
	public void printMatrix() {
		int n = matrix.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(matrix[i][j].toString() + " ");
			}
			System.out.println("");
		}
	}
	
	/**
	 * DEBUG method that prints the vector.
	 */
	public void printVector() {
		int n = vector.length;
		for (int i = 0; i < n; i++) {
			System.out.println(vector[i].toString());
		}
	}
}
