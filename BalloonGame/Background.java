package BalloonGame;

public class Background extends Element {

	private Game game;
	public Background(Game game,String ref,int x,int y) {
		super(ref,x,y);
		this.game = game;
	}
	
	public void collidedWith(Element other) {
		
		
	}

	}


