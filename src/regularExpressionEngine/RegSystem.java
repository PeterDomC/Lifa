package regularExpressionEngine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

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
 * Class for handling automata as linear equation systems over regular expressions.
 * Contains basic methods that are needed to transform an automaton into a regular expression.
 */
public class RegSystem {

	private RegExp[][] matrix;
	private RegExp[] vector;
	private HashMap<State,Integer> index;
	
	/**
	 * Constructor: it takes an automaton A and translates it into linear equations over regular expressions.
	 * The system is (X_1, ..., X_n)^T = matrix * (X_1, ..., X_n)^T + vector.
	 * @param A is the given automaton.
	 */
	public RegSystem(Autom A) {
		
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
	 * TODO
	 * Method that returns the regular expression.
	 * @return
	 * 
	 * NOTE: This method performs a variable elimination step by step.
	 */
	public RegExp solve() {

		int n = matrix.length;
		System.out.println("The equation system is given by:");
		print();
		
		// Variable elimination.
		// Begin with the last variable X_{n-1} and continue with X_{n-2} and so on.
		for (int i = n - 1; i >= 0; i--) {
			
			// Apply Arden's lemma to the equation for X_i.
			System.out.println("Apply Arden's lemma on X_" + getStateByIndex(i).getName());
			arden(i);
			
			// Plug the i-th equation in all equations of X_j that are smaller than i.
			for (int j = i-1; j >= 0; j--) {
				plugIn(i,j);
				System.out.println("Plug X_" + getStateByIndex(i).getName() + " into X_" + getStateByIndex(j).getName());
			}
			
			print();
		}
		
		// The regular expression for the automaton is the equation of X_0.
		// It is stored in the corresponding entry of the vector.
		return vector[0];
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
	 * In our case, this means that X_q = R_q.X_q + (R_1.X_1 + ... + R_n.X_n) can be turned
	 * into the equation X_q = R_q*.(R_1.X_1 + ... + R_n.X_n) without reference to X_q.
	 * This amounts to setting the value for X_q to the empty expression and then 
	 * multiplying the equation by R_q* from the left.
	 */
	private void arden (int row) {
		// Get value for X_row = U.
		RegExp U = matrix[row][row];
		
		// Set new value to the empty expression - removes the self reference.
		matrix[row][row] = new RegExp(EmptyExp.getEmptySet());
		
		// Multiply other entries in the equation by U*.
		scalarMult(Kleene.star(U),row);
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
	 * Plug row j into the row i.
	 * This means if X_i = R_0 + R_1.X_1 + ... + R_n.X_n and X_j = S_0 + S_1.X_1 + ... + S_n.X_n,
	 * plugging j into i yields the equation: X_i = (R_0 + R_j.S_0) + (R_1 + R_j.S_1).X_1 + ... + (R_n + R_j.S_n).X_n.
	 * @param j is the given row that we plug into
	 * @param i, the outer row.
	 * 
	 * NOTE: The method computes the new factors R_l + R_j.S_l for X_l in row i.
	 * NOTE: It is assumed that i and j respect the borders of vector and matrix
	 * and therefore describe proper rows of both.
	 */
	private void plugIn (int j, int i) {
		
		// The factor that we need throughout the computation: R_j.
		RegExp factor = matrix[i][j];
		
		// First compute the new expression in the i-th entry of the vector.
		vector[i] = Kleene.add(vector[i], Kleene.concat(factor, vector[j]));
		
		// Now compute the new expressions in the i-th row of the matrix.
		int n = matrix.length;
		for (int l = 0; l < n; l++) {
			// New factor in the i-th rowto variable X_l.
			if (l != j) matrix[i][l] = Kleene.add(matrix[i][l], Kleene.concat(factor, matrix[j][l]));
		}
		
		// The reference in the i-th row to the j-th variable disappears.
		matrix[i][j] = new RegExp(EmptyExp.getEmptySet());
	}
	
	/**
	 * Method that prints the equation system
	 * TODO: String builder.
	 */
	public void print() {
		int n = matrix.length;
		for (int i = 0; i < n; i++) {
			System.out.print("X_" + getStateByIndex(i).getName() + " = ");
			
			for (int j = 0; j < n; j++) {
				System.out.print("(" + matrix[i][j].toString() + ")" + ".X_" + getStateByIndex(j).getName() + " + ");
			}
			
			System.out.print(vector[i] + "\n");
		}
		
		System.out.print("\n");
	}
	
	/**
	 * Method that finds the state for a given integer in the index map.
	 * @param i is the given integer.
	 * 
	 * NOTE: It is assumed that i encodes a proper index of a state.
	 * Otherwise, the method returns null.
	 */
	private State getStateByIndex(int i) {
		for (Entry<State,Integer> entry : index.entrySet()) {
			if (entry.getValue().equals(i)) return entry.getKey();
	    }
		
		return null;
	}
}
