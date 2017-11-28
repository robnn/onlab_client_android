package kurovszky.robin.unicalendar.web_service.grpc.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 *for subjects
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.6.1)",
    comments = "Source: handler.proto")
public final class SubjectServiceGrpc {

  private SubjectServiceGrpc() {}

  public static final String SERVICE_NAME = "SubjectService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<kurovszky.robin.unicalendar.web_service.grpc.generated.Institute,
      kurovszky.robin.unicalendar.web_service.grpc.generated.SubjectList> METHOD_GET_SUBJECTS_BY_INSTITUTE =
      io.grpc.MethodDescriptor.<kurovszky.robin.unicalendar.web_service.grpc.generated.Institute, kurovszky.robin.unicalendar.web_service.grpc.generated.SubjectList>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "SubjectService", "getSubjectsByInstitute"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              kurovszky.robin.unicalendar.web_service.grpc.generated.Institute.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              kurovszky.robin.unicalendar.web_service.grpc.generated.SubjectList.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<kurovszky.robin.unicalendar.web_service.grpc.generated.SubjectName,
      kurovszky.robin.unicalendar.web_service.grpc.generated.Subject> METHOD_GET_SUBJECT_BY_NAME =
      io.grpc.MethodDescriptor.<kurovszky.robin.unicalendar.web_service.grpc.generated.SubjectName, kurovszky.robin.unicalendar.web_service.grpc.generated.Subject>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "SubjectService", "getSubjectByName"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              kurovszky.robin.unicalendar.web_service.grpc.generated.SubjectName.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              kurovszky.robin.unicalendar.web_service.grpc.generated.Subject.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<kurovszky.robin.unicalendar.web_service.grpc.generated.Subject,
      kurovszky.robin.unicalendar.web_service.grpc.generated.Empty> METHOD_ADD_SUBJECT =
      io.grpc.MethodDescriptor.<kurovszky.robin.unicalendar.web_service.grpc.generated.Subject, kurovszky.robin.unicalendar.web_service.grpc.generated.Empty>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "SubjectService", "addSubject"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              kurovszky.robin.unicalendar.web_service.grpc.generated.Subject.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              kurovszky.robin.unicalendar.web_service.grpc.generated.Empty.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SubjectServiceStub newStub(io.grpc.Channel channel) {
    return new SubjectServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SubjectServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new SubjectServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SubjectServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new SubjectServiceFutureStub(channel);
  }

  /**
   * <pre>
   *for subjects
   * </pre>
   */
  public static abstract class SubjectServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getSubjectsByInstitute(kurovszky.robin.unicalendar.web_service.grpc.generated.Institute request,
        io.grpc.stub.StreamObserver<kurovszky.robin.unicalendar.web_service.grpc.generated.SubjectList> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_SUBJECTS_BY_INSTITUTE, responseObserver);
    }

    /**
     */
    public void getSubjectByName(kurovszky.robin.unicalendar.web_service.grpc.generated.SubjectName request,
        io.grpc.stub.StreamObserver<kurovszky.robin.unicalendar.web_service.grpc.generated.Subject> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_SUBJECT_BY_NAME, responseObserver);
    }

    /**
     */
    public void addSubject(kurovszky.robin.unicalendar.web_service.grpc.generated.Subject request,
        io.grpc.stub.StreamObserver<kurovszky.robin.unicalendar.web_service.grpc.generated.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_ADD_SUBJECT, responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_SUBJECTS_BY_INSTITUTE,
            asyncUnaryCall(
              new MethodHandlers<
                kurovszky.robin.unicalendar.web_service.grpc.generated.Institute,
                kurovszky.robin.unicalendar.web_service.grpc.generated.SubjectList>(
                  this, METHODID_GET_SUBJECTS_BY_INSTITUTE)))
          .addMethod(
            METHOD_GET_SUBJECT_BY_NAME,
            asyncUnaryCall(
              new MethodHandlers<
                kurovszky.robin.unicalendar.web_service.grpc.generated.SubjectName,
                kurovszky.robin.unicalendar.web_service.grpc.generated.Subject>(
                  this, METHODID_GET_SUBJECT_BY_NAME)))
          .addMethod(
            METHOD_ADD_SUBJECT,
            asyncUnaryCall(
              new MethodHandlers<
                kurovszky.robin.unicalendar.web_service.grpc.generated.Subject,
                kurovszky.robin.unicalendar.web_service.grpc.generated.Empty>(
                  this, METHODID_ADD_SUBJECT)))
          .build();
    }
  }

  /**
   * <pre>
   *for subjects
   * </pre>
   */
  public static final class SubjectServiceStub extends io.grpc.stub.AbstractStub<SubjectServiceStub> {
    private SubjectServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SubjectServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected SubjectServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SubjectServiceStub(channel, callOptions);
    }

    /**
     */
    public void getSubjectsByInstitute(kurovszky.robin.unicalendar.web_service.grpc.generated.Institute request,
        io.grpc.stub.StreamObserver<kurovszky.robin.unicalendar.web_service.grpc.generated.SubjectList> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_SUBJECTS_BY_INSTITUTE, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getSubjectByName(kurovszky.robin.unicalendar.web_service.grpc.generated.SubjectName request,
        io.grpc.stub.StreamObserver<kurovszky.robin.unicalendar.web_service.grpc.generated.Subject> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_SUBJECT_BY_NAME, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addSubject(kurovszky.robin.unicalendar.web_service.grpc.generated.Subject request,
        io.grpc.stub.StreamObserver<kurovszky.robin.unicalendar.web_service.grpc.generated.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_ADD_SUBJECT, getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   *for subjects
   * </pre>
   */
  public static final class SubjectServiceBlockingStub extends io.grpc.stub.AbstractStub<SubjectServiceBlockingStub> {
    private SubjectServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SubjectServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected SubjectServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SubjectServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public kurovszky.robin.unicalendar.web_service.grpc.generated.SubjectList getSubjectsByInstitute(kurovszky.robin.unicalendar.web_service.grpc.generated.Institute request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_SUBJECTS_BY_INSTITUTE, getCallOptions(), request);
    }

    /**
     */
    public kurovszky.robin.unicalendar.web_service.grpc.generated.Subject getSubjectByName(kurovszky.robin.unicalendar.web_service.grpc.generated.SubjectName request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_SUBJECT_BY_NAME, getCallOptions(), request);
    }

    /**
     */
    public kurovszky.robin.unicalendar.web_service.grpc.generated.Empty addSubject(kurovszky.robin.unicalendar.web_service.grpc.generated.Subject request) {
      return blockingUnaryCall(
          getChannel(), METHOD_ADD_SUBJECT, getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *for subjects
   * </pre>
   */
  public static final class SubjectServiceFutureStub extends io.grpc.stub.AbstractStub<SubjectServiceFutureStub> {
    private SubjectServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SubjectServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected SubjectServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SubjectServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<kurovszky.robin.unicalendar.web_service.grpc.generated.SubjectList> getSubjectsByInstitute(
        kurovszky.robin.unicalendar.web_service.grpc.generated.Institute request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_SUBJECTS_BY_INSTITUTE, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<kurovszky.robin.unicalendar.web_service.grpc.generated.Subject> getSubjectByName(
        kurovszky.robin.unicalendar.web_service.grpc.generated.SubjectName request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_SUBJECT_BY_NAME, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<kurovszky.robin.unicalendar.web_service.grpc.generated.Empty> addSubject(
        kurovszky.robin.unicalendar.web_service.grpc.generated.Subject request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_ADD_SUBJECT, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_SUBJECTS_BY_INSTITUTE = 0;
  private static final int METHODID_GET_SUBJECT_BY_NAME = 1;
  private static final int METHODID_ADD_SUBJECT = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SubjectServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SubjectServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_SUBJECTS_BY_INSTITUTE:
          serviceImpl.getSubjectsByInstitute((kurovszky.robin.unicalendar.web_service.grpc.generated.Institute) request,
              (io.grpc.stub.StreamObserver<kurovszky.robin.unicalendar.web_service.grpc.generated.SubjectList>) responseObserver);
          break;
        case METHODID_GET_SUBJECT_BY_NAME:
          serviceImpl.getSubjectByName((kurovszky.robin.unicalendar.web_service.grpc.generated.SubjectName) request,
              (io.grpc.stub.StreamObserver<kurovszky.robin.unicalendar.web_service.grpc.generated.Subject>) responseObserver);
          break;
        case METHODID_ADD_SUBJECT:
          serviceImpl.addSubject((kurovszky.robin.unicalendar.web_service.grpc.generated.Subject) request,
              (io.grpc.stub.StreamObserver<kurovszky.robin.unicalendar.web_service.grpc.generated.Empty>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class SubjectServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return kurovszky.robin.unicalendar.web_service.grpc.generated.Handler.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (SubjectServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SubjectServiceDescriptorSupplier())
              .addMethod(METHOD_GET_SUBJECTS_BY_INSTITUTE)
              .addMethod(METHOD_GET_SUBJECT_BY_NAME)
              .addMethod(METHOD_ADD_SUBJECT)
              .build();
        }
      }
    }
    return result;
  }
}
