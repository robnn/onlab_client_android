package kurovszky.robin.unicalendar.web_service;

import java.util.List;

import kurovszky.robin.unicalendar.web_service.grpc.client.OnlabClient;
import kurovszky.robin.unicalendar.web_service.model.Comment;
import kurovszky.robin.unicalendar.web_service.model.Institute;
import kurovszky.robin.unicalendar.web_service.model.Subject;
import kurovszky.robin.unicalendar.web_service.model.User;

/**
 * Created by robin on 4/10/17.
 */

public class GrpcWebServiceImpl implements WebService {

    static GrpcWebServiceImpl instance = null;
    OnlabClient client;

    public GrpcWebServiceImpl() {
        client = new OnlabClient();
    }

    public static WebService getInstance() {
        if(instance == null)
            instance = new GrpcWebServiceImpl();
        return instance;
    }

    @Override
    public void authenticate(User u) {

    }

    @Override
    public Institute getInstituteById(Long id) {
        return client.getInstituteById(id);
    }

    @Override
    public List<Institute> getInstitutes() {
        return client.getAllInstitutes();
    }

    @Override
    public void addInstitute(Institute i) {
        client.addInstitute(i);
    }

    @Override
    public User register(User u) {
        return client.register(u);
    }

    @Override
    public User getUserByName(String name) {
        //NOT USED
        return null;
    }

    @Override
    public String getNameById(long id) {
        return client.getNamebyId(id);
    }

    @Override
    public long getIdByName(User u) { //FIXME refactor the parameter to string
        return client.getIdByName(u.getUserName());
    }

    @Override
    public long getInstituteIdByName(User u) { //FIXME refactor the parameter to string
        return client.getInstituteIdByName(u.getUserName());
    }

    @Override
    public List<Subject> getSubjectsByInstitute(Institute i) {
        return client.getSubjectsByInstitute(i);
    }

    @Override
    public Subject getSubjectByName(String name) {
        return client.getSubjectByName(name);
    }

    @Override
    public void addSubject(Subject s) {
        client.addSubject(s);
    }

    @Override
    public List<Comment> getCommentsBySubject(Subject s) {
        return client.getCommentsBySubject(s);
    }

    @Override
    public void addComment(Comment c) {
        client.addComment(c);
    }
}
