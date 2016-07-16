import com.mcmacker4.mars.application.Application;
import com.mcmacker4.mars.application.ApplicationSettings;
import com.mcmacker4.mars.application.display.DisplaySettingsBuilder;
import com.mcmacker4.mars.application.events.KeyboardEvent;
import com.mcmacker4.mars.application.events.MouseButtonEvent;
import com.mcmacker4.mars.application.events.CursorMoveEvent;
import com.mcmacker4.mars.application.events.listeners.KeyboardListener;
import com.mcmacker4.mars.application.events.listeners.MouseButtonListener;
import com.mcmacker4.mars.application.events.listeners.CursorPosListener;
import com.mcmacker4.mars.system.log.Log;

import java.util.HashMap;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

/**
 * Created by McMacker4 on 09/07/2016.
 */
public class MainTest extends Application implements KeyboardListener, CursorPosListener, MouseButtonListener {

    HashMap<Integer, String> map;

    public MainTest() {
        super(new ApplicationSettings().setDisplaySettings(
                new DisplaySettingsBuilder().setClearColor(0.3f, 0.6f, 0.9f).setTitle("My Game").create()
        ));
    }

    @Override
    public void init() {
        addEventListener(this);
    }

    @Override
    public void onKeyEvent(KeyboardEvent event) {
        if(event.getAction() == GLFW_PRESS) {
            Log.info("Key " + event.getKeyCode() + " Pressed.");
        }
    }

    @Override
    public void onMouseButtonEvent(MouseButtonEvent event) {
    }

    @Override
    public void onCursorMove(CursorMoveEvent event) {
    }

    public static void main(String[] args) {
        new MainTest().start();
    }
}
