package de.fhws.fiw.pvs.assignment01.sum.utils;

/**
 * Created by braunpet on 12.04.16.
 */
public class InitializeArray
{
	public static int[] initializeArray( final int n )
	{
		final int[] array = new int[ n ];
		for ( int i = 0; i < n; i++ )
		{
			array[ i ] = i;
		}
		return array;
	}
}
