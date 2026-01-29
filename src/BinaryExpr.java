public class BinaryExpr implements Expr {
    public Expr left;
    public Token operator;
    public Expr right;

    public BinaryExpr(Expr left, Token operator, Expr right)
    {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }
}