package kurovszky.robin.unicalendar.web_service.model;


import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

public class User  implements KvmSerializable{
    private Long id;
    private String userName;
    private String realName;
    private Long instituteId;
    private String password;

    public User() {
    }

    public User(Long id, String userName, String realName, Long instituteId, String password) {
        this.id = id;
        this.userName = userName;
        this.realName = realName;
        this.instituteId = instituteId;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Long getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(Long instituteId) {
        this.instituteId = instituteId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Object getProperty(int index) {
        switch (index){
            case 0:
                return id;
            case 1:
                return userName;
            case 2:
                return realName;
            case 3:
                return instituteId;
            case 4:
                return password;
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 5;
    }

    @Override
    public void setProperty(int index, Object value) {
        switch (index){
            case 0:
                this.id = (Long) value;
                break;
            case 1:
                this.userName = (String) value;
                break;
            case 2:
                this.realName = (String) value;
                break;
            case 3:
                this.instituteId = (Long) value;
                break;
            case 4:
                this.password = (String) value;
                break;
        }
    }

    @Override
    public void getPropertyInfo(int index, Hashtable properties, PropertyInfo info) {
        switch (index){
            case 0:
                info.type = PropertyInfo.LONG_CLASS;
                info.name = "id";
                break;
            case 1:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "userName";
                break;
            case 21:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "realName";
                break;
            case 3:
                info.type = PropertyInfo.LONG_CLASS;
                info.name = "instituteId";
                break;
            case 4:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "password";
                break;
        }
    }
}
