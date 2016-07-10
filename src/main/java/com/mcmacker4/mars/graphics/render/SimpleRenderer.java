package com.mcmacker4.mars.graphics.render;

import java.util.Queue;

/**
 * Created by McMacker4 on 09/07/2016.
 */
public class SimpleRenderer  extends Renderer {

    private Queue<Renderable> queue;

    @Override
    public void submit(Renderable renderable) {
        queue.add(renderable);
    }

    @Override
    public void render() {
        while(!queue.isEmpty()) {
            Renderable renderable = queue.poll();
        }
    }
}
