package kurovszky.robin.unicalendar.web_service.grpc.client;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import kurovszky.robin.unicalendar.exception.BaseException;
import kurovszky.robin.unicalendar.web_service.error.ErrorObject;
import kurovszky.robin.unicalendar.web_service.grpc.generated.CommentServiceGrpc;
import kurovszky.robin.unicalendar.web_service.grpc.generated.Empty;
import kurovszky.robin.unicalendar.web_service.grpc.generated.Institute;
import kurovszky.robin.unicalendar.web_service.grpc.generated.InstituteId;
import kurovszky.robin.unicalendar.web_service.grpc.generated.InstituteList;
import kurovszky.robin.unicalendar.web_service.grpc.generated.InstituteServiceGrpc;
import kurovszky.robin.unicalendar.web_service.grpc.generated.SubjectName;
import kurovszky.robin.unicalendar.web_service.grpc.generated.SubjectServiceGrpc;
import kurovszky.robin.unicalendar.web_service.grpc.generated.UserId;
import kurovszky.robin.unicalendar.web_service.grpc.generated.UserName;
import kurovszky.robin.unicalendar.web_service.grpc.generated.UserServiceGrpc;
import kurovszky.robin.unicalendar.web_service.model.Comment;
import kurovszky.robin.unicalendar.web_service.model.Subject;
import kurovszky.robin.unicalendar.web_service.model.User;
import kurovszky.robin.unicalendar.web_service.tools.GrpcMapper;
import kurovszky.robin.unicalendar.web_service.type.ErrorCode;

public class GrpcClient {
    private static final Logger logger = Logger.getLogger(GrpcClient.class.getName());

    private final ManagedChannel channel;
    private final InstituteServiceGrpc.InstituteServiceBlockingStub instituteBlockingStub;
    private final UserServiceGrpc.UserServiceBlockingStub userBlockingStub;
    private final SubjectServiceGrpc.SubjectServiceBlockingStub subjectBlockingStub;
    private final CommentServiceGrpc.CommentServiceBlockingStub commentBlockingStub;
    private final InstituteServiceGrpc.InstituteServiceStub asyncStub;

    public static final String URL = "robnn.dynu.net";
    public static final int port = 8010;
    private TestHelper testHelper;

    public GrpcClient() {
        this(ManagedChannelBuilder.forAddress(URL, port).usePlaintext(true));
    }

    public GrpcClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext(true));
    }
    private GrpcClient(ManagedChannelBuilder<?> channelBuilder) {
        channel = channelBuilder.build();
        instituteBlockingStub = InstituteServiceGrpc.newBlockingStub(channel);
        userBlockingStub = UserServiceGrpc.newBlockingStub(channel);
        subjectBlockingStub = SubjectServiceGrpc.newBlockingStub(channel);
        commentBlockingStub = CommentServiceGrpc.newBlockingStub(channel);
        asyncStub = InstituteServiceGrpc.newStub(channel);
        testHelper = new TestHelper();
    }

    public List<kurovszky.robin.unicalendar.web_service.model.Institute> getAllInstitutes() throws BaseException {
        InstituteList list = null;
        try {
            list = instituteBlockingStub.getAllInstitutes(Empty.getDefaultInstance());
        } catch (StatusRuntimeException e) {
            warning("RPC failed: {0}", e.getStatus());
            if (testHelper != null) {
                testHelper.onRpcError(e);
            }
        }
        return GrpcMapper.mapInstituteList(list);
    }
    public kurovszky.robin.unicalendar.web_service.model.Institute getInstituteById(long id) throws BaseException {
        Institute institute = null;
        try {
            institute = instituteBlockingStub.getInstitutebyId(GrpcMapper.instituteIdMap(id));
        } catch (StatusRuntimeException e) {
            warning("RPC failed: {0}", e.getStatus());
            if (testHelper != null) {
                testHelper.onRpcError(e);
            }
        }
        return GrpcMapper.mapInstitute(institute);
    }
    public  void addInstitute(kurovszky.robin.unicalendar.web_service.model.Institute institute) throws BaseException {
        Institute mapped = GrpcMapper.addInstituteMap(institute);
        try {
            instituteBlockingStub.addInstitute(mapped);
        } catch (StatusRuntimeException e) {
            warning("RPC failed: {0}", e.getStatus());
            if (testHelper != null) {
                testHelper.onRpcError(e);
            }
        }
    }
    public boolean validateUser(User u) throws BaseException {
        kurovszky.robin.unicalendar.web_service.grpc.generated.User mapped = GrpcMapper.userNameAndPasswordMap(u);
        boolean isValid = false;
        try {
            isValid = GrpcMapper.mapBoolean(userBlockingStub.validate(mapped));
        }catch (StatusRuntimeException e) {
            warning("RPC failed: {0}", e.getStatus());
            if (testHelper != null) {
                testHelper.onRpcError(e);
            }
        }
        return isValid;
    }
    public User register(User u) throws BaseException {
        kurovszky.robin.unicalendar.web_service.grpc.generated.User mapped = GrpcMapper.registerMap(u);
        try {
            userBlockingStub.register(mapped);
            u.setId(GrpcMapper.mapUserId(userBlockingStub.getIdByName(GrpcMapper.userNameMap(u.getUserName()))));
        } catch (StatusRuntimeException e) {
            warning("RPC failed: {0}", e.getStatus());
            if (testHelper != null) {
                testHelper.onRpcError(e);
            }
        }
        return  u;
    }
    public String getNamebyId(long id) throws BaseException {
        kurovszky.robin.unicalendar.web_service.grpc.generated.UserId mapped = GrpcMapper.userIdMap(id);
        UserName userName = null;
        try {
            userName = userBlockingStub.getNameById(mapped);
        } catch (StatusRuntimeException e) {
            warning("RPC failed: {0}", e.getStatus());
            if (testHelper != null) {
                testHelper.onRpcError(e);
            }
        }
        return  GrpcMapper.mapUserName(userName);

    }
    public long getIdByName(String name) throws BaseException {
        UserId userId = null;
        UserName mapped = GrpcMapper.userNameMap(name);
        try {
            userId = userBlockingStub.getIdByName(mapped);
        } catch (StatusRuntimeException e) {
            warning("RPC failed: {0}", e.getStatus());
            if (testHelper != null) {
                testHelper.onRpcError(e);
            }
        }
        return  GrpcMapper.mapUserId(userId);
    }
    public long getInstituteIdByName(String name) throws BaseException {
        InstituteId instituteId = null;
        UserName mapped = GrpcMapper.userNameMap(name);
        try {
            instituteId = userBlockingStub.getInstituteIdByName(mapped);
        } catch (StatusRuntimeException e) {
            warning("RPC failed: {0}", e.getStatus());
            if (testHelper != null) {
                testHelper.onRpcError(e);
            }
        }
        return  GrpcMapper.mapInstituteId(instituteId);
    }
    public List<kurovszky.robin.unicalendar.web_service.model.Subject> getSubjectsByInstitute(kurovszky.robin.unicalendar.web_service.model.Institute institute) throws BaseException {
        Institute mapped = GrpcMapper.addInstituteMap(institute);
        List<Subject> subjects = null;
        try {
            subjects = GrpcMapper.mapSubjectList(subjectBlockingStub.getSubjectsByInstitute(mapped));
        } catch (StatusRuntimeException e) {
            warning("RPC failed: {0}", e.getStatus());
            if (testHelper != null) {
                testHelper.onRpcError(e);
            }
        }
        return subjects;
    }
    public Subject getSubjectByName(String name) throws BaseException {
        Subject subject = null;
        SubjectName mapped = GrpcMapper.subjectNameMap(name);
        try {
            subject = GrpcMapper.mapSubject(subjectBlockingStub.getSubjectByName(mapped));
        } catch (StatusRuntimeException e) {
            warning("RPC failed: {0}", e.getStatus());
            if (testHelper != null) {
                testHelper.onRpcError(e);
            }
        }
        return subject;
    }
    public void addSubject(Subject subject) throws BaseException {
        kurovszky.robin.unicalendar.web_service.grpc.generated.Subject mapped = GrpcMapper.addSubjectMap(subject);
        try {
            subjectBlockingStub.addSubject(mapped);
        } catch (StatusRuntimeException e) {
            warning("RPC failed: {0}", e.getStatus());
            if (testHelper != null) {
                testHelper.onRpcError(e);
            }
        }
    }
    public List<Comment> getCommentsBySubject(Subject subject) throws BaseException {
        List<Comment> comments = null;
        kurovszky.robin.unicalendar.web_service.grpc.generated.Subject mapped = GrpcMapper.addSubjectMap(subject);
        try {
            comments = GrpcMapper.mapCommentList(commentBlockingStub.getCommentsBySubject(mapped));
        } catch (StatusRuntimeException e) {
            warning("RPC failed: {0}", e.getStatus());
            if (testHelper != null) {
                testHelper.onRpcError(e);
            }
        }
        return comments;
    }
    public void addComment(Comment comment) throws BaseException {
        kurovszky.robin.unicalendar.web_service.grpc.generated.Comment mapped = GrpcMapper.addCommentMap(comment);
        try {
            commentBlockingStub.addComment(mapped);
        } catch (StatusRuntimeException e) {
            warning("RPC failed: {0}", e.getStatus());
            if (testHelper != null) {
                testHelper.onRpcError(e);
            }
        }
    }
    private void warning(String msg, Object... params) {
        logger.log(Level.WARNING, msg, params);
    }

    private class TestHelper {
        void onRpcError(Throwable e) throws BaseException {
            throw new BaseException(new ErrorObject(ErrorCode.SERVER_DOWN));
        }
    }
}
