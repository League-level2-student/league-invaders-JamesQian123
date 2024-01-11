import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	Font titleFont;
	Font startFont;
	Font instructionFont;
	Font gameOverFont;
	Timer frameDraw;
	Timer alienSpawn;
	Rocketship ship = new Rocketship(250,750,50,50);
	int currentState = MENU;
	ObjectManager objectManager = new ObjectManager(ship);
	GamePanel(){
		titleFont = new Font("Arial", Font.PLAIN, 48);
		startFont = new Font("Arial", Font.PLAIN, 25);
		instructionFont = new Font("Arial", Font.PLAIN, 25);
		gameOverFont = new Font("Arial", Font.PLAIN, 48);
		frameDraw = new Timer(1000/60,this);
	    frameDraw.start();
	    if (needImage) {
	        loadImage ("space.png");
	    }
	}
	@Override
	public void paintComponent(Graphics g){
		//g.fillRect(10, 10, 100, 100);
		
		if(currentState == MENU) {
			drawMenuState(g);
		}
		else if(currentState == GAME) {
			drawGameState(g);
		}
		else if(currentState == END) {
			drawEndState(g);
		}
		
	}
	void updateMenuState() {
		
	}
	void updateGameState() {
		ship.updatePos();
		objectManager.update();
		
	}
	void updateEndState() {
		
	}
	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.yellow);
		g.drawString("League Invaders", 75, 200);
		g.setFont(startFont);
		g.setColor(Color.yellow);
		g.drawString("press enter", 195, 450);
		g.setFont(instructionFont);
		g.setColor(Color.yellow);
		g.drawString("press space for instructions", 120, 600);
	}
	void drawGameState(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		objectManager.draw(g);
		if (gotImage) {
			g.drawImage(image, 0, 0, 500, 800, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, 500, 800);
		}
		ship.draw(g);
	
	}
	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(gameOverFont);
		g.setColor(Color.black);
		g.drawString("GAME OVER", 100, 400);
		g.setFont(startFont);
		g.setColor(Color.black);
		g.drawString("press enter to restart", 135, 450);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}
		System.out.println("action");
		repaint();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		        currentState = MENU;
		    } else {
		        currentState++;
		    }
		}   
		if(currentState == GAME) {
			if (e.getKeyCode()==KeyEvent.VK_UP) {
			    System.out.println("UP");
			    if(ship.y > 0) {
			    	//ship.up();
			    	ship.isMovingUp = true;
			    	
			    }
			    
			    }
			
			else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			    System.out.println("DOWN");
			    if(ship.y < LeagueInvaders.HEIGHT-50) {
			    	//ship.down();
			    	ship.isMovingDown = true;
			    }
			    
			    
			}
			else if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			    System.out.println("RIGHT");
			    if(ship.x < LeagueInvaders.WIDTH-50) {
			    	//ship.right();
			    	ship.isMovingRight = true;
			    }
			   
			    
			}
			else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			    System.out.println("LEFT");
			    if(ship.x > 0) {
			    	//ship.left();
			    	ship.isMovingLeft = true;
			    }
			   
			}
		}
		startGame();
		if(currentState == END) {
			alienSpawn.stop();
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			objectManager.addProjectile(ship.getProjectile());
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_UP) {
			ship.isMovingUp = false;
	
		}
		else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			ship.isMovingDown = false;
		}
		else if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			ship.isMovingRight = false;
		}
		else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			ship.isMovingLeft = false;
		}
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
	void startGame() {
		alienSpawn = new Timer(1000,objectManager);
		alienSpawn.start();
	}
}
