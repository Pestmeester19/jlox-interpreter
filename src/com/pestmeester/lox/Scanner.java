package com.pestmeester.lox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scanner {
    private final String source;
    private final List<Token> tokens = new ArrayList<>();
    private int start = 0;
    private int current = 0;
    private int line = 1;

    Scanner(String source) {
        this.source = source;
    }

    List<Token> scanTokens() {
        while(!isAtEnd()) {
            start = current;
            scanTokens();
        }
        tokens.add(new Token(TokenType.EOF, "", null, line));
        return tokens;
    }

    private void scanToken() {
        char c = advance();
        switch(c) {
            case '(': addToken(TokenType.LEFT_PAREN);
            break;
            case ')': addToken(TokenType.RIGHT_PAREN);
            break;
            case '{': addToken(TokenType.LEFT_BRACE);
            break;
            case '}': addToken(TokenType.RIGHT_BRACE);
            break;
            case ',': addToken(TokenType.COMMA);
            break;
            case '.': addToken(TokenType.DOT);
            break;
            case '-': addToken(TokenType.MINUS);
            break;
            case '+': addToken(TokenType.PLUS);
            break;
            case ';': addToken(TokenType.SEMICOLON);
            break;
            case '*': addToken(TokenType.STAR);
            break;
        }
    }

    private boolean isAtEnd() {
        return current >= source.length();
    }

    private char advance() {
        current++;
        return source.charAt(current - 1);
    }

    private void addToken(TokenType type) {
        addToken(type, null);
    }

    private void addToken(TokenType type, Object literal) {
        String text = source.substring(start, current);
        tokens.add(new Token(type, text, literal, line));
    }

}
