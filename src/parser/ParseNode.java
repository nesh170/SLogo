package parser;

import java.util.ArrayList;
import java.util.List;

public class ParseNode {
	private String myName;
	private List<ParseNode> myChildren;
	
	public ParseNode(String name){
		myName = name;
		myChildren = new ArrayList<>();
	}
	
	public String getName(){
		return myName;
	}
	
	public int getNumChildren(){
		return myChildren.size();
	}
	
	public void addChild(ParseNode newChild){
		myChildren.add(newChild);
	}

	public List<ParseNode> getChildren(){
		return myChildren;
	}
}
