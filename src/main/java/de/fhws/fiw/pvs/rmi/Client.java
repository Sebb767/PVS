package de.fhws.fiw.pvs.rmi;

import javax.xml.transform.SourceLocator;
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

			Counter c = exampleService.getCounter();
			c.increase();

			exampleService.increaseCounter(c, new Loop() /* at least loop works */);
			System.out.println("Counter: " + exampleService.getCounter().getCounter());

		}
		catch ( Exception e )
		{
			e.printStackTrace( );
		}
	}
}