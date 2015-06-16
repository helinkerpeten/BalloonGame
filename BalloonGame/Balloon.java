package BalloonGame;

import java.util.Random;

public class Balloon extends Element {
	private double moveSpeed;
	private Game game;
	private Shape[] frames = new Shape[3];
	private long lastFrameChange;
	private long frameDuration = 500;
	private int frameNumber;
	
	//renk random olarak �nstaNCE OLUSTULDUGUNDA GIRsin
	public Balloon(Game game, int x, int y, int color) {
//SUPeREDE STR�NG(REF),�NT ,�NT
	super("shapes/" + color + "1.png", x, y);
	//bu random h�z�n varyasyonunu saglamak �c�n
		Random r = new Random();
		moveSpeed = r.nextInt(50) + 25;
		dy = r.nextInt(25);
		frames[0] = shape;
		frames[1] = reader.getShape("shapes/" + color + "2.png");
		frames[2] = reader.getShape("shapes/" + color + "3.png");
		this.game = game;
		dx = -moveSpeed;
	}

	public void move(long delta) {
		lastFrameChange += delta;
		if (lastFrameChange > frameDuration) {
			lastFrameChange = 0;
			frameNumber++;
			if (frameNumber >= frames.length) {
				frameNumber = 0;
			}

			shape = frames[frameNumber];
		}

	//y�n degi�tirme s�n�rlar�
		if ((dx < 0) && (x < 10)) {
			game.UpdateDirection();
		}else if ((dx > 0) && (x > 925)) {
			game.UpdateDirection();
		}
		super.move(delta);
	}
//y�n degi�tiriyor balonlar s�n�ra geld�g�nde  yon deg�st�r�yor  
	//  yand�g� y derler�o�n� s�n�rl�o
	
	// BU metot sadece balon da var
	
	public void DirectionChange() {
		dx = -dx;
		if (y > 570 || y < -300) {
			game.notifyLost();
		}

	}

	public void collidedWith(Element other) {

	}
}