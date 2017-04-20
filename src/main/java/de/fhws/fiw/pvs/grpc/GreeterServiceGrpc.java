package de.fhws.fiw.pvs.grpc;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.2.0)",
    comments = "Source: hello.proto")
public final class GreeterServiceGrpc {

  private GreeterServiceGrpc() {}

  public static final String SERVICE_NAME = "de.fhws.fiw.pvs.grpc.GreeterService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<de.fhws.fiw.pvs.grpc.Greeter.Request,
      de.fhws.fiw.pvs.grpc.Greeter.Reply> METHOD_GET_GREETING =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "de.fhws.fiw.pvs.grpc.GreeterService", "getGreeting"),
          io.grpc.protobuf.ProtoUtils.marshaller(de.fhws.fiw.pvs.grpc.Greeter.Request.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(de.fhws.fiw.pvs.grpc.Greeter.Reply.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GreeterServiceStub newStub(io.grpc.Channel channel) {
    return new GreeterServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GreeterServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GreeterServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static GreeterServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GreeterServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class GreeterServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getGreeting(de.fhws.fiw.pvs.grpc.Greeter.Request request,
        io.grpc.stub.StreamObserver<de.fhws.fiw.pvs.grpc.Greeter.Reply> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_GREETING, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_GREETING,
            asyncUnaryCall(
              new MethodHandlers<
                de.fhws.fiw.pvs.grpc.Greeter.Request,
                de.fhws.fiw.pvs.grpc.Greeter.Reply>(
                  this, METHODID_GET_GREETING)))
          .build();
    }
  }

  /**
   */
  public static final class GreeterServiceStub extends io.grpc.stub.AbstractStub<GreeterServiceStub> {
    private GreeterServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreeterServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreeterServiceStub(channel, callOptions);
    }

    /**
     */
    public void getGreeting(de.fhws.fiw.pvs.grpc.Greeter.Request request,
        io.grpc.stub.StreamObserver<de.fhws.fiw.pvs.grpc.Greeter.Reply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_GREETING, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class GreeterServiceBlockingStub extends io.grpc.stub.AbstractStub<GreeterServiceBlockingStub> {
    private GreeterServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreeterServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreeterServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public de.fhws.fiw.pvs.grpc.Greeter.Reply getGreeting(de.fhws.fiw.pvs.grpc.Greeter.Request request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_GREETING, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class GreeterServiceFutureStub extends io.grpc.stub.AbstractStub<GreeterServiceFutureStub> {
    private GreeterServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreeterServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreeterServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<de.fhws.fiw.pvs.grpc.Greeter.Reply> getGreeting(
        de.fhws.fiw.pvs.grpc.Greeter.Request request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_GREETING, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_GREETING = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GreeterServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GreeterServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_GREETING:
          serviceImpl.getGreeting((de.fhws.fiw.pvs.grpc.Greeter.Request) request,
              (io.grpc.stub.StreamObserver<de.fhws.fiw.pvs.grpc.Greeter.Reply>) responseObserver);
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

  private static final class GreeterServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return de.fhws.fiw.pvs.grpc.Greeter.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GreeterServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GreeterServiceDescriptorSupplier())
              .addMethod(METHOD_GET_GREETING)
              .build();
        }
      }
    }
    return result;
  }
}
