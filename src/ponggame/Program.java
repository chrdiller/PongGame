
package ponggame;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/***
 * Program entry point
 * @author Christian Diller
 */
public class Program {
    
    public static void main(String[] args) {
        System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "lib/native"), LWJGLUtil.getPlatformName()).getAbsolutePath());
        System.setProperty("net.java.games.input.librarypath", System.getProperty("org.lwjgl.librarypath"));
        
        try {
            AppGameContainer app = new AppGameContainer(new PongGame(800, 600));
            
            app.setDisplayMode(800, 600, false);
            app.setTargetFrameRate(120);
            app.start();
            
        } catch (SlickException ex) {
            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
