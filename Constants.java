import java.awt.Color;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.WorldImage;

interface IConstants {
  //Constants
  int WORLD_SCENE_W = 600;
  int WORLD_SCENE_H = 400;
  int SPACESHIP_POSX = 290;
  int SPACESHIP_POSY = 370;
  CartPt SPACESHIP_START = new CartPt(SPACESHIP_POSX, SPACESHIP_POSY);
  Color SPACESHIP_COLOR = Color.black;
  int SPACESHIP_SIZE = 20;
  int SPACESHIP_SPEED = 3;
  int INVADER_SIZE = 20;
  Color INVADER_COLOR = Color.red; 
  int BULLET_SIZE = 5;
  int BULLET_SPEED = 2;
  Color BULLET_COLOR = Color.orange;
  WorldImage SPACESHIP_IMG = new RectangleImage(SPACESHIP_SIZE, SPACESHIP_SIZE, 
      "solid", SPACESHIP_COLOR);
  WorldImage INVADER_IMG = new RectangleImage(INVADER_SIZE, INVADER_SIZE, 
      "solid", INVADER_COLOR);
}