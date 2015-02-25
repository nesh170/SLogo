Possible Design Changes
=======================

* Kaighn
* Anika
* Siva

### Updating fd 50

#####Current Design
* executeCommand is called in my Controller to send the String to model
* processCommand is called in the Model by the controller, passing it the string of code to be executed.  
* parse from the model internal API is called by processCommand in the Model to parse the string into SLogo commands
* executeProgram (from model internal API) is called by processCommand once parse has completed.
* drawTurtle() which is a public View API is called by the model/backend (specifically the forward command object). It then moves the turtle by 50px and creates a 50px line

#####Updated Design Notes
* add errorCheckers into processCommand.



