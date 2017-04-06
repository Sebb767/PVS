package de.fhws.fiw.pvs.assignment02.prefix.sequential;

import de.fhws.fiw.pvs.assignment01.sum.utils.Measurement;

public class PrefixSequential extends Measurement
{
	private final int[] array;

	public PrefixSequential( final int[] array )
	{
		this.array = array;
	}

	protected Integer _call( )
	{
		for ( int i = 1; i < this.array.length; i++ )
		{
			this.array[ i ] = this.array[ i - 1 ] + this.array[ i ];
		}

		return this.array[ this.array.length - 1 ];
	}
}
