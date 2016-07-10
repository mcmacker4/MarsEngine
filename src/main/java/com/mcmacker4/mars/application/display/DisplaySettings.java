package com.mcmacker4.mars.application.display;

import org.joml.Vector2i;
import org.joml.Vector3f;

/**
 * Created by McMacker4 on 09/07/2016.
 */
public class DisplaySettings {

    private String title;
    private Vector2i resolution;
    private boolean resizable;
    private boolean fullscreen;
    private boolean vsync;
    private Vector3f clearColor;

    public DisplaySettings(String title, Vector2i resolution, boolean resizable, boolean fullscreen, boolean vsync, Vector3f clearColor) {
        this.title = title;
        this.resolution = resolution;
        this.resizable = resizable;
        this.fullscreen = fullscreen;
        this.vsync = vsync;
        this.clearColor = clearColor;
    }

    protected String getTitle() {
        return title;
    }

    protected int getWidth() {
        return resolution.x;
    }

    protected int getHeight() {
        return resolution.y;
    }

    protected Vector2i getResolution() {
        return resolution;
    }

    protected boolean isResizable() {
        return resizable;
    }

    protected boolean isFullscreen() {
        return fullscreen;
    }

    protected boolean isVsync() {
        return vsync;
    }

    protected Vector3f getClearColor() {
        return clearColor;
    }
}
