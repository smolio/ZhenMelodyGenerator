package org.altervista.zhen.MusicalElements;

import org.altervista.zhen.MusicalElements.Pitch.ScaleDegree;

import static org.altervista.zhen.MusicalElements.Pitch.ScaleDegree.*;

/**
 * All chords that may show up on the AP Music Theory exam.
 */
public enum Chord {
    I(ONE, THREE, FIVE),
    i(ONE, THREE_FLAT, FIVE),
    ii(TWO, FOUR, SIX),
    iiDim(TWO, FOUR, SIX_FLAT),
    IV(ONE, FOUR, SIX),
    iv(ONE, FOUR, SIX_FLAT),
    V7(TWO, FOUR, FIVE, SEVEN),
    V_V(TWO, FOUR_SHARP),
    vi(ONE, THREE, SIX),
    VI(ONE, THREE_FLAT, SIX_FLAT),
    viiDim(TWO, FOUR, SEVEN);

	public final ScaleDegree[] scaleDegrees;
	Chord(ScaleDegree... scaleDegrees_loc)
	{
		scaleDegrees = scaleDegrees_loc;
	}

	/**
	 * Call this on a chord to get a random chord that logically precedes this chord.
	 * Example:
	 * I.getRandomPrecedingChord() will give a random chord that logically precedes the I chord.
	 * @return A random chord that logically precedes the chord that the method was called on
	 */
	public Chord getRandomPrecedingChord(boolean keySigIsMajor)
	{
		int randInt = (int)(Math.random()*1000);
		switch(this)
		{
			case I:
			case i:
				if (randInt < 900) return V7;//0-899
				else return viiDim;//900-999
			case ii:
				if (randInt < 500) return I; //0-499
				else if (randInt >= 750) return vi; //750-999
				else return IV; //500-749
			case iiDim:
				if (randInt < 500) return i; //0-499
				else if (randInt >= 750) return VI; //750-999
				else return iv; //500-749
			case IV:
				if (randInt < 900) return I; //0-899
				else return vi; //900-999
			case iv:
				if (randInt < 900) return i; //0-899
				else return VI; //900-999
			case V7:
				if (keySigIsMajor)
				{
					if (randInt < 100) return I; //0-99
					else if (randInt < 300) return ii; //100-299
					else if (randInt < 750) return IV; //300-749
					else if (randInt < 825) return V_V; //750-824
					else if (randInt < 900) return vi; //825-899
					else return viiDim; //900-999
				}
				else
				{
					if (randInt < 100) return i;
					else if (randInt < 300) return iiDim;
					else if (randInt < 750) return iv;
					else if (randInt < 825) return V_V;
					else if (randInt < 900) return VI;
					else return viiDim;
				}
			case V_V:
				if (keySigIsMajor)
				{
					if (randInt < 250) return ii; //333-457
					else return IV; //458-832
				}
				else
				{
					if (randInt < 250) return iiDim; //333-457
					else return iv; //458-832
				}
			case vi:
			case VI:
				if (randInt < 667) return V7; //0-666
				else return viiDim; //667-999
			case viiDim:
				if (keySigIsMajor)
				{
					if (randInt < 250) return I; //0-249
					else if (randInt < 344) return ii; //250-343
					else if (randInt < 625) return IV; //344-625
					else if (randInt < 875) return V_V; //625-874
					else return vi; //875-999
				}
				else
				{
					if (randInt < 250) return i; //0-249
					else if (randInt < 344) return iiDim; //250-343
					else if (randInt < 625) return iv; //344-625
					else if (randInt < 875) return V_V; //625-874
					else return VI; //875-999
				}
			default:
				assert false;
				return null;
		}
	}
}
