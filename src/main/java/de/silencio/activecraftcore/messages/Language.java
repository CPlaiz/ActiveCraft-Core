package de.silencio.activecraftcore.messages;

public enum Language {

    EN,
    DE;

    Language fromString(String text) {
        for (Language lang : Language.values()) {
            if (text.equalsIgnoreCase(lang.name())) {
                return lang;
            }
        }
        return null;
    }

}
