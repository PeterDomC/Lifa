// Grammar for parsing automata
grammar Automaton;

// An automaton is a list of transitions (at least one), 
// followed by an optional initial state, 
// optional final states, and optional additional states.
automaton
	: 'trans' transitions ('init' initial)? ('final' finals)? ('add' finals)?
	;

// A transition has a source state (surrounded by []), 
// a label (surrounded by ()), 
// and a target (surr. by []).
// The following models a list of transitions.
transitions
	: '[' NAME ']' '(' NAME ')' '[' NAME '];'
	| '[' NAME ']' '(' NAME ')' '[' NAME '],' transitions
	;

// One initial state.
initial
	: '[' NAME '];'
	;

// Final states can be a list of states.
finals
	: '[' NAME '];'
	| '[' NAME '],' finals
	;

// Allowed names for states and labels.
NAME
	: [a-zA-Z0-9]+
	;
 
WS
	: [ \t\r\n]+ -> skip 
	;