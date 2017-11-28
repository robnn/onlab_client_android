package kurovszky.robin.unicalendar.web_service;

import java.util.List;

import kurovszky.robin.unicalendar.exception.BaseException;
import kurovszky.robin.unicalendar.web_service.model.Comment;
import kurovszky.robin.unicalendar.web_service.model.Institute;
import kurovszky.robin.unicalendar.web_service.model.Subject;
import kurovszky.robin.unicalendar.web_service.model.User;
import kurovszky.robin.unicalendar.web_service.rest.RestClient;

public class RestWebServiceImpl implements WebService {

    private static RestWebServiceImpl instance=null;

    private RestClient restClient;
    private RestWebServiceImpl(User user) {
        restClient = new RestClient(user);
        restClient.authenticate(user);
    }

    public static WebService getInstance(User u) {
        if(instance == null)
            instance = new RestWebServiceImpl(u);
        instance.authenticate(u);
        return instance;
    }

    @Override
    public void authenticate(final User u) {
        restClient.authenticate(u);
    }

    @Override
    public Institute getInstituteById(Long id) {
        return restClient.getInstituteById(id);
    }

    @Override
    public List<Institute> getInstitutes() throws BaseException {
        return restClient.getInstitutes();
    }

    @Override
    public void addInstitute(Institute i) throws BaseException {
        restClient.addInstitute(i);
    }

    @Override
    public User register(User u) throws BaseException {
        return restClient.register(u);
    }

    //only with admin
    @Override
    public User getUserByName(String name) {
       return restClient.getUserByName(name);
    }

    @Override
    public String getNameById(long id) {
        return restClient.getNameById(id);
    }

    @Override
    public long getIdByName(User u) throws BaseException {
        return restClient.getIdByName(u);
    }

    @Override
    public long getInstituteIdByName(User u) throws BaseException {
        return restClient.getInstituteIdByName(u);
    }


    @Override
    public List<Subject> getSubjectsByInstitute(Institute i) {
        return restClient.getSubjectsByInstitute(i);
    }

    @Override
    public Subject getSubjectByName(String name) {
        return restClient.getSubjectByName(name);
    }

    @Override
    public void addSubject(Subject s) {
        restClient.addSubject(s);
    }

    @Override
    public List<Comment> getCommentsBySubject(Subject s){
        return restClient.getCommentsBySubject(s);
    }

    @Override
    public void addComment(Comment c) {
        restClient.addComment(c);
    }
}
