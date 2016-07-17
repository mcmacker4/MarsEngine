package com.mcmacker4.mars.application.events;

import com.mcmacker4.mars.application.events.listeners.EventListener;
import com.mcmacker4.mars.application.events.listeners.KeyboardListener;
import com.mcmacker4.mars.application.events.listeners.MouseButtonListener;
import com.mcmacker4.mars.application.events.listeners.CursorPosListener;
import org.joml.Vector2d;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.glfwGetKey;

/**
 * Created by McMacker4 on 10/07/2016.
 */
public class Input {

    private List<EventListener> listeners = new LinkedList<>();

    private KeyCallback keyCallback = new KeyCallback();
    private MouseBtnCallback mouseBtnCallback = new MouseBtnCallback();
    private CursorPosCallback mousePosCallback = new CursorPosCallback();

    private HashMap<Integer, Boolean> keys = new HashMap<>();
    private HashMap<Integer, Boolean> buttons = new HashMap<>();
    private Vector2d mousePos = new Vector2d(0, 0);
    private Vector2d mouseDelta = new Vector2d(0, 0);

    public void addListener(EventListener listener) {
        if(!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public boolean removeListener(EventListener listener) {
        return listeners.remove(listener);
    }

    private void throwKeyEvent(KeyboardEvent event) {
        keys.put(event.getKeyCode(), event.getAction() == GLFW_PRESS);
        listeners.stream().filter(listener -> listener instanceof KeyboardListener)
                .forEach(listener -> ((KeyboardListener) listener).onKeyEvent(event));
    }

    private void throwMouseButtonEvent(MouseButtonEvent event) {
        buttons.put(event.getButton(), event.getAction() == GLFW_PRESS);
        listeners.stream().filter(listener -> listener instanceof MouseButtonListener)
                .forEach(listener -> ((MouseButtonListener) listener).onMouseButtonEvent(event));
    }

    private void throwCursorMoveEvent(CursorMoveEvent event) {
        mousePos = new Vector2d(event.getX(), event.getY());
        mouseDelta = new Vector2d(event.getDX(), event.getDY());
        listeners.stream().filter(listener -> listener instanceof CursorPosListener)
                .forEach(listener -> ((CursorPosListener)listener).onCursorMove(event));
    }

    public boolean isKeyDown(int key) {
        if(!keys.containsKey(key)) return false;
        return keys.get(key);
    }

    public boolean isMouseBtnDown(int btn) {
        if(!buttons.containsKey(btn)) return false;
        return buttons.get(btn);
    }

    public Vector2d getMousePos() {
        return mousePos;
    }

    public double getMX() {
        return mousePos.x;
    }

    public double getMY() {
        return mousePos.y;
    }

    public Vector2d getMouseDelta() {
        return mouseDelta;
    }

    public double getDX() {
        return mouseDelta.x;
    }

    public double getDY() {
        return mouseDelta.y;
    }

    private class KeyCallback extends GLFWKeyCallback {
        @Override
        public void invoke(long window, int key, int scancode, int action, int mods) {
            throwKeyEvent(new KeyboardEvent(key, action, mods));
        }
    }

    private class MouseBtnCallback extends GLFWMouseButtonCallback {
        @Override
        public void invoke(long window, int button, int action, int mods) {
            throwMouseButtonEvent(new MouseButtonEvent(button, action, mousePos));
        }
    }

    private class CursorPosCallback extends GLFWCursorPosCallback {
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
