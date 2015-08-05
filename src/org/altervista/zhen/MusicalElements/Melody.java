package org.altervista.zhen.MusicalElements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhendeveloper on 7/29/15.
 */
public class Melody
{
	public static final int NUM_OF_BARS = 4;

	private KeySignature mKS;
	private TimeSignature mTS;

	private HarmonicNote[] mHarmonicNotes;

	public Melody(KeySignature ks, TimeSignature ts)
	{
		mKS = ks;
		mTS = ts;
		makeHarmonicNotes();
	}

	public KeySignature getKeySignature()
	{
		return mKS;
	}

	public TimeSignature getTimeSignature()
	{
		return mTS;
	}

	public HarmonicNote[] getHarmonicNotes()
	{
		return mHarmonicNotes;
	}

	private void makeHarmonicNotes()
	{
		if (mTS.isCompound())
		{
			mHarmonicNotes = new HarmonicNote[mTS.upperNum * NUM_OF_BARS / 3];
			for (int i = 0; i < mHarmonicNotes.length; i++)
			{
				mHarmonicNotes[i] = new HarmonicNote(this, Duration.DOTTED_QUARTER_NOTE);
			}
		}
		else //simple time
		{
			List<HarmonicNote> harmonicNoteList = new ArrayList<>();

			for (int i = 0; i < NUM_OF_BARS; i++)
			{
				int numOfQuarterNotesToFillInBar = mTS.upperNum;

				while(numOfQuarterNotesToFillInBar != 0)
				{
					if (numOfQuarterNotesToFillInBar == 1)
					{
						harmonicNoteList.add(new HarmonicNote(this, Duration.QUARTER_NOTE));
						break;
						//it's not needed to lower numOfQuarterNotesToFillInBar due to the break statement above
					}
					//else
					//TODO: CHANGE THIS TO MAKE AN INDEPENDENT METHOD DETERMINE THE DURATION OF THE HARMONIC NOTE
					if ((int)(Math.random()*1000) < Duration.HALF_NOTE_BAR_SUBUNIT_PROBABILITY) //if randomNum is between 0-PROBABILITY
					{
						harmonicNoteList.add(new HarmonicNote(this, Duration.HALF_NOTE));
						numOfQuarterNotesToFillInBar -= 2;
					}
					else //add quarter note harmonic note
					{
						harmonicNoteList.add(new HarmonicNote(this, Duration.QUARTER_NOTE));
						numOfQuarterNotesToFillInBar -= 1;
					}
				}
			}

			mHarmonicNotes = new HarmonicNote[harmonicNoteList.size()];
			harmonicNoteList.toArray(mHarmonicNotes);
		}

		if (mHarmonicNotes.length <= 3)
		{
			throw new RuntimeException("Not enough harmonic notes for a cadence and starting chord.");
		}

		if (mKS.isMajor)
		{
			mHarmonicNotes[0].setChord(Chord.I);
			mHarmonicNotes[mHarmonicNotes.length - 2].setChord(Chord.V7);
			mHarmonicNotes[mHarmonicNotes.length - 1].setChord(Chord.I);
		}
		else //minor KS
		{
			mHarmonicNotes[0].setChord(Chord.i);
			mHarmonicNotes[mHarmonicNotes.length - 2].setChord(Chord.V7);
			mHarmonicNotes[mHarmonicNotes.length - 1].setChord(Chord.i);
		}

		if (mHarmonicNotes.length >= 5)
		{
			for (int i = mHarmonicNotes.length - 3; i >= 2; i--)
			{
				mHarmonicNotes[i].setChord(mHarmonicNotes[i+1].getChord().getRandomPrecedingChord(mKS.isMajor));
			}
		}

		//make the 2nd chord (mHN[1])
		int randInt = (int)(Math.random() * 1000);
		switch(mHarmonicNotes[2].getChord())
		{
			case I:
			case i:
				if (randInt < 750) mHarmonicNotes[1].setChord(Chord.V7); //0-749
				else mHarmonicNotes[1].setChord(Chord.viiDim); //750-999
				break;
			case ii:
				if (randInt < 500) mHarmonicNotes[1].setChord(Chord.I); //0-499
				else mHarmonicNotes[1].setChord(Chord.IV); //500-999
				break;
			case iiDim:
				if (randInt < 500) mHarmonicNotes[1].setChord(Chord.i); //0-499
				else mHarmonicNotes[1].setChord(Chord.iv); //500-999
				break;
			case IV:
				mHarmonicNotes[1].setChord(Chord.I);
				break;
			case iv:
				mHarmonicNotes[1].setChord(Chord.i);
				break;
			case V7:
				if (mKS.isMajor)
				{
					if (randInt < 200) mHarmonicNotes[1].setChord(Chord.I);
					else if (randInt < 400) mHarmonicNotes[1].setChord(Chord.ii);
					else if (randInt < 600) mHarmonicNotes[1].setChord(Chord.IV);
					else if (randInt < 800) mHarmonicNotes[1].setChord(Chord.V_V);
					else mHarmonicNotes[1].setChord(Chord.viiDim);
				}
				else
				{
					if (randInt < 200) mHarmonicNotes[1].setChord(Chord.i);
					else if (randInt < 400) mHarmonicNotes[1].setChord(Chord.iiDim);
					else if (randInt < 600) mHarmonicNotes[1].setChord(Chord.iv);
					else if (randInt < 800) mHarmonicNotes[1].setChord(Chord.V_V);
					else mHarmonicNotes[1].setChord(Chord.viiDim);
				}
				break;
			case V_V:
				if (mKS.isMajor)
				{
					if (randInt < 333) mHarmonicNotes[1].setChord(Chord.I);
					else if (randInt < 666) mHarmonicNotes[1].setChord(Chord.ii);
					else mHarmonicNotes[1].setChord(Chord.IV);
				}
				else
				{
					if (randInt < 333) mHarmonicNotes[1].setChord(Chord.i);
					else if (randInt < 666) mHarmonicNotes[1].setChord(Chord.iiDim);
					else mHarmonicNotes[1].setChord(Chord.iv);
				}
				break;
			case vi:
			case VI:
				if (randInt < 500) mHarmonicNotes[1].setChord(Chord.V7);
				else mHarmonicNotes[1].setChord(Chord.viiDim);
				break;
			case viiDim:
				if (mKS.isMajor)
				{
					if (randInt < 250) mHarmonicNotes[1].setChord(Chord.I);
					else if (randInt < 500) mHarmonicNotes[1].setChord(Chord.ii);
					else if (randInt < 750) mHarmonicNotes[1].setChord(Chord.IV);
					else mHarmonicNotes[1].setChord(Chord.V_V);
				}
				else
				{
					if (randInt < 250) mHarmonicNotes[1].setChord(Chord.i);
					else if (randInt < 500) mHarmonicNotes[1].setChord(Chord.iiDim);
					else if (randInt < 750) mHarmonicNotes[1].setChord(Chord.iv);
					else mHarmonicNotes[1].setChord(Chord.V_V);
				}
				break;
		}

		//DO NOT GENERATE MELODIC NOTES FOR THE LAST HARMONIC NOTE, do generate melNotes for the 2nd to last

		for (int i = 0; i < mHarmonicNotes.length - 1; i++)
		{
			MelodicNote prevNote;
			if (i == 0) prevNote = null;
			else
			{
				MelodicNote[] prevMelodicNotesOfPrevHarmonicNote  = mHarmonicNotes[i-1].getMelNotes();
				if (prevMelodicNotesOfPrevHarmonicNote.length == 0) prevNote = null;
				else prevNote = prevMelodicNotesOfPrevHarmonicNote[prevMelodicNotesOfPrevHarmonicNote.length - 1];
			}
			mHarmonicNotes[i].makeMelodicNotes(prevNote);
		}

		Pitch.BasePitch basePitchLastMelNote = mKS.getPitchBasedOnScaleDegree(Pitch.ScaleDegree.ONE);
		Pitch pitchOfSecondToLastMelNote = mHarmonicNotes[mHarmonicNotes.length-2]
				.getMelNotes()[mHarmonicNotes[mHarmonicNotes.length-2].getMelNotes().length - 1].getPitch();

		Pitch pitchLastMelNote;

		//tritone checks should not be necessary for the last note
		//same octave marker
		Pitch testPitch = new Pitch(basePitchLastMelNote, pitchOfSecondToLastMelNote.octave);
		if (Math.abs(Pitch.getSemitonesBetweenPitches(testPitch, pitchOfSecondToLastMelNote)) <= Pitch.MAX_NUM_OF_SEMITONES_BETWEEN_PITCHES)
		{
			pitchLastMelNote = testPitch;
		}
		else
		{
			//higher octave marker
			testPitch = new Pitch(basePitchLastMelNote, pitchOfSecondToLastMelNote.octave + 1);
			if (Math.abs(Pitch.getSemitonesBetweenPitches(testPitch, pitchOfSecondToLastMelNote)) <= Pitch.MAX_NUM_OF_SEMITONES_BETWEEN_PITCHES)
			{
				pitchLastMelNote = testPitch;
			}
			else
			{
				//lower octave marker
				pitchLastMelNote = new Pitch(basePitchLastMelNote, pitchOfSecondToLastMelNote.octave - 1);
			}
		}
		MelodicNote lastMelNote = new MelodicNote
				(
						//parent HarmonicNote
						mHarmonicNotes[mHarmonicNotes.length-1],
						//tonic
						pitchLastMelNote,
						//duration of last melodic note == duration of last harmonic note
						mHarmonicNotes[mHarmonicNotes.length-1].getDuration(),
						//not NCT (shouldn't matter)
						false
				);

		mHarmonicNotes[mHarmonicNotes.length-1].setMelNotes(new MelodicNote[]{lastMelNote});
		//No need to change 1st melodic note to tonic, this is handled in the algorithm
	}
}
