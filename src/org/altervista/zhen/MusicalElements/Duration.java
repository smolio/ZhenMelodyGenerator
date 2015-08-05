package org.altervista.zhen.MusicalElements;

/**
 * A set of integers that represents each supported note. The integer value is the number of tied sixteenth notes that comprise the note.
 */
public class Duration
{
	public static final int SIXTEENTH_NOTE = 1;
	public static final int EIGHTH_NOTE = 2;
	public static final int DOTTED_EIGHTH_NOTE = 3;
	public static final int QUARTER_NOTE = 4;
	public static final int DOTTED_QUARTER_NOTE = 6;
	public static final int HALF_NOTE = 8;

	public static final int HALF_NOTE_BAR_SUBUNIT_PROBABILITY = 400;
	public static final int QUARTER_NOTE_BAR_SUBUNIT_PROBABILITY = 600;

	/*
	The sum of the probabilities of BarSubunits in an array = 1000
	 */
	public static final BarSubunit[] BAR_SUBUNITS_SIMPLE_HALF_NOTE =
			{
					new BarSubunit(new int[]{HALF_NOTE}, new boolean[]{false}, 150),
					new BarSubunit(new int[]{DOTTED_QUARTER_NOTE, EIGHTH_NOTE}, new boolean[]{false, false}, 150),
					new BarSubunit(new int[]{DOTTED_QUARTER_NOTE, SIXTEENTH_NOTE, SIXTEENTH_NOTE}, new boolean[]{false, true, false}, 50),
					new BarSubunit(new int[]{EIGHTH_NOTE, DOTTED_QUARTER_NOTE}, new boolean[]{false, false}, 50),
					new BarSubunit(new int[]{QUARTER_NOTE, QUARTER_NOTE}, new boolean[]{false, false}, 250),
					new BarSubunit(new int[]{EIGHTH_NOTE, EIGHTH_NOTE, EIGHTH_NOTE, EIGHTH_NOTE}, new boolean[] {false, true, false, false}, 350)
			};

	public static final BarSubunit[] BAR_SUBUNITS_SIMPLE_QUARTER_NOTE =
			{
					new BarSubunit(new int[]{QUARTER_NOTE}, new boolean[]{false}, 350),
					new BarSubunit(new int[]{DOTTED_EIGHTH_NOTE, SIXTEENTH_NOTE}, new boolean[]{false, false}, 25),
					new BarSubunit(new int[]{EIGHTH_NOTE, EIGHTH_NOTE}, new boolean[]{false, false}, 550),
					new BarSubunit(new int[]{EIGHTH_NOTE, SIXTEENTH_NOTE, SIXTEENTH_NOTE}, new boolean[]{false, true, false}, 25),
					new BarSubunit(new int[]{SIXTEENTH_NOTE, SIXTEENTH_NOTE, EIGHTH_NOTE}, new boolean[]{false, true, false}, 25),
					new BarSubunit(new int[]{SIXTEENTH_NOTE, SIXTEENTH_NOTE, SIXTEENTH_NOTE, SIXTEENTH_NOTE}, new boolean[]{false, true, false, false}, 25)
			};

	public static final BarSubunit[] BAR_SUBUNITS_COMPOUND_DOTTED_QUARTER_NOTE =
			{
					//Almost equal probabilities to all bar subunits
					new BarSubunit(new int[]{DOTTED_QUARTER_NOTE}, new boolean[]{false}, 59),
					new BarSubunit(new int[]{QUARTER_NOTE, EIGHTH_NOTE}, new boolean[]{false, false}, 59),
					new BarSubunit(new int[]{QUARTER_NOTE, SIXTEENTH_NOTE, SIXTEENTH_NOTE}, new boolean[]{false, true, false}, 58),
					new BarSubunit(new int[]{DOTTED_EIGHTH_NOTE, SIXTEENTH_NOTE, EIGHTH_NOTE}, new boolean[]{false, true, false}, 59),
					new BarSubunit(new int[]{DOTTED_EIGHTH_NOTE, SIXTEENTH_NOTE, SIXTEENTH_NOTE, SIXTEENTH_NOTE}, new boolean[]{false, true, false, false}, 59),
					new BarSubunit(new int[]{EIGHTH_NOTE, QUARTER_NOTE}, new boolean[]{false, false}, 58),
					new BarSubunit(new int[]{EIGHTH_NOTE, DOTTED_EIGHTH_NOTE, SIXTEENTH_NOTE}, new boolean[]{false, true, false}, 59),
					new BarSubunit(new int[]{EIGHTH_NOTE, EIGHTH_NOTE, EIGHTH_NOTE}, new boolean[]{false, true, false}, 59),
					new BarSubunit(new int[]{EIGHTH_NOTE, EIGHTH_NOTE, SIXTEENTH_NOTE, SIXTEENTH_NOTE}, new boolean[]{false, false, true, false}, 59),
					new BarSubunit(new int[]{EIGHTH_NOTE, SIXTEENTH_NOTE, SIXTEENTH_NOTE, EIGHTH_NOTE}, new boolean[]{false, false, true, false}, 59),
					new BarSubunit(new int[]{EIGHTH_NOTE, SIXTEENTH_NOTE, SIXTEENTH_NOTE, SIXTEENTH_NOTE, SIXTEENTH_NOTE}, new boolean[]{false, true, false, true, false}, 59),
					new BarSubunit(new int[]{SIXTEENTH_NOTE, SIXTEENTH_NOTE, QUARTER_NOTE}, new boolean[]{false, true, false}, 58),
					new BarSubunit(new int[]{SIXTEENTH_NOTE, SIXTEENTH_NOTE, DOTTED_EIGHTH_NOTE, SIXTEENTH_NOTE}, new boolean[]{false, true, false, false}, 59),
					new BarSubunit(new int[]{SIXTEENTH_NOTE, SIXTEENTH_NOTE, EIGHTH_NOTE, EIGHTH_NOTE}, new boolean[]{false, true, false, false}, 59),
					new BarSubunit(new int[]{SIXTEENTH_NOTE, SIXTEENTH_NOTE, EIGHTH_NOTE, SIXTEENTH_NOTE, SIXTEENTH_NOTE}, new boolean[]{false, true, false, true, false}, 59),
					new BarSubunit(new int[]{SIXTEENTH_NOTE, SIXTEENTH_NOTE, SIXTEENTH_NOTE, SIXTEENTH_NOTE, EIGHTH_NOTE}, new boolean[]{false, true, false, true, false}, 59),
					new BarSubunit(new int[]{SIXTEENTH_NOTE, SIXTEENTH_NOTE, SIXTEENTH_NOTE, SIXTEENTH_NOTE, SIXTEENTH_NOTE, SIXTEENTH_NOTE}, new boolean[]{false, true, false, true, false, false}, 59),
			};

	/**
	 * The probability is between 0-1000 inclusive, the sum of the probability of a set of bar subunits should be 1000
	 * The mDurations array and mMayBeNCT array are aligned, mDurations[x] is paired with mMayBeNCT[x]
	 */
	public static class BarSubunit
	{
		public final int[] mDurations;
		public final boolean[] mMayBeNCT;
		public final int mProbability;

		public BarSubunit(int[] durations, boolean[] nct, int probability)
		{
			if (durations.length != nct.length)
			{
				throw new IllegalArgumentException("Length of duration array and nct array do not match.");
			}
			mDurations = durations;
			mMayBeNCT = nct;
			mProbability = probability;
		}
	}
}
