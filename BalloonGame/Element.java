package BalloonGame;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Element {

	protected double x;
	protected double y;
	protected Shape shape;
	protected double dx;
	protected double dy;
	private Rectangle first = new Rectangle();
	private Rectangle second = new Rectangle();

	static FileReader reader = new FileReader();

	public Element(String ref, int x, int y) {
		this.shape = reader.getShape(ref);
		this.x = x;
		this.y = y;
	}

	public void move(long delta) {
		
		x += (delta * dx) / 1000;
		y += (delta * dy) / 1000;
		}
	

	public void draw(Graphics g) {
		shape.draw(g, (int) x, (int) y);
	}

	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public boolean collidesWith(Element other) {
		first.setBounds((int) x, (int) y, shape.getWidth(), shape.getHeight());
		second.setBounds((int) other.x, (int) other.y, other.shape.getWidth(),
				other.shape.getHeight());

		return first.intersects(second);
	}

	public abstract void collidedWith(Element other);

}