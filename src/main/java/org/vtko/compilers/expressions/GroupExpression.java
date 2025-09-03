package org.vtko.compilers.expressions;

public class GroupExpression extends Expression {
    public Expression expression;

    public GroupExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "GroupExpression{" +
                "expression=" + expression +
                '}';
    }
}
