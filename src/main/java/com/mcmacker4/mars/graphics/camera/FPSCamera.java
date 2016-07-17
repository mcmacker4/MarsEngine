package com.mcmacker4.mars.graphics.camera;

import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * Created by McMacker4 on 17/07/2016.
 */
public class FPSCamera extends Camera {

    public FPSCamera(Vector3f position, float fov, float aspect) {
        super(new Matrix4f().setPerspective(fov, aspect, 0.1f, 100f), position);
    }

}
