package com.mcmacker4.mars;

import com.mcmacker4.mars.application.Application;
import com.mcmacker4.mars.application.ApplicationSettings;
import com.mcmacker4.mars.application.display.DisplaySettings;

/**
 * Created by McMacker4 on 09/07/2016.
 */
public class Start {

    private void start() {
        Application application = new Application(new ApplicationSettings().setDisplaySettings(
                new DisplaySettings().setTitle("My new Game Engine.").setResolution(800, 600)
        ));
        application.setInitMethod(Start::init);
        application.setUpdateMethod(Start::update);
        application.setRenderMethod(Start::render);
        application.start();
    }

    private static void init(Application app) {
        app.clearColor(1.0f, 0.5f, 0.3f, 1.0f);
    }

    private static void update(Application app, double delta) {

    }

    private static void render(Application app) {
        app.clear();
    }

    public static void main(String[] args) {
        new Start().start();
    }

}
