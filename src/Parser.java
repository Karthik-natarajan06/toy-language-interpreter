import java.util.*;

class Parser
{
    private final List<Token> tokens;
    private int pos = 0;

    public Parser(List<Token> tokens)
    {
        this.tokens = tokens;
    }

    public List<Stmt> parse()
    {
        List<Stmt> statements = new ArrayList<>();
        while (!check(TokenType.EOF))
        {
            statements.add(statement());
        }
        return statements;
    }

    private Stmt statement()
    {
        if (match(TokenType.PRINT))
        {
            return new PrintStmt(expression());
        }

        Token name = consume(TokenType.IDENTIFIER, "Expected variable name");
        consume(TokenType.EQUAL, "Expected '=' after variable name");
        Expr value = expression();
        return new AssignStmt(name.lexeme,value);
    }
    
    private Expr expression()
    {
        Expr expr = term();
        while (match(TokenType.PLUS, TokenType.MINUS))
        {
            Token operator = previous();
            Expr right = term();
            expr = new BinaryExpr(expr, operator, right);
        }
        return expr;
    }

    private Expr term()
    {
        Expr expr = factor();
        while(match(TokenType.STAR, TokenType.SLASH))
        {
            Token operator = previous();
            Expr right = factor();
            expr = new BinaryExpr(expr, operator, right);
        }
        return expr;
    }

    private Expr factor()
    {
        if(match(TokenType.NUMBER))
        {
            return new NumberExpr(Integer.parseInt(previous().lexeme));
        }
        if(match(TokenType.IDENTIFIER))
        {
            return new VariableExpr(previous().lexeme);
        }
        if(match(TokenType.LEFT_PAREN))
        {
            Expr expr = expression();
            consume(TokenType.RIGHT_PAREN, "Expected ')' after expression");//whhat does this do and is the "" a string output
            return expr;
        }
        throw new RuntimeException("Expected expression");
    }

    private boolean match(TokenType... types)// what doe sthis do
    {
        for  (TokenType type : types)
        {
            if (check(type))
            {
                advance();
                return true;
            }
        }
        return false;
    }

    private Token consume(TokenType type, String message)
    {
        if (check(type))
        {
            return advance();
        }
        throw new RuntimeException("Expected " + message);
    }

    private boolean check(TokenType type)
    {
        return peek().type == type;
    }

    private Token advance()
    {
        return tokens.get(pos++);
    }

    private Token peek()
    {
        return tokens.get(pos);
    }

    private Token previous()
    {
        return tokens.get(pos - 1 );//why cant i go pos--
    }
}