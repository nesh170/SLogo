package Statements.operator;

import java.util.List;

import Statements.Statement;

public class Not extends Operator{

    public Not(List<Statement> statements){
        super(statements);
    }

    @Override
    public double execute () {
        if(getMyStatements().get(0).execute()==0){
            return 1;
        }
        return 0;
    }
}
