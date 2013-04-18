
package ponggame;

/**
 * A class representing one of the two pong players
 * @author Christian Diller
 */
public class Player {
    private String name;
    private boolean lost;
    
    public Player(String name) {
        this.name = name;
        lost = false;
    }
    
    public String getNameString() {
        return name;
    }
    
    public void setLost(boolean lost) {
        this.lost = lost;
    }
    
    public boolean getLost() {
        return lost;
    }
}
