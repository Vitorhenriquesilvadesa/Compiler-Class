package org.vtko.compilers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lexer {
    private int start;
    private int end;
    private char current;
    private int line;
    private String source;
    private List<Token> tokens;

    private static Map<String, TokenType> keywords = new HashMap<String, TokenType>();

    static {
        keywords.put("let", TokenType.Let);
        keywords.put("def", TokenType.Def);
        keywords.put("if", TokenType.If);
        keywords.put("else", TokenType.Else);
    }

    public Lexer() {
        this.tokens = new ArrayList<Token>();
    }

    public TokenStream scanTokens(String source) {
        this.line = 1;
        this.source = source;

        while (!isAtEnd()) {
            this.scanToken();
        }

        makeToken(TokenType.EndOfFile);
        return new TokenStream(this.tokens);
    }

    public void scanToken() {
        char c = this.advance();

        switch (c) {
            case ' ':
            case '\t':
            case '\r':
                start = end;
                break;

            case '\n':
                line++;
                start = end;
                break;

            case ';': {
                makeToken(TokenType.Semicolon);
                break;
            }
            case '=': {
                makeToken(TokenType.Equal);
                break;
            }

            case '{': {
                makeToken(TokenType.LeftBrace);
                break;
            }

            case '}': {
                makeToken(TokenType.RightBrace);
                break;
            }

            case '(': {
                makeToken(TokenType.LeftParen);
                break;
            }

            case ')': {
                makeToken(TokenType.RightParen);
                break;
            }

            default: {
                if (isAlpha(c)) {
                    identifier();
                } else {
                    throw new RuntimeException("Invalid character '" + c + "', at line " + line + ".");
                }
            }
        }
    }

    void identifier() {
        while (!isAtEnd() && isAlphanumeric(peek())) {
            advance();
        }

        String lexeme = this.source.substring(start, end);

        if (keywords.containsKey(lexeme)) {
            makeToken(keywords.get(lexeme));
            return;
        }

        makeToken(TokenType.Identifier, lexeme);
    }

    Token makeToken(TokenType type) {
        String lexeme = this.source.substring(this.start, this.end);
        this.start = this.current;
        return this.makeToken(lexeme, type, null);
    }

    Token makeToken(TokenType type, Object literal) {
        String lexeme = this.source.substring(this.start, this.end);
        this.start = this.current;

        return this.makeToken(lexeme, type, literal);
    }

    Token makeToken(String lexeme, TokenType type, Object literal) {
        Token token = new Token(type, lexeme, literal, line);
        this.tokens.add(token);

        this.start = this.end;

        return token;
    }

    boolean isAtEnd() {
        return this.end >= source.length();
    }

    char advance() {
        char c = this.peek();
        this.end++;

        return c;
    }

    char peek() {
        return this.source.charAt(this.end);
    }

    boolean isAlphanumeric(char c) {
        return isAlpha(c) || isDigit(c);
    }

    boolean isAlpha(char c) {
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
    }

    boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
}
