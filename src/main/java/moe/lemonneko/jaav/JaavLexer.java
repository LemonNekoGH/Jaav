// Generated from D:/Projects/IdeaProjects/jaav/src/main/antlr\Jaav.g4 by ANTLR 4.8

package moe.lemonneko.jaav;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class JaavLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		VAR=1, VAR_DEF_INFIX=2, EQUALS=3, PRINT=4, ID=5, NUMBER=6, STRING=7, WS=8;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"VAR", "VAR_DEF_INFIX", "EQUALS", "PRINT", "ID", "NUMBER", "STRING", 
			"WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'\u58F0\u660E\u540D\u4E3A'", "'\u7684\u53D8\u91CF'", "'\u8D4B\u503C\u4E3A'", 
			"'\u5411\u63A7\u5236\u53F0\u8F93\u51FA'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "VAR", "VAR_DEF_INFIX", "EQUALS", "PRINT", "ID", "NUMBER", "STRING", 
			"WS"
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


	public JaavLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Jaav.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\nE\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\3\2"+
		"\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3"+
		"\6\7\6*\n\6\f\6\16\6-\13\6\3\7\3\7\7\7\61\n\7\f\7\16\7\64\13\7\3\b\3\b"+
		"\7\b8\n\b\f\b\16\b;\13\b\3\b\3\b\3\t\6\t@\n\t\r\t\16\tA\3\t\3\t\39\2\n"+
		"\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\3\2\7\4\2C\\c|\5\2\62;C\\c|\3\2\63"+
		";\3\2\62;\5\2\13\f\17\17\"\"\2H\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2"+
		"\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\3\23\3\2"+
		"\2\2\5\30\3\2\2\2\7\34\3\2\2\2\t \3\2\2\2\13\'\3\2\2\2\r.\3\2\2\2\17\65"+
		"\3\2\2\2\21?\3\2\2\2\23\24\7\u58f2\2\2\24\25\7\u6610\2\2\25\26\7\u540f"+
		"\2\2\26\27\7\u4e3c\2\2\27\4\3\2\2\2\30\31\7\u7686\2\2\31\32\7\u53da\2"+
		"\2\32\33\7\u91d1\2\2\33\6\3\2\2\2\34\35\7\u8d4d\2\2\35\36\7\u503e\2\2"+
		"\36\37\7\u4e3c\2\2\37\b\3\2\2\2 !\7\u5413\2\2!\"\7\u63a9\2\2\"#\7\u5238"+
		"\2\2#$\7\u53f2\2\2$%\7\u8f95\2\2%&\7\u51fc\2\2&\n\3\2\2\2\'+\t\2\2\2("+
		"*\t\3\2\2)(\3\2\2\2*-\3\2\2\2+)\3\2\2\2+,\3\2\2\2,\f\3\2\2\2-+\3\2\2\2"+
		".\62\t\4\2\2/\61\t\5\2\2\60/\3\2\2\2\61\64\3\2\2\2\62\60\3\2\2\2\62\63"+
		"\3\2\2\2\63\16\3\2\2\2\64\62\3\2\2\2\659\7$\2\2\668\13\2\2\2\67\66\3\2"+
		"\2\28;\3\2\2\29:\3\2\2\29\67\3\2\2\2:<\3\2\2\2;9\3\2\2\2<=\7$\2\2=\20"+
		"\3\2\2\2>@\t\6\2\2?>\3\2\2\2@A\3\2\2\2A?\3\2\2\2AB\3\2\2\2BC\3\2\2\2C"+
		"D\b\t\2\2D\22\3\2\2\2\7\2+\629A\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}