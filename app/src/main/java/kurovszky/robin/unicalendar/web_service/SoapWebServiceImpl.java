package kurovszky.robin.unicalendar.web_service;

import java.util.List;

import kurovszky.robin.unicalendar.web_service.model.Comment;
import kurovszky.robin.unicalendar.web_service.model.Institute;
import kurovszky.robin.unicalendar.web_service.model.Subject;
import kurovszky.robin.unicalendar.web_service.model.User;
import kurovszky.robin.unicalendar.web_service.soap.SoapClient;

/**
 * Created by robin on 2017. 05. 03..
 */

public class SoapWebServiceImpl implements WebService {

    static SoapWebServiceImpl soapWebService = null;
    SoapClient soapClient;
    private SoapWebServiceImpl() {
        soapClient = new SoapClient();
    }

    public static SoapWebServiceImpl getInstance(){
        if(soapWebService==null)
            soapWebService = new SoapWebServiceImpl();
        return soapWebService;
    }

    @Override
    public void authenticate(User u) {

    }

    @Override
    public Institute getInstituteById(Long id) {
        return soapClient.getInstituteById(id);
    }

    @Override
    public List<Institute> getInstitutes() {
        return soapClient.getInstitutes();
    }

    @Override
    public void addInstitute(Institute i) {
        soapClient.addInstitute(i);
    }

    @Override
    public User register(User u) {
        return soapClient.register(u);
    }

    @Override
    public User getUserByName(String name) {
        return null;
    }

    @Override
    public String getNameById(long id) {
        return soapClient.getNamebyId(id);
    }

    @Override
    public long getIdByName(User u) {
        return soapClient.getIdByName(u.getUserName());
    }

    @Override
    public long getInstituteIdByName(User u) {
        return soapClient.getInstituteIdByName(u.getUserName());
    }

    @Override
    public List<Subject> getSubjectsByInstitute(Institute i) {
        return soapClient.getSubjectsByInstitute(i);
    }

    @Override
    public Subject getSubjectByName(String name) {
        return soapClient.getSubjectByName(name);
    }

    @Override
    public void addSubject(Subject s) {
        soapClient.addSubject(s);
    }

    @Override
    public List<Comment> getCommentsBySubject(Subject s) {
        return soapClient.getCommentsBySubject(s);
    }

    @Override
    public void addComment(Comment c) {
        soapClient.addComment(c);
    }
}
