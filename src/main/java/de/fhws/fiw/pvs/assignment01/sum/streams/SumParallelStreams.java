package de.fhws.fiw.pvs.assignment01.sum.streams;

import de.fhws.fiw.pvs.assignment01.sum.utils.Measurement;

import java.util.Arrays;

/**
 * Created by braunpet on 12.04.16.
 */
public class SumParallelStreams extends Measurement
{
	private final int[] array;

	public SumParallelStreams( final int[] array )
	{
		this.array = array;
	}

	@Override
	protected Integer _call( )
	{
		return Arrays.stream( this.array ).parallel( ).reduce( 0, ( a, b ) -> a + b );
	}
}
