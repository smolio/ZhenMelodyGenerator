package org.altervista.zhen.MusicalElements;

/**
 * Represents a musical pitch
 */
public class Pitch
{
	//interval constants in semitones
	static final int MAX_NUM_OF_SEMITONES_BETWEEN_PITCHES = 9; //major 6th
	static final int TRITONE = 6;
	static final int UNISON = 0;

	static final Pitch VOCAL_RANGE_MIN = new Pitch(BasePitch.A, 3);
	static final Pitch VOCAL_RANGE_MAX = new Pitch(BasePitch.D, 5);

	public final BasePitch basePitch;
	public final int octave;

	/**
	 * @param basePitch_loc The pitch
	 * @param octave_loc The octave (use scientific octave notation)
	 */
	public Pitch(BasePitch basePitch_loc, int octave_loc)
	{
		basePitch = basePitch_loc;
		octave = octave_loc;
	}

	//works
	/**
	 * @param p1 1st Pitch
	 * @param p2 2nd Pitch
	 * @return Number of semitones between the 2 pitches (positive if p1 is lower than p2, negative if p1 is higher than p2)
	 */
	public static int getSemitonesBetweenPitches(Pitch p1, Pitch p2)
	{
		int octaveAdjustment = (p2.octave - p1.octave) * 12;
		switch (p1.basePitch)
		{
			case G_SHARP:
			case A_FLAT:
				switch (p2.basePitch)
				{
					case G_SHARP:
					case A_FLAT:
						return octaveAdjustment;
					case A:
						return octaveAdjustment+1;
					case A_SHARP:
					case B_FLAT:
						return octaveAdjustment+2;
					case B:
						return octaveAdjustment+3;
					case B_SHARP:
						return octaveAdjustment+4;
					case C_FLAT:
						return octaveAdjustment-9;
					case C:
						return octaveAdjustment-8;
					case C_SHARP:
					case D_FLAT:
						return octaveAdjustment-7;
					case D:
						return octaveAdjustment-6;
					case D_SHARP:
					case E_FLAT:
						return octaveAdjustment-5;
					case E:
					case F_FLAT:
						return octaveAdjustment-4;
					case E_SHARP:
					case F:
						return octaveAdjustment-3;
					case F_SHARP:
					case G_FLAT:
						return octaveAdjustment-2;
					case G:
						return octaveAdjustment-1;
				}
			case A:
				switch (p2.basePitch)
				{
					case A_FLAT:
						return octaveAdjustment-1;
					case A:
						return octaveAdjustment;
					case A_SHARP:
						return octaveAdjustment+1;
					case B_FLAT:
						return octaveAdjustment+1;
					case B:
						return octaveAdjustment+2;
					case B_SHARP:
						return octaveAdjustment+3;
					case C_FLAT:
						return octaveAdjustment-10;
					case C:
						return octaveAdjustment-9;
					case C_SHARP:
						return octaveAdjustment-8;
					case D_FLAT:
						return octaveAdjustment-8;
					case D:
						return octaveAdjustment-7;
					case D_SHARP:
						return octaveAdjustment-6;
					case E_FLAT:
						return octaveAdjustment-6;
					case E:
						return octaveAdjustment-5;
					case E_SHARP:
						return octaveAdjustment-4;
					case F_FLAT:
						return octaveAdjustment-5;
					case F:
						return octaveAdjustment-4;
					case F_SHARP:
						return octaveAdjustment-3;
					case G_FLAT:
						return octaveAdjustment-3;
					case G:
						return octaveAdjustment-2;
					case G_SHARP:
						return octaveAdjustment-1;
				}
			case A_SHARP:
			case B_FLAT:
				switch (p2.basePitch)
				{
					case A_SHARP:
					case B_FLAT:
						return octaveAdjustment;
					case B:
						return octaveAdjustment+1;
					case B_SHARP:
						return octaveAdjustment+2;
					case A:
						return octaveAdjustment-1;
					case A_FLAT:
					case G_SHARP:
						return octaveAdjustment-2;
					case G:
						return octaveAdjustment-3;
					case G_FLAT:
					case F_SHARP:
						return octaveAdjustment-4;
					case F:
					case E_SHARP:
						return octaveAdjustment-5;
					case E:
					case F_FLAT:
						return octaveAdjustment-6;
					case E_FLAT:
					case D_SHARP:
						return octaveAdjustment-7;
					case D:
						return octaveAdjustment-8;
					case D_FLAT:
					case C_SHARP:
						return octaveAdjustment-9;
					case C:
						return octaveAdjustment-10;
					case C_FLAT:
						return octaveAdjustment-11;
				}
			case B:
				switch (p2.basePitch)
				{
					case B:
						return octaveAdjustment;
					case B_SHARP:
						return octaveAdjustment+1;
					case B_FLAT:
					case A_SHARP:
						return octaveAdjustment-1;
					case A:
						return octaveAdjustment-2;
					case A_FLAT:
					case G_SHARP:
						return octaveAdjustment-3;
					case G:
						return octaveAdjustment-4;
					case G_FLAT:
					case F_SHARP:
						return octaveAdjustment-5;
					case F:
					case E_SHARP:
						return octaveAdjustment-6;
					case E:
					case F_FLAT:
						return octaveAdjustment-7;
					case E_FLAT:
					case D_SHARP:
						return octaveAdjustment-8;
					case D:
						return octaveAdjustment-9;
					case D_FLAT:
					case C_SHARP:
						return octaveAdjustment-10;
					case C:
						return octaveAdjustment-11;
					case C_FLAT:
						return octaveAdjustment-12;
				}
			case B_SHARP:
				switch (p2.basePitch)
				{
					case B_SHARP:
						return octaveAdjustment;
					case B:
						return octaveAdjustment-1;
					case B_FLAT:
					case A_SHARP:
						return octaveAdjustment-2;
					case A:
						return octaveAdjustment-3;
					case A_FLAT:
					case G_SHARP:
						return octaveAdjustment-4;
					case G:
						return octaveAdjustment-5;
					case G_FLAT:
					case F_SHARP:
						return octaveAdjustment-6;
					case F:
					case E_SHARP:
						return octaveAdjustment-7;
					case E:
					case F_FLAT:
						return octaveAdjustment-8;
					case E_FLAT:
					case D_SHARP:
						return octaveAdjustment-9;
					case D:
						return octaveAdjustment-10;
					case D_FLAT:
					case C_SHARP:
						return octaveAdjustment-11;
					case C:
						return octaveAdjustment-12;
					case C_FLAT:
						return octaveAdjustment-13;
				}
			case C_FLAT: //different from B
				switch (p2.basePitch)
				{
					case C_FLAT:
						return octaveAdjustment;
					case C:
						return octaveAdjustment+1;
					case C_SHARP:
					case D_FLAT:
						return octaveAdjustment+2;
					case D:
						return octaveAdjustment+3;
					case D_SHARP:
					case E_FLAT:
						return octaveAdjustment+4;
					case E:
					case F_FLAT:
						return octaveAdjustment+5;
					case F:
					case E_SHARP:
						return octaveAdjustment+6;
					case F_SHARP:
					case G_FLAT:
						return octaveAdjustment+7;
					case G:
						return octaveAdjustment+8;
					case G_SHARP:
					case A_FLAT:
						return octaveAdjustment+9;
					case A:
						return octaveAdjustment+10;
					case A_SHARP:
					case B_FLAT:
						return octaveAdjustment+11;
					case B:
						return octaveAdjustment+12;
					case B_SHARP:
						return octaveAdjustment+13;
				}
			case C: //different from B Sharp
				switch (p2.basePitch)
				{
					case C:
						return octaveAdjustment;
					case C_FLAT:
						return octaveAdjustment-1;
					case C_SHARP:
					case D_FLAT:
						return octaveAdjustment+1;
					case D:
						return octaveAdjustment+2;
					case D_SHARP:
					case E_FLAT:
						return octaveAdjustment+3;
					case E:
					case F_FLAT:
						return octaveAdjustment+4;
					case F:
					case E_SHARP:
						return octaveAdjustment+5;
					case F_SHARP:
					case G_FLAT:
						return octaveAdjustment+6;
					case G:
						return octaveAdjustment+7;
					case G_SHARP:
					case A_FLAT:
						return octaveAdjustment+8;
					case A:
						return octaveAdjustment+9;
					case A_SHARP:
					case B_FLAT:
						return octaveAdjustment+10;
					case B:
						return octaveAdjustment+11;
					case B_SHARP:
						return octaveAdjustment+12;
				}
			case C_SHARP:
			case D_FLAT:
				switch (p2.basePitch)
				{
					case C_SHARP:
					case D_FLAT:
						return octaveAdjustment;
					case C:
						return octaveAdjustment-1;
					case C_FLAT:
						return octaveAdjustment-2;
					case D:
						return octaveAdjustment+1;
					case D_SHARP:
					case E_FLAT:
						return octaveAdjustment+2;
					case E:
					case F_FLAT:
						return octaveAdjustment+3;
					case F:
					case E_SHARP:
						return octaveAdjustment+4;
					case F_SHARP:
					case G_FLAT:
						return octaveAdjustment+5;
					case G:
						return octaveAdjustment+6;
					case G_SHARP:
					case A_FLAT:
						return octaveAdjustment+7;
					case A:
						return octaveAdjustment+8;
					case A_SHARP:
					case B_FLAT:
						return octaveAdjustment+9;
					case B:
						return octaveAdjustment+10;
					case B_SHARP:
						return octaveAdjustment+11;
				}
			case D:
				switch (p2.basePitch)
				{
					case D:
						return octaveAdjustment;
					case D_FLAT:
					case C_SHARP:
						return octaveAdjustment-1;
					case C:
						return octaveAdjustment-2;
					case C_FLAT:
						return octaveAdjustment-3;
					case D_SHARP:
					case E_FLAT:
						return octaveAdjustment+1;
					case E:
					case F_FLAT:
						return octaveAdjustment+2;
					case F:
					case E_SHARP:
						return octaveAdjustment+3;
					case F_SHARP:
					case G_FLAT:
						return octaveAdjustment+4;
					case G:
						return octaveAdjustment+5;
					case G_SHARP:
					case A_FLAT:
						return octaveAdjustment+6;
					case A:
						return octaveAdjustment+7;
					case A_SHARP:
					case B_FLAT:
						return octaveAdjustment+8;
					case B:
						return octaveAdjustment+9;
					case B_SHARP:
						return octaveAdjustment+10;
				}
			case D_SHARP:
			case E_FLAT:
				switch (p2.basePitch)
				{
					case D_SHARP:
					case E_FLAT:
						return octaveAdjustment;
					case D:
						return octaveAdjustment-1;
					case D_FLAT:
					case C_SHARP:
						return octaveAdjustment-2;
					case C:
						return octaveAdjustment-3;
					case C_FLAT:
						return octaveAdjustment-4;
					case E:
					case F_FLAT:
						return octaveAdjustment+1;
					case F:
					case E_SHARP:
						return octaveAdjustment+2;
					case F_SHARP:
					case G_FLAT:
						return octaveAdjustment+3;
					case G:
						return octaveAdjustment+4;
					case G_SHARP:
					case A_FLAT:
						return octaveAdjustment+5;
					case A:
						return octaveAdjustment+6;
					case A_SHARP:
					case B_FLAT:
						return octaveAdjustment+7;
					case B:
						return octaveAdjustment+8;
					case B_SHARP:
						return octaveAdjustment+9;
				}
			case E:
			case F_FLAT:
				switch (p2.basePitch)
				{
					case E:
					case F_FLAT:
						return octaveAdjustment;
					case E_FLAT:
					case D_SHARP:
						return octaveAdjustment-1;
					case D:
						return octaveAdjustment-2;
					case D_FLAT:
					case C_SHARP:
						return octaveAdjustment-3;
					case C:
						return octaveAdjustment-4;
					case C_FLAT:
						return octaveAdjustment-5;
					case E_SHARP:
					case F:
						return octaveAdjustment+1;
					case F_SHARP:
					case G_FLAT:
						return octaveAdjustment+2;
					case G:
						return octaveAdjustment+3;
					case G_SHARP:
					case A_FLAT:
						return octaveAdjustment+4;
					case A:
						return octaveAdjustment+5;
					case A_SHARP:
					case B_FLAT:
						return octaveAdjustment+6;
					case B:
						return octaveAdjustment+7;
					case B_SHARP:
						return octaveAdjustment+8;
				}
			case E_SHARP:
			case F:
				switch (p2.basePitch)
				{
					case E_SHARP:
					case F:
						return octaveAdjustment;
					case E:
					case F_FLAT:
						return octaveAdjustment-1;
					case E_FLAT:
					case D_SHARP:
						return octaveAdjustment-2;
					case D:
						return octaveAdjustment-3;
					case D_FLAT:
					case C_SHARP:
						return octaveAdjustment-4;
					case C:
						return octaveAdjustment-5;
					case C_FLAT:
						return octaveAdjustment-6;
					case F_SHARP:
					case G_FLAT:
						return octaveAdjustment+1;
					case G:
						return octaveAdjustment+2;
					case G_SHARP:
					case A_FLAT:
						return octaveAdjustment+3;
					case A:
						return octaveAdjustment+4;
					case A_SHARP:
					case B_FLAT:
						return octaveAdjustment+5;
					case B:
						return octaveAdjustment+6;
					case B_SHARP:
						return octaveAdjustment+7;
				}
			case F_SHARP:
			case G_FLAT:
				switch (p2.basePitch)
				{
					case F_SHARP:
					case G_FLAT:
						return octaveAdjustment;
					case F:
					case E_SHARP:
						return octaveAdjustment-1;
					case E:
					case F_FLAT:
						return octaveAdjustment-2;
					case E_FLAT:
					case D_SHARP:
						return octaveAdjustment-3;
					case D:
						return octaveAdjustment-4;
					case D_FLAT:
					case C_SHARP:
						return octaveAdjustment-5;
					case C:
						return octaveAdjustment-6;
					case C_FLAT:
						return octaveAdjustment-7;
					case G:
						return octaveAdjustment+1;
					case G_SHARP:
					case A_FLAT:
						return octaveAdjustment+2;
					case A:
						return octaveAdjustment+3;
					case A_SHARP:
					case B_FLAT:
						return octaveAdjustment+4;
					case B:
						return octaveAdjustment+5;
					case B_SHARP:
						return octaveAdjustment+6;
				}
			case G:
				switch (p2.basePitch)
				{
					case G:
						return octaveAdjustment;
					case F_SHARP:
					case G_FLAT:
						return octaveAdjustment-1;
					case F:
					case E_SHARP:
						return octaveAdjustment-2;
					case E:
					case F_FLAT:
						return octaveAdjustment-3;
					case E_FLAT:
					case D_SHARP:
						return octaveAdjustment-4;
					case D:
						return octaveAdjustment-5;
					case D_FLAT:
					case C_SHARP:
						return octaveAdjustment-6;
					case C:
						return octaveAdjustment-7;
					case C_FLAT:
						return octaveAdjustment-8;
					case G_SHARP:
					case A_FLAT:
						return octaveAdjustment+1;
					case A:
						return octaveAdjustment+2;
					case A_SHARP:
					case B_FLAT:
						return octaveAdjustment+3;
					case B:
						return octaveAdjustment+4;
					case B_SHARP:
						return octaveAdjustment+5;
				}
		}
		//if this method fails to return before exhausing all cases
		throw new RuntimeException("Zhen, somehow an input failed to trigger a pair of cases that leads to a return.");
	}

	@Override
	public String toString()
	{
		return basePitch.toString() + octave;
	}

	public enum BasePitch
	{
		A_FLAT,
		A,
		A_SHARP,
		B_FLAT,
		B,
		B_SHARP,
		C_FLAT,
		C,
		C_SHARP,
		D_FLAT,
		D,
		D_SHARP,
		E_FLAT,
		E,
		E_SHARP,
		F_FLAT,
		F,
		F_SHARP,
		G_FLAT,
		G,
		G_SHARP
	}

	/**
	 * Major scale degrees: (1, 2, 3, 4, 5, 6, 7)
	 * Minor scale degrees: (1, 2, b3, 4, 5, b6, b7) // (1, 2, b3, 4, 5, b6, 7) // (1, 2, b3, 4, 5, 6, 7)
	 */
	public enum ScaleDegree
	{
		ONE_FLAT,
		ONE,
		ONE_SHARP,
		TWO_FLAT,
		TWO,
		TWO_SHARP,
		THREE_FLAT,
		THREE,
		THREE_SHARP,
		FOUR_FLAT,
		FOUR,
		FOUR_SHARP,
		FIVE_FLAT,
		FIVE,
		FIVE_SHARP,
		SIX_FLAT,
		SIX,
		SIX_SHARP,
		SEVEN_FLAT,
		SEVEN,
		SEVEN_SHARP
	}
}
