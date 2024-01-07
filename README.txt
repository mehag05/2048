=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 1200 Game Project README
PennKey: mehag05
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1.2D arrays: stores the board tiles at any given time, calls on methods to modify the
   array when up, down, right, or left keys pressed.

  2.Collections: Using a LinkedList to store all the moves being implemented
  in order to implement an undo button. The linked list stores every new move as the head of the list.
  The undo button removes the move just done and sets the board to the previous state (given by the list).

  3.JUnit testable component: Made a reset button and created a display for the board using Swing.

  4.FileIO: creating a save progress method that stores moves and allows the user to reload a saved game.

===============================
=: File Structure Screenshot :=
===============================
- Include a screenshot of your project's file structure. This should include
  all of the files in your project, and the folders they are in. You can
  upload this screenshot in your homework submission to gradescope, named 
  "file_structure.png".

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
   I created a tile class that is an object that contains the value of each tile in the board.
   The board is a 2D array of tiles.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  The hardest part was implementing the up, down, right, left arrow methods because of how many
  technicalities go into the moves and how many cases you have to be able to work in. I also found
  the reload method extremely difficult because I had to reread every four lines in my saved progress
  txt file.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  I would refactor the up down right left methods, because they all contain really similar code but
  with differences in indices and loop bounds. I also don't really think the Tile class is necessary,
  I could've just used a 2D int array, that might have made a lot of my methods easier to write
  because I wouldn't have had to worry about tiles being null.


========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.
I used the 2048 game website to understand how the game worked to implement my methods.