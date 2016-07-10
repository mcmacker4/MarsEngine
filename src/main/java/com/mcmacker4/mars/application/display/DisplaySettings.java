package com.mcmacker4.mars.application.display;

/**
 * Created by McMacker4 on 09/07/2016.
 */
public class DisplaySettings {

    private int width, height;
    private boolean fullscreen;

    private boolean vSync;
    private boolean resizable;

    private String title;

    public DisplaySettings() {
        width = 1280; height = 720;
        fullscreen = false;
        vSync = false;
        resizable = false;
    }

    public String getTitle() {
        return title;
    }

    public DisplaySettings setTitle(String title) {
        this.title = title;
        return this;
    }

    public boolean isResizable() {
        return resizable;
    }

    public DisplaySettings setResizable(boolean resizable) {
        this.resizable = resizable;
        return this;
    }

    public DisplaySettings setResolution(int width, int height) {
        setWidth(width);
        setHeight(height);
        return this;
    }

    public int getWidth() {
        return width;
    }

    public DisplaySettings setWidth(int width) {
        this.width = width;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public DisplaySettings setHeight(int height) {
        this.height = height;
        return this;
    }

    public boolean isFullscreen() {
        return fullscreen;
    }

    public DisplaySettings setFullscreen(boolean fullscreen) {
        this.fullscreen = fullscreen;
        return this;
    }

    public boolean isvSync() {
        return vSync;
    }

    public DisplaySettings setvSync(boolean vSync) {
        this.vSync = vSync;
        return this;
    }
}
