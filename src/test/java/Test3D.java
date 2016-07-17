import com.mcmacker4.mars.application.Application;
import com.mcmacker4.mars.application.ApplicationSettings;
import com.mcmacker4.mars.application.display.DisplaySettingsBuilder;
import com.mcmacker4.mars.application.events.KeyboardEvent;
import com.mcmacker4.mars.application.events.listeners.KeyboardListener;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;

/**
 * Created by McMacker4 on 17/07/2016.
 */
public class Test3D extends Application implements KeyboardListener {

    public Test3D() {
        super(new ApplicationSettings().setDisplaySettings(
                new DisplaySettingsBuilder().setTitle("My Mars Engine Game.")
                        .setClearColor(0.3f, 0.6f, 0.9f).create()
        ));
    }

    @Override
    public void init() {
        addEventListener(this);
        pushLayer(new TestLayer());
    }

    @Override
    public void onKeyEvent(KeyboardEvent event) {
        if(event.getKeyCode() == GLFW_KEY_ESCAPE)
            exit();
    }

    public static void main(String[] args) {
        new Test3D().start();
    }

}
