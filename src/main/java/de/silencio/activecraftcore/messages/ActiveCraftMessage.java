package de.silencio.activecraftcore.messages;

import de.silencio.activecraftcore.ActiveCraftCore;
import de.silencio.activecraftcore.utils.FileConfig;

public class ActiveCraftMessage {

    private FileConfig fileConfig;

    public ActiveCraftMessage() {
        fileConfig = new FileConfig("messages.yml");
    }

    public String getMessage(MessageType type, String input) {
        return fileConfig.getString(ActiveCraftCore.getLanguage().name().toLowerCase()
                + "." + type.name().toLowerCase()
                + "." + input);
    }

    public String getMessage(MessageType type, CommandType cmdType , String input) {
        return fileConfig.getString(ActiveCraftCore.getLanguage().name().toLowerCase()
                + "." + type.name().toLowerCase()
                + "." + cmdType.name().toLowerCase().replace("_", "-")
                + "." + input);
    }
}