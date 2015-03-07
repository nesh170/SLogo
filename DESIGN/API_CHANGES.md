API_CHANGES
===========

View
====
* drawShape(double X,double Y,int ID, String penColor,double strokeWidth,String strokeType)

This is identical to drawTurtle in the previous program design except it takes in more parameters as the view does not store any information about the turtle but just it's shape.


* printError(String message)

This method prints in the terminal to red. This was needed to indicate to to the user whether there was an error or a regular message.


* addVariable(String variableName, Double value)

* addMethodVariable(String methodName)

* visibleShape(boolean hideOrShow, int ID)

* visualActiveShape(boolean activeOrInactive, int ID)

* getRoot()

* stamp(int ID)

* changeShape(String shapeType, int ID)

* updateColorListView(List<String> colorList)

* clearStamps()

* setUpDialogBox(Pen pen, int ID, List<String> colorList)

Model
=====

* getColors()

* getShapes()

* initializeDefaultShapes()

* getTurtleManager()

* getVariableManager()

* getMethodManager()

* processCommand(Program program)

* updateVariable(String name, double newValue)

* sendInfoForDialog(int ID)