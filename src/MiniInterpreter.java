import java.util.*;


public class MiniInterpreter {
    public static void main(String[] args) {
        String program = """
            x=5
            y=x+3*2
            print y
        """;

        Lexer lexer = new Lexer(program);
        List<Token> tokens = lexer.tokenize();//what does tokenze do
        
        Parser parser = new Parser(tokens);
        List<Stmt> statements = parser.parse();

        Interpreter interpreter = new Interpreter();
        interpreter.execute(statements);
    }
}

