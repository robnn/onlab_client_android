package kurovszky.robin.unicalendar.web_service.tools;

import java.util.ArrayList;
import java.util.List;

import kurovszky.robin.unicalendar.web_service.model.Comment;
import kurovszky.robin.unicalendar.web_service.model.Institute;
import kurovszky.robin.unicalendar.web_service.model.Subject;
import kurovszky.robin.unicalendar.web_service.model.User;

public class GrpcMapper {
    public static boolean mapBoolean(kurovszky.robin.unicalendar.web_service.grpc.generated.Boolean tomap){
        return tomap.getValid();
    }
    public static Institute mapInstitute(kurovszky.robin.unicalendar.web_service.grpc.generated.Institute tomap){
        Institute institute = new Institute();
        institute.setId(tomap.getId());
        institute.setName(tomap.getInstituteName());
        return institute;
    }
    public static List<Institute> mapInstituteList(kurovszky.robin.unicalendar.web_service.grpc.generated.InstituteList tomap){
        List<Institute> institutes = new ArrayList<>();
        for(kurovszky.robin.unicalendar.web_service.grpc.generated.Institute i : tomap.getInstitutesList()){
            institutes.add(GrpcMapper.mapInstitute(i));
        }
        return institutes;
    }
    public static Comment mapComment(kurovszky.robin.unicalendar.web_service.grpc.generated.Comment tomap){
        Comment comment = new Comment();
        comment.setId(tomap.getId());
        comment.setCommentText(tomap.getCommentText());
        comment.setSubjectId(tomap.getSubjectId());
        comment.setUserId(tomap.getUserId());
        return comment;
    }
    public static List<Comment> mapCommentList(kurovszky.robin.unicalendar.web_service.grpc.generated.CommentList tomap){
        List<Comment> comments = new ArrayList<>();
        for(kurovszky.robin.unicalendar.web_service.grpc.generated.Comment c : tomap.getCommentsList()){
            comments.add(GrpcMapper.mapComment(c));
        }
        return comments;
    }
    public static Subject mapSubject(kurovszky.robin.unicalendar.web_service.grpc.generated.Subject tomap){
        Subject subject = new Subject();
        subject.setId(tomap.getId());
        subject.setName(tomap.getName());
        subject.setInstituteId(tomap.getInstituteId());
        subject.setSemester(tomap.getSemester());
        return subject;
    }
    public static List<Subject> mapSubjectList(kurovszky.robin.unicalendar.web_service.grpc.generated.SubjectList tomap){
        List<Subject> subjects = new ArrayList<>();
        for(kurovszky.robin.unicalendar.web_service.grpc.generated.Subject s : tomap.getSubjectsList()){
            subjects.add(GrpcMapper.mapSubject(s));
        }
        return subjects;
    }
    public static User mapUser(kurovszky.robin.unicalendar.web_service.grpc.generated.User tomap){
        User user = new User();
        user.setId(tomap.getId());
        user.setInstituteId(tomap.getInstituteId());
        user.setPassword(tomap.getPassword());
        user.setRealName(tomap.getRealName());
        user.setUserName(tomap.getUserName());
        return user;
    }
    public static String mapUserName(kurovszky.robin.unicalendar.web_service.grpc.generated.UserName tomap){
        return tomap.getName();
    }
    public static long mapUserId(kurovszky.robin.unicalendar.web_service.grpc.generated.UserId tomap){
        return tomap.getId();
    }
    public static long mapInstituteId(kurovszky.robin.unicalendar.web_service.grpc.generated.InstituteId tomap){
        return tomap.getId();
    }
    //to grpc from model

    public static kurovszky.robin.unicalendar.web_service.grpc.generated.Boolean booleanMap(boolean tomap){
        kurovszky.robin.unicalendar.web_service.grpc.generated.Boolean.Builder builder = kurovszky.robin.unicalendar.web_service.grpc.generated.Boolean.newBuilder();
        builder.setValid(tomap);
        return builder.build();
    }
    public static kurovszky.robin.unicalendar.web_service.grpc.generated.Institute addInstituteMap(Institute tomap){
        kurovszky.robin.unicalendar.web_service.grpc.generated.Institute institute;
        kurovszky.robin.unicalendar.web_service.grpc.generated.Institute.Builder builder = kurovszky.robin.unicalendar.web_service.grpc.generated.Institute.newBuilder();
        builder.setId(tomap.getId());
        builder.setInstituteName(tomap.getName());
        institute = builder.build();
        return institute;
    }
    public static kurovszky.robin.unicalendar.web_service.grpc.generated.Subject addSubjectMap(Subject tomap){
        kurovszky.robin.unicalendar.web_service.grpc.generated.Subject subject;
        kurovszky.robin.unicalendar.web_service.grpc.generated.Subject.Builder builder = kurovszky.robin.unicalendar.web_service.grpc.generated.Subject.newBuilder();
        builder.setId(tomap.getId());
        builder.setInstituteId(tomap.getInstituteId());
        builder.setName(tomap.getName());
        builder.setSemester(tomap.getSemester());
        subject = builder.build();
        return subject;
    }
    public static kurovszky.robin.unicalendar.web_service.grpc.generated.Comment addCommentMap(Comment tomap){
        kurovszky.robin.unicalendar.web_service.grpc.generated.Comment comment;
        kurovszky.robin.unicalendar.web_service.grpc.generated.Comment.Builder builder = kurovszky.robin.unicalendar.web_service.grpc.generated.Comment.newBuilder();
        builder.setId(tomap.getId());
        builder.setCommentText(tomap.getCommentText());
        builder.setSubjectId(tomap.getSubjectId());
        builder.setUserId(tomap.getUserId());
        comment = builder.build();
        return comment;
    }
    public static kurovszky.robin.unicalendar.web_service.grpc.generated.SubjectName subjectNameMap(String tomap){
        kurovszky.robin.unicalendar.web_service.grpc.generated.SubjectName subjectName;
        kurovszky.robin.unicalendar.web_service.grpc.generated.SubjectName.Builder builder= kurovszky.robin.unicalendar.web_service.grpc.generated.SubjectName.newBuilder();
        builder.setName(tomap);
        subjectName = builder.build();
        return subjectName;
    }
    public static kurovszky.robin.unicalendar.web_service.grpc.generated.UserId userIdMap(long tomap){
        kurovszky.robin.unicalendar.web_service.grpc.generated.UserId userId;
        kurovszky.robin.unicalendar.web_service.grpc.generated.UserId.Builder builder = kurovszky.robin.unicalendar.web_service.grpc.generated.UserId.newBuilder();
        builder.setId(tomap);
        userId = builder.build();
        return userId;
    }
    public static kurovszky.robin.unicalendar.web_service.grpc.generated.UserName userNameMap(String tomap){
        return kurovszky.robin.unicalendar.web_service.grpc.generated.UserName.newBuilder().setName(tomap).build();
    }
    public static kurovszky.robin.unicalendar.web_service.grpc.generated.InstituteId instituteIdMap(long tomap){
        return kurovszky.robin.unicalendar.web_service.grpc.generated.InstituteId.newBuilder().setId(tomap).build();
    }
    public  static kurovszky.robin.unicalendar.web_service.grpc.generated.User registerMap(User tomap){
        kurovszky.robin.unicalendar.web_service.grpc.generated.User.Builder builder = kurovszky.robin.unicalendar.web_service.grpc.generated.User.newBuilder();
        builder.setId(tomap.getId());
        builder.setInstituteId(tomap.getInstituteId());
        builder.setPassword(tomap.getPassword());
        builder.setUserName(tomap.getUserName());
        builder.setRealName(tomap.getRealName());
        return builder.build();
    }

    public static kurovszky.robin.unicalendar.web_service.grpc.generated.User userNameAndPasswordMap(User tomap){
        kurovszky.robin.unicalendar.web_service.grpc.generated.User.Builder builder = kurovszky.robin.unicalendar.web_service.grpc.generated.User.newBuilder();
        builder.setPassword(tomap.getPassword());
        builder.setUserName(tomap.getUserName());
        return builder.build();
    }
}
