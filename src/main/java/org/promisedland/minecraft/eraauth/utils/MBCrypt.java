package org.promisedland.minecraft.eraauth.utils;

import org.mindrot.jbcrypt.BCrypt;

public class MBCrypt {
    public static String encodedPassword(String password){
        return BCrypt.hashpw(password,BCrypt.gensalt());
    }
    public static boolean checkPassword(String password,String encodedPassword){
        return BCrypt.checkpw(password,encodedPassword);
    }
}
