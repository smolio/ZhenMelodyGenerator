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
			Pitch sameOctaveMark = new Pitch(basePitch, previousMelNote.getPitch().octave, scaleDegreesChordTones[i]);
			if (sameOctaveMark.scaleDegree == Pitch.ScaleDegree.FOUR && previousMelNote.getPitch().scaleDegree == Pitch.ScaleDegree.FOUR_SHARP)
			{
				//ignore 4 -> #4
				continue;
			}
			if (sameOctaveMark.scaleDegree == Pitch.ScaleDegree.FOUR_SHARP && previousMelNote.getPitch().scaleDegree == Pitch.ScaleDegree.FOUR)
			{
				//ignore #4 -> 4
				continue;
			}
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
				Pitch aboveOctaveMark = new Pitch(basePitch, previousMelNote.getPitch().octave + 1, scaleDegreesChordTones[i]);
				if (Math.abs(Pitch.getSemitonesBetweenPitches(previousMelNote.getPitch(), aboveOctaveMark)) <= Pitch.MAX_NUM_OF_SEMITONES_BETWEEN_PITCHES
						&& Pitch.getSemitonesBetweenPitches(aboveOctaveMark, Pitch.VOCAL_RANGE_MIN) <= 0
						&& Pitch.getSemitonesBetweenPitches(aboveOctaveMark, Pitch.VOCAL_RANGE_MAX) >= 0)
				{
					possiblePitchesList.add(aboveOctaveMark);
				}
				else
				{
					//it's guaranteed that the pitch resides in prev. octave marker - 1, as long as it's within vocal range
					Pitch belowOctaveMark = new Pitch(basePitch, previousMelNote.getPitch().octave - 1, scaleDegreesChordTones[i]);
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
			//all notes in possiblePitchesList is guaranteed to be within vocal range and within the interval limit
			ArrayList<Pitch> possiblePitchesNCTSuccessorList = new ArrayList<>(2);
			for (int i = 0; i < possiblePitchesList.size(); i++)
			{
				if (Math.abs(Pitch.getSemitonesBetweenPitches(possiblePitchesList.get(i), previousMelNote.getPitch())) <= Pitch.MAJOR_SECOND)
				{
					possiblePitchesNCTSuccessorList.add(possiblePitchesList.get(i));
				}
			}

			possiblePitchesList = possiblePitchesNCTSuccessorList;
		}
		if (previousMelNote.getPitch().scaleDegree == null)
		{
			System.out.println("null scaleDegree");
		}
		else if (mIsNCT)
		{
			ArrayList<Pitch> possiblePitchesNCTList = new ArrayList<>(2);

			//both neighboring notes are eligible due to neighbor tones
			Pitch.ScaleDegree upperNCTScaleDegree = null, lowerNCTScaleDegree = null;
			int upperNCTOctaveMarkerShift = 0, lowerNCTOctaveMarkerShift = 0;

			switch (previousMelNote.getPitch().scaleDegree)
			{
				case ONE_FLAT:
					throw new UnsupportedOperationException("Zhen was too lazy to support non-AP scaleDegrees");
				case ONE:
					if (mParentHarmonicNote.mParentMelody.getKeySignature().isMajor)
					{
						lowerNCTScaleDegree = Pitch.ScaleDegree.SEVEN;
					}
					else
					{
						lowerNCTScaleDegree = Pitch.ScaleDegree.SEVEN_FLAT;
					}
					lowerNCTOctaveMarkerShift = -1;
					upperNCTScaleDegree = Pitch.ScaleDegree.TWO; //no octave shift
					break;
				case ONE_SHARP:
					throw new UnsupportedOperationException("Zhen was too lazy to support non-AP scaleDegrees");
				case TWO_FLAT:
					throw new UnsupportedOperationException("Zhen was too lazy to support non-AP scaleDegrees");
				case TWO:
					if (mParentHarmonicNote.mParentMelody.getKeySignature().isMajor)
					{
						upperNCTScaleDegree = Pitch.ScaleDegree.THREE;
					}
					else
					{
						upperNCTScaleDegree = Pitch.ScaleDegree.THREE_FLAT;
					}
					lowerNCTScaleDegree = Pitch.ScaleDegree.ONE;
					break;
				case TWO_SHARP:
					throw new UnsupportedOperationException("Zhen was too lazy to support non-AP scaleDegrees");
				case THREE_FLAT:
				case THREE:
					upperNCTScaleDegree = Pitch.ScaleDegree.FOUR;
					lowerNCTScaleDegree = Pitch.ScaleDegree.TWO;
					break;
				case THREE_SHARP:
					throw new UnsupportedOperationException("Zhen was too lazy to support non-AP scaleDegrees");
				case FOUR_FLAT:
					throw new UnsupportedOperationException("Zhen was too lazy to support non-AP scaleDegrees");
				case FOUR:
					if (mParentHarmonicNote.mParentMelody.getKeySignature().isMajor)
					{
						lowerNCTScaleDegree = Pitch.ScaleDegree.THREE;
					}
					else
					{
						lowerNCTScaleDegree = Pitch.ScaleDegree.THREE_FLAT;
					}
					upperNCTScaleDegree = Pitch.ScaleDegree.FIVE;
					break;
				case FOUR_SHARP:
					upperNCTScaleDegree = Pitch.ScaleDegree.FIVE;
					lowerNCTScaleDegree = Pitch.ScaleDegree.THREE; //even for minor key sigs
					break;
				case FIVE_FLAT:
					throw new UnsupportedOperationException("Zhen was too lazy to support non-AP scaleDegrees");
				case FIVE:
					if (mParentHarmonicNote.mParentMelody.getKeySignature().isMajor)
					{
						upperNCTScaleDegree = Pitch.ScaleDegree.SIX;
					}
					else
					{
						upperNCTScaleDegree = Pitch.ScaleDegree.SIX_FLAT;
					}
					lowerNCTScaleDegree = Pitch.ScaleDegree.FOUR;
					break;
				case FIVE_SHARP:
					throw new UnsupportedOperationException("Zhen was too lazy to support non-AP scaleDegrees");
				case SIX_FLAT:
					//implicitly minor key sig
					upperNCTScaleDegree = Pitch.ScaleDegree.SEVEN_FLAT;
					lowerNCTScaleDegree = Pitch.ScaleDegree.FIVE;
					break;
				case SIX:
					//implicitly major key sig
					upperNCTScaleDegree = Pitch.ScaleDegree.SEVEN;
					lowerNCTScaleDegree = Pitch.ScaleDegree.FIVE;
					break;
				case SIX_SHARP:
					throw new UnsupportedOperationException("Zhen was too lazy to support non-AP scaleDegrees");
				case SEVEN_FLAT:
					//implicitly minor
					upperNCTScaleDegree = Pitch.ScaleDegree.ONE;
					upperNCTOctaveMarkerShift = 1;
					lowerNCTScaleDegree = Pitch.ScaleDegree.SIX_FLAT;
					break;
				case SEVEN:
					//implicitly major
					upperNCTScaleDegree = Pitch.ScaleDegree.ONE;
					upperNCTOctaveMarkerShift = 1;
					lowerNCTScaleDegree = Pitch.ScaleDegree.SIX;
					break;
				case SEVEN_SHARP:
					throw new UnsupportedOperationException("Zhen was too lazy to support non-AP scaleDegrees");
			}

			possiblePitchesNCTList.add(new Pitch(mParentHarmonicNote.mParentMelody.getKeySignature().getPitchBasedOnScaleDegree(upperNCTScaleDegree),
					previousMelNote.getPitch().octave + upperNCTOctaveMarkerShift, upperNCTScaleDegree));
			possiblePitchesNCTList.add(new Pitch(mParentHarmonicNote.mParentMelody.getKeySignature().getPitchBasedOnScaleDegree(lowerNCTScaleDegree),
					previousMelNote.getPitch().octave + lowerNCTOctaveMarkerShift, lowerNCTScaleDegree));

			possiblePitchesList = possiblePitchesNCTList;
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
