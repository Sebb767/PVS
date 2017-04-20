package de.fhws.fiw.pvs.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * Created by braunpet on 18.04.17.
 */
public class GreeterServer
{
	private final Server server;

	public GreeterServer( int port )
	{
		this.server = ServerBuilder.forPort( port )
								   .addService( new GreeterServiceImpl( ) )
								   .build( );
	}

	public static void main( String[] args ) throws Exception
	{
		GreeterServer server = new GreeterServer( 8888 );
		server.start( );
		System.out.println( "Server running ..." );
		server.blockUntilShutdown( );
	}

	public void start( ) throws IOException
	{
		server.start( );

		Runtime.getRuntime( ).addShutdownHook( new Thread( )
		{

			@Override
			public void run( )
			{
				System.err.println( "Shutting down gRPC server since JVM is shutting down" );
				GreeterServer.this.stop( );
				System.err.println( "Server shut down" );
			}
		} );
	}

	public void stop( )
	{
		if ( server != null )
		{
			server.shutdown( );
		}
	}

	private void blockUntilShutdown( ) throws InterruptedException
	{
		if ( server != null )
		{
			server.awaitTermination( );
		}
	}
}
