/*
NOTLAR
akt�fl�k button da kal�o oyuna baslamadan 
once ekrana t�klamak gerek�o nas�l engeller�z

 */
package BalloonGame;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Game extends Canvas {
	private String windowTitle = "Balloon Game";
	private JFrame window;
	private BufferStrategy strategy;
	private JLabel label;
	private Background background;
	private boolean DoesDirectionChange = false;
	private int groundNo;
	private ArrayList  elements = new ArrayList();
	private ArrayList removeList = new ArrayList();
	private Element sling;
	private long lastShot = 0;
	private long firingInterval = 300;
	private boolean wait = true;
	private boolean firePressed = false;
	public  int burstCount;

	JLabel notification;

	JPanel controlpanel;
	public Game() {


		window = new JFrame("Balloon Game");
		window.setSize(1200, 700);
		window.setLayout(new BorderLayout());
		window.setBackground(Color.blue);
		JPanel panel = (JPanel) window.getContentPane();
		panel.setPreferredSize(new Dimension(800, 600));
		controlpanel = new JPanel(new FlowLayout());
		JButton b = new JButton("Start");
		JButton b1 = new JButton("Change BackGround");
		JButton b2 = new JButton("Quit Game");
		notification= new JLabel("WELCOME!Click start button") ;
		notification.setBackground(Color.DARK_GRAY);
		notification.setPreferredSize(new Dimension(200, 300));
		MyButtonListener listener = new MyButtonListener(this);
		b.addActionListener(listener);
		b1.addActionListener(listener);
		b2.addActionListener(listener);

		controlpanel.setPreferredSize(new Dimension(200, 600));
		controlpanel.add(b);
		controlpanel.add(b1);
		controlpanel.add(b2);
		controlpanel.add(notification);


		b.setPreferredSize(new Dimension(200, 50));
		b1.setPreferredSize(new Dimension(200, 100));
		b2.setPreferredSize(new Dimension(200, 100));

		controlpanel.setBounds(0, 0, 300, 800);
		controlpanel.setBackground(Color.WHITE);

		label = new JLabel();

		label.setBackground(Color.WHITE);
		label.setOpaque(true);

		controlpanel.add(b1);
		controlpanel.add(b2);
		controlpanel.add(label);
		setBackground(Color.CYAN);
		setBounds(0, 0, 800, 800);
		panel.add(this);

		window.add(controlpanel, BorderLayout.EAST);

		window.setResizable(false);
		window.setVisible(true);

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		createBufferStrategy(3);
		strategy = getBufferStrategy();
		initElements();

	}
	private void startGame() {
		
		firePressed = false;
		elements.clear();
		initElements();

	}

	private void initElements() {
	
		sling = new Sling(this, "shapes/spling2.png", 370, 600);
		background = new Background(this, "shapes/sky0.jpg", 0, 0);
		elements.add(background);
		elements.add(sling);

		MyMouseListener list = new MyMouseListener(sling);
		addMouseListener(list);
		addMouseMotionListener(list);
		Random r = new Random();
		for (int k = 0; k <40; k++) {
			int row = r.nextInt(7);
			int col = r.nextInt(20);
			Element balloon = new Balloon(this, 20 + (col * 30), row * 50,r.nextInt(4));
			elements.add(balloon);
			

		}
	}

	public void UpdateDirection () {
		DoesDirectionChange = true;
	}

	public void removeElement(Element element) {
		removeList.add(element);
	}

	public void notifyLost() {

		wait = true;
		notification.setText("Do you want play again ?");
		notification.setBackground(Color.RED);
		controlpanel.setBackground(Color.RED);
	}

	public void notifyWin() {
		wait = true;
		notification.setText("AWESOME! Let's play again!");
		notification.setBackground(Color.RED);
		controlpanel.setBackground(Color.RED);

	}

	public void notifyballoonBurst() {
		burstCount++;
		if (burstCount == 40) {
			notifyWin();
			wait=true;
		}
	}

	public void tryToFire() {

		if (System.currentTimeMillis() - lastShot < firingInterval) {
			return;
		}

		lastShot = System.currentTimeMillis();

		SlingShot shot = new SlingShot(this, "shapes/pepple.png",
				sling.getX() + 10, sling.getY() - 30);
		elements.add(shot);
	}

	public void gameLoop() {
		while (true) {

			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.fillRect(0, 0, 1000, 800);

			label.setText("Burst Count : " + burstCount);
			window.setTitle(windowTitle);

			if (!wait) {
				for (int i = 0; i < elements.size(); i++) {
					Element element = (Element) elements.get(i);
					element.move(7);
					if (element instanceof SlingShot){
						element.move(4);
					}
					controlpanel.setBackground(Color.WHITE);
					notification.setText("YEAPS! You can do it!");


				}
			}
			
			
			for (int i = 0; i < elements.size(); i++) {
				Element element = (Element) elements.get(i);
				element.draw(g);
			}
			for (int i= 0; i < elements.size(); i++) {
				for (int j = i + 1; j< elements.size(); j++) {
					Element one = (Element) elements.get(i);
					Element two= (Element) elements.get(j);

					if (two!= null && one.collidesWith(two)) {
						one.collidedWith(two);
						two.collidedWith(one);
					}
				}
			}
			elements.removeAll(removeList);
			removeList.clear();

			//Eger yon degistrme ihtiyac� true �se  tum elementler� tara  ballon olanlaru�n yon 
			// degi�tirme metodunu �ag�r.
			if (DoesDirectionChange) {
				for (int i = 0; i < elements.size(); i++) {
					Element element = (Element) elements.get(i);
					if (element instanceof Balloon){
					((Balloon) element).DirectionChange();
					}
				}

				DoesDirectionChange = false;
			}
		
			strategy.show();

			if (firePressed) {
				tryToFire();
			}
		}
	}

	class MyButtonListener implements ActionListener {
		public Game game;

		public MyButtonListener(Game g) {
			game = g;
		}

		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			if (b.getText().equals("Start")) {
				startGame();
				burstCount=0;
				wait = false;
			}
			if (b.getText().equals("Quit Game")) {
				System.exit(0);
			}
			if (b.getText().equals("Change BackGround")) {
				groundNo++;
				String ref = (String) "shapes/sky" + groundNo + ".jpg";
				background = new Background(game, ref, 0, 0);
				elements.set(0, background);
				if (groundNo == 3) {
					groundNo = -1;
				}
			}
		}

	}

	class MyMouseListener extends MouseAdapter implements MouseMotionListener  {
		private Element sling;

		MyMouseListener(Element sling) {
			super();
			this.sling = sling;
		}
		public void mousePressed(MouseEvent e) {
			if (!wait){
				firePressed = true;
			}
		}
		public void mouseReleased(MouseEvent e) { 
			firePressed = false;
		}
		public void mouseDragged(MouseEvent e) {
			if (!wait&&(e.getX() < 950)){
				firePressed = true;

				sling.setX(e.getX());
			}
		}

		public void mouseMoved(MouseEvent e) {
			if(!wait&& (e.getX() < 950)){
				sling.setX(e.getX());
			}
		}
	}

	public static void main(String argv[]){
		try{
			Game g = new Game();
			g.gameLoop();
		}catch( NullPointerException e ){
			System.out.println(" Can you try again please?");
		}
	}
}
