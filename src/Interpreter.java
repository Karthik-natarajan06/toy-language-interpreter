import java.util.*;

public class Interpreter
{
    private final Map<String, Integer> enviroment = new HashMap<>();

    public void execute(List<Stmt> statements)
    {
        for(Stmt stmt : statements)
        {
            executeStmt(stmt);
        }
    }

    private void executeStmt(Stmt stmt)
    {
        if(stmt instanceof AssignStmt)
        {
            AssignStmt assign = (AssignStmt) stmt;
            int value = evaluate(assign.value);
            enviroment.put(assign.name, value);//what is this
        }
        else if(stmt instanceof PrintStmt)
        {
            PrintStmt print = (PrintStmt) stmt;
            System.out.println(evaluate(print.expression));
        }
    }

    private int evaluate(Expr expr)
    {
        if(expr instanceof NumberExpr)
        {
            return ((NumberExpr) expr).value;
        }

        if(expr instanceof VariableExpr)
        {
            String name = ((VariableExpr) expr).name;
            if (!enviroment.containsKey(name))//what is this
            {
                throw new RuntimeException("Undefined variable: " + name);
            }
            return enviroment.get(name);//what is this and why
        }

        if(expr instanceof BinaryExpr)
        {
            BinaryExpr binary = (BinaryExpr) expr;
            int left = evaluate(binary.left);
            int right = evaluate(binary.right);

            switch(binary.operator.type)
            {
                case PLUS:
                    return left + right;
                case MINUS:
                    return left - right;
                case STAR:
                    return left*right;
                case SLASH:
                    return left/right;
                default:
                    throw new RuntimeException("Unexpected token: " + binary.operator.type);      
            }
        }
        throw new RuntimeException("Invalid expression");//how do these work
    }
}