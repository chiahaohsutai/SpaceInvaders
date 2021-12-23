import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import javalib.funworld.WorldScene;

// Function objects thats implement Predicate

// checks if the given CartPt is equivalent to the current CartPt
class SamePoint implements Predicate<CartPt> {
  CartPt compare;

  SamePoint(CartPt compare) {
    this.compare = compare;
  }

  public boolean test(CartPt t) {
    return this.compare.sameAs(t);
  }
}

// returns true if the given Cartesian point is contained in the list 
class PointInList implements Predicate<CartPt> {
  IList<CartPt> points;

  PointInList(IList<CartPt> points) {
    this.points = points;
  }

  public boolean test(CartPt t) {
    return this.points.ormap(new SamePoint(t));
  }
}

// checks if the given objects has left the window
class Remove implements Predicate<IGamePiece> {
  public boolean test(IGamePiece t) {
    return !(t.lowerBound() || t.upperBound());
  }
}

// checks if the an IGamePice was hit by a Bullet
class Hit implements Predicate<IGamePiece> {
  IGamePiece player;

  Hit(IGamePiece player) {
    this.player = player;
  }

  public boolean test(IGamePiece t) {
    return this.player.getPos().inRange(t.getPos());
  } 

}

//Check if the the CartPt is within the range of any object in the given list 
class HitList implements Predicate<IGamePiece> {
  IList<IGamePiece> bullets;

  HitList(IList<IGamePiece> bullets) {
    this.bullets = bullets;
  }

  public boolean test(IGamePiece t) {
    return this.bullets.ormap(new Hit(t));
  } 
}

//Check if the the CartPt is within the range of any object in the given list
class HitList2 implements Predicate<IGamePiece> {
  IList<IGamePiece> bullets;

  HitList2(IList<IGamePiece> bullets) {
    this.bullets = bullets;
  }

  public boolean test(IGamePiece t) {
    return !(this.bullets.ormap(new Hit(t)));
  } 
}

//checks if the Bullet has hit any IList<IGamePiece> 
class RemoveB implements Predicate<IGamePiece> {
  IList<IGamePiece> invaders;

  RemoveB(IList<IGamePiece> invaders) {
    this.invaders = invaders;
  }

  public boolean test(IGamePiece t) {
    return !(this.invaders.ormap(new RemoveBHelper(t)));
  }
}

//checks if the Bullet has hit any IGamePiece
class RemoveBHelper implements Predicate<IGamePiece> {
  IGamePiece bullet;

  RemoveBHelper(IGamePiece bullet) {
    this.bullet = bullet;
  }

  public boolean test(IGamePiece t) {
    return this.bullet.getPos().inRange2(t.getPos());
  }
}

// Function Objects that implement BiFunction

// Draws the given IGamePieces onto the WorldScene
class Draw implements BiFunction<IGamePiece, WorldScene, WorldScene> {
  public WorldScene apply(IGamePiece t, WorldScene u) {
    return t.draw(u);
  }
}

// Adds one to the counter
// Accumulator: tracks the number of IGamePieces in the list
class LengthList implements BiFunction<IGamePiece, Integer, Integer> {
  public Integer apply(IGamePiece t, Integer u) {
    return 1 + u;
  }
}

// moves the given IGamePiece
class Move implements Function<IGamePiece, IGamePiece> {
  public IGamePiece apply(IGamePiece t) {
    return t.move();
  }
}
