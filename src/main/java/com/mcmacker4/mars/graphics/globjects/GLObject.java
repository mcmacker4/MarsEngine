package com.mcmacker4.mars.graphics.globjects;

/**
 * Created by McMacker4 on 15/07/2016.
 */
public abstract class GLObject {

    int id;

    public int getId() {
        return id;
    }

    public abstract void bind();
    public abstract void unbind();

}
