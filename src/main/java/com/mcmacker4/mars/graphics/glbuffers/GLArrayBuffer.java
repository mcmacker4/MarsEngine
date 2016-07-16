package com.mcmacker4.mars.graphics.glbuffers;

import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;

/**
 * Created by McMacker4 on 15/07/2016.
 */
public class GLArrayBuffer extends GLBuffer {

    private int vertexSize;

    public GLArrayBuffer(float[] data, int vertexSize) {
        FloatBuffer buffer = storeInIntBuffer(data);
        this.vertexSize = vertexSize;
        construct(buffer);
    }

    public GLArrayBuffer(FloatBuffer buffer, int vertexSize) {
        this.vertexSize = vertexSize;
        construct(buffer);
    }

    private void construct(FloatBuffer buffer) {
        id = glGenBuffers();
        bind();
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        unbind();
    }

    private FloatBuffer storeInIntBuffer(float[] data) {
        FloatBuffer buffer = MemoryUtil.memAllocFloat(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public int getVertexSize() {
        return vertexSize;
    }

    @Override
    public void bind() {
        glBindBuffer(GL_ARRAY_BUFFER, id);
    }

    @Override
    public void unbind() {
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }
}
