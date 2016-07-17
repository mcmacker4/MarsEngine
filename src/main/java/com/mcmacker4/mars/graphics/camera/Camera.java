package com.mcmacker4.mars.graphics.camera;

import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * Created by McMacker4 on 17/07/2016.
 */
public abstract class Camera {

    protected Matrix4f projectionMatrix;
    protected Vector3f position, rotation;

    public Camera(Matrix4f projectionMatrix) {
        this.projectionMatrix = projectionMatrix;
    }

    public Camera(Matrix4f projectionMatrix, Vector3f position) {
        this(projectionMatrix);
        this.position = position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public void setPosition(float x, float y, float z) {
        this.position = new Vector3f(x, y, z);
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public void setRotation(float x, float y, float z) {
        this.rotation = new Vector3f(x, y, z);
    }

    public Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }

    public Matrix4f getViewMatrix() {
        return new Matrix4f()
                .rotateX(-rotation.x)
                .rotateY(-rotation.y)
                .rotateZ(-rotation.z)
                .translate(-position.x, -position.y, -position.z);
    }

}
