package kurovszky.robin.unicalendar.web_service.model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

/**
 * Created by robin on 2017. 03. 15..
 */

public class Comment implements KvmSerializable {
    private Long id;
    private String commentText;
    private Long userId;
    private Long subjectId;
    private String userName;

    public Comment() {
    }

    public Comment(Long id, String commentText, Long userId, Long subjectId, String userName) {
        this.id = id;
        this.commentText = commentText;
        this.userId = userId;
        this.subjectId = subjectId;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    @Override
    public Object getProperty(int index) {
        switch (index){
            case 0:
                return id;
            case 1:
                return commentText;
            case 2:
                return userId;
            case 3:
                return subjectId;
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 4;
    }

    @Override
    public void setProperty(int index, Object value) {
        switch (index){
            case 0:
                id = (Long) value;
                break;
            case 1:
                commentText = (String) value;
                break;
            case 2:
                userId = (Long) value;
                break;
            case 3:
                subjectId = (Long) value;
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
                info.name = "commentText";
                break;
            case 2:
                info.type = PropertyInfo.LONG_CLASS;
                info.name= "userId";
                break;
            case 3:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "subjectId";
                break;
        }
    }
}
