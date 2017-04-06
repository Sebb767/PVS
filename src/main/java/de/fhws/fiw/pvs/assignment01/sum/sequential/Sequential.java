package de.fhws.fiw.pvs.assignment01.sum.sequential;

import de.fhws.fiw.pvs.assignment01.sum.utils.Measurement;

public class Sequential extends Measurement
{
	private final int[] array;

	public Sequential( final int[] array )
	{
		this.array = array;
	}

	protected Integer _call( )
	{
		int sum = 0;

		for ( int i = 0; i < this.array.length; i++ )
		{
			sum += this.array[ i ];
		}

		return sum;
	}
}
