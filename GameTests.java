import java.awt.Color;
import java.util.Random;

import javalib.funworld.WorldScene;
import javalib.worldimages.TextImage;
import tester.Tester;

class GameTests {

  // Makes a list of invaders
  IList<IGamePiece> invaders = new Utils().createInvaders(new MtList<IGamePiece>(), 
      new CartPt(80, 20));

  // Makes a single invader
  IList<IGamePiece> singleInvader = new ConsList<IGamePiece>(
      new Invader(new CartPt(280, 20)), 
      new MtList<IGamePiece>());

  // Makes the spaceship
  Spaceship p = new Spaceship();

  //Creates a non-random world for testing purposes
  SpaceInvaders myWorld = new SpaceInvaders(this.singleInvader, new MtList<IGamePiece>(), 
      new MtList<IGamePiece>(), this.p, new Random(1));

  // tests onTick()
  boolean testOnTick(Tester t) {
    return t.checkExpect(myWorld.onTick(), new SpaceInvaders(this.singleInvader, 
        new ConsList<IGamePiece>(new Bullet(new CartPt(280, 22), "down"), 
            new MtList<IGamePiece>()), 
        new MtList<IGamePiece>(), this.p.move(), new Random(1)));
  }

  //tests makeScene()
  boolean testMakeScene(Tester t) {
    return t.checkExpect(myWorld.makeScene(), this.p.draw( 
        this.singleInvader.fold(new Draw(), 
            new WorldScene(600, 400))));
  }

  // tests onKeyEvent
  boolean testOnKeyRight(Tester t) {
    return t.checkExpect(myWorld.onKeyEvent("right"), this.myWorld);
  }

  // tests onKeyEvent
  boolean testOnKeyLeft(Tester t) {
    return t.checkExpect(myWorld.onKeyEvent("left"), new SpaceInvaders(
        this.singleInvader, new MtList<IGamePiece>(), 
        new MtList<IGamePiece>(), this.p.setDirLeft(), new Random(1)));
  }

  // tests onKeyEvent
  boolean testOnKeySpace(Tester t) {
    return t.checkExpect(myWorld.onKeyEvent(" "), new SpaceInvaders(
        this.singleInvader, new MtList<IGamePiece>(), 
        new ConsList<IGamePiece>(new Bullet(
            new CartPt(290, 370), Color.black, "up"), new MtList<IGamePiece>()),
        this.p, new Random(1)));
  }

  // tests onKeyEvent
  boolean testOnKeyInvalid(Tester t) {
    return t.checkExpect(myWorld.onKeyEvent("b"), myWorld);
  }

  // tests lastScene
  boolean testlastScene(Tester t) {
    return t.checkExpect(myWorld.lastScene("Gruezi"), 
        new WorldScene(600, 400).placeImageXY(
            new TextImage("Gruezi", Color.red), 300, 100));
  }
}