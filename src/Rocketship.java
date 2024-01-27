import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Rocketship extends GameObject{
	boolean isMovingUp = false;
	boolean isMovingDown = false;
	boolean isMovingRight = false;
	boolean isMovingLeft = false;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		speed = 5;
		if (needImage) {
			loadImage ("rocket.png");
		}
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
		
		super.update();
	}
	void draw(Graphics g) {

		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
		}
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
	void loadImage(String imageFile) {
		if (needImage) {
			try {
				image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				gotImage = true;
			} catch (Exception e) {

			}
			needImage = false;
		}
	}
	public Projectile getProjectile() {
		return new Projectile(x+width/2, y, 10, 10);
	}
}
