package com.mcmacker4.mars.application.events;

import org.joml.Vector2d;

/**
 * Created by McMacker4 on 10/07/2016.
 */
public class CursorMoveEvent implements Event {

    //New Position
    Vector2d lastPos;
    //Last Position
    Vector2d newPos;

    protected CursorMoveEvent(Vector2d lastPos, Vector2d newPos) {
        this.lastPos = lastPos;
        this.newPos = newPos;
    }

    public double getX() {
        return newPos.x;
    }

    public double getY() {
        return newPos.y;
    }

    public double oldX() {
        return lastPos.x;
    }

    public double oldY() {
        return lastPos.y;
    }

    public double getDX() {
        return newPos.x - lastPos.x;
    }

    public double getDY() {
        return newPos.y - lastPos.y;
    }

}
