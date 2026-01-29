public class PrintStmt implements Stmt {
    public Expr expression;

    public PrintStmt(Expr expression)
    {
        this.expression = expression;
    }
}