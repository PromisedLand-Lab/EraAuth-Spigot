package org.promisedland.minecraft.eraauth.manager;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import org.promisedland.minecraft.eraauth.utils.DataBase;

public class UserManager {
    public static String getPassword(String username) {
        return DataBase.searchValue("users","password","username",username);
    }

    public static boolean registerUser(String username,String password,String UUID,String ip) {
        return DataBase.insertValue(
                "users",
                new String[]{"username","password","UUID","email","ip","register_time","retrieve","nickname","special_value"},
                new String[]{username,password,UUID,"",ip, String.valueOf(System.currentTimeMillis()),"",username, NanoIdUtils.randomNanoId()}
        );
    }
    public static boolean checkUserExists(String username) {
        return DataBase.checkExists("users","username",username);
    }

}
