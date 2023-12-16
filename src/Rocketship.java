import java.awt.Color;
import java.awt.Graphics;

public class Rocketship extends GameObject{
	
	Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		speed = 10;
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
	y+=speed;
}
void down() {
	y-=speed;
}
}
