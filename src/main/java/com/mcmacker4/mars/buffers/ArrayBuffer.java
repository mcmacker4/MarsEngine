package com.mcmacker4.mars.buffers;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL15.*;

/**
 * Created by McMacker4 on 09/07/2016.
 */
public class ArrayBuffer extends Buffer {

    public ArrayBuffer(FloatBuffer data) {
        id = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, id);
        glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

}
