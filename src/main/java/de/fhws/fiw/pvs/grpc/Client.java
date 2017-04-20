package de.fhws.fiw.pvs.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.TimeUnit;

/**
 * Created by braunpet on 18.04.17.
 */
public class Client
{
	private final ManagedChannel channel;
	private final GreeterServiceGrpc.GreeterServiceBlockingStub blockingStub;

	public Client( String host, int port )
	{
		/* In the following statement, do NOT remove usePlaintext or set it to false. */
		channel = ManagedChannelBuilder.forAddress( host, port ).usePlaintext( true ).build( );
		blockingStub = GreeterServiceGrpc.newBlockingStub( channel );
	}

	public static void main( String[] args ) throws InterruptedException
	{
		Client client = new Client( "localhost", 8888 );
		try
		{
			client.greet( "James" );
		}
		finally
		{
			client.shutdown( );
		}
	}

	public void shutdown( ) throws InterruptedException
	{
		channel.shutdown( ).awaitTermination( 5, TimeUnit.SECONDS );
	}

	/**
	 * Say hello to server.
	 */
	public void greet( String name )
	{
		Greeter.Request request = Greeter.Request.newBuilder( ).setName( name ).build( );
		//Greeter.Person person = Greeter.Person.newBuilder().setFirstName("John").setLastName("Doe").build();

		try
		{
			Greeter.Reply response = blockingStub.getGreeting( request );
			System.out.println( response.getGreeting( ) );
		}
		catch ( StatusRuntimeException e )
		{
			return;
		}
	}
}
