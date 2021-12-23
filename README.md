# SpaceInvaders

*What did the team create?* The team created a version of Tomohiro Nishikado's Space Invaders arcade videogame. 

:books: Course Name: Fundamentals of Computer Science 2

Group Memebers: Chia Hao Hsu Tai (myself), and Robert Yoo.
 
### :bulb: Summary of Technical Design:

The design focuses on the use of the self-defined data structure called IList, recursion through ILists, and object functions. The project defines a set of recursive algorithms that effectively traverse an IList. Moreover, the project utilizes these alghorithms in conjuction with object functions in order to achieve the different game mechanics. The project contains tests for all game machanics as well as the recursive algoritms. The project uses Northesterns tester libary in order to tests the different aspects of the game. The project runs the game using Java's funworld and image libraries. 

### :facepunch: Summary of the Game:

The spaceship is located at the bottom of the scene and will move left and right only. The spaceship will also have the ability to fire bullets in order to hit invaders. Bullets are fired when the space bar is pressed by the user. Spaceship bullets move vertically (bottom to top) at a steady speed. The spaceship cannot go beyond the left or right border. If the spaceship reaches the edge of the scene and the user presses the arrow key in the direction of the edge, the spaceship will not move. If the user presses the arrow key in the opposite direction the spaceship can move in that direction. Invaders are arranged in rows, each has 9 invaders side by side. Invaders do not move at all. At every clock tick a number of invaders get to fire bullets. The choice of which invader gets to fire on each tick is random and an invader cannot fire more than one bullet per tick. When a spaceship bullet hits an invader, then the invader is destroyed. The game ends when either all invaders have been eliminated, or, when the spaceship has been hit by an invader bullet.

#### What makes our version different?

- The space ship moves in a steady speed in a direction. The direction is either left or right. The ship changes direction when the user clicks on the left or right arrow key.
- At any given point in time in the game there can only be at most 3 spaceship bullets in flight.
- At any given point in time in the game there can only be at most 10 invader bullets in flight.

### :space_invader: How to play: 

To play please do the following:
- Download all the files in the repository
- Add the java files to your project folder
- Add the .jar as extrenal libraries to the project's classpath
- In your run configurations:
  - Under main, for your main class write: tester.Main
  - Under arguments, for your program arguments write: RunSpaceInvaders
- If you want to run all the tests, write the following for your program arguments:
  - RunSpaceInvaders
  - UtilsTests
  - ListsTests
  - GameTests
  - GamePiecesTests

#### :smile: Sneak Peak into the Game:
![Screen Shot 2021-12-23 at 11 30 12 AM](https://user-images.githubusercontent.com/89400862/147268138-93e19aa2-3a0f-4af1-a0ef-995124be1a2a.png)
