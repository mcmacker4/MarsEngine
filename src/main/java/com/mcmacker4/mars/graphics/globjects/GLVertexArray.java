package com.mcmacker4.mars.graphics.globjects;

import com.mcmacker4.mars.graphics.glbuffers.GLArrayBuffer;
import com.mcmacker4.mars.graphics.glbuffers.GLIndicesBuffer;

import java.util.HashMap;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.*;

/**
 * Created by McMacker4 on 15/07/2016.
 */
public class GLVertexArray extends GLObject {

    public GLVertexArray() {
        id = glGenVertexArrays();
    }

    public GLVertexArray(GLIndicesBuffer indices, HashMap<Integer, GLArrayBuffer> buffers) {
        this();
        storeIndices(indices);
        buffers.forEach(this::storeBuffer);
    }

    public void storeBuffer(int index, GLArrayBuffer buffer) {
        bind();
        glBindBuffer(GL_ARRAY_BUFFER, buffer.getId());
        glVertexAttribIPointer(index, buffer.getVertexSize(), GL_FLOAT, 0, 0);
        glEnableVertexAttribArray(index);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        unbind();
    }

    public void storeIndices(GLIndicesBuffer buffer) {
        bind();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, buffer.getId());
        unbind();
    }

    @Override
    public void bind() {
        glBindVertexArray(id);
    }

    @Override
    public void unbind() {
        glBindVertexArray(id);
    }
}
