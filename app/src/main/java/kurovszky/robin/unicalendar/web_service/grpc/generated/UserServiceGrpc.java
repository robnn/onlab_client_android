package kurovszky.robin.unicalendar.web_service.grpc.generated;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 *methods needed for users
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.2.0)",
    comments = "Source: handler.proto")
public final class UserServiceGrpc {

  private UserServiceGrpc() {}

  public static final String SERVICE_NAME = "UserService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<User,
          User> METHOD_REGISTER =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "UserService", "register"),
          io.grpc.protobuf.ProtoUtils.marshaller(User.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(User.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<UserId,
          UserName> METHOD_GET_NAME_BY_ID =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "UserService", "getNameById"),
          io.grpc.protobuf.ProtoUtils.marshaller(UserId.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(UserName.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<UserName,
          UserId> METHOD_GET_ID_BY_NAME =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "UserService", "getIdByName"),
          io.grpc.protobuf.ProtoUtils.marshaller(UserName.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(UserId.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<UserName,
          InstituteId> METHOD_GET_INSTITUTE_ID_BY_NAME =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "UserService", "getInstituteIdByName"),
          io.grpc.protobuf.ProtoUtils.marshaller(UserName.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(InstituteId.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UserServiceStub newStub(io.grpc.Channel channel) {
    return new UserServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UserServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new UserServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static UserServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new UserServiceFutureStub(channel);
  }

  /**
   * <pre>
   *methods needed for users
   * </pre>
   */
  public static abstract class UserServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void register(User request,
        io.grpc.stub.StreamObserver<User> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_REGISTER, responseObserver);
    }

    /**
     */
    public void getNameById(UserId request,
        io.grpc.stub.StreamObserver<UserName> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_NAME_BY_ID, responseObserver);
    }

    /**
     */
    public void getIdByName(UserName request,
        io.grpc.stub.StreamObserver<UserId> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_ID_BY_NAME, responseObserver);
    }

    /**
     */
    public void getInstituteIdByName(UserName request,
        io.grpc.stub.StreamObserver<InstituteId> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_INSTITUTE_ID_BY_NAME, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_REGISTER,
            asyncUnaryCall(
              new MethodHandlers<
                      User,
                      User>(
                  this, METHODID_REGISTER)))
          .addMethod(
            METHOD_GET_NAME_BY_ID,
            asyncUnaryCall(
              new MethodHandlers<
                      UserId,
                      UserName>(
                  this, METHODID_GET_NAME_BY_ID)))
          .addMethod(
            METHOD_GET_ID_BY_NAME,
            asyncUnaryCall(
              new MethodHandlers<
                      UserName,
                      UserId>(
                  this, METHODID_GET_ID_BY_NAME)))
          .addMethod(
            METHOD_GET_INSTITUTE_ID_BY_NAME,
            asyncUnaryCall(
              new MethodHandlers<
                      UserName,
                      InstituteId>(
                  this, METHODID_GET_INSTITUTE_ID_BY_NAME)))
          .build();
    }
  }

  /**
   * <pre>
   *methods needed for users
   * </pre>
   */
  public static final class UserServiceStub extends io.grpc.stub.AbstractStub<UserServiceStub> {
    private UserServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserServiceStub(channel, callOptions);
    }

    /**
     */
    public void register(User request,
        io.grpc.stub.StreamObserver<User> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_REGISTER, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getNameById(UserId request,
        io.grpc.stub.StreamObserver<UserName> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_NAME_BY_ID, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getIdByName(UserName request,
        io.grpc.stub.StreamObserver<UserId> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_ID_BY_NAME, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getInstituteIdByName(UserName request,
        io.grpc.stub.StreamObserver<InstituteId> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_INSTITUTE_ID_BY_NAME, getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   *methods needed for users
   * </pre>
   */
  public static final class UserServiceBlockingStub extends io.grpc.stub.AbstractStub<UserServiceBlockingStub> {
    private UserServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public User register(User request) {
      return blockingUnaryCall(
          getChannel(), METHOD_REGISTER, getCallOptions(), request);
    }

    /**
     */
    public UserName getNameById(UserId request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_NAME_BY_ID, getCallOptions(), request);
    }

    /**
     */
    public UserId getIdByName(UserName request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_ID_BY_NAME, getCallOptions(), request);
    }

    /**
     */
    public InstituteId getInstituteIdByName(UserName request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_INSTITUTE_ID_BY_NAME, getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *methods needed for users
   * </pre>
   */
  public static final class UserServiceFutureStub extends io.grpc.stub.AbstractStub<UserServiceFutureStub> {
    private UserServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<User> register(
        User request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_REGISTER, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<UserName> getNameById(
        UserId request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_NAME_BY_ID, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<UserId> getIdByName(
        UserName request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_ID_BY_NAME, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<InstituteId> getInstituteIdByName(
        UserName request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_INSTITUTE_ID_BY_NAME, getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER = 0;
  private static final int METHODID_GET_NAME_BY_ID = 1;
  private static final int METHODID_GET_ID_BY_NAME = 2;
  private static final int METHODID_GET_INSTITUTE_ID_BY_NAME = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final UserServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(UserServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REGISTER:
          serviceImpl.register((User) request,
              (io.grpc.stub.StreamObserver<User>) responseObserver);
          break;
        case METHODID_GET_NAME_BY_ID:
          serviceImpl.getNameById((UserId) request,
              (io.grpc.stub.StreamObserver<UserName>) responseObserver);
          break;
        case METHODID_GET_ID_BY_NAME:
          serviceImpl.getIdByName((UserName) request,
              (io.grpc.stub.StreamObserver<UserId>) responseObserver);
          break;
        case METHODID_GET_INSTITUTE_ID_BY_NAME:
          serviceImpl.getInstituteIdByName((UserName) request,
              (io.grpc.stub.StreamObserver<InstituteId>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class UserServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return Handler.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (UserServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UserServiceDescriptorSupplier())
              .addMethod(METHOD_REGISTER)
              .addMethod(METHOD_GET_NAME_BY_ID)
              .addMethod(METHOD_GET_ID_BY_NAME)
              .addMethod(METHOD_GET_INSTITUTE_ID_BY_NAME)
              .build();
        }
      }
    }
    return result;
  }
}
