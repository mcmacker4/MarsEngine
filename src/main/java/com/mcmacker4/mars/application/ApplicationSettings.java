package com.mcmacker4.mars.application;

import com.mcmacker4.mars.application.display.DisplaySettings;

/**
 * Created by McMacker4 on 09/07/2016.
 */
public class ApplicationSettings {

    DisplaySettings displaySettings;

    public DisplaySettings getDisplaySettings() {
        return displaySettings;
    }

    public ApplicationSettings setDisplaySettings(DisplaySettings displaySettings) {
        this.displaySettings = displaySettings;
        return this;
    }
}
