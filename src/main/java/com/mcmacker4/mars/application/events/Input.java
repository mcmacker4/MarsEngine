package com.mcmacker4.mars.application.events;

import com.mcmacker4.mars.application.events.listeners.EventListener;
import com.mcmacker4.mars.application.events.listeners.KeyboardListener;
import com.mcmacker4.mars.application.events.listeners.MouseButtonListener;
import com.mcmacker4.mars.application.events.listeners.CursorPosListener;
import org.joml.Vector2d;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by McMacker4 on 10/07/2016.
 */
public class Input {

    private List<EventListener> listeners = new LinkedList<>();

    private Vector2d mousePos = new Vector2d(0, 0);

    private KeyCallback keyCallback = new KeyCallback();
    private MouseBtnCallback mouseBtnCallback = new MouseBtnCallback();
    private CursorPosCallback mousePosCallback = new CursorPosCallback();

    public void addListener(EventListener listener) {
        if(!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public boolean removeListener(EventListener listener) {
        return listeners.remove(listener);
    }

    private void throwKeyEvent(KeyboardEvent event) {
        listeners.stream().filter(listener -> listener instanceof KeyboardListener)
                .forEach(listener -> ((KeyboardListener) listener).onKeyEvent(event));
    }

    private void throwMouseButtonEvent(MouseButtonEvent event) {
        listeners.stream().filter(listener -> listener instanceof MouseButtonListener)
                .forEach(listener -> ((MouseButtonListener) listener).onMouseButtonEvent(event));
    }

    private void throwCursorMoveEvent(CursorMoveEvent event) {
        listeners.stream().filter(listener -> listener instanceof CursorPosListener)
                .forEach(listener -> ((CursorPosListener)listener).onCursorMove(event));
    }

    class KeyCallback extends GLFWKeyCallback {
        @Override
        public void invoke(long window, int key, int scancode, int action, int mods) {
            throwKeyEvent(new KeyboardEvent(key, action, mods));
        }
    }

    class MouseBtnCallback extends GLFWMouseButtonCallback {
        @Override
        public void invoke(long window, int button, int action, int mods) {
            throwMouseButtonEvent(new MouseButtonEvent(button, action, mousePos));
        }
    }

    class CursorPosCallback extends GLFWCursorPosCallback {
        @Override
        public void invoke(long window, double xpos, double ypos) {
            throwCursorMoveEvent(new CursorMoveEvent(mousePos, new Vector2d(xpos, ypos)));
            mousePos.x = xpos; mousePos.y = ypos;
        }
    }

    public KeyCallback getKeyCallback() {
        return keyCallback;
    }

    public MouseBtnCallback getMouseBtnCallback() {
        return mouseBtnCallback;
    }

    public CursorPosCallback getCursorPosCallback() {
        return mousePosCallback;
    }

}
