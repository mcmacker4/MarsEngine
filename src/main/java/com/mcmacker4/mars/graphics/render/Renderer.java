package com.mcmacker4.mars.graphics.render;

import org.joml.Matrix4f;

/**
 * Created by McMacker4 on 09/07/2016.
 */
public abstract class Renderer {

    private Matrix4f projectionMatrix;

    public abstract void submit(Renderable renderable);
    public abstract void render();

    public void setProjectionMatrix(Matrix4f projectionMatrix) {
        this.projectionMatrix = projectionMatrix;
    }
}
