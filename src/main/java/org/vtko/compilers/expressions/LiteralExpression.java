package org.vtko.compilers.expressions;

import org.vtko.compilers.Token;

public class LiteralExpression extends Expression {
    public Token literal;

    public LiteralExpression(Token literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return "LiteralExpression{" +
                "literal=" + literal +
                '}';
    }
}
