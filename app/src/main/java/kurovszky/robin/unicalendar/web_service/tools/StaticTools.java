package kurovszky.robin.unicalendar.web_service.tools;

import android.content.Context;
import android.content.SharedPreferences;

import kurovszky.robin.unicalendar.web_service.model.User;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by robin on 2017. 03. 19..
 */

public class StaticTools {
    public static User loadUserFromPrefs(Context ctx){
        SharedPreferences userDetails = ctx.getSharedPreferences("userDetails",MODE_PRIVATE);
        String userName = userDetails.getString("userName", null);
        String passwordPref = userDetails.getString("password", "");
        Long id = userDetails.getLong("id",0);
        Long instituteId = userDetails.getLong("instituteId",0);
        String realName = userDetails.getString("realName","");
        User toReturn = new User(id,userName,realName,instituteId,passwordPref);
        return toReturn;
    }
}
