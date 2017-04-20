package de.fhws.fiw.pvs.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.ZonedDateTime;

public class ExampleServiceImpl extends UnicastRemoteObject implements IExampleService
{
	Counter c = new Counter();

	public ExampleServiceImpl( ) throws RemoteException
	{
		super( );
	}

	@Override
	public String echo( String msg ) throws RemoteException
	{
		return msg.toUpperCase( );
	}

	@Override
	public ZonedDateTime getServerTime( ) throws RemoteException
	{
		return ZonedDateTime.now( );
	}

	@Override
	public int add( int n1, int n2 ) throws RemoteException
	{
		return n1 + n2;
	}

	@Override
	public void increaseCounter(Counter c, Loop lp) throws RemoteException {
		c.increase();
	}

	public Counter getCounter() throws RemoteException { return c; }
}