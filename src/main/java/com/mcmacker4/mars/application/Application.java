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

    /**
     * Simple game loop.
     */
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
            //Calculate the elapsed time between last frame and this frame.
            double now = glfwGetTime();
            double delta = now - lastUpdate;
            lastUpdate = now;
            //Call the update method.
            updateMethod.accept(this, delta);
        }
    }

    private void render() {
        if(renderMethod != null)
            renderMethod.accept(this);
    }

    /**
     * Destroys the display and terminates GLFW.
     */
    private void destroy() {
        display.destroy();
        glfwTerminate();
    }

    /**
     * Clear the Color and Buffer bits of the current FrameBuffer.
     */
    public void clear() {
        validateContext();
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    /**
     * Set the method that is going to be called on init event.
     * @param init the consumer (lambda?) to be called on init.
     */
    public void setInitMethod(Consumer<Application> init) {
        this.initMethod = init;
    }

    /**
     * Set the update method to be called every frame.
     * This should handle all the dynamic changes of the game.
     * @param update the update method.
     */
    public void setUpdateMethod(BiConsumer<Application, Double> update) {
        this.updateMethod = update;
    }

    /**
     * Set the render method to be called every frame.
     * This should handle the rendering part of the game.
     * @param render the render method.
     */
    public void setRenderMethod(Consumer<Application> render) {
        this.renderMethod = render;
    }

    /**
     * Should only be called from the main thread.
     * Checks if the display has been created.
     * If it has been created there probably is an
     * OpenGL context bound to the current thread.
     */
    private void validateContext() {
        if(display == null)
            throw new IllegalStateException("No context.");
    }

    /**
     * Creates a display and shows it on the screen.
     */
    private void createDisplay() {
        //If displaySettings is null, use default settings.
        if(appSettings.getDisplaySettings() == null) {
            Log.warning("DisplaySettings is null. Launching with default DisplaySettings.");
            appSettings.setDisplaySettings(new DisplaySettings().setTitle("Mars Engine"));
        }
        //Create display
        display = new Display(appSettings.getDisplaySettings());
        printSystemInfo();
        //Initialize the timer
        lastUpdate = glfwGetTime();
    }

    /**
     * Prints useful system information to console.
     * This info includes GPU vendor, model and OpenGL version.
     */
    private void printSystemInfo() {
        Log.info("System information: " + "\r\n"
                + "GPU Vendor: " + glGetString(GL_VENDOR) + "\r\n"
                + "GPU Model: " + glGetString(GL_RENDERER) + "\r\n"
                + "OpenGL: " + glGetString(GL_VERSION) + "\r\n"
                + "GLSL: " + glGetString(GL_SHADING_LANGUAGE_VERSION) + "\r\n"
                + "Extensions: " + (glGetString(GL_EXTENSIONS) == null ? "NONE" : glGetString(GL_EXTENSIONS))
        );
    }

    /**
     * Returns the display of this application.
     * @return Display
     */
    public Display getDisplay() {
        return display;
    }

    /**
     * Returns the settings for this application.
     * @return Settings
     */
    public ApplicationSettings getAppSettings() {
        return appSettings;
    }

    /**
     * Poll input and other events.
     */
    private void pollEvents() {
        glfwPollEvents();
    }

}
