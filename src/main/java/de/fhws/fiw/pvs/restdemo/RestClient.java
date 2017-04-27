package de.fhws.fiw.pvs.restdemo;

import okhttp3.*;
import okio.BufferedSink;

import java.io.BufferedReader;
import java.io.IOException;

public class RestClient {
    public static void main(String[] args) {
        final OkHttpClient client = new OkHttpClient( );
        final RequestBody rq = new RequestBody() {
            @Override
            public MediaType contentType() {
                return MediaType.parse("text/plain");
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                sink.write("James".getBytes());
            }
        };

        Request request = new Request.Builder( )
                .url( "http://localhost:8081/demo/api/users" )
                .post(rq)
                .build( );

        try
        {
            Response response = client.newCall( request ).execute( );
            BufferedReader br = new BufferedReader(response.body().charStream() );

            System.out.println( br.readLine() );
        }
        catch ( IOException e )
        {
            e.printStackTrace( );
        }
    }
}
