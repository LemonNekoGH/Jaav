// Generated from D:/Projects/IdeaProjects/jaav/src/main/antlr\Jaav.g4 by ANTLR 4.8

package moe.lemonneko.jaav;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link JaavParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface JaavVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link JaavParser#compilationUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompilationUnit(JaavParser.CompilationUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link JaavParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(JaavParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link JaavParser#equals}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquals(JaavParser.EqualsContext ctx);
	/**
	 * Visit a parse tree produced by {@link JaavParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(JaavParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by {@link JaavParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(JaavParser.ValueContext ctx);
}