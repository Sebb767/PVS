package de.fhws.fiw.pvs.rmi;

import java.rmi.Naming;

public class Server
{
	public static void main( String[] args )
	{
		try
		{
			IExampleService exampleService = new ExampleServiceImpl( );
			Naming.rebind( "ExampleService", exampleService );

			CounterRemoteInterface cri = new Counter();
			Naming.rebind(CounterRemoteInterface.EXAMPLE_SERVICE_NAME, cri);

			System.out.println( "Server is running..." );
		}
		catch ( Exception e )
		{
			e.printStackTrace( );
		}
	}
}