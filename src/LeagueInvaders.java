import javax.swing.JFrame;

public class LeagueInvaders {
	JFrame frame;
	GamePanel object;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	LeagueInvaders(){
		frame = new JFrame();
		object = new GamePanel();
		
	}
	void setup() {
		frame.add(object);
		frame.setSize(WIDTH,HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(object);
	}
	
}
