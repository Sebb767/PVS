package de.fhws.fiw.pvs.rmi;

import java.io.Serializable;

/**
 * Created by braunpet on 04.04.17.
 */
public class Location implements Serializable
{
	private float longitude;

	private float latitude;

	public Location( )
	{
	}

	public Location( float longitude, float latitude )
	{
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public float getLongitude( )
	{
		return longitude;
	}

	public void setLongitude( float longitude )
	{
		this.longitude = longitude;
	}

	public float getLatitude( )
	{
		return latitude;
	}

	public void setLatitude( float latitude )
	{
		this.latitude = latitude;
	}

	@Override public String toString( )
	{
		return "Location{" +
			"longitude=" + longitude +
			", latitude=" + latitude +
			'}';
	}
}
