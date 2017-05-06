package kurovszky.robin.unicalendar.web_service.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import kurovszky.robin.unicalendar.web_service.GrpcWebServiceImpl;
import kurovszky.robin.unicalendar.web_service.RestWebServiceImpl;
import kurovszky.robin.unicalendar.web_service.SoapWebServiceImpl;
import kurovszky.robin.unicalendar.web_service.WebService;
import kurovszky.robin.unicalendar.web_service.model.User;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by robin on 2017. 03. 19..
 */

public class StaticTools {
    public enum protocol {gRPC, REST, SOAP};
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
    public static protocol loadProtocolFromPrefs(Context ctx){
        String proto = PreferenceManager.getDefaultSharedPreferences(ctx).getString("networking_protocol", "gRPC");
        switch (proto){
            case "gRPC":
                return protocol.gRPC;
            case "REST":
                return protocol.REST;
            case "SOAP":
                return protocol.SOAP;
        }
        return null;
    }
    public static WebService initWebService(Context ctx, User u){
        StaticTools.protocol protocol = StaticTools.loadProtocolFromPrefs(ctx);

        WebService webService;

        switch (protocol){
            case gRPC:
                webService = GrpcWebServiceImpl.getInstance();
                break;
            case REST:
                webService = RestWebServiceImpl.getInstance(u);
                break;
            case SOAP:
                webService = SoapWebServiceImpl.getInstance();
                break;
            default:
                webService = SoapWebServiceImpl.getInstance();
                break;

        }
        return webService;
    }
}
