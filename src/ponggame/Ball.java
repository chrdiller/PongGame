
package ponggame;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

/**
 * Class representing the Pong Ball
 * @author Christian Diller
 */
public class Ball {
    Circle ballShape;
    private Vector2f velocity;
    
    public Ball(float xOrigin, float yOrigin, Vector2f velocity, float diameter) {
        ballShape = new Circle(xOrigin, yOrigin, diameter / 2);
        this.velocity = new Vector2f(velocity.x, velocity.y);
    }
    
    public void update() {
        ballShape.setCenterX(ballShape.getCenterX() + velocity.x);
        ballShape.setCenterY(ballShape.getCenterY() + velocity.y);
    }
    
    public void render(Graphics graphics) {
        graphics.fill(ballShape, 
                new GradientFill(ballShape.getX(), 
                                 ballShape.getY() + ballShape.getHeight(),
                                 Color.yellow, ballShape.getX() + ballShape.getWidth(),
                                 ballShape.getY(), Color.magenta));
    }
    
    public Vector2f getOrigin() {
        return new Vector2f(ballShape.getCenterX(), ballShape.getCenterY());
    }
    
    public float getRadius() {
        return ballShape.getRadius();
    }
    
    public void reflectSide() {
        velocity.x = - velocity.x;
    }
    
    public void reflectTopBottom() {
        velocity.y = - velocity.y;
    }
    
    public void setVelocity(Vector2f velocity) {
        this.velocity = new Vector2f(velocity.x, velocity.y);
    }
    
    public boolean intersects(Shape shape) {
        return ballShape.intersects(shape);
    }
}
