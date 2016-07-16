package com.mcmacker4.mars.graphics.glbuffers;

import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryUtil;

import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL15.*;

/**
 * Created by McMacker4 on 15/07/2016.
 */
public class GLIndicesBuffer extends GLBuffer {

    public GLIndicesBuffer(int[] data) {
        IntBuffer buffer = storeInIntBuffer(data);
        construct(buffer);
    }

    public GLIndicesBuffer(IntBuffer buffer) {
        construct(buffer);
    }

    private void construct(IntBuffer buffer) {
        id = glGenBuffers();
        bind();
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        unbind();
    }

    private IntBuffer storeInIntBuffer(int[] data) {
        IntBuffer buffer = MemoryUtil.memAllocInt(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    @Override
    public void bind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, id);
    }

    @Override
    public void unbind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }
}
