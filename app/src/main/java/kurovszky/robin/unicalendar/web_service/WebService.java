package kurovszky.robin.unicalendar.web_service;

import java.util.List;

import kurovszky.robin.unicalendar.web_service.model.Comment;
import kurovszky.robin.unicalendar.web_service.model.Institute;
import kurovszky.robin.unicalendar.web_service.model.Subject;
import kurovszky.robin.unicalendar.web_service.model.User;

/**
 * Created by robin on 2017. 03. 15..
 */

public interface WebService {
    public void authenticate(User u);

    public Institute getInstituteById(Long id);
    public List<Institute> getInstitutes();
    public void addInstitute(Institute i);

    public User register(User u);
    public User getUserByName(String name);
    public String getNameById(long id);
    public long getIdByName(User u);
    public long getInstituteIdByName(User u);

    public List<Subject> getSubjectsByInstitute(Institute i);
    public Subject getSubjectByName(String name);
    public void addSubject(Subject s);

    public List<Comment> getCommentsBySubject(Subject s);
    public void addComment(Comment c);


}
