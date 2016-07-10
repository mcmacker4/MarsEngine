package com.mcmacker4.mars.application.display;

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

        glfwWindowHint(GLFW_RESIZABLE, settings.isResizable() ? GL_TRUE : GL_FALSE);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE);

        glfw_window = glfwCreateWindow(settings.getWidth(), settings.getHeight(), settings.getTitle(), NULL, NULL);

        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(glfw_window,
                (vidmode.width() - settings.getWidth()) / 2,
                (vidmode.height() - settings.getHeight()) / 2
        );

        glfwMakeContextCurrent(glfw_window);

        glfwSwapInterval(settings.isvSync() ? 1 : 0);

        GL.createCapabilities();

        glfwShowWindow(glfw_window);

    }

    public boolean shouldClose() {
        return glfwWindowShouldClose(glfw_window);
    }

    public void swapBuffers() {
        glfwSwapBuffers(glfw_window);
    }

    public void destroy() {
        glfwDestroyWindow(glfw_window);
    }

}
