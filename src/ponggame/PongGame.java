
package ponggame;

import java.awt.Font;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

/**
 * Main game class for Pong
 * @author Christian Diller
 */
public class PongGame extends BasicGame {
    private Ball ball;
    private Bar leftBar, rightBar;
    Player[] players; int activePlayer;
    float width, height;
    
    public PongGame(float width, float height) {
        super("Pong Game");
        this.width = width; this.height = height;
    }
    
    @Override
    public void init(GameContainer gc) throws SlickException {
        gc.setMinimumLogicUpdateInterval(20);
        gc.setMaximumLogicUpdateInterval(50);
        
        ball = new Ball(100f, 100f, new Vector2f(3f, 3f), 10f);
        players = new Player[] { new Player("Player 1"), new Player("Player 2") };
        activePlayer = 1; //Zero-Based Array!
        leftBar = new Bar(30, 50, 20, 200, height);
        rightBar = new Bar(width - 50, 50, 20, 200, height);
                
        gc.getGraphics().setFont(new TrueTypeFont(new Font("Arial", Font.BOLD, 20), true));
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        processInput(gc.getInput());
        
        ball.update();
        
        checkBars();
        
        if(ball.getOrigin().y - ball.getRadius() <= 0 || ball.getOrigin().y + ball.getRadius() >= height)
            ball.reflectTopBottom();
    }

    @Override
    public void render(GameContainer gc, Graphics graphics) throws SlickException {
        ball.render(graphics);
        leftBar.render(graphics);
        rightBar.render(graphics);
        
        graphics.drawString("FPS: " + gc.getFPS(), 10, 10);
        graphics.drawString("Playing: Pong Game", 10, 30);
        graphics.drawString("Press R to start a new Game", 10, height - 30);
        
        if(players[activePlayer].getLost())
            graphics.drawString(players[activePlayer].getNameString() + " LOST", 10, height - 50);
    }

    private void processInput(Input input) {
        if(!players[activePlayer].getLost() && input.isKeyDown(Input.KEY_W))
            leftBar.move(-10f);
        if(!players[activePlayer].getLost() && input.isKeyDown(Input.KEY_S))
            leftBar.move(10f);
        if(!players[activePlayer].getLost() && input.isKeyDown(Input.KEY_UP))
            rightBar.move(-10f);
        if(!players[activePlayer].getLost() && input.isKeyDown(Input.KEY_DOWN))
            rightBar.move(10f);
        if(input.isKeyDown(Input.KEY_R))
            restart();
    }

    private void checkBars() {     
        if(ball.getOrigin().x - ball.getRadius() <= leftBar.getBounds().getX() + leftBar.getBounds().getWidth()) {
            if(leftBar.hit(ball)) {
                ball.reflectSide();
                activePlayer = 1;
            }
            else {
                players[activePlayer].setLost(true);
                ball.setVelocity(new Vector2f(0f, 0f));
            }
        }
        if(ball.getOrigin().x + ball.getRadius() >= rightBar.getBounds().getX()) {
            if(rightBar.hit(ball)) {
                ball.reflectSide();
                activePlayer = 0;
            }
            else {
                players[activePlayer].setLost(true);
                ball.setVelocity(new Vector2f(0f, 0f));
            }
        }
    }

    private void restart() {
        ball = new Ball(100f, 100f, new Vector2f(3f, 3f), 10f);
        players = new Player[] { new Player("Player 1"), new Player("Player 2") };
        activePlayer = 1; //Zero-Based Array!
        leftBar = new Bar(30, 50, 20, 200, height);
        rightBar = new Bar(width - 50, 50, 20, 200, height);
    }
}
