package org.promisedland.minecraft.manager;

import org.promisedland.minecraft.utils.MBCrypt;
import org.promisedland.minecraft.utils.Redis;

import java.sql.SQLException;

public class LoginManager {
    public static boolean isPlayerLogin(String username){
        return Redis.isLoggedIn(username);
    }
    public static boolean isPlayerRegister(String username) {
        return UserManager.checkUserExists(username);
    }
    public static boolean isCorrectPassword(String username,String password){
        return MBCrypt.checkPassword(
                password,
                UserManager.getPassword(username)
        );
    }
}
