package kurovszky.robin.unicalendar.web_service.grpc.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 *methods needed for users
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.6.1)",
    comments = "Source: handler.proto")
public final class UserServiceGrpc {

  private UserServiceGrpc() {}

  public static final String SERVICE_NAME = "UserService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<kurovszky.robin.unicalendar.web_service.grpc.generated.User,
      kurovszky.robin.unicalendar.web_service.grpc.generated.Boolean> METHOD_VALIDATE =
      io.grpc.MethodDescriptor.<kurovszky.robin.unicalendar.web_service.grpc.generated.User, kurovszky.robin.unicalendar.web_service.grpc.generated.Boolean>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "UserService", "validate"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              kurovszky.robin.unicalendar.web_service.grpc.generated.User.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              kurovszky.robin.unicalendar.web_service.grpc.generated.Boolean.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<kurovszky.robin.unicalendar.web_service.grpc.generated.User,
      kurovszky.robin.unicalendar.web_service.grpc.generated.User> METHOD_REGISTER =
      io.grpc.MethodDescriptor.<kurovszky.robin.unicalendar.web_service.grpc.generated.User, kurovszky.robin.unicalendar.web_service.grpc.generated.User>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "UserService", "register"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              kurovszky.robin.unicalendar.web_service.grpc.generated.User.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              kurovszky.robin.unicalendar.web_service.grpc.generated.User.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<kurovszky.robin.unicalendar.web_service.grpc.generated.UserId,
      kurovszky.robin.unicalendar.web_service.grpc.generated.UserName> METHOD_GET_NAME_BY_ID =
      io.grpc.MethodDescriptor.<kurovszky.robin.unicalendar.web_service.grpc.generated.UserId, kurovszky.robin.unicalendar.web_service.grpc.generated.UserName>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "UserService", "getNameById"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              kurovszky.robin.unicalendar.web_service.grpc.generated.UserId.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              kurovszky.robin.unicalendar.web_service.grpc.generated.UserName.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<kurovszky.robin.unicalendar.web_service.grpc.generated.UserName,
      kurovszky.robin.unicalendar.web_service.grpc.generated.UserId> METHOD_GET_ID_BY_NAME =
      io.grpc.MethodDescriptor.<kurovszky.robin.unicalendar.web_service.grpc.generated.UserName, kurovszky.robin.unicalendar.web_service.grpc.generated.UserId>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "UserService", "getIdByName"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              kurovszky.robin.unicalendar.web_service.grpc.generated.UserName.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              kurovszky.robin.unicalendar.web_service.grpc.generated.UserId.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<kurovszky.robin.unicalendar.web_service.grpc.generated.UserName,
      kurovszky.robin.unicalendar.web_service.grpc.generated.InstituteId> METHOD_GET_INSTITUTE_ID_BY_NAME =
      io.grpc.MethodDescriptor.<kurovszky.robin.unicalendar.web_service.grpc.generated.UserName, kurovszky.robin.unicalendar.web_service.grpc.generated.InstituteId>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "UserService", "getInstituteIdByName"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              kurovszky.robin.unicalendar.web_service.grpc.generated.UserName.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              kurovszky.robin.unicalendar.web_service.grpc.generated.InstituteId.getDefaultInstance()))
          .build();

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
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
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
    public void validate(kurovszky.robin.unicalendar.web_service.grpc.generated.User request,
        io.grpc.stub.StreamObserver<kurovszky.robin.unicalendar.web_service.grpc.generated.Boolean> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_VALIDATE, responseObserver);
    }

    /**
     */
    public void register(kurovszky.robin.unicalendar.web_service.grpc.generated.User request,
        io.grpc.stub.StreamObserver<kurovszky.robin.unicalendar.web_service.grpc.generated.User> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_REGISTER, responseObserver);
    }

    /**
     */
    public void getNameById(kurovszky.robin.unicalendar.web_service.grpc.generated.UserId request,
        io.grpc.stub.StreamObserver<kurovszky.robin.unicalendar.web_service.grpc.generated.UserName> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_NAME_BY_ID, responseObserver);
    }

    /**
     */
    public void getIdByName(kurovszky.robin.unicalendar.web_service.grpc.generated.UserName request,
        io.grpc.stub.StreamObserver<kurovszky.robin.unicalendar.web_service.grpc.generated.UserId> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_ID_BY_NAME, responseObserver);
    }

    /**
     */
    public void getInstituteIdByName(kurovszky.robin.unicalendar.web_service.grpc.generated.UserName request,
        io.grpc.stub.StreamObserver<kurovszky.robin.unicalendar.web_service.grpc.generated.InstituteId> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_INSTITUTE_ID_BY_NAME, responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_VALIDATE,
            asyncUnaryCall(
              new MethodHandlers<
                kurovszky.robin.unicalendar.web_service.grpc.generated.User,
                kurovszky.robin.unicalendar.web_service.grpc.generated.Boolean>(
                  this, METHODID_VALIDATE)))
          .addMethod(
            METHOD_REGISTER,
            asyncUnaryCall(
              new MethodHandlers<
                kurovszky.robin.unicalendar.web_service.grpc.generated.User,
                kurovszky.robin.unicalendar.web_service.grpc.generated.User>(
                  this, METHODID_REGISTER)))
          .addMethod(
            METHOD_GET_NAME_BY_ID,
            asyncUnaryCall(
              new MethodHandlers<
                kurovszky.robin.unicalendar.web_service.grpc.generated.UserId,
                kurovszky.robin.unicalendar.web_service.grpc.generated.UserName>(
                  this, METHODID_GET_NAME_BY_ID)))
          .addMethod(
            METHOD_GET_ID_BY_NAME,
            asyncUnaryCall(
              new MethodHandlers<
                kurovszky.robin.unicalendar.web_service.grpc.generated.UserName,
                kurovszky.robin.unicalendar.web_service.grpc.generated.UserId>(
                  this, METHODID_GET_ID_BY_NAME)))
          .addMethod(
            METHOD_GET_INSTITUTE_ID_BY_NAME,
            asyncUnaryCall(
              new MethodHandlers<
                kurovszky.robin.unicalendar.web_service.grpc.generated.UserName,
                kurovszky.robin.unicalendar.web_service.grpc.generated.InstituteId>(
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

    @Override
    protected UserServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserServiceStub(channel, callOptions);
    }

    /**
     */
    public void validate(kurovszky.robin.unicalendar.web_service.grpc.generated.User request,
        io.grpc.stub.StreamObserver<kurovszky.robin.unicalendar.web_service.grpc.generated.Boolean> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_VALIDATE, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void register(kurovszky.robin.unicalendar.web_service.grpc.generated.User request,
        io.grpc.stub.StreamObserver<kurovszky.robin.unicalendar.web_service.grpc.generated.User> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_REGISTER, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getNameById(kurovszky.robin.unicalendar.web_service.grpc.generated.UserId request,
        io.grpc.stub.StreamObserver<kurovszky.robin.unicalendar.web_service.grpc.generated.UserName> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_NAME_BY_ID, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getIdByName(kurovszky.robin.unicalendar.web_service.grpc.generated.UserName request,
        io.grpc.stub.StreamObserver<kurovszky.robin.unicalendar.web_service.grpc.generated.UserId> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_ID_BY_NAME, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getInstituteIdByName(kurovszky.robin.unicalendar.web_service.grpc.generated.UserName request,
        io.grpc.stub.StreamObserver<kurovszky.robin.unicalendar.web_service.grpc.generated.InstituteId> responseObserver) {
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

    @Override
    protected UserServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public kurovszky.robin.unicalendar.web_service.grpc.generated.Boolean validate(kurovszky.robin.unicalendar.web_service.grpc.generated.User request) {
      return blockingUnaryCall(
          getChannel(), METHOD_VALIDATE, getCallOptions(), request);
    }

    /**
     */
    public kurovszky.robin.unicalendar.web_service.grpc.generated.User register(kurovszky.robin.unicalendar.web_service.grpc.generated.User request) {
      return blockingUnaryCall(
          getChannel(), METHOD_REGISTER, getCallOptions(), request);
    }

    /**
     */
    public kurovszky.robin.unicalendar.web_service.grpc.generated.UserName getNameById(kurovszky.robin.unicalendar.web_service.grpc.generated.UserId request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_NAME_BY_ID, getCallOptions(), request);
    }

    /**
     */
    public kurovszky.robin.unicalendar.web_service.grpc.generated.UserId getIdByName(kurovszky.robin.unicalendar.web_service.grpc.generated.UserName request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_ID_BY_NAME, getCallOptions(), request);
    }

    /**
     */
    public kurovszky.robin.unicalendar.web_service.grpc.generated.InstituteId getInstituteIdByName(kurovszky.robin.unicalendar.web_service.grpc.generated.UserName request) {
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

    @Override
    protected UserServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<kurovszky.robin.unicalendar.web_service.grpc.generated.Boolean> validate(
        kurovszky.robin.unicalendar.web_service.grpc.generated.User request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_VALIDATE, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<kurovszky.robin.unicalendar.web_service.grpc.generated.User> register(
        kurovszky.robin.unicalendar.web_service.grpc.generated.User request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_REGISTER, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<kurovszky.robin.unicalendar.web_service.grpc.generated.UserName> getNameById(
        kurovszky.robin.unicalendar.web_service.grpc.generated.UserId request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_NAME_BY_ID, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<kurovszky.robin.unicalendar.web_service.grpc.generated.UserId> getIdByName(
        kurovszky.robin.unicalendar.web_service.grpc.generated.UserName request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_ID_BY_NAME, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<kurovszky.robin.unicalendar.web_service.grpc.generated.InstituteId> getInstituteIdByName(
        kurovszky.robin.unicalendar.web_service.grpc.generated.UserName request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_INSTITUTE_ID_BY_NAME, getCallOptions()), request);
    }
  }

  private static final int METHODID_VALIDATE = 0;
  private static final int METHODID_REGISTER = 1;
  private static final int METHODID_GET_NAME_BY_ID = 2;
  private static final int METHODID_GET_ID_BY_NAME = 3;
  private static final int METHODID_GET_INSTITUTE_ID_BY_NAME = 4;

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

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_VALIDATE:
          serviceImpl.validate((kurovszky.robin.unicalendar.web_service.grpc.generated.User) request,
              (io.grpc.stub.StreamObserver<kurovszky.robin.unicalendar.web_service.grpc.generated.Boolean>) responseObserver);
          break;
        case METHODID_REGISTER:
          serviceImpl.register((kurovszky.robin.unicalendar.web_service.grpc.generated.User) request,
              (io.grpc.stub.StreamObserver<kurovszky.robin.unicalendar.web_service.grpc.generated.User>) responseObserver);
          break;
        case METHODID_GET_NAME_BY_ID:
          serviceImpl.getNameById((kurovszky.robin.unicalendar.web_service.grpc.generated.UserId) request,
              (io.grpc.stub.StreamObserver<kurovszky.robin.unicalendar.web_service.grpc.generated.UserName>) responseObserver);
          break;
        case METHODID_GET_ID_BY_NAME:
          serviceImpl.getIdByName((kurovszky.robin.unicalendar.web_service.grpc.generated.UserName) request,
              (io.grpc.stub.StreamObserver<kurovszky.robin.unicalendar.web_service.grpc.generated.UserId>) responseObserver);
          break;
        case METHODID_GET_INSTITUTE_ID_BY_NAME:
          serviceImpl.getInstituteIdByName((kurovszky.robin.unicalendar.web_service.grpc.generated.UserName) request,
              (io.grpc.stub.StreamObserver<kurovszky.robin.unicalendar.web_service.grpc.generated.InstituteId>) responseObserver);
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

  private static final class UserServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return kurovszky.robin.unicalendar.web_service.grpc.generated.Handler.getDescriptor();
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
              .addMethod(METHOD_VALIDATE)
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
