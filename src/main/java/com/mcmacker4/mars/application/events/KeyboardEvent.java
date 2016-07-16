package com.mcmacker4.mars.application.events;

/**
 * Created by McMacker4 on 10/07/2016.
 */
public class KeyboardEvent implements Event {

    private int keyCode, action, mods;

    protected KeyboardEvent(int keyCode, int action, int mods) {
        this.keyCode = keyCode;
        this.action = action;
        this.mods = mods;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public int getAction() {
        return action;
    }

    public int getMods() {
        return mods;
    }

}
