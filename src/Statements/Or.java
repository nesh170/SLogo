package Statements;

import java.util.List;

public class Or extends Operator {
    
    public Or (List<Statement> statements) {
        super(statements);
    }

    @Override
    public double execute () {
        if(myStatements.get(0).execute()!=0 || myStatements.get(1).execute()!=0){ 
            return 1; 
        }
        return 0;
    }
}