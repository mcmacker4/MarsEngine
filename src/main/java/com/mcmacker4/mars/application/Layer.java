package com.mcmacker4.mars.application;

/**
 * Created by McMacker4 on 17/07/2016.
 */
public interface Layer {

    void init();
    void update(double delta);
    void render();
    void destroy();

}
