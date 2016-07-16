package com.mcmacker4.mars.application;

import com.mcmacker4.mars.application.display.Display;
import com.mcmacker4.mars.application.display.DisplaySettingsBuilder;
import com.mcmacker4.mars.application.events.Input;
import com.mcmacker4.mars.application.events.listeners.EventListener;
import com.mcmacker4.mars.system.log.Log;

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

    private double lastUpdate = 0;

    public Application(ApplicationSettings settings) {
        if(settings == null) throw new IllegalArgumentException("ApplicationSettings is null.");
        this.appSettings = settings;
    }

    public void start() {
        createDisplay();
        loop();
        destroy();
        Log.info("Goodbye");
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

    public abstract void init();

    private void update() {
        //Calculate the elapsed time between last frame and this frame.
        double now = glfwGetTime();
        double delta = now - lastUpdate;
        lastUpdate = now;
        //TODO: Update stuff
    }

    private void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    /**
     * Destroys the display and terminates GLFW.
     */
    private void destroy() {
        display.destroy();
        glfwTerminate();
    }

    public void addEventListener(EventListener listener) {
        input.addListener(listener);
    }

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

    public boolean isKeyDown(int key) {
        return glfwGetKey(display.getWindow(), key) == GLFW_PRESS;
    }

}
