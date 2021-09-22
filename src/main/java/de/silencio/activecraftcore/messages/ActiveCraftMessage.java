package de.silencio.activecraftcore.messages;

import de.silencio.activecraftcore.utils.FileConfig;

public abstract class ActiveCraftMessage {

    protected static FileConfig fileConfig;

    public ActiveCraftMessage(String input) {
        fileConfig = new FileConfig(input);
    }

    protected static String getMessage(String input) {
        return fileConfig.getString(input);
    }

}
