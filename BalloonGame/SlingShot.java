package BalloonGame;

public class SlingShot extends Element {
	
	private double moveSpeed = -300;
	private Game game;
	private boolean used = false;
	
	public SlingShot(Game game,String shape,int x,int y) {
		super(shape,x,y);
		
		this.game = game;
		
		dy = moveSpeed;
	}
	public void move(long delta) {
		super.move(delta);	
	}
		
	public void collidedWith(Element other) {
		if (used) {
			return;
		}
		
		if (other instanceof Balloon) {
			game.removeElement(this);
			game.removeElement(other);
			game.notifyballoonBurst();
			used = true;
		}
	}
}