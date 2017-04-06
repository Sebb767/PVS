package de.fhws.fiw.pvs.sockets;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by braunpet on 04.04.17.
 */
public class TcpClient
{
	public static void main( String argv[] ) throws Exception
	{
		BufferedReader inFromUser = new BufferedReader( new InputStreamReader( System.in ) );

		Socket clientSocket = new Socket( "localhost", 6789 );

		DataOutputStream outToServer = new DataOutputStream( clientSocket.getOutputStream( ) );
		BufferedReader inFromServer = new BufferedReader( new InputStreamReader( clientSocket.getInputStream( ) ) );
		String input = inFromUser.readLine( );

		outToServer.writeBytes( input + "\n" );
		String output = inFromServer.readLine( );

		System.out.println( "FROM SERVER: " + output );
		clientSocket.close( );
	}

}
