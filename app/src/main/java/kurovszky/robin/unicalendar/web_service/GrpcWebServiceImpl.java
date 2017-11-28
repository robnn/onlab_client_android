package kurovszky.robin.unicalendar.web_service;

import java.util.List;

import kurovszky.robin.unicalendar.exception.BaseException;
import kurovszky.robin.unicalendar.web_service.error.ErrorObject;
import kurovszky.robin.unicalendar.web_service.grpc.client.GrpcClient;
import kurovszky.robin.unicalendar.web_service.model.Comment;
import kurovszky.robin.unicalendar.web_service.model.Institute;
import kurovszky.robin.unicalendar.web_service.model.Subject;
import kurovszky.robin.unicalendar.web_service.model.User;
import kurovszky.robin.unicalendar.web_service.type.ErrorCode;

public class GrpcWebServiceImpl implements WebService {

    private static GrpcWebServiceImpl instance = null;
    private GrpcClient client;

    public GrpcWebServiceImpl() {
        client = new GrpcClient();
    }

    public static WebService getInstance(User user, boolean allowedWithoutLogin) throws BaseException {
        if(instance == null)
            instance = new GrpcWebServiceImpl();
        if(!allowedWithoutLogin)
            instance.authenticate(user);
        return instance;
    }

    @Override
    public void authenticate(User u) throws BaseException {
        if(!client.validateUser(u))
            throw new BaseException(new ErrorObject(ErrorCode.LOGIN_FAILED));
    }

    @Override
    public Institute getInstituteById(Long id) throws BaseException {
        return client.getInstituteById(id);
    }

    @Override
    public List<Institute> getInstitutes() throws BaseException {
        return client.getAllInstitutes();
    }

    @Override
    public void addInstitute(Institute i) throws BaseException {
        client.addInstitute(i);
    }

    @Override
    public User register(User u) throws BaseException {
        return client.register(u);
    }

    @Override
    public User getUserByName(String name) {
        //NOT USED
        return null;
    }

    @Override
    public String getNameById(long id) throws BaseException {
        return client.getNamebyId(id);
    }

    @Override
    public long getIdByName(User u) throws BaseException {
        return client.getIdByName(u.getUserName());
    }

    @Override
    public long getInstituteIdByName(User u) throws BaseException {
        return client.getInstituteIdByName(u.getUserName());
    }

    @Override
    public List<Subject> getSubjectsByInstitute(Institute i) throws BaseException {
        return client.getSubjectsByInstitute(i);
    }

    @Override
    public Subject getSubjectByName(String name) throws BaseException {
        return client.getSubjectByName(name);
    }

    @Override
    public void addSubject(Subject s) throws BaseException {
        client.addSubject(s);
    }

    @Override
    public List<Comment> getCommentsBySubject(Subject s) throws BaseException {
        return client.getCommentsBySubject(s);
    }

    @Override
    public void addComment(Comment c) throws BaseException {
        client.addComment(c);
    }
}
