import tester.Tester;
import java.awt.Color;
import java.util.Random;

import javalib.funworld.*;
import javalib.worldimages.*; 

// to represent the game SpaceInvaders
class SpaceInvaders extends World {
  Random rand; 
  IList<IGamePiece> invaders;   // tracks invaders
  IList<IGamePiece> invBullet;  // tracks bullets shot by invaders
  IList<IGamePiece> spaBullet;  // tracks bullets shot by the spaceship/player
  Spaceship player;      

  SpaceInvaders(IList<IGamePiece> invaders, IList<IGamePiece> invBullet, 
      IList<IGamePiece> spaBullet, Spaceship player, Random rand) {
    this.invaders = invaders;
    this.invBullet = invBullet;
    this.spaBullet = spaBullet;
    this.player = player;
    this.rand = rand;
  }

  /* TEMPLATE:
   * Fields:
   * ... this.rand ...      - Random
   * ... this.invaders ...  - IList<IGamePiece>
   * ... this.invBullet ... - IList<IGamePiece>
   * ... this.spaBullet ... - IList<IGamePiece>
   * ... this.player ...    - Spaceship
   * Methods:
   * ... this.makeScene() ...        - WorldScene
   * ... this.onTick() ...           - World
   * ... this.onKeyEvent(String) ... - World 
   * Methods of Fields:
   * this.invaders:
   * ... this.invaders.filter(Predicate<IGamePiece>) ...         - IList<IGamePiece>
   * ... this.invaders.map(Function<IGamePiece, u>) ...          - IList<U>
   * ... this.invader.fold(BiFunction<IGamePiece, U, U>, U) ...  - IList<U>
   * ... this.invaders.andmap(Predicate<IGamePiece>) ...         - boolean
   * ... this.invaders.ormap(Predicate<IGamePiece>) ...          - boolean
   * ... this.invaders.pick(Integer) ...                         - IGamePiece
   * this.invBullet:
   * ... this.invBullet.filter(Predicate<IGamePiece>) ...          - IList<IGamePiece>
   * ... this.invBullet.map(Function<IGamePiece, u>) ...           - IList<U>
   * ... this.invBullet.fold(BiFunction<IGamePiece, U, U>, U) ...  - IList<U>
   * ... this.invBullet.andmap(Predicate<IGamePiece>) ...          - boolean
   * ... this.invBullet.ormap(Predicate<IGamePiece>) ...           - boolean
   * ... this.invBullet.pick(Integer) ...                          - IGamePiece
   * this.spaBullet:
   * ... this.spaBullet.filter(Predicate<IGamePiece>) ...          - IList<IGamePiece>
   * ... this.spaBullet.map(Function<IGamePiece, u>) ...           - IList<U>
   * ... this.spaBullet.fold(BiFunction<IGamePiece, U, U>, U) ...  - IList<U>
   * ... this.spaBullet.andmap(Predicate<IGamePiece>) ...          - boolean
   * ... this.spaBullet.ormap(Predicate<IGamePiece>) ...           - boolean
   * ... this.spaBullet.pick(Integer) ...                          - IGamePiece
   * this.player
   * ... this.player.draw(WorldScene) ... - WorldScene
   * ... this.player.move() ...           - IGamePiece
   * ... this.player.lowerBound() ...     - boolean
   * ... this.player.upperBound() ...     - boolean
   * ... this.player.rightBound() ...     - boolean
   * ... this.player.leftBound()  ...     - boolean
   * ... this.player.getPos() ...         - CartPt
   * ... this.player.moveL() ...          - Spaceship
   * ... this.player.moveR() ...          - Spaceship
   * ... this.player.setDirRight() ...    - Spaceship
   * ... this.player.setDirLeft() ...     - Spaceship
   */

  //draws the game pieces onto the background
  public WorldScene makeScene() {
    return this.player.draw(spaBullet.fold(new Draw(), 
        this.invBullet.fold(new Draw(), 
            invaders.fold(new Draw(), 
                new WorldScene(600, 400)))));
  }

  // Generates Invader bullets at random that originate from a random invader
  // Removes any bullet that has come off the window boundaries
  // While the bullets are still in the window, it moves the bullets
  public World onTick() {
    int current_number_bullets = this.invBullet.fold(new LengthList(), 0);  
    int number_of_inv = this.invaders.fold(new LengthList(), 0);

    if (this.invBullet.ormap(new Hit(this.player))) {
      return this.endOfWorld("The Spaceship has been hit !!! The spaceship has been destroyed !!!");
    }

    else if (this.invaders.ormap(new HitList(this.spaBullet))) {
      return new SpaceInvaders(this.invaders.filter(new HitList2(this.spaBullet)), 
          this.invBullet, this.spaBullet.filter(new RemoveB(this.invaders)), 
          this.player.move(), new Random()); 
    }

    else if (this.invaders.fold(new LengthList(), 0) == 0) {
      return this.endOfWorld("You have wiped all the invaders !!! You Win !!!");
    }

    IGamePiece invader_rand = this.invaders.pick(rand.nextInt(number_of_inv)); 
    CartPt inv_pos = invader_rand.getPos();

    if (current_number_bullets < 10) {
      return new SpaceInvaders(this.invaders, 
          new ConsList<IGamePiece>(new Bullet(inv_pos, "down"), 
              this.invBullet).map(new Move()).filter(new Remove()), 
          this.spaBullet.map(new Move()).filter(new Remove()), this.player.move(), new Random());
    }

    else {
      return new SpaceInvaders(this.invaders, 
          this.invBullet.map(new Move()).filter(new Remove()), 
          this.spaBullet.map(new Move()).filter(new Remove()), this.player.move(), new Random()); 
    }
  }

  //move the spaceship/player based on the key input, shoots a bullet if space bar is pressed
  public World onKeyEvent(String key) {
    int current_number_bullets = this.spaBullet.fold(new LengthList(), 0);
    CartPt player = this.player.getPos();

    if (key.equals("right")) {
      return new SpaceInvaders(this.invaders, this.invBullet, this.spaBullet, 
          this.player.setDirRight(), new Random());
    }
    else if (key.equals("left")) {
      return new SpaceInvaders(this.invaders, this.invBullet, this.spaBullet, 
          this.player.setDirLeft(), new Random());
    }
    else if (key.equals(" ") && (current_number_bullets < 3)) {
      return new SpaceInvaders(this.invaders, this.invBullet, 
          new ConsList<IGamePiece>(new Bullet(player, Color.black, "up"), 
              this.spaBullet), this.player, new Random());
    }
    else {
      return new SpaceInvaders(this.invaders, this.invBullet, this.spaBullet, 
          this.player, new Random());
    }
  }

  public WorldScene lastScene(String msg) {
    return new WorldScene(600, 400).placeImageXY(new TextImage(msg, Color.red), 300, 100);
  }
}

class RunSpaceInvaders {

  //Makes a list of invaders
  IList<IGamePiece> invaders = new Utils().createInvaders(new MtList<IGamePiece>(), 
      new CartPt(80, 20));

  //Makes the spaceship
  Spaceship p = new Spaceship();

  // runs SpaceInvaders
  boolean testBigBang(Tester t) {
    SpaceInvaders world = new SpaceInvaders(this.invaders, new MtList<IGamePiece>(), 
        new MtList<IGamePiece>(), this.p, new Random());
    int worldWidth = 600;
    int worldHeight = 400;
    double tickRate = 0.03;
    return world.bigBang(worldWidth, worldHeight, tickRate);
  }
}