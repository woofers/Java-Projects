package com.jaxson.lib.math.random;

import com.jaxson.lib.math.ComparableNumber;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * An immutable {@link Object} used to define the a random number.
 * @author Jaxson Van Doorn
 * @since 1.0
 */
public class RandomNumber extends ComparableNumber
{
	private static int DEFAULT_MIN = 0;
	private static int DEFAULT_MAX = 100;
	private static String MIN_EXCEEDS_MAX = "Min cannot be greather or equal to max";

	private Number min;
	private Number max;
	private Number[] excluded;

	/**
	 * Construct a {@link RandomNumber} between {@code 0} and {@code 100}.
	 */
	public RandomNumber()
	{
		this(DEFAULT_MIN, DEFAULT_MAX);
	}

	/**
	 * Construct a {@link RandomNumber} between the min and max.
	 * @param min The min value of the {@link Number}
	 * @param max The max value of the {@link Number}
	 */
	public RandomNumber(Number min, Number max)
	{
		this(min, max, (Number) null);
	}

	/**
	 * Construct a {@link RandomNumber} between the min and max.
	 * @param min The min value of the {@link Number}
	 * @param max The max value of the {@link Number}
	 * @param excluded Array of excluded {@link Number}s from the range
	 */
	public RandomNumber(Number min, Number max, Number... excluded)
	{
		if (max.floatValue() < min.floatValue()) throw new IllegalArgumentException(MIN_EXCEEDS_MAX);
		this.min = min;
		this.max = max;
		this.excluded = excluded;
	}

	@Override
	public double doubleValue()
	{
		double value = getRandom().nextDouble() * (getMax().doubleValue() - getMin().doubleValue() + 1) + getMin().doubleValue();
		if (isExcluded(value)) return doubleValue();
		return value;
	}

	@Override
	public float floatValue()
	{
		float value = getRandom().nextFloat() * (getMax().floatValue() - getMin().floatValue() + 1) + getMin().floatValue();
		if (isExcluded(value)) return floatValue();
		return value;
	}

	/**
	 * Gets the excluded range.
	 * @return {@link Number}[] - The rxcluded range
	 */
	public Number[] getExclude()
	{
		return excluded;
	}

	/**
	 * Gets the max value of the range.
	 * @return {@link Number} - The max value of the range
	 */
	public Number getMax()
	{
		return max;
	}

	/**
	 * Gets the min value of the range.
	 * @return {@link Number} - The min value of the range
	 */
	public Number getMin()
	{
		return min;
	}

	@Override
	public int intValue()
	{
		int value = getRandom().nextInt(getMax().intValue() - getMin().intValue() + 1) + getMin().intValue();
		if (isExcluded(value)) return intValue();
		return value;
	}

	public boolean isExcluded(Number value)
	{
		if (value.floatValue() < getMin().floatValue() || value.floatValue() > getMax().floatValue()) return false;
		for (Number number: getExclude())
		{
			if (value.equals(number)) return true;
		}
		return false;
	}

	@Override
	public long longValue()
	{
		long value = getRandom().nextLong() * (getMax().longValue() - getMin().longValue() + 1) + getMin().longValue();
		if (isExcluded(value)) return longValue();
		return value;
	}

	/**
	 * Returns the current Random instance.
	 * @return {@link Random} - The Random instance
	 */
	private static Random getRandom()
	{
		return ThreadLocalRandom.current();
	}
}