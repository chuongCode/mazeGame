import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import java.util.Random;


public class mazeGame extends JPanel implements ActionListener, KeyListener {
	
	public mazeGame() {
		addKeyListener(this);
		setFocusable(true);
		
		Timer timer2 = new Timer(1000, new score());
        timer2.start();
		
	}
	
	
	public static JFrame frame = new JFrame("The Maze Game");
	Image image;
	Graphics graphics;
	
	protected static int gameScore = 5000;
	boolean hitObstacle = false;
	boolean gameWon = false;
	boolean gameLost = false;
    Timer timer1 = new Timer(12, this);
    
    Player player1 = new Player(125, 175, 10, 10, 1, 1);
    Rectangle playerRectangle = new Rectangle(100 + player1.getXCoord(), 175 + player1.getYCoord(), 1, 1);
    Rectangle barrier = new Rectangle(100, 150, 1, 50); 
    Rectangle goal = new Rectangle(400, 300, 10, 50); 
    
    Rectangle topLine = new Rectangle(100, 100, 300, 1);
    Rectangle bottomLine = new Rectangle(100, 400, 300, 1);
    Rectangle lv = new Rectangle(100, 100, 1, 50);
    Rectangle lv2 = new Rectangle(100, 200, 1, 200);
    Rectangle rv = new Rectangle(400, 100, 1, 250);
    Rectangle rv2 = new Rectangle(400, 300, 1, 50);
    Rectangle x1 = new Rectangle(100, 150, 50, 1);
    Rectangle x2 = new Rectangle(100, 200, 150, 1);
    Rectangle x3 = new Rectangle(100, 250, 200, 1);
    Rectangle x4 = new Rectangle(150, 300, 350, 1);
    Rectangle x5 = new Rectangle(150, 350, 100, 1);
    Rectangle x6 = new Rectangle(350, 350, 50, 1);
    Rectangle y1 = new Rectangle(250, 100, 1, 50);
    Rectangle y2 = new Rectangle(200, 150, 1, 50);
    Rectangle y3 = new Rectangle(300, 150, 1, 100); 
    Rectangle y4 = new Rectangle(350, 150, 1, 150);
    Rectangle y5 = new Rectangle(300, 300, 1, 50);
    Rectangle y6 = new Rectangle(250, 350, 1, 50);
    
   
	@Override
	public void paintComponent(Graphics g) {
		image = createImage(this.getWidth(), this.getHeight());
		g.drawImage(image, 0, 0, this);
		
		g.setColor(Color.RED);
		player1.draw(g);
		
		// top and bottom lines
		g.setColor(Color.BLACK);
		mazeMaker topLine = new mazeMaker(100, 100, 300, 1); // top horizontal line
		topLine.draw(g); 
		mazeMaker bottomLine = new mazeMaker(100, 400, 300, 1); // bottom horizontal line
		bottomLine.draw(g);
		
		// opening and exit
		mazeMaker lv = new mazeMaker(100, 100, 1, 50); // left vertical small segment
		lv.draw(g);
		mazeMaker lv2 = new mazeMaker(100, 200, 1, 200); // left vertical large segment
		lv2.draw(g); 
		mazeMaker rv = new mazeMaker(400, 100, 1, 200); // right vertical large segment
		rv.draw(g);
		mazeMaker rv2 = new mazeMaker(400, 350, 1, 50); // right vertical small segment
		rv2.draw(g);
		
		// inner lines
		mazeMaker x1 = new mazeMaker(100, 150, 50, 1); // x1
		x1.draw(g);
		mazeMaker x2 = new mazeMaker(100, 200, 150, 1); // x2
		x2.draw(g);
		mazeMaker x3 = new mazeMaker(100, 250, 200, 1); // x3
		x3.draw(g);
		mazeMaker x4 = new mazeMaker(150, 300, 250, 1); // x4
		x4.draw(g);
		mazeMaker x5 = new mazeMaker(150, 350, 100, 1); // x5
		x5.draw(g);
		mazeMaker x6 = new mazeMaker(350, 350, 50, 1); // x6
		x6.draw(g);

        mazeMaker y1 = new mazeMaker(250, 100, 1, 50); // y1
        y1.draw(g);
        mazeMaker y2 = new mazeMaker(200, 150, 1, 50); // y2
        y2.draw(g);
        mazeMaker y3 = new mazeMaker(300, 150, 1, 100); // y3
        y3.draw(g);
        mazeMaker y4 = new mazeMaker(350, 150, 1, 150); // y4
        y4.draw(g);
        mazeMaker y5 = new mazeMaker(300, 300, 1, 50); // y5
        y5.draw(g);
        mazeMaker y6 = new mazeMaker(250, 350, 1, 50); // y6
        y6.draw(g);
		
		// score display
		g.drawString("Score: " + gameScore, 350, 30);
	}
	
	protected class score implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	if(gameWon != true) {
        		gameScore -= 100;
        	}
        }
    }
	
	private void collisionDetection() {
		if ( (playerRectangle.intersects(barrier)) || 
			(playerRectangle.intersects(topLine)) ||
			(playerRectangle.intersects(bottomLine)) ||
			(playerRectangle.intersects(lv)) ||
			(playerRectangle.intersects(lv2)) || 
			(playerRectangle.intersects(rv)) ||
			(playerRectangle.intersects(rv2)) ||
			(playerRectangle.intersects(x1)) ||
			(playerRectangle.intersects(x2)) ||
			(playerRectangle.intersects(x3)) ||
			(playerRectangle.intersects(x4)) ||
			(playerRectangle.intersects(x5)) ||
			(playerRectangle.intersects(x6)) ||
			(playerRectangle.intersects(y1)) || 
			(playerRectangle.intersects(y2)) ||
			(playerRectangle.intersects(y3)) ||
			(playerRectangle.intersects(y4)) ||
			(playerRectangle.intersects(y5)) ||
			(playerRectangle.intersects(y6)) ) {		
			hitObstacle = true;
			playSound("\\SoundFiles\\MLG Hitmarker Sound Effect.wav");
		}
		
	}

	private void loseCheck() {
		if (gameScore <= 0) {
			gameLost = true;
    		frame.setVisible(false);
			frame.dispose();
			loserScreen();
		}
	}
	private void winCheck() {
		if (playerRectangle.intersects(goal)) {
			gameWon = true;
			frame.setVisible(false);
			frame.dispose();
			winnerScreen();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		player1.tick();
		loseCheck();
		winCheck();
		collisionDetection();
		playerRectangle = new Rectangle(player1.getXCoord(), player1.getYCoord(), 1, 1);
		if(hitObstacle) {
			player1.setX(125);
			player1.setY(175);
			gameScore -= 100;
			hitObstacle = false;
			
		}
		repaint();
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if ((hitObstacle) || (gameWon)) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_W:
				player1.setDy(0);
				break;
			case KeyEvent.VK_A:
				player1.setDx(0);
				break;
			case KeyEvent.VK_S:
				player1.setDy(0);
				break;
			case KeyEvent.VK_D:
				player1.setDx(0);
				break;
			}
		}
		else {
			timer1.start();
			switch (e.getKeyCode()) {
			case KeyEvent.VK_W:
				player1.setDy(-1);
				break;
			case KeyEvent.VK_A:
				player1.setDx(-1);
				break;
			case KeyEvent.VK_S:
				player1.setDy(1);
				break;
			case KeyEvent.VK_D:
				player1.setDx(1);
				break;	
			}
		}
		
	}
	 
	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			player1.setDy(0);
			break;
		case KeyEvent.VK_A:
			player1.setDx(0);
			break;
		case KeyEvent.VK_S:
			player1.setDy(0);
			break;
		case KeyEvent.VK_D:
			player1.setDx(0);
			break;
			
		}
	}
	
	private static void loserScreen() {
		JFrame frame = new JFrame("LOSER!");
		
		JLabel loser = new JLabel ("Oh no! You ran out of points and lost!");
		loser.setFont(new Font("Comic Sans MS", Font.PLAIN,22 ));
		frame.add(loser);
		
        frame.setSize(500,500);
        frame.setVisible(true);
        playSound("\\SoundFiles\\The Price is Right Losing Horn - Gaming Sound Effect (HD).wav");
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
	}
	
	private static void winnerScreen() {
		JFrame frame = new JFrame("Congratulations!");

		JLabel winner = new JLabel ("Congratulations! You won with " + gameScore + " points!");
		winner.setFont(new Font("Comic Sans MS", Font.PLAIN,22 ));
		frame.add(winner);
		
        frame.setSize(500,500);
        frame.setVisible(true);
        playSound("\\SoundFiles\\Final Fantasy Victory Fanfare - Sound Effect [HQ].wav");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	private static void playSound(String soundFile) {
	    File f = new File("./" + soundFile);
	    try {
	    AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());  
	    Clip clip = AudioSystem.getClip();
	    clip.open(audioIn);
	    clip.start();
	    }
	    catch(Exception e) {
			   JOptionPane.showMessageDialog(null, "Error");
		   }
	    
	}
	
	private static void menu() {
        GridBagConstraints positionConst = new GridBagConstraints();
        JFrame Menu = new JFrame("Maze game");
        Menu.setLayout(new GridBagLayout());
        
        JLabel title = new JLabel ("Maze Game!!!");
        title.setFont(new Font("Comic Sans MS", Font.PLAIN,75 ));
        positionConst.gridx = 0;
        positionConst.gridy = 0;
        positionConst.insets = new Insets(25,100,300,100);
        Menu.add(title,positionConst);

        JButton GStart = new JButton("GAME START");
        positionConst.gridx = 0;
        positionConst.gridy = 0;
        positionConst.insets = new Insets(300,100,100,100);
        Menu.add(GStart, positionConst);
        
        JButton b = new JButton("ding!");
        positionConst.gridx = 0;
        positionConst.gridy = 0 ;
        positionConst.insets = new Insets(250,100,300,100);
        Menu.add(b, positionConst);
        
        GStart.setPreferredSize(new Dimension(150,100));
        b.setPreferredSize(new Dimension(150, 100));

        Menu.setSize(500,500);
        Menu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GStart.addActionListener(new ActionListener() {
        	
        public void actionPerformed(ActionEvent event) {
			start();
			Menu.setVisible(false);
			Menu.dispose();
        	}
        });
        
        b.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
        		playSound("\\SoundFiles\\Family Feud Ding Sound Effect (HD).wav");
        	}
        });
        
        Menu.pack();
        Menu.setVisible(true);
        playSound("\\SoundFiles\\Family Feud Ding Sound Effect (HD).wav");
    	}
	
	private static void start() {
		
		mazeGame canvas = new mazeGame();
		
		frame.add(canvas);
		
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		menu();
	}
	
}
