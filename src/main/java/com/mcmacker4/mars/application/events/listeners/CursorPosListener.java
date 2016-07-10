package com.mcmacker4.mars.application.events.listeners;

import com.mcmacker4.mars.application.events.CursorMoveEvent;

/**
 * Created by McMacker4 on 10/07/2016.
 */
public interface CursorPosListener extends EventListener {

    void onCursorMove(CursorMoveEvent event);

}
