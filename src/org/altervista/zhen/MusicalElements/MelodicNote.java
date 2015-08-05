package org.altervista.zhen.MusicalElements;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;

/**
 * Created by zhendeveloper on 7/29/15.
 */
public class MelodicNote
{

	private HarmonicNote mParentHarmonicNote;
	private Pitch mPitch;
	private int mDuration;
	private boolean mMayBeNCT; //NCT = non-chord tone
	private boolean mIsNCT;

	public MelodicNote(HarmonicNote parentNote, Pitch pitch, int duration, boolean mayBeNCT)
	{
		mParentHarmonicNote = parentNote;
		mPitch = pitch;
		mDuration = duration;
		mMayBeNCT = mayBeNCT;
		mIsNCT = mMayBeNCT;
	}

	public MelodicNote(HarmonicNote parentNote, int duration, boolean mayBeNCT)
	{
		this(parentNote, null, duration, mayBeNCT);
	}

	public void setPitch(Pitch pitch)
	{
		mPitch = pitch;
	}

	public Pitch getPitch()
	{
		return mPitch;
	}

	public int getDuration() { return mDuration; }

	//TODO: TEST THIS METHOD
	protected Pitch[] getPossiblePitches(@NotNull MelodicNote previousMelNote)
	{
		ArrayList<Pitch> possiblePitchesList = new ArrayList<>();
		Pitch.ScaleDegree[] scaleDegreesChordTones = mParentHarmonicNote.getChord().scaleDegrees;
		for (int i = 0; i < scaleDegreesChordTones.length; i++)
		{
			Pitch.BasePitch basePitch = mParentHarmonicNote.mParentMelody.getKeySignature().getPitchBasedOnScaleDegree(scaleDegreesChordTones[i]);

			/*
			There exists only one & always one octave marker that the pitch may reside such that the pitch is within the max interval limit
			between pitches. That octave marker is either the same octave marker as the preceding pitch or one octave marker above/below it.

			No base pitch is ever out of range when the max interval is a major 6th.
			 */
			Pitch sameOctaveMark = new Pitch(basePitch, previousMelNote.getPitch().octave);
			if (Math.abs(Pitch.getSemitonesBetweenPitches(previousMelNote.getPitch(), sameOctaveMark)) == Pitch.TRITONE)
			{
				//if the interval between the 2 pitches at the same octave marker is a tritone, then no octave modifications may change the tritone
				//ignore all notes that require a tritone interval jump
				continue;
			}
			if (Math.abs(Pitch.getSemitonesBetweenPitches(previousMelNote.getPitch(), sameOctaveMark)) == Pitch.UNISON)
			{
				//ignore the note that is identical to the preceding note.
				continue;
			}
			if (Math.abs(Pitch.getSemitonesBetweenPitches(previousMelNote.getPitch(), sameOctaveMark)) <= Pitch.MAX_NUM_OF_SEMITONES_BETWEEN_PITCHES
					&& Pitch.getSemitonesBetweenPitches(sameOctaveMark, Pitch.VOCAL_RANGE_MIN) <= 0
					&& Pitch.getSemitonesBetweenPitches(sameOctaveMark, Pitch.VOCAL_RANGE_MAX) >= 0)
			{
				possiblePitchesList.add(sameOctaveMark);
			}
			else
			{
				Pitch aboveOctaveMark = new Pitch(basePitch, previousMelNote.getPitch().octave + 1);
				if (Math.abs(Pitch.getSemitonesBetweenPitches(previousMelNote.getPitch(), aboveOctaveMark)) <= Pitch.MAX_NUM_OF_SEMITONES_BETWEEN_PITCHES
						&& Pitch.getSemitonesBetweenPitches(aboveOctaveMark, Pitch.VOCAL_RANGE_MIN) <= 0
						&& Pitch.getSemitonesBetweenPitches(aboveOctaveMark, Pitch.VOCAL_RANGE_MAX) >= 0)
				{
					possiblePitchesList.add(aboveOctaveMark);
				}
				else
				{
					//it's guaranteed that the pitch resides in prev. octave marker - 1, as long as it's within vocal range
					Pitch belowOctaveMark = new Pitch(basePitch, previousMelNote.getPitch().octave - 1);
					if (Pitch.getSemitonesBetweenPitches(belowOctaveMark, Pitch.VOCAL_RANGE_MIN) <= 0
							&& Pitch.getSemitonesBetweenPitches(belowOctaveMark, Pitch.VOCAL_RANGE_MAX) >= 0)
					{
						possiblePitchesList.add(belowOctaveMark);
					}
				}
			}
		}

		//the NCT code may be removed and the rest of the method will still work
		//at the moment, mIsNCT = mMayBeNCT is set in the constructor
		if (previousMelNote.mIsNCT)
		{
			
		}
		if (mIsNCT)
		{

		}

		Pitch[] possiblePitches = new Pitch[possiblePitchesList.size()];
		possiblePitchesList.toArray(possiblePitches);

		return possiblePitches;
	}

	@Override
	public String toString()
	{
		return mPitch.toString() + "," + mDuration;
	}
}
