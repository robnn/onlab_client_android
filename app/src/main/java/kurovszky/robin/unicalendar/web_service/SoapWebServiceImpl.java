package kurovszky.robin.unicalendar.web_service;

import java.util.List;

import kurovszky.robin.unicalendar.exception.BaseException;
import kurovszky.robin.unicalendar.web_service.error.ErrorObject;
import kurovszky.robin.unicalendar.web_service.model.Comment;
import kurovszky.robin.unicalendar.web_service.model.Institute;
import kurovszky.robin.unicalendar.web_service.model.Subject;
import kurovszky.robin.unicalendar.web_service.model.User;
import kurovszky.robin.unicalendar.web_service.soap.SoapClient;
import kurovszky.robin.unicalendar.web_service.type.ErrorCode;

/**
 * Created by robin on 2017. 05. 03..
 */

public class SoapWebServiceImpl implements WebService {

    private static SoapWebServiceImpl soapWebService = null;
    private SoapClient soapClient;
    private SoapWebServiceImpl() {
        soapClient = new SoapClient();
    }

    public static SoapWebServiceImpl getInstance(User u){
        if(soapWebService==null) {
            soapWebService = new SoapWebServiceImpl();
        }
        soapWebService.authenticate(u);
        return soapWebService;
    }

    @Override
    public void authenticate(User u) {
        soapClient.setAuthData(u);
    }

    @Override
    public Institute getInstituteById(Long id) {
        return soapClient.getInstituteById(id);
    }

    @Override
    public List<Institute> getInstitutes() throws BaseException{
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
    public long getIdByName(User u) throws BaseException{
        return soapClient.getIdByName(u.getUserName());
    }

    @Override
    public long getInstituteIdByName(User u) throws BaseException {
        long id = soapClient.getInstituteIdByName(u.getUserName());
        if(id == 0L){
            throw new BaseException(new ErrorObject(ErrorCode.SERVER_DOWN));
        }
        if(id == -1L)
            throw new BaseException(new ErrorObject(ErrorCode.LOGIN_FAILED));
        return id;
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
