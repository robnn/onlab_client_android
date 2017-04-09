package kurovszky.robin.unicalendar.web_service.model;

/**
 * Created by robin on 2017. 03. 15..
 */

public class Comment {
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
}
