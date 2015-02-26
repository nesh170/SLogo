package Statements;

import java.util.List;

public class NotEqual extends Operator{
    
    public NotEqual(List<Statement> statements) {
        super(statements);
    }

    @Override
    public double execute() {
        if(myStatements.get(0).execute() != myStatements.get(1).execute()){
                return 1;
        }
        return 0;
}

}
