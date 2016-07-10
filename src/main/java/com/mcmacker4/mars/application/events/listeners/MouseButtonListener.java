package com.mcmacker4.mars.application.events.listeners;

import com.mcmacker4.mars.application.events.MouseButtonEvent;

/**
 * Created by McMacker4 on 10/07/2016.
 */
public interface MouseButtonListener extends EventListener {

    void onMouseButtonEvent(MouseButtonEvent event);

}
