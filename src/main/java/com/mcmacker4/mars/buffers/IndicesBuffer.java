package com.mcmacker4.mars.buffers;

import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL15.*;

/**
 * Created by McMacker4 on 09/07/2016.
 */
public class IndicesBuffer extends Buffer {

    public IndicesBuffer(IntBuffer data) {
        id = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, id);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, data, GL_STATIC_DRAW);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }

}
