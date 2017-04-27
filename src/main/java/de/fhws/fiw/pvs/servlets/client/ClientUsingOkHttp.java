package de.fhws.fiw.pvs.servlets.client;

import okhttp3.*;

import java.io.IOException;

public class ClientUsingOkHttp
{
	public static void main( String[] args )
	{
		final OkHttpClient client = new OkHttpClient( );

		Request request = new Request.Builder( )
				.url( "http://localhost:8080/demo/test?name=James" )
				.get()
				.build( );

		try
		{
			Response response = client.newCall( request ).execute( );
			System.out.println( response.body().string() );
		}
		catch ( IOException e )
		{
			e.printStackTrace( );
		}
	}
}
