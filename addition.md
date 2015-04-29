Additions to SLogo - FrontEnd
========================

##Estimation
1) How long do you think it will take you to complete this new feature?

I think it will take me about 30 minutes to complete this feature as I was familiar about the basics and the design of my SLogo front end code.

2) How many files will you need to add or update? Why?
	
I will need to add 1 file and edit 3 files. One of the file is a property file to set the width of the button so it would fit in the pane. The other 2 classes I need to edit is ViewFX to add a getShapesMap method to pass to the new view and I also need to edit the buttonPane to create a button for the new view. Lastly, I would need to add a new class which contains this new view of turtles.

##Review
1) How long did it take you to complete this new feature?

It took me about 1.5 hours to complete this new feature. I had the feature up really fast but there was bug that took a while to fix. This bug was because when you add the same instance of the shape to a new Stage, it removes it from the old one. This required me to rerender each turtle by clearing the old group in the ViewFX then readding it in. 

2) Did you get it completely right on the first try?

I did not due to the bug explained above.

3) How many files did you need to add or update? Why?

This was accurate based on my estimations, I only edited 3 files where one of them is a value on a property file. I added one extra class to generate this new view.

## Analysis

1) Was it good (or bad) as you remembered?

I remembered most of the code that I wrote and at least the basic design on how the front end worked. However, during this time, I realized there was a lot of loops and method calls could be simplified using lambda expression. This is true for the ButtonPane class. instead of having a createButton method call each time, this could be done using a forEach loop over each time.

2) What could be improved?

I realized that most of the implementation methods can be replaced with lambda expressions such as looping over repetitive methods like in the ButtonPane method which is called multiple times to create a button. However in this extension assignment, I used more of this lambda expressions.

3) What would it have been like if you were not familiar with the code at all?

It would be much more difficult. I would have had to figure out where to obtain a representation of the JavaFX or to obtain enough information to create a JavaFX representation. Aside from that, I would also need to figure out where it would be appropriate to create a new Button to render this new list of shapes. Overall, it would have taken a lot more time to implement this addition as I would have needed to spend more time familiarizing with the code.
