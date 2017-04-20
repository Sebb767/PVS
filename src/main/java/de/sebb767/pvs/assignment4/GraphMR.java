package de.sebb767.pvs.assignment4;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ICompletableFuture;
import com.hazelcast.core.IMap;
import com.hazelcast.mapreduce.*;
import de.fhws.fiw.pvs.assignment03.mapreduce.Main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


public class GraphMR {
    private static long startTime, stopTime;

    public static void main( String[] args )
    {
        System.out.println( "Map-Reduce graph main node" );

        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance( );

        initializeMap( hazelcastInstance );

        ICompletableFuture<Map<String, Long>> future = startMapReduce( hazelcastInstance );

        long duration = waitForResult( future );

        System.out.println( "Duration: " + duration );
    }

    private static long waitForResult( ICompletableFuture<Map<String, Long>> future )
    {
        try
        {
            startTime = System.currentTimeMillis( );
            Map<String, Long> result = future.get( );
            System.out.println( result );
            stopTime = System.currentTimeMillis( );

            return ( stopTime - startTime );
        }
        catch ( Exception e )
        {
            e.printStackTrace( );
            return 0;
        }
    }

    private static ICompletableFuture<Map<String, Long>> startMapReduce( HazelcastInstance hazelcastInstance )
    {
        IMap<String, String> map = hazelcastInstance.getMap( "input" );
        KeyValueSource<String, String> source = KeyValueSource.fromMap( map );

        JobTracker jobTracker = hazelcastInstance.getJobTracker( "mapreduce" );
        Job<String, String> job = jobTracker.newJob( source );

        /*return job
                .mapper( new GraphMR.TokenizerMapper( ) )
                .combiner( new GraphMR.WordCountCombinerFactory( ) )
                .reducer( new GraphMR.WordCountReducerFactory( ) )
                .submit( );*/
        return null;
    }

    private static void initializeMap( HazelcastInstance hazelcastInstance )
    {
        IMap<SimpleGraph.NodePair, List<Integer>> data = hazelcastInstance.getMap( "input" );

        putAllWordsOfEachLineInMap( data );
    }

    private static void putAllWordsOfEachLineInMap( IMap<SimpleGraph.NodePair, List<Integer>> data )
    {
        SimpleGraph sg = SimpleGraph.randomGraph();

        sg.getNodes().forEach(n -> {
            n.getConnections().forEach(x -> {
                data.put(new SimpleGraph.NodePair(n.getNumber(), x), n.getConnections());
            });
        });

        /*try
        {
            final InputStream inputStream = Main.class.getResourceAsStream( "/kant-krv-utf8.txt" );
            final BufferedReader br = new BufferedReader( new InputStreamReader( inputStream ) );
            int indexOfKey = 0;
            String line;

            while ( ( line = br.readLine( ) ) != null )
            {
                if ( line.trim( ).isEmpty( ) == false )
                {
                    data.put( "KEY" + indexOfKey++, line );
                }
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace( );
        }*/
    }

    static class TokenizerMapper implements Mapper<SimpleGraph.NodePair, List<Integer>, SimpleGraph.NodePair, List<Integer>>
    {
        @Override
        public void map(SimpleGraph.NodePair nodePair, List<Integer> integers, Context<SimpleGraph.NodePair, List<Integer>> context) {
            context.emit(nodePair, integers);
        }


       /*@Override
        public void map( String key, String document, Context<String, Long> context )
        {
            final StringTokenizer tokenizer = new StringTokenizer( document.toLowerCase( ) );

            while ( tokenizer.hasMoreTokens( ) )
            {
                context.emit( tokenizer.nextToken( ).replaceAll( "[\\.,()\\*]", "" ), ONE );
            }
        }*/
    }

    static class WordCountCombinerFactory implements CombinerFactory<String, Long, Long>
    {
        @Override
        public Combiner<Long, Long> newCombiner( String key )
        {
            return new GraphMR.WordCountCombinerFactory.WordCountCombiner( );
        }

        private class WordCountCombiner extends Combiner<Long, Long>
        {
            private long sum = 0;

            @Override
            public void combine( Long value )
            {
                sum++;
            }

            @Override
            public Long finalizeChunk( )
            {
                return sum;
            }

            @Override
            public void reset( )
            {
                sum = 0;
            }
        }
    }

    static class WordCountReducerFactory implements ReducerFactory<String, Long, Long>
    {
        @Override
        public Reducer<Long, Long> newReducer( String key )
        {
            return new GraphMR.WordCountReducerFactory.WordCountReducer( );
        }

        private class WordCountReducer extends Reducer<Long, Long>
        {
            private volatile long sum = 0;

            @Override
            public void reduce( Long value )
            {
                sum += value.longValue( );
            }

            @Override
            public Long finalizeReduce( )
            {
                return sum;
            }
        }
    }
}
