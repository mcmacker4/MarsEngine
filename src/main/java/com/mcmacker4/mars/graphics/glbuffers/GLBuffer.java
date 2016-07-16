package com.mcmacker4.mars.graphics.glbuffers;

/**
 * Created by McMacker4 on 15/07/2016.
 */
public abstract class GLBuffer {

    int id = 0;

    public int getId() {
        return id;
    }

    public abstract void bind();
    public abstract void unbind();

}
