package com.mcmacker4.mars.application;

import com.mcmacker4.mars.application.display.Display;
import com.mcmacker4.mars.application.display.DisplaySettings;
import com.mcmacker4.mars.log.Log;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static org.lwjgl.glfw.GLFW.glfwGetTime;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.GL_SHADING_LANGUAGE_VERSION;

/**
 * Created by McMacker4 on 09/07/2016.
 */
public class Application {

    private ApplicationSettings appSettings;

    private Consumer<Application> initMethod;
    private BiConsumer<Application, Double> updateMethod;
    private Consumer<Application> renderMethod;

    private Display display;

    private double lastUpdate = 0;

    public Application(ApplicationSettings settings) {
        if(settings == null) throw new IllegalArgumentException("ApplicationSettings is null.");
        this.appSettings = settings;
    }

    public void start() {
        createDisplay();
        loop();
        destroy();
    }

    private void loop() {
        init();
        while(!display.shouldClose()) {
            pollEvents();
            update();
            render();
            display.swapBuffers();
        }
    }

    private void init() {
        if(initMethod != null)
            initMethod.accept(this);
    }

    private void update() {
        if(updateMethod != null) {
            double now = glfwGetTime();
            double delta = now - lastUpdate;
            lastUpdate = now;
            updateMethod.accept(this, delta);
        }
    }

    private void render() {
        if(renderMethod != null)
            renderMethod.accept(this);
    }

    private void destroy() {
        display.destroy();
        glfwTerminate();
    }

    public void clear() {
        validateContext();
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void setInitMethod(Consumer<Application> init) {
        this.initMethod = init;
    }

    public void setUpdateMethod(BiConsumer<Application, Double> update) {
        this.updateMethod = update;
    }

    public void setRenderMethod(Consumer<Application> render) {
        this.renderMethod = render;
    }

    public void clearColor(float r, float g, float b, float a) {
        validateContext();
        glClearColor(r, g, b, a);
    }

    private void validateContext() {
        if(display == null)
            throw new IllegalStateException("No context.");
    }

    private void createDisplay() {
        if(appSettings.getDisplaySettings() == null) {
            Log.warning("DisplaySettings is null. Launching with default DisplaySettings.");
            appSettings.setDisplaySettings(new DisplaySettings().setTitle("Mars Engine"));
        }
        display = new Display(appSettings.getDisplaySettings());
        printSystemInfo();
        lastUpdate = glfwGetTime();
    }

    private void printSystemInfo() {
        Log.info("System information: " + "\r\n"
                + "GPU Vendor: " + glGetString(GL_VENDOR) + "\r\n"
                + "GPU Model: " + glGetString(GL_RENDERER) + "\r\n"
                + "OpenGL: " + glGetString(GL_VERSION) + "\r\n"
                + "GLSL: " + glGetString(GL_SHADING_LANGUAGE_VERSION) + "\r\n"
                + "Extensions: " + (glGetString(GL_EXTENSIONS) == null ? "NONE" : glGetString(GL_EXTENSIONS))
        );
    }

    public Display getDisplay() {
        return display;
    }

    public ApplicationSettings getAppSettings() {
        return appSettings;
    }

    private void pollEvents() {
        if(display != null)
            glfwPollEvents();
    }

}
