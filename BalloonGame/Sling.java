 package BalloonGame;

public class Sling extends Element {
	
	private Game game;
	public Sling(Game game,String ref,int x,int y) {
		super(ref,x,y);
		this.game = game;
	}

	
	public void collidedWith(Element other) {
		if (other instanceof Balloon) {
			game.notifyLost();
		}
	}
}