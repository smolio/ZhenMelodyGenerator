package org.altervista.zhen.MusicalElements;


import com.sun.istack.internal.Nullable;

import java.util.Arrays;

/**
 * Created by zhendeveloper on 7/29/15.
 */
public class HarmonicNote
{
	public static final int[] POSSIBLE_DURATIONS_SIMPLE_TIME = {Duration.HALF_NOTE, Duration.QUARTER_NOTE};
	public static final int[] POSSIBLE_DURATIONS_COMPOUND_TIME = {Duration.DOTTED_QUARTER_NOTE};

	public final Melody mParentMelody;
	private Chord mChord;
	private int mDuration;
	private MelodicNote[] mMelNotes;

	public HarmonicNote(Melody parentMelody, Chord chord, int duration, MelodicNote[] melNotes)
	{
		mParentMelody = parentMelody;
		mChord = chord;
		mDuration = duration;
		mMelNotes = melNotes;
	}

	public HarmonicNote(Melody parentMelody, Chord chord, int duration)
	{
		this(parentMelody, chord, duration, null);
	}

	public HarmonicNote(Melody parentMelody, int duration) { this(parentMelody, null, duration, null); }

	public Chord getChord()
	{
		return mChord;
	}

	public void setChord(Chord chord)
	{
		mChord = chord;
	}

	public int getDuration()
	{
		return mDuration;
	}

	public MelodicNote[] getMelNotes()
	{
		return mMelNotes;
	}

	public void setMelNotes(MelodicNote[] melNotes) { mMelNotes = melNotes; }

	/**
	 * Generate melodic notes for the harmonic note
	 * @param lastMelodicNoteOfPreviousHarmonicNote The last melodic note of the harmonic note that precedes this note, or null if this harmonic note is the first in the melody.
	 */
	protected void makeMelodicNotes(@Nullable MelodicNote lastMelodicNoteOfPreviousHarmonicNote)
	{
		//Determine which subunit to use
		Duration.BarSubunit[] barSubunits;
		if (mParentMelody.getTimeSignature().isCompound())
		{
			switch (mDuration)
			{
				case Duration.DOTTED_QUARTER_NOTE:
					barSubunits = Duration.BAR_SUBUNITS_COMPOUND_DOTTED_QUARTER_NOTE;
					break;
				default:
					throw new RuntimeException("Zhen, you forgot to include a Harmonic Note case.");
			}
		}
		else
		{
			switch (mDuration)
			{
				case Duration.QUARTER_NOTE:
					barSubunits = Duration.BAR_SUBUNITS_SIMPLE_QUARTER_NOTE;
					break;
				case Duration.HALF_NOTE:
					barSubunits = Duration.BAR_SUBUNITS_SIMPLE_HALF_NOTE;
					break;
				default:
					throw new RuntimeException("Zhen, you forgot to include a Harmonic Note case.");
			}
		}

		//select the subunit
		int randInt = (int)(Math.random() * 1000);
		int lessThanProbability = 0;
		Duration.BarSubunit subunitToUse = null;

		for (Duration.BarSubunit barSubunit : barSubunits)
		{
			lessThanProbability += barSubunit.mProbability;
			if (randInt < lessThanProbability)
			{
				subunitToUse = barSubunit;
				break;
			}
		}

		if (subunitToUse == null) throw new RuntimeException("There is no subunit, you probably screwed up on probabilities");
		//else
		//convert subunit into MelodicNote[]
		mMelNotes = new MelodicNote[subunitToUse.mDurations.length];
		for (int i = 0; i < mMelNotes.length; i++)
		{
			mMelNotes[i] = new MelodicNote(this, subunitToUse.mDurations[i], subunitToUse.mMayBeNCT[i]);
		}

		if (lastMelodicNoteOfPreviousHarmonicNote == null)
		{
			//this harmonic note is the 1st harmonic note in the melody
			//any note in the 4th octave is within range
			mMelNotes[0].setPitch(new Pitch(mParentMelody.getKeySignature().getPitchBasedOnScaleDegree(Pitch.ScaleDegree.ONE), 4, Pitch.ScaleDegree.ONE));
		}
		else
		{
			Pitch[] possible0thPitches = mMelNotes[0].getPossiblePitches(lastMelodicNoteOfPreviousHarmonicNote);
			int randInt0thPitchIndex = (int)(Math.random() * possible0thPitches.length); //Range: [0, length) = [0, length-1]
			mMelNotes[0].setPitch(possible0thPitches[randInt0thPitchIndex]);
		}

		if (mMelNotes.length == 1) return;
		//else
		for (int i = 1; i < mMelNotes.length; i++)
		{
			Pitch[] possiblePitches = mMelNotes[i].getPossiblePitches(mMelNotes[i-1]);
			if (possiblePitches.length == 0) System.out.println("possiblePitches == 0");
			int randIntPitchIndex = (int)(Math.random() * possiblePitches.length); //Range: [0, length) = [0, length-1]
			mMelNotes[i].setPitch(possiblePitches[randIntPitchIndex]);
		}
	}

	@Override
	public String toString()
	{
		return Arrays.toString(mMelNotes);
	}
}
