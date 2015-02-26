package Statements;

import java.util.List;

public class Not extends Operator{

    public Not(List<Statement> statements){
        super(statements);
    }

    @Override
    public double execute () {
        if(myStatements.get(0).execute()==0){
            return 1;
        }
        return 0;
    }
}
