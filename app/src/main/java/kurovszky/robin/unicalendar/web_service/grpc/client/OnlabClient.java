package kurovszky.robin.unicalendar.web_service.grpc.client;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
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

public class OnlabClient {
    private static final Logger logger = Logger.getLogger(OnlabClient.class.getName());

    private final ManagedChannel channel;
    private final InstituteServiceGrpc.InstituteServiceBlockingStub instituteBlockingStub;
    private final UserServiceGrpc.UserServiceBlockingStub userBlockingStub;
    private final SubjectServiceGrpc.SubjectServiceBlockingStub subjectBlockingStub;
    private final CommentServiceGrpc.CommentServiceBlockingStub commentBlockingStub;
    private final InstituteServiceGrpc.InstituteServiceStub asyncStub;

    public static final String URL = "robinandroidrest.hopto.org";
    public static final int port = 8010;
    private TestHelper testHelper;

    public OnlabClient() {
        this(ManagedChannelBuilder.forAddress(URL, port).usePlaintext(true));
    }

    public OnlabClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext(true));
    }
    private OnlabClient(ManagedChannelBuilder<?> channelBuilder) {
        channel = channelBuilder.build();
        instituteBlockingStub = InstituteServiceGrpc.newBlockingStub(channel);
        userBlockingStub = UserServiceGrpc.newBlockingStub(channel);
        subjectBlockingStub = SubjectServiceGrpc.newBlockingStub(channel);
        commentBlockingStub = CommentServiceGrpc.newBlockingStub(channel);
        asyncStub = InstituteServiceGrpc.newStub(channel);
        testHelper = new TestHelper();
    }
    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
    public void testGetInstitutes() {
        info("*** printing all institutes");

        InstituteList list;
        try {
            list = instituteBlockingStub.getAllInstitutes(Empty.getDefaultInstance());
            if (testHelper != null) {
                testHelper.onMessage(list);
            }
        } catch (StatusRuntimeException e) {
            warning("RPC failed: {0}", e.getStatus());
            if (testHelper != null) {
                testHelper.onRpcError(e);
            }
        }
    }
    public void addTestInstitute(){
        info("adding institute");
        Institute.Builder builder = Institute.newBuilder();
        builder.setId(9999);
        builder.setInstituteName("TesztInstitute");
        Institute toadd = builder.build();
        try {
            instituteBlockingStub.addInstitute(toadd);
        } catch (StatusRuntimeException e) {
            warning("RPC failed: {0}", e.getStatus());
            if (testHelper != null) {
                testHelper.onRpcError(e);
            }
        }
    }
    public List<kurovszky.robin.unicalendar.web_service.model.Institute> getAllInstitutes(){
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
    public kurovszky.robin.unicalendar.web_service.model.Institute getInstituteById(long id){
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
    public  void addInstitute(kurovszky.robin.unicalendar.web_service.model.Institute institute){
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
    public User register(User u){
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
    public String getNamebyId(long id){
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
    public long getIdByName(String name){
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
    public long getInstituteIdByName(String name){
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
    public List<kurovszky.robin.unicalendar.web_service.model.Subject> getSubjectsByInstitute(kurovszky.robin.unicalendar.web_service.model.Institute institute){
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
    public Subject getSubjectByName(String name){
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
    public void addSubject(Subject subject){
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
    public List<Comment> getCommentsBySubject(Subject subject){
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
    public void addComment(Comment comment){
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
    private void info(String msg, Object... params) {
        //System.out.println(msg);
        logger.log(Level.INFO, msg, params);
    }
    private void warning(String msg, Object... params) {
        logger.log(Level.WARNING, msg, params);
    }

    private class TestHelper {
        void onMessage(InstituteList list) {
            List<Institute> toprint = list.getInstitutesList();
            for(Institute i : toprint){
                info(" id: " +i.getId() + " name: " + i.getInstituteName());
            }
        }

        void onRpcError(Throwable e) {
            e.printStackTrace();
        }
    }
}
