package com.mcmacker4.mars.graphics.globjects;

import static org.lwjgl.opengl.GL30.*;

/**
 * Created by McMacker4 on 16/07/2016.
 */
public class GLFrameBuffer extends GLObject {

    int buffer_id;

    public GLFrameBuffer() {
        id = glGenFramebuffers();
    }

    @Override
    public void bind() {
        glBindFramebuffer(GL_FRAMEBUFFER, id);
    }

    @Override
    public void unbind() {
        glBindFramebuffer(GL_FRAMEBUFFER, id);
    }

}
