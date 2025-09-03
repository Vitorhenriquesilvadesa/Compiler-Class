package org.vtko.compilers.expressions;

import org.vtko.compilers.Token;

public class UnaryExpression extends Expression {
    public Token operator;
    public Expression expression;

    public UnaryExpression(Token operator, Expression expression) {
        this.operator = operator;
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "UnaryExpression{" +
                "operator=" + operator +
                ", expression=" + expression +
                '}';
    }
}
