##Possible design changes to Slogo 

After discussing SLogo with my cell society group, the following were design changes that are worth consideration:

 - Another group is creating "state objects" that contain all possible properties of the program and passing this to each command so that the constructor for each command is common.  
 - Another group is giving all of the commands to the turtle, which then executes them on itself.  This was done to avoid different parameters for each command, which my group will probably have to deal with using a large if tree.  However, in their approach, the turtle is executing commands that don't affect it, such as math operations.  