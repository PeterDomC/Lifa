// Grammar for parsing automata
grammar Automaton;

// An automaton is a list of transitions (at least one), 
// followed by an optional initial state and optional final states
automaton
	: 'trans' transitions ('init' initial)? ('final' finals)?
	;

// A transition has a source state (surrounded by []), 
// a label (surrounded by ()), 
// and a target (surr. by []).
// The following models a list of transitions.
transitions
	: STATENAME LABELNAME STATENAME ';'
	| STATENAME LABELNAME STATENAME ',' transitions
	;

// One initial state.
initial
	: STATENAME ';'
	;

// Final states can be a list of states.
finals
	: STATENAME ';'
	| STATENAME ',' finals
	;

// Allowed names for states.
STATENAME
	: '[' [a-zA-Z0-9]+ ']'
	;

// Allowed names for labels.
LABELNAME
	: '(' ([a-z] | [0-9])+ ')'
	; 
 
WS
	: [ \t\r\n]+ -> skip 
	;
