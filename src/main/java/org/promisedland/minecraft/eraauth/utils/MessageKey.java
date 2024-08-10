package org.promisedland.minecraft.eraauth.utils;

public enum MessageKey {
    KICK_UNRESOLVED_HOSTNAME("kick.unresolvedhostname");

    private String key;
    private String[] tags;

    MessageKey(String key,String... tags){
        this.key = key;
        this.tags = tags;
    }

    public String getKey(){
        return key;
    }
    public String[] getTags(){
        return tags;
    }

    @Override
    public String toString() {
        return key;
    }
}
