package com.mcmacker4.mars.application.events;

/**
 * Created by McMacker4 on 10/07/2016.
 */
public class KeyboardEvent implements Event {

    private int key, action, mods;

    protected KeyboardEvent(int key, int action, int mods) {
        this.key = key;
        this.action = action;
        this.mods = mods;
    }

    public int getKey() {
        return key;
    }

    public int getAction() {
        return action;
    }

    public int getMods() {
        return mods;
    }

}
