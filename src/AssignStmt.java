public class AssignStmt implements Stmt {
    public String name; 
    public Expr value;

    public AssignStmt(String name, Expr value)
    {
        this.name = name;
        this.value = value;
    }
}