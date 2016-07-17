package com.mcmacker4.mars.application;

import com.mcmacker4.mars.application.display.Display;
import com.mcmacker4.mars.application.display.DisplaySettingsBuilder;
import com.mcmacker4.mars.application.events.Input;
import com.mcmacker4.mars.application.events.listeners.EventListener;
import com.mcmacker4.mars.system.log.Log;

import java.util.LinkedList;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.GL_SHADING_LANGUAGE_VERSION;

/**
 * Created by McMacker4 on 09/07/2016.
 */
public abstract class Application {

    private ApplicationSettings appSettings;

    private Display display;
    private Input input = new Input();

    private LinkedList<Layer> layers = new LinkedList<>();

    private double lastUpdate = 0;

    public Application(ApplicationSettings settings) {
        if(settings == null) throw new IllegalArgumentException("ApplicationSettings is null.");
        this.appSettings = settings;
    }

    public Application(int width, int height, String title) {
        this.appSettings = new ApplicationSettings().setDisplaySettings(
                new DisplaySettingsBuilder().setResolution(width, height).setTitle(title).create()
        );
    }

    public void start() {
        createDisplay();
        loop();
        destroy();
        Log.info("Goodbye.");
    }

    /**
     * Simple game loop.
     */
    private void loop() {
        _init();
        while(!display.shouldClose()) {
            glfwPollEvents();
            update();
            render();
            display.swapBuffers();
        }
    }

    private void _init() {
        init();
        layers.forEach(Layer::init);
    }

    public abstract void init();

    private void update() {
        //Calculate the elapsed time between last frame and this frame.
        double now = glfwGetTime();
        double delta = now - lastUpdate;
        lastUpdate = now;
        layers.forEach((layer) -> layer.update(delta));
    }

    private void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        layers.forEach(Layer::render);
    }

    /**
     * Destroys the display and terminates GLFW.
     */
    private void destroy() {
        layers.forEach(Layer::destroy);
        display.destroy();
        glfwTerminate();
    }

    /**
     * Tells the display it should close.
     */
    public void exit() {
        display.close();
    }

    /**
     * Add an event listener (implementing some kind of EventListener
     * @param listener The event listener to add.
     */
    public void addEventListener(EventListener listener) {
        input.addListener(listener);
    }

    /**
     * Remove a previously set event listener.
     * @param listener The event listener to remove.
     * @return True if the listener was correctly removed. False if not.
     */
    public boolean removeEventListener(EventListener listener) {
        return input.removeListener(listener);
    }

    /**
     * Creates a display and shows it on the screen.
     */
    private void createDisplay() {
        //If displaySettings is null, use default settings.
        if(appSettings.getDisplaySettings() == null) {
            Log.warning("DisplaySettings is null. Launching with default DisplaySettings.");
            appSettings.setDisplaySettings(new DisplaySettingsBuilder().create());
        }
        //Create display
        display = new Display(appSettings.getDisplaySettings());
        printSystemInfo();
        //Set callbacks
        display.setKeyCallback(input.getKeyCallback());
        display.setMouseButtonCallback(input.getMouseBtnCallback());
        display.setCursorPosCallback(input.getCursorPosCallback());
        //Initialize the timer
        lastUpdate = glfwGetTime();
    }

    /**
     * Prints useful system information to console.
     * This info includes GPU vendor, model and OpenGL version.
     */
    private void printSystemInfo() {
        Log.info("System information: " + "\r\n"
                + "\t\t\t\tGPU Vendor: " + glGetString(GL_VENDOR) + "\r\n"
                + "\t\t\t\tGPU Model: " + glGetString(GL_RENDERER) + "\r\n"
                + "\t\t\t\tOpenGL: " + glGetString(GL_VERSION) + "\r\n"
                + "\t\t\t\tGLSL: " + glGetString(GL_SHADING_LANGUAGE_VERSION) + "\r\n"
                + "\t\t\t\tExtensions: " + (glGetString(GL_EXTENSIONS) == null ? "NONE" : glGetString(GL_EXTENSIONS))
        );
    }

    public void pushLayer(Layer layer) {
        layers.add(layer);
    }

    public void removeLayer(Layer layer) {
        layers.remove(layer);
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

}
