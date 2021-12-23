import javalib.worldimages.*;   
import javalib.funworld.*;      
import java.awt.Color;
//import java.util.Random;    

// to represent a Cartesian Coordinate in XY-Plane
class CartPt implements IConstants {
  int x;
  int y;

  CartPt(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /* TEMPLATE
   * Fields:
   * ... this.x ... - int
   * ... this.y ... - int
   * Methods:
   * ... this.sameAs(CartPt) ...   - boolean
   * ... this.nextInRow() ...      - CartPt
   * ... this.nextRowUp() ...      - CartPt  
   * ... this.inRange(CartPt) ...  - boolean
   * ... this.inRange2(CartPt) ... - boolean 
   */

  // determines if the given CartPts are the same
  boolean sameAs(CartPt t) {
    return this.x == t.x && this.y == t.y;
  }

  // Makes a Cartesian point that comes next in the sequence
  CartPt nextInRow() {
    return new CartPt(this.x + 40, this.y);
  }

  // Moves the Cartesian Point to the next column
  CartPt nextRowUp() {
    return new CartPt(120, this.y + 30);
  }

  // Checks if the CartPoint is within range of the 
  // IGamePiece (Invader or spaceship)
  boolean inRange(CartPt t) { 
    return t.x + 5 > this.x - 10 && t.x - 5 < this.x + 10
        && t.y + 5 >= this.y - 10 && t.y - 5 <= this.y + 10;
  }

  // Checks if the CartPoint is within range of the 
  // IGamePiece (Bullet)
  boolean inRange2(CartPt t) { 
    return this.x + 5 > t.x - 10 && this.x - 5 < t.x + 10
        && this.y - 5 <= t.y + 10;
  }
}

interface IGamePiece extends IConstants {

  // draw this IGamePiece onto the given scene
  WorldScene draw(WorldScene acc);

  // move the IGamePiece 
  IGamePiece move();

  // checks if the IGamePiece reached the lower limit of window
  boolean lowerBound();

  // checks if the IGamePiece reached the upper limit of window
  boolean upperBound();

  // checks if the IGamePiece reached the left bound limit of window 
  boolean leftBound();

  //checks if IGamePiece reached the right limit of window
  boolean rightBound();

  // gets the position of the IGamePiece
  CartPt getPos();
}

// Abstract class from IGamePiece
abstract class AGamePiece implements IGamePiece {
  CartPt pos;
  Color color;

  AGamePiece(CartPt pos, Color color) {
    this.pos = pos;
    this.color = color;
  }

  // draws the IGamePiece onto the scene
  public abstract WorldScene draw(WorldScene acc); 

  //checks if the IGamePiece has reached the lower limit of window
  public boolean lowerBound() {
    return false;
  }

  //checks if the IGamePiece reached the upper limit of window
  public boolean upperBound() {
    return false;
  }

  // checks if IGamePiece reached the left limit of window
  public boolean leftBound() {
    return false;
  }

  //checks if IGamePiece reached the right limit of window
  public boolean rightBound() {
    return false;
  }

  // moves the IGamePiece
  public IGamePiece move() {
    return this;
  }

  // gets the position of the IGamePiece
  public CartPt getPos() {
    return this.pos;
  }
}

//a class to represent a spaceship
class Spaceship extends AGamePiece {
  String direction;

  // Constructors
  Spaceship(CartPt pos, Color color, String direction) {
    super(pos, color);
    this.direction = new Utils().sDir(direction);
  }

  Spaceship(CartPt pos, String direction) {
    this(pos, SPACESHIP_COLOR, direction);
  }

  Spaceship() {
    this(SPACESHIP_START, SPACESHIP_COLOR, "right");
  }

  /* Template:
   * fields:
   * ... this.pos ...        - CartPt
   * ... this.color ...      - Color
   * ... this.direction ...  - String
   * methods:
   * ... this.draw(WorldScene) ... - WorldScene
   * ... this.move() ...           - IGamePiece
   * ... this.lowerBound() ...     - boolean
   * ... this.upperBound() ...     - boolean
   * ... this.rightBound() ...     - boolean
   * ... this.leftBound()  ...     - boolean
   * ... this.getPos() ...         - CartPt
   * ... this.moveL() ...          - Spaceship
   * ... this.moveR() ...          - Spaceship
   * ... this.setDirRight() ...    - Spaceship
   * ... this.setDirLeft() ...     - Spaceship
   * methods of fields:
   * ... this.pos.sameAs(CartPt) ...   - boolean
   * ... this.pos.nextInRow() ...      - CartPt
   * ... this.pos.nextRowUp() ...      - CartPt  
   * ... this.pos.inRange(CartPt) ...  - boolean
   * ... this.pos.inRange2(CartPt) ... - boolean 
   */

  // draws the spaceship onto the scene
  public WorldScene draw(WorldScene acc) {
    return acc.placeImageXY(new RectangleImage(SPACESHIP_SIZE, SPACESHIP_SIZE,
        "solid", this.color), 
        this.pos.x, this.pos.y);
  }

  // checks if IGamePiece reached the left limit of window
  public boolean leftBound() {
    return this.pos.x <= 10;
  }

  //checks if IGamePiece reached the right limit of window
  public boolean rightBound() {
    return this.pos.x >= WORLD_SCENE_W - 10;
  }

  // moves the spaceship to the right
  Spaceship moveR() {
    if (this.rightBound()) {
      return new Spaceship(new CartPt(this.pos.x, this.pos.y), this.direction);
    }
    else {
      return new Spaceship(new CartPt(this.pos.x + SPACESHIP_SPEED, this.pos.y), 
          this.direction); 
    }
  }

  //moves the spaceship to the left
  Spaceship moveL() {
    if (this.leftBound()) {
      return new Spaceship(new CartPt(this.pos.x, this.pos.y), this.direction);
    }
    else {
      return new Spaceship(new CartPt(this.pos.x - SPACESHIP_SPEED, this.pos.y), 
          this.direction); 
    }
  }

  // moves the spaceship automatically depending on which direction it's currently set to
  public Spaceship move() {
    if (this.direction.equals("right") && (this.rightBound())) {
      return this;
    }
    if (this.direction.equals("left") && (this.leftBound())) {
      return this;
    }
    else if (this.direction.equals("right")) {
      return this.moveR();
    }
    else {
      return this.moveL();
    }
  }

  // sets the spaceship's direction to left
  public Spaceship setDirLeft() {
    return new Spaceship(this.pos, this.color, "left");
  }

  //sets the spaceship's direction to right
  public Spaceship setDirRight() {
    return new Spaceship(this.pos, this.color, "right");
  }
}

//a class to represent an invader
class Invader extends AGamePiece {

  Invader(CartPt pos, Color color) {
    super(pos, color);
  }

  Invader(CartPt pos) {
    this(pos, INVADER_COLOR);
  }

  /* TEMPLATE:
   * Fields:
   * ... this.pos ...      - CartPt
   * ... this.color ...    - Color
   * Methods:
   * ... this.draw(WorldScene) ...  - WorldScene
   * ... this.move() ...            - IGamePiece
   * ... this.lowerBound() ...      - boolean
   * ... this.upperBound() ...      - boolean
   * ... this.rightBound() ...      - boolean
   * ... this.leftBound()  ...      - boolean
   * ... this.getPos() ...         - CartPt
   * methods of fields:
   * ... this.pos.sameAs(CartPt) ...   - boolean
   * ... this.pos.nextInRow() ...      - CartPt
   * ... this.pos.nextRowUp() ...      - CartPt  
   * ... this.pos.inRange(CartPt) ...  - boolean
   * ... this.pos.inRange2(CartPt) ... - boolean 
   */

  // draws the invader onto the world scene
  public WorldScene draw(WorldScene acc) {
    return acc.placeImageXY(new RectangleImage(INVADER_SIZE, INVADER_SIZE, "solid", 
        this.color), 
        this.pos.x, this.pos.y);
  }
}

//A Class to represent a bullet
class Bullet extends AGamePiece {
  String direction;

  Bullet(CartPt pos, Color color, String direction) {
    super(pos, color);
    this.direction = new Utils().bDir(direction);
  }

  Bullet(CartPt pos, String direction) {
    this(pos, BULLET_COLOR, direction);
  }

  /* TEMPLATE:
   * Fields:
   * ... this.pos ...       - CartPt
   * ... this.color ...     - Color
   * ... this.direction ... - String
   * Methods:
   * ... this.draw(WorldScene) ...  - WorldScene
   * ... this.move() ...            - IGamePiece
   * ... this.lowerBound() ...      - boolean
   * ... this.upperBound() ...      - boolean
   * ... this.rightBound() ...      - boolean
   * ... this.leftBound()  ...      - boolean
   * ... this.getPos() ...          - CartPt
   * methods of fields:
   * ... this.pos.sameAs(CartPt) ...   - boolean
   * ... this.pos.nextInRow() ...      - CartPt
   * ... this.pos.nextRowUp() ...      - CartPt  
   * ... this.pos.inRange(CartPt) ...  - boolean
   * ... this.pos.inRange2(CartPt) ... - boolean 
   */

  // draws the bullet onto the scene
  public WorldScene draw(WorldScene acc) {
    return acc.placeImageXY(new CircleImage(BULLET_SIZE, "solid", this.color), 
        this.pos.x, this.pos.y);
  }

  // moves the bullet
  public IGamePiece move() {
    if (this.direction.equals("down")) {
      return new Bullet(new CartPt(this.pos.x, this.pos.y + BULLET_SPEED), this.color,
          this.direction);
    }
    else {
      return new Bullet(new CartPt(this.pos.x, this.pos.y - BULLET_SPEED), this.color, 
          this.direction);
    }
  }

  // checks if the bullet has reached the lower limit of window
  public boolean lowerBound() {
    return this.pos.y >= WORLD_SCENE_H;
  }

  //checks if the IGamePIece reached the upper limit of window
  public boolean upperBound() {
    return this.pos.y <= 0;
  }

}
