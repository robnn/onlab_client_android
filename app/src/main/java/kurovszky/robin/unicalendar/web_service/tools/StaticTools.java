package kurovszky.robin.unicalendar.web_service.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import kurovszky.robin.unicalendar.exception.BaseException;
import kurovszky.robin.unicalendar.web_service.GrpcWebServiceImpl;
import kurovszky.robin.unicalendar.web_service.RestWebServiceImpl;
import kurovszky.robin.unicalendar.web_service.SoapWebServiceImpl;
import kurovszky.robin.unicalendar.web_service.WebService;
import kurovszky.robin.unicalendar.web_service.error.ErrorObject;
import kurovszky.robin.unicalendar.web_service.model.User;
import kurovszky.robin.unicalendar.web_service.type.ErrorCode;
import kurovszky.robin.unicalendar.web_service.type.Transaction;

import static android.content.Context.MODE_PRIVATE;

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
        String proto = PreferenceManager.getDefaultSharedPreferences(ctx).getString("networking_protocol", "REST");
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


    public static WebService initWebService(Context ctx, User u, Transaction transaction) throws BaseException {
        return initWebService(ctx,u,transaction.isAllowedWithoutLogin());

    }

    public static WebService initWebService(Context ctx, User u, boolean allowedWithoutLogin) throws BaseException {
        StaticTools.protocol protocol = StaticTools.loadProtocolFromPrefs(ctx);

        WebService webService;
        if(protocol == null)
            protocol = StaticTools.protocol.REST;
        switch (protocol){
            case gRPC:
                //in this case we need to check manually if it is allowed
                webService = GrpcWebServiceImpl.getInstance(u, allowedWithoutLogin);
                break;
            case REST:
                webService = RestWebServiceImpl.getInstance(u);
                break;
            case SOAP:
                webService = SoapWebServiceImpl.getInstance(u);
                break;
            default:
                webService = SoapWebServiceImpl.getInstance(u);
                break;

        }
        return webService;
    }

    public static <T> T checkResponse(T o) throws BaseException {
        if(o == null){
            throw new BaseException(new ErrorObject(ErrorCode.SERVER_DOWN));
        }
        return o;
    }
}
