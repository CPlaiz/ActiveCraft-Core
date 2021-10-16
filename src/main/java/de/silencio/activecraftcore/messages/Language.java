package de.silencio.activecraftcore.messages;

public enum Language {

    EN("English"),
    DE("German"),
    SV("Swedish");

    private final String name;
    private final String code;

    Language(String name) {
        this.name = name;
        this.code = name();
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    Language fromString(String text) {
        for (Language lang : Language.values()) {
            if (text.equalsIgnoreCase(lang.name())) {
                return lang;
            }
        }
        return null;
    }

}
