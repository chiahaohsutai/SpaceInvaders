import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import javalib.funworld.WorldScene;
import javalib.worldimages.CircleImage;
import javalib.worldimages.WorldImage;
import tester.Tester;

class ListsTests implements IConstants {
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

  // Examples of List of CartPt
  IList<CartPt> empty_CP = new MtList<CartPt>();
  IList<CartPt> list1 = new ConsList<CartPt>(p1, this.empty_CP); // (2, 3)
  IList<CartPt> list2 = new ConsList<CartPt>(p2, this.list1); // (0, 0) (2, 3)

  // Example of List of IGamePieces 
  // Lists
  IList<IGamePiece> pl1 = new MtList<IGamePiece>();
  IList<IGamePiece> playerlist2 = new ConsList<IGamePiece>(this.player, this.pl1);
  IList<IGamePiece> pl3 = new ConsList<IGamePiece>(this.player11, this.pl1);
  IList<IGamePiece> pl4 = new ConsList<IGamePiece>(this.bullet6, this.playerlist2);
  IList<IGamePiece> pl5 = new ConsList<IGamePiece>(this.bullet6, this.pl1);
  IList<IGamePiece> empty_IGP = new MtList<IGamePiece>();
  IList<IGamePiece> player_in_list = new ConsList<IGamePiece>(this.player, 
      this.empty_IGP); // player
  IList<IGamePiece> player_left_in_list = new ConsList<IGamePiece>(this.player_left, 
      this.empty_IGP); // p_left
  IList<IGamePiece> inv_in_list = new ConsList<IGamePiece>(this.invader1, 
      this.empty_IGP); // invader1
  IList<IGamePiece> b_in_list = new ConsList<IGamePiece>(this.bullet1, 
      this.empty_IGP); // bullet1
  IList<IGamePiece> b1b7 = new ConsList<IGamePiece>(this.bullet7, this.b_in_list);
  IList<IGamePiece> in2in1 = new ConsList<IGamePiece>(this.invader2, this.inv_in_list);

  // Function Objects
  Predicate<CartPt> same_point = new SamePoint(new CartPt(2, 3));
  Predicate<CartPt> point_in_list1 = new PointInList(this.list1);
  Predicate<CartPt> point_in_list2 = new PointInList(this.list2);
  BiFunction<IGamePiece, WorldScene, WorldScene> draw_1 = new Draw();
  Function<IGamePiece, IGamePiece> move_pieces = new Move(); 
  BiFunction<IGamePiece, Integer, Integer> lengthlist = new LengthList();
  Predicate<IGamePiece> remove = new Remove();
  Predicate<IGamePiece> hit = new Hit(this.player);
  Predicate<IGamePiece> hitlist = new HitList(b_in_list);
  Predicate<IGamePiece> removebhelper = new RemoveBHelper(this.bullet1); 
  Predicate<IGamePiece> removeb = new RemoveB(this.in2in1);

  //Test for Hit Object Function
  boolean testHit(Tester t) {
    return t.checkExpect(this.hit.test(this.bullet1), false)
        && t.checkExpect(this.hit.test(this.bullet7), true);
  }

  // Test for HitList Object Function
  boolean testHitList(Tester t) {
    return t.checkExpect(this.hitlist.test(this.invader1), false)
        && t.checkExpect(this.hitlist.test(this.invader2), true);
  }

  // Test for HitList2 Object Function 
  boolean testHitList2(Tester t) {
    return t.checkExpect(this.hitlist.test(this.invader1), false)
        && t.checkExpect(this.hitlist.test(this.invader2), true);
  }

  // Test for RemoveBHelper Object Function
  boolean testRemoveBHelper(Tester t) {
    return t.checkExpect(this.removebhelper.test(this.invader2), true)
        && t.checkExpect(this.removebhelper.test(this.invader1), false);
  }

  // Test for RemoveB Object Function 
  boolean testRemoveB(Tester t) {
    return t.checkExpect(this.removeb.test(this.bullet1), false)
        && t.checkExpect(this.removeb.test(this.bullet5), true);
  }


  // Test for Remove Object Function
  boolean testRemove(Tester t) {
    return t.checkExpect(this.remove.test(this.bullet1), true)
        && t.checkExpect(this.remove.test(this.bullet5), false)
        && t.checkExpect(this.remove.test(this.bullet6), false);
  }

  // Tests for LengthList ObjectFunction
  boolean testLengthList(Tester t) {
    return t.checkExpect(this.lengthlist.apply(this.bullet1, 0), 1)
        && t.checkExpect(this.lengthlist.apply(this.player, 10), 11)
        && t.checkExpect(this.lengthlist.apply(this.invader1, 2), 3);
  }

  // Test for SamePoint Object Function
  boolean testSamePoint(Tester t) {
    return t.checkExpect(this.same_point.test(new CartPt(2, 3)), true)
        && t.checkExpect(this.same_point.test(p2), false);
  }

  // Test for PointInList Object Function
  boolean testPointInList(Tester t) {
    return t.checkExpect(this.point_in_list1.test(p1), true)  // list:(2,3)      given:(2,3)
        && t.checkExpect(this.point_in_list2.test(p3), false) // list:(0,0)(2,3) given:(5,7)
        && t.checkExpect(this.point_in_list2.test(p1), true)  // list:(0,0)(2,3) given:(2,3)
        && t.checkExpect(this.point_in_list2.test(p2), true); // list:(0,0)(2,3) given:(0,0)
  }

  // Test for Draw Object Function
  boolean testDraw(Tester t) {
    return t.checkExpect(this.draw_1.apply(player, base_scene), this.scene_player)
        && t.checkExpect(this.draw_1.apply(invader1, scene_player), this.scene_inv1_player);
  }

  // Tests for Move Object Function 
  boolean testMove(Tester t) {
    return t.checkExpect(this.move_pieces.apply(this.player), this.player11)
        && t.checkExpect(this.move_pieces.apply(this.player2), this.player22)
        && t.checkExpect(this.move_pieces.apply(this.bullet1), this.bullet2)
        && t.checkExpect(this.move_pieces.apply(this.bullet3), this.bullet4)
        && t.checkExpect(this.move_pieces.apply(this.invader1), this.invader1);
  }

  // Test of pick()
  boolean testPick(Tester t) {
    return t.checkExpect(this.list2.pick(1), this.p1)
        && t.checkExpect(this.list2.pick(0), this.p2)
        && t.checkException(new IllegalArgumentException("No object found"), 
            this.list2, "pick", 4);
  }

  // Test List Abstractions

  // Test fold 
  boolean testFold(Tester t) {
    return t.checkExpect(this.playerlist2.fold(new LengthList(), 0), 1)
        && t.checkExpect(this.playerlist2.fold(new Draw(), this.base_scene), this.scene_player);
  }

  // Test map 
  boolean testMap(Tester t) {
    return t.checkExpect(this.playerlist2.map(new Move()), this.pl3);
  }

  // Test filter
  boolean testFilter(Tester t) {
    return t.checkExpect(this.playerlist2.filter(new Remove()), this.playerlist2)
        && t.checkExpect(this.pl4.filter(new Remove()), this.playerlist2);
  }

  // Test AndMap
  boolean testAndMap(Tester t) {
    return t.checkExpect(this.playerlist2.andmap(new Remove()), true)
        && t.checkExpect(this.pl4.andmap(new Remove()), false);
  }

  // Test OrMap
  boolean testOrMap(Tester t) {
    return t.checkExpect(this.pl4.ormap(new Remove()), true)
        && t.checkExpect(this.pl5.ormap(new Remove()), false)
        && t.checkExpect(this.b1b7.ormap(this.hit), true)
        && t.checkExpect(this.b_in_list.ormap(this.hit), false)
        && t.checkExpect(this.in2in1.ormap(this.hitlist), true)
        && t.checkExpect(this.inv_in_list.ormap(this.hitlist), false);
  } 
}