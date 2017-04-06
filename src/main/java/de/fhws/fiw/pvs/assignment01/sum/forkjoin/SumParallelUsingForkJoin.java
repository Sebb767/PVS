package de.fhws.fiw.pvs.assignment01.sum.forkjoin;

import de.fhws.fiw.pvs.assignment01.sum.utils.Measurement;

import java.util.concurrent.ForkJoinPool;

/**
 * Created by braunpet on 12.04.16.
 */
public class SumParallelUsingForkJoin extends Measurement
{
	private final int[] array;

	private final ForkJoinPool forkJoinPool;

	public SumParallelUsingForkJoin( final int[] array )
	{
		this.array = array;
		this.forkJoinPool = new ForkJoinPool( );
	}

	@Override protected Integer _call( )
	{
		return this.forkJoinPool.invoke( new SumForkJoinWorker( this.array, 0, this.array.length ) );
	}
}
