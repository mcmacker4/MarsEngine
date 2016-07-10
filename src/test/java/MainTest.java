import com.mcmacker4.mars.application.Application;
import com.mcmacker4.mars.application.ApplicationSettings;
import com.mcmacker4.mars.application.display.DisplaySettings;

import static org.lwjgl.opengl.GL11.glClearColor;

/**
 * Created by McMacker4 on 09/07/2016.
 */
public class MainTest {

    private void start() {
        Application application = new Application(new ApplicationSettings().setDisplaySettings(
                new DisplaySettings().setTitle("My new Game Engine.")
        ));
        application.setInitMethod(this::init);
        application.setUpdateMethod(this::update);
        application.setRenderMethod(this::render);
        application.start();
    }

    private void init(Application app) {
        glClearColor(0.3f, 0.6f, 0.9f, 1.0f);
    }

    private void update(Application app, double delta) {

    }

    private void render(Application app) {
        app.clear();
    }

    public static void main(String[] args) {
        new MainTest().start();
    }

}
