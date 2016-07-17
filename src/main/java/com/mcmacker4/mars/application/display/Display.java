package com.mcmacker4.mars.application.display;

import com.mcmacker4.mars.application.events.Input;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * Created by McMacker4 on 09/07/2016.
 */
public class Display {

    private long glfw_window;

    //TODO: Fullscreen support
    public Display(DisplaySettings settings) {

        if(!glfwInit())
            throw new IllegalStateException("Could not initialize GLFW.");

        //Set window hints. Display configuration.
        glfwWindowHint(GLFW_RESIZABLE, settings.isResizable() ? GL_TRUE : GL_FALSE);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE);

        //Create window.
        glfw_window = glfwCreateWindow(settings.getWidth(), settings.getHeight(), settings.getTitle(), NULL, NULL);

        //Center window on screen.
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(glfw_window,
                (vidmode.width() - settings.getWidth()) / 2,
                (vidmode.height() - settings.getHeight()) / 2
        );

        glfwMakeContextCurrent(glfw_window);

        glfwSwapInterval(settings.isVsync() ? 1 : 0);

        GL.createCapabilities();

        glfwShowWindow(glfw_window);

        Vector3f cc = settings.getClearColor();
        glClearColor(cc.x, cc.y, cc.z, 1.0f);

    }

    public void setKeyCallback(GLFWKeyCallback callback) {
        glfwSetKeyCallback(glfw_window, callback);
    }

    public void setMouseButtonCallback(GLFWMouseButtonCallback callback) {
        glfwSetMouseButtonCallback(glfw_window, callback);
    }

    public void setCursorPosCallback(GLFWCursorPosCallback callback) {
        glfwSetCursorPosCallback(glfw_window, callback);
    }

    public void close() {
        glfwSetWindowShouldClose(glfw_window, true);
    }

    /**
     * Returns if the window is going to close.
     * @return true if the window is closing.
     */
    public boolean shouldClose() {
        return glfwWindowShouldClose(glfw_window);
    }

    /**
     * Swap the buffers of this screen.
     */
    public void swapBuffers() {
        glfwSwapBuffers(glfw_window);
    }

    /**
     * Destroy this display.
     */
    public void destroy() {
        glfwDestroyWindow(glfw_window);
    }

    protected long getWindow() {
        return glfw_window;
    }
}
