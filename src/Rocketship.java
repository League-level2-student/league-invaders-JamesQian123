import java.awt.Color;
import java.awt.Graphics;

public class Rocketship extends GameObject{
	boolean isMovingUp = false;
	boolean isMovingDown = false;
	boolean isMovingRight = false;
	boolean isMovingLeft = false;
	Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		speed = 5;
	}
void updatePos() {
	if(isMovingUp && y > 0) {
		up();
	}
	if(isMovingDown && y < LeagueInvaders.HEIGHT-width) {
		down();
	}
	if(isMovingRight && x < LeagueInvaders.WIDTH-width) {
		right();
	}
	if(isMovingLeft && x > 0) {
		left();
	}
}
void draw(Graphics g) {
    g.setColor(Color.BLUE);
    g.fillRect(x, y, width, height);
}
void right() {
	x+=speed;
}
void left() {
	x-=speed;
}
void up() {
	y-=speed;
}
void down() {
	y+=speed;
}
}
