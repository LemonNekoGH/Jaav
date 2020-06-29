// Generated from D:/Projects/IdeaProjects/jaav/src/main/antlr\Jaav.g4 by ANTLR 4.8

package moe.lemonneko.jaav;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link JaavParser}.
 */
public interface JaavListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link JaavParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompilationUnit(JaavParser.CompilationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link JaavParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompilationUnit(JaavParser.CompilationUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link JaavParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(JaavParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link JaavParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(JaavParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link JaavParser#equals}.
	 * @param ctx the parse tree
	 */
	void enterEquals(JaavParser.EqualsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JaavParser#equals}.
	 * @param ctx the parse tree
	 */
	void exitEquals(JaavParser.EqualsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JaavParser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(JaavParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link JaavParser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(JaavParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by {@link JaavParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(JaavParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link JaavParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(JaavParser.ValueContext ctx);
}