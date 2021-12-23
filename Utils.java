import tester.Tester;

class Utils implements IConstants {

  /* TEMPLATE
   * Fields:
   * None
   * Methods:
   * ... this.bDir ...               - String
   * ... this.sDir ...               - String
   * ... this.createInvaders ...     - IList<IGamePiece>
   */

  // checks if the direction for the bullet is correct 
  String bDir(String dir) {
    if (dir.equals("down") || dir.equals("up")) {
      return dir;
    }
    else {
      throw new IllegalArgumentException("Bullets only go 'up' and 'down'");
    }
  }

  // checks if the direction of the player is correct
  String sDir(String dir) {
    if (dir.equals("left") || dir.equals("right")) {
      return dir;
    }
    else {
      throw new IllegalArgumentException("Bullets only go 'left' and 'right'");
    }
  }

  IList<IGamePiece> createInvaders(IList<IGamePiece> invaders, CartPt last) {
    if (invaders.fold(new LengthList(), 0) == 9) {
      return createInvaders(new ConsList<IGamePiece>(new Invader(last.nextRowUp()), invaders), 
          last.nextRowUp());
    }
    else if (invaders.fold(new LengthList(), 0) == 18) {
      return createInvaders(new ConsList<IGamePiece>(new Invader(last.nextRowUp()), invaders), 
          last.nextRowUp());
    }
    else if (invaders.fold(new LengthList(), 0) == 27) {
      return createInvaders(new ConsList<IGamePiece>(new Invader(last.nextRowUp()), invaders), 
          last.nextRowUp());
    }
    else if (invaders.fold(new LengthList(), 0) == 36) {
      return invaders;
    }
    else {
      return createInvaders(new ConsList<IGamePiece>(new Invader(last.nextInRow()), invaders), 
          last.nextInRow());
    }
  }
}

// Examples and tests for the Utils class
class UtilsTests {

  // creates a list of invaders
  IList<IGamePiece> l1 = new Utils().createInvaders(new MtList<IGamePiece>(), new CartPt(80, 20));

  //Positions for IGamePieces
  CartPt inv1  = new CartPt(120, 20);
  CartPt inv2  = new CartPt(160, 20);
  CartPt inv3  = new CartPt(200, 20);
  CartPt inv4  = new CartPt(240, 20);
  CartPt inv5  = new CartPt(280, 20);
  CartPt inv6  = new CartPt(320, 20);
  CartPt inv7  = new CartPt(360, 20);
  CartPt inv8  = new CartPt(400, 20);
  CartPt inv9  = new CartPt(440, 20);

  CartPt inv10 = new CartPt(120, 50);
  CartPt inv11 = new CartPt(160, 50);
  CartPt inv12 = new CartPt(200, 50);
  CartPt inv13 = new CartPt(240, 50);
  CartPt inv14 = new CartPt(280, 50);
  CartPt inv15 = new CartPt(320, 50);
  CartPt inv16 = new CartPt(360, 50);
  CartPt inv17 = new CartPt(400, 50);
  CartPt inv18 = new CartPt(440, 50);

  CartPt inv19 = new CartPt(120, 80);
  CartPt inv20 = new CartPt(160, 80);
  CartPt inv21 = new CartPt(200, 80);
  CartPt inv22 = new CartPt(240, 80);
  CartPt inv23 = new CartPt(280, 80);
  CartPt inv24 = new CartPt(320, 80);
  CartPt inv25 = new CartPt(360, 80);
  CartPt inv26 = new CartPt(400, 80);
  CartPt inv27 = new CartPt(440, 80);

  CartPt inv28 = new CartPt(120, 110);
  CartPt inv29 = new CartPt(160, 110);
  CartPt inv30 = new CartPt(200, 110);
  CartPt inv31 = new CartPt(240, 110);
  CartPt inv32 = new CartPt(280, 110);
  CartPt inv33 = new CartPt(320, 110);
  CartPt inv34 = new CartPt(360, 110);
  CartPt inv35 = new CartPt(400, 110);
  CartPt inv36 = new CartPt(440, 110);

  // Example invaders
  IGamePiece invader_1  = new Invader(inv1);
  IGamePiece invader_2  = new Invader(inv2);
  IGamePiece invader_3  = new Invader(inv3);
  IGamePiece invader_4  = new Invader(inv4);
  IGamePiece invader_5  = new Invader(inv5);
  IGamePiece invader_6  = new Invader(inv6);
  IGamePiece invader_7  = new Invader(inv7);
  IGamePiece invader_8  = new Invader(inv8);
  IGamePiece invader_9  = new Invader(inv9);

  IGamePiece invader_10 = new Invader(inv10);
  IGamePiece invader_11 = new Invader(inv11);
  IGamePiece invader_12 = new Invader(inv12);
  IGamePiece invader_13 = new Invader(inv13);
  IGamePiece invader_14 = new Invader(inv14);
  IGamePiece invader_15 = new Invader(inv15);
  IGamePiece invader_16 = new Invader(inv16);
  IGamePiece invader_17 = new Invader(inv17);
  IGamePiece invader_18 = new Invader(inv18);

  IGamePiece invader_19 = new Invader(inv19);
  IGamePiece invader_20 = new Invader(inv20);
  IGamePiece invader_21 = new Invader(inv21);
  IGamePiece invader_22 = new Invader(inv22);
  IGamePiece invader_23 = new Invader(inv23);
  IGamePiece invader_24 = new Invader(inv24);
  IGamePiece invader_25 = new Invader(inv25);
  IGamePiece invader_26 = new Invader(inv26);
  IGamePiece invader_27 = new Invader(inv27);

  IGamePiece invader_28 = new Invader(inv28);
  IGamePiece invader_29 = new Invader(inv29);
  IGamePiece invader_30 = new Invader(inv30);
  IGamePiece invader_31 = new Invader(inv31);
  IGamePiece invader_32 = new Invader(inv32);
  IGamePiece invader_33 = new Invader(inv33);
  IGamePiece invader_34 = new Invader(inv34);
  IGamePiece invader_35 = new Invader(inv35);
  IGamePiece invader_36 = new Invader(inv36);

  // SpaceInvaders List
  IList<IGamePiece> r1c0 = new MtList<IGamePiece>();
  IList<IGamePiece> r1c1 = new ConsList<IGamePiece>(this.invader_1, this.r1c0);
  IList<IGamePiece> r1c2 = new ConsList<IGamePiece>(this.invader_2, this.r1c1);
  IList<IGamePiece> r1c3 = new ConsList<IGamePiece>(this.invader_3, this.r1c2);
  IList<IGamePiece> r1c4 = new ConsList<IGamePiece>(this.invader_4, this.r1c3);
  IList<IGamePiece> r1c5 = new ConsList<IGamePiece>(this.invader_5, this.r1c4);
  IList<IGamePiece> r1c6 = new ConsList<IGamePiece>(this.invader_6, this.r1c5);
  IList<IGamePiece> r1c7 = new ConsList<IGamePiece>(this.invader_7, this.r1c6);
  IList<IGamePiece> r1c8 = new ConsList<IGamePiece>(this.invader_8, this.r1c7);
  IList<IGamePiece> r1c9 = new ConsList<IGamePiece>(this.invader_9, this.r1c8);

  IList<IGamePiece> r2c1 = new ConsList<IGamePiece>(this.invader_10, this.r1c9);
  IList<IGamePiece> r2c2 = new ConsList<IGamePiece>(this.invader_11, this.r2c1);
  IList<IGamePiece> r2c3 = new ConsList<IGamePiece>(this.invader_12, this.r2c2);
  IList<IGamePiece> r2c4 = new ConsList<IGamePiece>(this.invader_13, this.r2c3);
  IList<IGamePiece> r2c5 = new ConsList<IGamePiece>(this.invader_14, this.r2c4);
  IList<IGamePiece> r2c6 = new ConsList<IGamePiece>(this.invader_15, this.r2c5);
  IList<IGamePiece> r2c7 = new ConsList<IGamePiece>(this.invader_16, this.r2c6);
  IList<IGamePiece> r2c8 = new ConsList<IGamePiece>(this.invader_17, this.r2c7);
  IList<IGamePiece> r2c9 = new ConsList<IGamePiece>(this.invader_18, this.r2c8);

  IList<IGamePiece> r3c1 = new ConsList<IGamePiece>(this.invader_19, this.r2c9);
  IList<IGamePiece> r3c2 = new ConsList<IGamePiece>(this.invader_20, this.r3c1);
  IList<IGamePiece> r3c3 = new ConsList<IGamePiece>(this.invader_21, this.r3c2);
  IList<IGamePiece> r3c4 = new ConsList<IGamePiece>(this.invader_22, this.r3c3);
  IList<IGamePiece> r3c5 = new ConsList<IGamePiece>(this.invader_23, this.r3c4);
  IList<IGamePiece> r3c6 = new ConsList<IGamePiece>(this.invader_24, this.r3c5);
  IList<IGamePiece> r3c7 = new ConsList<IGamePiece>(this.invader_25, this.r3c6);
  IList<IGamePiece> r3c8 = new ConsList<IGamePiece>(this.invader_26, this.r3c7);
  IList<IGamePiece> r3c9 = new ConsList<IGamePiece>(this.invader_27, this.r3c8);

  IList<IGamePiece> r4c1 = new ConsList<IGamePiece>(this.invader_28, this.r3c9);
  IList<IGamePiece> r4c2 = new ConsList<IGamePiece>(this.invader_29, this.r4c1);
  IList<IGamePiece> r4c3 = new ConsList<IGamePiece>(this.invader_30, this.r4c2);
  IList<IGamePiece> r4c4 = new ConsList<IGamePiece>(this.invader_31, this.r4c3);
  IList<IGamePiece> r4c5 = new ConsList<IGamePiece>(this.invader_32, this.r4c4);
  IList<IGamePiece> r4c6 = new ConsList<IGamePiece>(this.invader_33, this.r4c5);
  IList<IGamePiece> r4c7 = new ConsList<IGamePiece>(this.invader_34, this.r4c6);
  IList<IGamePiece> r4c8 = new ConsList<IGamePiece>(this.invader_35, this.r4c7);
  IList<IGamePiece> r4c9 = new ConsList<IGamePiece>(this.invader_36, this.r4c8);

  // Utils example
  Utils u = new Utils();

  // Tests for bDir
  boolean testBDir(Tester t) {
    return t.checkConstructorException(
        new IllegalArgumentException("Bullets only go 'up' and 'down'"), 
        "Bullet", new CartPt(10, 30), "hola");
  }

  // Test sDir
  boolean testSDir(Tester t) {
    return t.checkConstructorException(
        new IllegalArgumentException("Bullets only go 'left' and 'right'"), 
        "Spaceship", new CartPt(300, 200), "down");
  }

  // Test CreateInvaders
  boolean testCreateInvaders(Tester t) {
    return t.checkExpect(this.l1, this.r4c9);
  }

}