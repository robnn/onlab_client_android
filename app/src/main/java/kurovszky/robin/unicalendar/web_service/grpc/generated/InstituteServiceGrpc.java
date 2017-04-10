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
 *the methods needed for institute
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.2.0)",
    comments = "Source: handler.proto")
public final class InstituteServiceGrpc {

  private InstituteServiceGrpc() {}

  public static final String SERVICE_NAME = "InstituteService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<InstituteId,
          Institute> METHOD_GET_INSTITUTEBY_ID =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "InstituteService", "getInstitutebyId"),
          io.grpc.protobuf.ProtoUtils.marshaller(InstituteId.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(Institute.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<Empty,
          InstituteList> METHOD_GET_ALL_INSTITUTES =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "InstituteService", "getAllInstitutes"),
          io.grpc.protobuf.ProtoUtils.marshaller(Empty.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(InstituteList.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<Institute,
          Empty> METHOD_ADD_INSTITUTE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "InstituteService", "addInstitute"),
          io.grpc.protobuf.ProtoUtils.marshaller(Institute.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(Empty.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static InstituteServiceStub newStub(io.grpc.Channel channel) {
    return new InstituteServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static InstituteServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new InstituteServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static InstituteServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new InstituteServiceFutureStub(channel);
  }

  /**
   * <pre>
   *the methods needed for institute
   * </pre>
   */
  public static abstract class InstituteServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getInstitutebyId(InstituteId request,
        io.grpc.stub.StreamObserver<Institute> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_INSTITUTEBY_ID, responseObserver);
    }

    /**
     */
    public void getAllInstitutes(Empty request,
        io.grpc.stub.StreamObserver<InstituteList> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_ALL_INSTITUTES, responseObserver);
    }

    /**
     */
    public void addInstitute(Institute request,
        io.grpc.stub.StreamObserver<Empty> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_ADD_INSTITUTE, responseObserver);
    }

    @java.lang.Override
    public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_INSTITUTEBY_ID,
            asyncUnaryCall(
              new MethodHandlers<
                      InstituteId,
                      Institute>(
                  this, METHODID_GET_INSTITUTEBY_ID)))
          .addMethod(
            METHOD_GET_ALL_INSTITUTES,
            asyncUnaryCall(
              new MethodHandlers<
                      Empty,
                      InstituteList>(
                  this, METHODID_GET_ALL_INSTITUTES)))
          .addMethod(
            METHOD_ADD_INSTITUTE,
            asyncUnaryCall(
              new MethodHandlers<
                      Institute,
                      Empty>(
                  this, METHODID_ADD_INSTITUTE)))
          .build();
    }
  }

  /**
   * <pre>
   *the methods needed for institute
   * </pre>
   */
  public static final class InstituteServiceStub extends io.grpc.stub.AbstractStub<InstituteServiceStub> {
    private InstituteServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private InstituteServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InstituteServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new InstituteServiceStub(channel, callOptions);
    }

    /**
     */
    public void getInstitutebyId(InstituteId request,
        io.grpc.stub.StreamObserver<Institute> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_INSTITUTEBY_ID, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAllInstitutes(Empty request,
        io.grpc.stub.StreamObserver<InstituteList> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_ALL_INSTITUTES, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addInstitute(Institute request,
        io.grpc.stub.StreamObserver<Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_ADD_INSTITUTE, getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   *the methods needed for institute
   * </pre>
   */
  public static final class InstituteServiceBlockingStub extends io.grpc.stub.AbstractStub<InstituteServiceBlockingStub> {
    private InstituteServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private InstituteServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InstituteServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new InstituteServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public Institute getInstitutebyId(InstituteId request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_INSTITUTEBY_ID, getCallOptions(), request);
    }

    /**
     */
    public InstituteList getAllInstitutes(Empty request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_ALL_INSTITUTES, getCallOptions(), request);
    }

    /**
     */
    public Empty addInstitute(Institute request) {
      return blockingUnaryCall(
          getChannel(), METHOD_ADD_INSTITUTE, getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *the methods needed for institute
   * </pre>
   */
  public static final class InstituteServiceFutureStub extends io.grpc.stub.AbstractStub<InstituteServiceFutureStub> {
    private InstituteServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private InstituteServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InstituteServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new InstituteServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Institute> getInstitutebyId(
        InstituteId request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_INSTITUTEBY_ID, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<InstituteList> getAllInstitutes(
        Empty request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_ALL_INSTITUTES, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Empty> addInstitute(
        Institute request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_ADD_INSTITUTE, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_INSTITUTEBY_ID = 0;
  private static final int METHODID_GET_ALL_INSTITUTES = 1;
  private static final int METHODID_ADD_INSTITUTE = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final InstituteServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(InstituteServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_INSTITUTEBY_ID:
          serviceImpl.getInstitutebyId((InstituteId) request,
              (io.grpc.stub.StreamObserver<Institute>) responseObserver);
          break;
        case METHODID_GET_ALL_INSTITUTES:
          serviceImpl.getAllInstitutes((Empty) request,
              (io.grpc.stub.StreamObserver<InstituteList>) responseObserver);
          break;
        case METHODID_ADD_INSTITUTE:
          serviceImpl.addInstitute((Institute) request,
              (io.grpc.stub.StreamObserver<Empty>) responseObserver);
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

  private static final class InstituteServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return Handler.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (InstituteServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new InstituteServiceDescriptorSupplier())
              .addMethod(METHOD_GET_INSTITUTEBY_ID)
              .addMethod(METHOD_GET_ALL_INSTITUTES)
              .addMethod(METHOD_ADD_INSTITUTE)
              .build();
        }
      }
    }
    return result;
  }
}
