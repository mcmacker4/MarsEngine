import com.mcmacker4.mars.application.Layer;
import com.mcmacker4.mars.system.log.Log;

/**
 * Created by McMacker4 on 17/07/2016.
 */
public class TestLayer implements Layer {
    @Override
    public void init() {
        Log.info("Initialized TestLayer.");
    }

    @Override
    public void update(double delta) {

    }

    @Override
    public void render() {

    }

    @Override
    public void destroy() {
        Log.info("Destroying TestLayer.");
    }
}
