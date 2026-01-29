public class Token{
    TokenType type; 
    String lexeme;//lexeme means plain english word

    Token(TokenType type, String lexeme)
    {
        this.type = type;
        this.lexeme = lexeme;
    }
}