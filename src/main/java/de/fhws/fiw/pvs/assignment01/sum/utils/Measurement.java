package de.fhws.fiw.pvs.assignment01.sum.utils;

import java.util.concurrent.Callable;

public abstract class Measurement implements Callable<Integer>
{
	private long duration = 0L;

	@Override
	public final Integer call( )
	{
		final long start = System.nanoTime( );
		final int result = _call( );
		this.duration = System.nanoTime( ) - start;
		return result;
	}

	public final long getDuration( )
	{
		return this.duration;
	}

	protected abstract Integer _call( );
}
