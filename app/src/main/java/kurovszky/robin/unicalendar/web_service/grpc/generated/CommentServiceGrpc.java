package kurovszky.robin.unicalendar.web_service.grpc.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 *for comments
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.6.1)",
    comments = "Source: handler.proto")
public final class CommentServiceGrpc {

  private CommentServiceGrpc() {}

  public static final String SERVICE_NAME = "CommentService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<kurovszky.robin.unicalendar.web_service.grpc.generated.Subject,
      kurovszky.robin.unicalendar.web_service.grpc.generated.CommentList> METHOD_GET_COMMENTS_BY_SUBJECT =
      io.grpc.MethodDescriptor.<kurovszky.robin.unicalendar.web_service.grpc.generated.Subject, kurovszky.robin.unicalendar.web_service.grpc.generated.CommentList>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "CommentService", "getCommentsBySubject"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              kurovszky.robin.unicalendar.web_service.grpc.generated.Subject.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              kurovszky.robin.unicalendar.web_service.grpc.generated.CommentList.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<kurovszky.robin.unicalendar.web_service.grpc.generated.Comment,
      kurovszky.robin.unicalendar.web_service.grpc.generated.Empty> METHOD_ADD_COMMENT =
      io.grpc.MethodDescriptor.<kurovszky.robin.unicalendar.web_service.grpc.generated.Comment, kurovszky.robin.unicalendar.web_service.grpc.generated.Empty>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "CommentService", "addComment"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              kurovszky.robin.unicalendar.web_service.grpc.generated.Comment.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              kurovszky.robin.unicalendar.web_service.grpc.generated.Empty.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CommentServiceStub newStub(io.grpc.Channel channel) {
    return new CommentServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CommentServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new CommentServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CommentServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new CommentServiceFutureStub(channel);
  }

  /**
   * <pre>
   *for comments
   * </pre>
   */
  public static abstract class CommentServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getCommentsBySubject(kurovszky.robin.unicalendar.web_service.grpc.generated.Subject request,
        io.grpc.stub.StreamObserver<kurovszky.robin.unicalendar.web_service.grpc.generated.CommentList> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_COMMENTS_BY_SUBJECT, responseObserver);
    }

    /**
     */
    public void addComment(kurovszky.robin.unicalendar.web_service.grpc.generated.Comment request,
        io.grpc.stub.StreamObserver<kurovszky.robin.unicalendar.web_service.grpc.generated.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_ADD_COMMENT, responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_COMMENTS_BY_SUBJECT,
            asyncUnaryCall(
              new MethodHandlers<
                kurovszky.robin.unicalendar.web_service.grpc.generated.Subject,
                kurovszky.robin.unicalendar.web_service.grpc.generated.CommentList>(
                  this, METHODID_GET_COMMENTS_BY_SUBJECT)))
          .addMethod(
            METHOD_ADD_COMMENT,
            asyncUnaryCall(
              new MethodHandlers<
                kurovszky.robin.unicalendar.web_service.grpc.generated.Comment,
                kurovszky.robin.unicalendar.web_service.grpc.generated.Empty>(
                  this, METHODID_ADD_COMMENT)))
          .build();
    }
  }

  /**
   * <pre>
   *for comments
   * </pre>
   */
  public static final class CommentServiceStub extends io.grpc.stub.AbstractStub<CommentServiceStub> {
    private CommentServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CommentServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected CommentServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CommentServiceStub(channel, callOptions);
    }

    /**
     */
    public void getCommentsBySubject(kurovszky.robin.unicalendar.web_service.grpc.generated.Subject request,
        io.grpc.stub.StreamObserver<kurovszky.robin.unicalendar.web_service.grpc.generated.CommentList> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_COMMENTS_BY_SUBJECT, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addComment(kurovszky.robin.unicalendar.web_service.grpc.generated.Comment request,
        io.grpc.stub.StreamObserver<kurovszky.robin.unicalendar.web_service.grpc.generated.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_ADD_COMMENT, getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   *for comments
   * </pre>
   */
  public static final class CommentServiceBlockingStub extends io.grpc.stub.AbstractStub<CommentServiceBlockingStub> {
    private CommentServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CommentServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected CommentServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CommentServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public kurovszky.robin.unicalendar.web_service.grpc.generated.CommentList getCommentsBySubject(kurovszky.robin.unicalendar.web_service.grpc.generated.Subject request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_COMMENTS_BY_SUBJECT, getCallOptions(), request);
    }

    /**
     */
    public kurovszky.robin.unicalendar.web_service.grpc.generated.Empty addComment(kurovszky.robin.unicalendar.web_service.grpc.generated.Comment request) {
      return blockingUnaryCall(
          getChannel(), METHOD_ADD_COMMENT, getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *for comments
   * </pre>
   */
  public static final class CommentServiceFutureStub extends io.grpc.stub.AbstractStub<CommentServiceFutureStub> {
    private CommentServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CommentServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected CommentServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CommentServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<kurovszky.robin.unicalendar.web_service.grpc.generated.CommentList> getCommentsBySubject(
        kurovszky.robin.unicalendar.web_service.grpc.generated.Subject request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_COMMENTS_BY_SUBJECT, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<kurovszky.robin.unicalendar.web_service.grpc.generated.Empty> addComment(
        kurovszky.robin.unicalendar.web_service.grpc.generated.Comment request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_ADD_COMMENT, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_COMMENTS_BY_SUBJECT = 0;
  private static final int METHODID_ADD_COMMENT = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CommentServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CommentServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_COMMENTS_BY_SUBJECT:
          serviceImpl.getCommentsBySubject((kurovszky.robin.unicalendar.web_service.grpc.generated.Subject) request,
              (io.grpc.stub.StreamObserver<kurovszky.robin.unicalendar.web_service.grpc.generated.CommentList>) responseObserver);
          break;
        case METHODID_ADD_COMMENT:
          serviceImpl.addComment((kurovszky.robin.unicalendar.web_service.grpc.generated.Comment) request,
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

  private static final class CommentServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return kurovszky.robin.unicalendar.web_service.grpc.generated.Handler.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (CommentServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CommentServiceDescriptorSupplier())
              .addMethod(METHOD_GET_COMMENTS_BY_SUBJECT)
              .addMethod(METHOD_ADD_COMMENT)
              .build();
        }
      }
    }
    return result;
  }
}
