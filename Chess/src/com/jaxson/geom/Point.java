package com.jaxson.geom;

public class Point
{
	public int x, y;

	public Point()
	{
		this(0, 0);
	}

	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public Point clone()
	{
		return new Point(x, y);
	}

	@Override
	public String toString()
	{
		return "X: " + Integer.toString(x) + ", Y: " + Integer.toString(y);
	}
}
