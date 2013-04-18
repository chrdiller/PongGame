
package ponggame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Rectangle;

/**
 * Class representing a player controllable bar
 * @author Christian Diller
 */
public class Bar {
    private Rectangle bounds;
    float screenHeight;
    
    public Bar(float xPos, float yPos, float width, float height, float screenHeight) {
        bounds = new Rectangle(xPos, yPos, width, height);
        this.screenHeight = screenHeight;
    }
    
    public void move(float value) {
        if(bounds.getY() + bounds.getHeight() + value <= screenHeight && bounds.getY() + value >= 0)
            bounds.setY(bounds.getY() + value);
    }
    
    public Rectangle getBounds() {
        return new Rectangle(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
    }
    
    public boolean hit(Ball ball) {
        return ball.getOrigin().y >= bounds.getY() && ball.getOrigin().y <= bounds.getY() + bounds.getHeight();
    }
    
    public void render(Graphics graphics) {
        graphics.fill(bounds, 
                        new GradientFill(bounds.getX(), 
                                         bounds.getY() + bounds.getHeight(),
                                         Color.magenta, bounds.getX() + bounds.getWidth(),
                                         bounds.getY(), Color.yellow));
    }
}
