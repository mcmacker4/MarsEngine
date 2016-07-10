package com.mcmacker4.mars.application.display;

import org.joml.Vector2i;
import org.joml.Vector3f;

/**
 * Created by McMacker4 on 10/07/2016.
 */
public class DisplaySettingsBuilder {

    private String title = "Mars Engine";
    private Vector2i resolution = new Vector2i(1280, 720);
    private boolean resizable = false;
    private boolean fullscreen = false;
    private boolean vsync = false;
    private Vector3f clearColor = new Vector3f(0.0f, 0.0f, 0.0f);

    public DisplaySettingsBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public DisplaySettingsBuilder setWidth(int width) {
        this.resolution.x = width;
        return this;
    }

    public DisplaySettingsBuilder setHeight(int height) {
        this.resolution.y = height;
        return this;
    }

    public DisplaySettingsBuilder setResolution(Vector2i resolution) {
        this.resolution = resolution;
        return this;
    }

    public DisplaySettingsBuilder setResizable(boolean resizable) {
        this.resizable = resizable;
        return this;
    }

    public DisplaySettingsBuilder setFullscreen(boolean fullscreen) {
        this.fullscreen = fullscreen;
        return this;
    }

    public DisplaySettingsBuilder setVsync(boolean vsync) {
        this.vsync = vsync;
        return this;
    }

    public DisplaySettingsBuilder setClearColor(Vector3f clearColor) {
        this.clearColor = clearColor;
        return this;
    }

    public DisplaySettingsBuilder setClearColor(float r, float g, float b) {
        this.clearColor = new Vector3f(r, g, b);
        return this;
    }

    public DisplaySettings create() {
        return new DisplaySettings(title, resolution, resizable, fullscreen, vsync, clearColor);
    }

}
