package automatonParser;

// Generated from Automaton.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AutomatonParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, STATENAME=6, LABELNAME=7, WS=8;
	public static final int
		RULE_automaton = 0, RULE_transitions = 1, RULE_initial = 2, RULE_finals = 3;
	private static String[] makeRuleNames() {
		return new String[] {
			"automaton", "transitions", "initial", "finals"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'trans'", "'init'", "'final'", "';'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, "STATENAME", "LABELNAME", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Automaton.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public AutomatonParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class AutomatonContext extends ParserRuleContext {
		public TransitionsContext transitions() {
			return getRuleContext(TransitionsContext.class,0);
		}
		public InitialContext initial() {
			return getRuleContext(InitialContext.class,0);
		}
		public FinalsContext finals() {
			return getRuleContext(FinalsContext.class,0);
		}
		public AutomatonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_automaton; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatonListener ) ((AutomatonListener)listener).enterAutomaton(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatonListener ) ((AutomatonListener)listener).exitAutomaton(this);
		}
	}

	public final AutomatonContext automaton() throws RecognitionException {
		AutomatonContext _localctx = new AutomatonContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_automaton);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(8);
			match(T__0);
			setState(9);
			transitions();
			setState(12);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(10);
				match(T__1);
				setState(11);
				initial();
				}
			}

			setState(16);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(14);
				match(T__2);
				setState(15);
				finals();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TransitionsContext extends ParserRuleContext {
		public List<TerminalNode> STATENAME() { return getTokens(AutomatonParser.STATENAME); }
		public TerminalNode STATENAME(int i) {
			return getToken(AutomatonParser.STATENAME, i);
		}
		public TerminalNode LABELNAME() { return getToken(AutomatonParser.LABELNAME, 0); }
		public TransitionsContext transitions() {
			return getRuleContext(TransitionsContext.class,0);
		}
		public TransitionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_transitions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatonListener ) ((AutomatonListener)listener).enterTransitions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatonListener ) ((AutomatonListener)listener).exitTransitions(this);
		}
	}

	public final TransitionsContext transitions() throws RecognitionException {
		TransitionsContext _localctx = new TransitionsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_transitions);
		try {
			setState(27);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(18);
				match(STATENAME);
				setState(19);
				match(LABELNAME);
				setState(20);
				match(STATENAME);
				setState(21);
				match(T__3);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(22);
				match(STATENAME);
				setState(23);
				match(LABELNAME);
				setState(24);
				match(STATENAME);
				setState(25);
				match(T__4);
				setState(26);
				transitions();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InitialContext extends ParserRuleContext {
		public TerminalNode STATENAME() { return getToken(AutomatonParser.STATENAME, 0); }
		public InitialContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initial; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatonListener ) ((AutomatonListener)listener).enterInitial(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatonListener ) ((AutomatonListener)listener).exitInitial(this);
		}
	}

	public final InitialContext initial() throws RecognitionException {
		InitialContext _localctx = new InitialContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_initial);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			match(STATENAME);
			setState(30);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FinalsContext extends ParserRuleContext {
		public TerminalNode STATENAME() { return getToken(AutomatonParser.STATENAME, 0); }
		public FinalsContext finals() {
			return getRuleContext(FinalsContext.class,0);
		}
		public FinalsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_finals; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatonListener ) ((AutomatonListener)listener).enterFinals(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomatonListener ) ((AutomatonListener)listener).exitFinals(this);
		}
	}

	public final FinalsContext finals() throws RecognitionException {
		FinalsContext _localctx = new FinalsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_finals);
		try {
			setState(37);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(32);
				match(STATENAME);
				setState(33);
				match(T__3);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(34);
				match(STATENAME);
				setState(35);
				match(T__4);
				setState(36);
				finals();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\n*\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\3\2\3\2\3\2\3\2\5\2\17\n\2\3\2\3\2\5\2\23\n\2\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\36\n\3\3\4\3\4\3\4\3\5\3\5\3\5\3\5"+
		"\3\5\5\5(\n\5\3\5\2\2\6\2\4\6\b\2\2\2)\2\n\3\2\2\2\4\35\3\2\2\2\6\37\3"+
		"\2\2\2\b\'\3\2\2\2\n\13\7\3\2\2\13\16\5\4\3\2\f\r\7\4\2\2\r\17\5\6\4\2"+
		"\16\f\3\2\2\2\16\17\3\2\2\2\17\22\3\2\2\2\20\21\7\5\2\2\21\23\5\b\5\2"+
		"\22\20\3\2\2\2\22\23\3\2\2\2\23\3\3\2\2\2\24\25\7\b\2\2\25\26\7\t\2\2"+
		"\26\27\7\b\2\2\27\36\7\6\2\2\30\31\7\b\2\2\31\32\7\t\2\2\32\33\7\b\2\2"+
		"\33\34\7\7\2\2\34\36\5\4\3\2\35\24\3\2\2\2\35\30\3\2\2\2\36\5\3\2\2\2"+
		"\37 \7\b\2\2 !\7\6\2\2!\7\3\2\2\2\"#\7\b\2\2#(\7\6\2\2$%\7\b\2\2%&\7\7"+
		"\2\2&(\5\b\5\2\'\"\3\2\2\2\'$\3\2\2\2(\t\3\2\2\2\6\16\22\35\'";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}