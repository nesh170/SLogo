package parser;

import java.util.ArrayList;
import java.util.List;
import exceptions.*;

public abstract class ParseNode {
	private String myName;
	private List<ParseNode> myChildren;
	protected Parser myParser;
	
	public ParseNode(String name, Parser parser){
		myName = name;
		myChildren = new ArrayList<>();
		myParser = parser;
	}
	
	public String getName(){
		return myName;
	}
	
	public int getChildCount(){
		return myChildren.size();
	}
	
	public void addChild(ParseNode newChild){
		myChildren.add(newChild);
	}

	public List<ParseNode> getChildren(){
		return myChildren;
	}
	
	public void removeChild(int index){
		myChildren.remove(index);
	}
	
	public abstract ParseNode finishProcessing() throws ParserException;
	
}
