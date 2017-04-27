package de.fhws.fiw.pvs.servlets.client;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by braunpet on 25.04.17.
 */
public class ClientUsingUrlConnection
{
	public static void main( String[] args )
	{
		try
		{
			final URL uri = new URL( "http://localhost:8080/demo/test" );
			final HttpURLConnection con = ( HttpURLConnection ) uri.openConnection( );

			con.setDoInput( true );
			con.setRequestMethod( "GET" );

			final InputStream inputStream = con.getInputStream( );
			String responseData = IOUtils.toString( inputStream );

			System.out.println(  responseData );
		}
		catch ( final Exception e )
		{
			e.printStackTrace( );
		}
	}

}
