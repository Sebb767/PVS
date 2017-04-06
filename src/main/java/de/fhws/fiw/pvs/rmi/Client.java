package de.fhws.fiw.pvs.rmi;

import java.rmi.Naming;

public class Client
{
	private static final String ADDRESS = "rmi://localhost/" + IExampleService.EXAMPLE_SERVICE_NAME;

	public static void main( String[] args )
	{
		try
		{
			IExampleService exampleService = ( IExampleService ) Naming.lookup( ADDRESS );

			System.out.println( "Echo: " + exampleService.echo( "Hello" ) );

			System.out.println( "Server Time: " + exampleService.getServerTime( ) );

			System.out.println( "Summe: " + exampleService.add( 101, 207 ) );

		}
		catch ( Exception e )
		{
			e.printStackTrace( );
		}
	}
}