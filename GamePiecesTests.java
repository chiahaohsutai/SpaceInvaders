import java.awt.Color;

import javalib.funworld.WorldScene;
import javalib.worldimages.CircleImage;
import javalib.worldimages.WorldImage;
import tester.Tester;

class GamePiecesTests implements IConstants {

  //Examples of Cartesian Points
  CartPt p1 = new CartPt(100, 100); // 2, 3
  CartPt p2 = new CartPt(0, 0); // 0, 0
  CartPt p3 = new CartPt(5, 7); // 5, 7
  CartPt p4 = new CartPt(610, SPACESHIP_POSY);
  CartPt p5 = new CartPt(-30, SPACESHIP_POSY);
  CartPt p6 = new CartPt(100, 100);
  CartPt p7 = new CartPt(100 + SPACESHIP_SPEED, 100);
  CartPt p8 = new CartPt(100 - SPACESHIP_SPEED, 100);
  CartPt invader1_pos = new CartPt(50, 20); // 50, 20
  CartPt bullet1_pos = new CartPt(10, 10); // 10, 10
  CartPt bullet2_pos = new CartPt(10, 10 + BULLET_SPEED);
  CartPt bullet4_pos = new CartPt(10, 10 - BULLET_SPEED);
  CartPt bullet5_pos = new CartPt(40, 440);
  CartPt bullet6_pos = new CartPt(40, -10);

  // Examples for Spaceship
  Spaceship s_outside_right = new Spaceship(this.p4, "right");  
  // Outside right bound of window
  Spaceship s_outside_left = new Spaceship(this.p5, "left");    
  // Outside left bound of window
  Spaceship s_switched_left = new Spaceship(this.p4, "left");   
  // s_outside_right with opposite direction
  Spaceship s_switched_right = new Spaceship(this.p5, "right"); 
  // s_outside_left with opposite direction
  Spaceship player_moveR = new Spaceship(this.p6, "right");
  Spaceship player_moveRR = new Spaceship(this.p7, "right");
  Spaceship player_moveL = new  Spaceship(this.p6, "left");
  Spaceship player_moveLL = new Spaceship(this.p8, "left");
  Spaceship playerA = new Spaceship();

  // Examples of IGamePieces
  IGamePiece player = new Spaceship();
  IGamePiece player11 = new Spaceship(new 
      CartPt(SPACESHIP_POSX + SPACESHIP_SPEED, SPACESHIP_POSY), "right");
  IGamePiece player2 = new Spaceship(this.p1, "left");
  IGamePiece player22 = new Spaceship(this.p8, "left");
  IGamePiece invader1 = new Invader(this.invader1_pos);
  IGamePiece invader2 = new Invader(new CartPt(15, 10), INVADER_COLOR);
  IGamePiece invader3 = new Invader(new CartPt(14, 10), INVADER_COLOR);
  IGamePiece bullet1 = new Bullet(this.bullet1_pos, "down");
  IGamePiece bullet2 = new Bullet(this.bullet2_pos, "down");     // bullet1 after moving
  IGamePiece bullet3 = new Bullet(this.bullet1_pos, "up");
  IGamePiece bullet4 = new Bullet(this.bullet4_pos, "up");       // bullet3 after moving
  IGamePiece bullet5 = new Bullet(this.bullet5_pos, "up");     // bullet outside lower limit
  IGamePiece bullet6 = new Bullet(this.bullet6_pos, "down");       // bullet outside upper limit
  IGamePiece bullet7 = new Bullet(SPACESHIP_START, "down");
  IGamePiece p_outside_right = new Spaceship(p4, "right");       // Outside right bound of window
  IGamePiece p_outside_left = new Spaceship(p5, "left");         // Outside left bound of window
  IGamePiece player_left = new Spaceship(new CartPt(280, 370), "left");

  // Examples of WorldImage
  WorldImage b1_img = new CircleImage(BULLET_SIZE, "solid", BULLET_COLOR);

  // Examples of WorldScenes
  WorldScene base_scene = new WorldScene(600, 400);
  WorldScene scene_player = this.base_scene.placeImageXY(SPACESHIP_IMG, 
      SPACESHIP_POSX, SPACESHIP_POSY);
  WorldScene scene_inv1_player = this.scene_player.placeImageXY(INVADER_IMG, 50, 20);
  WorldScene scene_b1 = this.base_scene.placeImageXY(b1_img, 10, 10);

  //Test inRange()
  boolean testInRange(Tester t) {
    return t.checkExpect(this.bullet1_pos.inRange(this.bullet5_pos), false)
        && t.checkExpect(this.bullet1_pos.inRange(new CartPt(23, 10)), true);
  }

  // test moveR()
  boolean testMoveR(Tester t) {
    return t.checkExpect(this.player_moveR.moveR(), this.player_moveRR) 
        && t.checkExpect(this.s_outside_right.moveR(), this.s_outside_right);
  }

  // test moveL()
  boolean testMoveL(Tester t) {
    return t.checkExpect(this.player_moveL.moveL(), this.player_moveLL);
  }

  // Test NextInRow()
  boolean testNextInRow(Tester t) {
    return t.checkExpect(this.p1.nextInRow(), new CartPt(140, 100));
  }

  // Test NextRowUp()
  boolean testNextRowUp(Tester t) {
    return t.checkExpect(this.p1.nextRowUp(), new CartPt(120, 130));
  }

  // test for getPos() 
  boolean testGetPos(Tester t) {
    return t.checkExpect(this.player.getPos(), SPACESHIP_START)
        && t.checkExpect(this.bullet1.getPos(), this.bullet1_pos)
        && t.checkExpect(this.invader1.getPos(), this.invader1_pos);
  }

  // Test for SameAs
  boolean testSameAs(Tester t) {
    return t.checkExpect(this.p1.sameAs(this.invader1_pos), false)
        && t.checkExpect(this.p1.sameAs(this.p1), true);
  }

  // Tests for Drawing an IGamePiece 
  boolean testDrawIGamePiece(Tester t) {
    return t.checkExpect(this.player.draw(base_scene), this.scene_player)          
        // drawing spaceship
        && t.checkExpect(this.invader1.draw(scene_player), this.scene_inv1_player) 
        // drawing invader
        && t.checkExpect(this.bullet1.draw(base_scene), this.scene_b1);           
    // drawing bullet
  }

  // Test for InRange2()
  boolean testInRange2(Tester t) {
    return t.checkExpect(this.bullet1_pos.inRange2(this.bullet5_pos), false)
        && t.checkExpect(this.bullet1_pos.inRange2(new CartPt(23, 10)), true);
  }

  //Tests for lowerBound()
  boolean testLowerBoundIGamePiece(Tester t) {
    return t.checkExpect(this.bullet5.lowerBound(), true) 
        && t.checkExpect(this.bullet1.lowerBound(), false)
        && t.checkExpect(this.invader1.lowerBound(), false) // invaders don't move
        && t.checkExpect(this.player.lowerBound(), false);  // spaceship doesn't move vertically
  }

  //Tests for upperBound()
  boolean testUpperBoundIGamePiece(Tester t) {
    return t.checkExpect(this.bullet2.upperBound(), false)
        && t.checkExpect(this.bullet6.upperBound(), true)
        && t.checkExpect(this.invader1.upperBound(), false) // invaders don't move
        && t.checkExpect(this.player.upperBound(), false);  // spaceship doesn't move vertically
  }

  //Tests for rightBound()
  boolean testRightBoundIGamePiece(Tester t) {
    return t.checkExpect(this.player.rightBound(), false)
        && t.checkExpect(this.p_outside_right.rightBound(), true)
        && t.checkExpect(this.bullet1.rightBound(), false)
        && t.checkExpect(this.invader1.rightBound(), false);
  }

  //Tests for leftBound()
  boolean testLeftBoundIGamePiece(Tester t) {
    return t.checkExpect(this.player.leftBound(), false)
        && t.checkExpect(this.p_outside_left.leftBound(), true)
        && t.checkExpect(this.invader1.leftBound(), false)
        && t.checkExpect(this.bullet1.leftBound(), false);
  }

  // Tests for Moving an IGamePiece 
  boolean testMoveIGamePiece(Tester t) {
    return t.checkExpect(this.player.move(), this.player11)    // player moving right
        && t.checkExpect(this.player2.move(), this.player22)   // player moving left
        && t.checkExpect(this.invader1.move(), this.invader1)  // invaders should not move
        && t.checkExpect(this.bullet1.move(), this.bullet2)   // bullet going down
        && t.checkExpect(this.bullet3.move(), this.bullet4);  // bullet going up
  }

  // Sets the direction to the right
  boolean testSetDirRight(Tester t) {
    return
        t.checkExpect(this.playerA.setDirRight(), this.playerA);
  }

  // Sets the direction to the left
  boolean testSetDirLeft(Tester t) {
    return
        t.checkExpect(this.playerA.setDirLeft(), new Spaceship(new CartPt(290, 370),
            Color.black, "left"));
  }
}