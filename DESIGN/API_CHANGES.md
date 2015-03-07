API_CHANGES
===========

View
====
* drawShape(double X,double Y,int ID, String penColor,double strokeWidth,String strokeType)

This is identical to drawTurtle in the previous program design except it takes in more parameters as the view does not store any information about the turtle but just it's shape.


* printError(String message)

This method prints in the terminal to red. This was needed to indicate to to the user whether there was an error or a regular message.


* addVariable(String variableName, Double value)

This method adds the variable to the variable table. It was needed because there wasn't a way for model to indicate to the view to add it.

* addMethodVariable(String methodName)

This method adds the variable to the method variable table. It was needed because there wasn't a way for model to indicate to the view to add it.

* visibleShape(boolean hideOrShow, int ID)

This method makes the shape hide and show. It was needed for the extension on making the turtle hide and show.

* visualActiveShape(boolean activeOrInactive, int ID)

This method was used for the extension to set the turtle's glow to indicate whether it is active or not

* stamp(int ID)

This method was used in the extension on to stamp the turtle.

* changeShape(String shapeType, int ID)

This method was in the extension as it was needed to change shapes.

* updateColorListView(List<String> colorList)

This method was in the extension for displaying the color Palette

* clearStamps()

This method was in needed in the extension to clear all the stamps.

* setUpDialogBox(Pen pen, int ID, List<String> colorList)

This method is called by the model when the user requests for a dialog box.

Model
=====

* getColors()

This method gets the colors to be sent through the controller to the view. It was needed for the extension

* getShapes()

This method gets the shapes to be sent through the controller to the view. It was needed for the extension

* initializeDefaultShapes()

With the inclusion of multiple shapes, the user could change the shape of the object. Hence a defaultshape method was needed to initialize the shape to a default shape

* getTurtleManager()

This methods was needed to get the turtle manager and pass it to the program builder to construct statements to operate on the statements. The program builder was part of the controller.

* getVariableManager()

This method was needed so the program builder could add to the variable manager and get the variables.

* getMethodManager()

This method was needed so the program builder could have access to the mehthods generated to add into them or use them

* processCommand(Program program)

Takes in a program object and then executes it. Previously the model was creating the program but now since that is done in the controller, the model just receives a program

* updateVariable(String name, double newValue)

The updateVariable method is called when the user tries to change the variable from the view. It then adds that back into the VariableManager

* sendInfoForDialog(int ID)

This method obtains the info for the turtle and sends all the information to a similar method in view to be displayed. This method was needed in the third sprint to display all the values

* clearTurtles()

This method clears the active turtle list and the turtle hashmap so the screen is cleared when the XML file is loaded. This method was needed in the third sprint

* addTurtle(double X, double Y, int ID)

This method is used by the XMLLoader to add turtle to list. It allows the XML to specify the X and Y location.

* getTurtleMap()

This method is used to get an unmodifiable map which is used to be loaded on the XML Writer where the turle data can be accessed to write to the XML File. Used for the third sprint in generating the XML file.