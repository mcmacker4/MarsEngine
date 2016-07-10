package com.mcmacker4.mars.application.events;

import org.joml.Vector2d;

/**
 * Created by McMacker4 on 10/07/2016.
 */
public class MouseButtonEvent implements Event {

    int btn;
    int action;
    Vector2d pos;

    protected MouseButtonEvent(int btn, int action, Vector2d pos) {
        this.btn = btn;
        this.action = action;
        this.pos = pos;
    }

    public int getButton() {
        return btn;
    }

    public int getAction() {
        return action;
    }

    public double getX() {
        return pos.x;
    }

    public double getY() {
        return pos.y;
    }

}
