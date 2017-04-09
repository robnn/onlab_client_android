package kurovszky.robin.unicalendar.web_service.model;


public class User  {
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
}
