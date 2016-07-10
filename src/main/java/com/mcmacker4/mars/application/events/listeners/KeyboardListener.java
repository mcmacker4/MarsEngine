package com.mcmacker4.mars.application.events.listeners;

import com.mcmacker4.mars.application.events.KeyboardEvent;

/**
 * Created by McMacker4 on 10/07/2016.
 */
public interface KeyboardListener extends EventListener {

    void onKeyEvent(KeyboardEvent event);

}
