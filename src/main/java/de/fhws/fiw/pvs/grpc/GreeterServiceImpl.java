package de.fhws.fiw.pvs.grpc;

import io.grpc.stub.StreamObserver;

/**
 * Created by braunpet on 18.04.17.
 */
public class GreeterServiceImpl extends GreeterServiceGrpc.GreeterServiceImplBase
{

	@Override public void getGreeting( Greeter.Request request,
		StreamObserver<Greeter.Reply> responseObserver )
	{
		String name = request.getName( );
		String greeting = "Hallo " + name + "!";
		Greeter.Reply reply = Greeter.Reply.newBuilder( ).setGreeting( greeting ).build( );
		responseObserver.onNext( reply );
		responseObserver.onCompleted( );
	}
}
