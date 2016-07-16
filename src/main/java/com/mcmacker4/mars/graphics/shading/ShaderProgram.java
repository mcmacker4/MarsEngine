package com.mcmacker4.mars.graphics.shading;

import com.mcmacker4.mars.system.log.Log;

import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL20.*;

/**
 * Created by McMacker4 on 16/07/2016.
 */
public abstract class ShaderProgram {

    protected int program;

    public ShaderProgram(String vShader, String fShader) {
        int vertex = createShader(GL_VERTEX_SHADER, vShader);
        int fragment = createShader(GL_FRAGMENT_SHADER, fShader);
        program = glCreateProgram();
        glAttachShader(program, vertex);
        glAttachShader(program, fragment);
        glLinkProgram(program);
        if(glGetProgrami(program, GL_LINK_STATUS) != GL_TRUE) {
            Log.error("Could not link program: \r\n" + glGetProgramInfoLog(program));
        }
        glValidateProgram(program);
        if(glGetProgrami(program, GL_VALIDATE_STATUS) != GL_TRUE) {
            Log.error("Error validating program: " + glGetProgramInfoLog(program));
        }
        getUniformLocations();
    }

    protected abstract void getUniformLocations();

    private int createShader(int type, String source) {
        int shader = glCreateShader(type);
        glShaderSource(shader, source);
        glCompileShader(shader);
        if(glGetShaderi(shader, GL_COMPILE_STATUS) != GL_TRUE) {
            String msg = "Could not compile " + ((type == GL_VERTEX_SHADER) ? "vertex " : "fragment ") + "shader:\r\n";
            msg += glGetShaderInfoLog(shader);
            Log.error(msg);
        }
        return shader;
    }

    public void start() {
        glUseProgram(program);
    }

    public void stop() {
        glUseProgram(0);
    }

}
