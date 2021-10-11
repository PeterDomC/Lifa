// Grammer for parsing automata
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
	: '[' STATENAME ']' '(' LABELNAME ')' '[' STATENAME '];'
	| '[' STATENAME ']' '(' LABELNAME ')' '[' STATENAME '],' transitions
	;

// Initial state is one state.
initial
	: '[' STATENAME '];'
	;

// Final states can be a list of states.
finals
	: '[' STATENAME '];'
	| '[' STATENAME '],' finals
	;

// Allowed names for states.
STATENAME
	: [a-zA-Z0-9]+
	;

// Allowed names for labels.
LABELNAME
	: [a-z0-9]+
	;
    
WS
	: [\t\r\n]+ -> skip 
	;