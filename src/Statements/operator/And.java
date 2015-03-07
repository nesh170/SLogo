package Statements.operator;

import java.util.List;

import Statements.Statement;
/**
 * 
 * @author Sivaneshwaran
 *
 */
public class And extends Operator {
    
    public And (List<Statement> statements) {
        super(statements);
    }

    @Override
    public double execute () {
        if(getMyStatements().get(0).execute()!=0 && getMyStatements().get(1).execute()!=0){ 
            return 1; 
        }
        return 0;
    }
}
