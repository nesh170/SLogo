package Statements.operator;

import java.util.List;

import Statements.Statement;

public class And extends Operator {
    
    public And (List<Statement> statements) {
        super(statements);
    }

    @Override
    public double execute () {
        if(myStatements.get(0).execute()!=0 && myStatements.get(1).execute()!=0){ 
            return 1; 
        }
        return 0;
    }
}
