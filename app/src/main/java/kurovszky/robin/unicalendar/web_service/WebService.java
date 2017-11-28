package kurovszky.robin.unicalendar.web_service;

import java.util.List;

import kurovszky.robin.unicalendar.exception.BaseException;
import kurovszky.robin.unicalendar.web_service.model.Comment;
import kurovszky.robin.unicalendar.web_service.model.Institute;
import kurovszky.robin.unicalendar.web_service.model.Subject;
import kurovszky.robin.unicalendar.web_service.model.User;

/**
 * Created by robin on 2017. 03. 15..
 */

public interface WebService {
    public void authenticate(User u) throws BaseException;

    public Institute getInstituteById(Long id) throws BaseException;
    public List<Institute> getInstitutes() throws BaseException;
    public void addInstitute(Institute i) throws BaseException;

    public User register(User u) throws BaseException;
    public User getUserByName(String name);
    public String getNameById(long id) throws BaseException;
    public long getIdByName(User u) throws BaseException;
    public long getInstituteIdByName(User u) throws BaseException;

    public List<Subject> getSubjectsByInstitute(Institute i) throws BaseException;
    public Subject getSubjectByName(String name) throws BaseException;
    public void addSubject(Subject s) throws BaseException;

    public List<Comment> getCommentsBySubject(Subject s) throws BaseException;
    public void addComment(Comment c) throws BaseException;


}
