import com.mcmacker4.mars.application.Application;
import com.mcmacker4.mars.application.ApplicationSettings;
import com.mcmacker4.mars.application.display.DisplaySettingsBuilder;
import com.mcmacker4.mars.application.events.Input;
import com.mcmacker4.mars.application.events.KeyboardEvent;
import com.mcmacker4.mars.application.events.MouseButtonEvent;
import com.mcmacker4.mars.application.events.CursorMoveEvent;
import com.mcmacker4.mars.application.events.listeners.KeyboardListener;
import com.mcmacker4.mars.application.events.listeners.MouseButtonListener;
import com.mcmacker4.mars.application.events.listeners.CursorPosListener;
import com.mcmacker4.mars.system.log.Log;

/**
 * Created by McMacker4 on 09/07/2016.
 */
public class MainTest extends Application implements KeyboardListener, CursorPosListener, MouseButtonListener {

    public MainTest(ApplicationSettings settings) {
        super(settings);
    }

    @Override
    public void init() {
        addEventListener(this);
    }

    @Override
    public void onKeyEvent(KeyboardEvent event) {
        Log.info("Key Event");
    }

    @Override
    public void onMouseButtonEvent(MouseButtonEvent event) {
    }

    @Override
    public void onCursorMove(CursorMoveEvent event) {
    }

    public static void main(String[] args) {
        new MainTest(new ApplicationSettings()).start();
    }
}
