package org.altervista.zhen.MusicalElements;

/**
 * Created by zhendeveloper on 7/29/15.
 */
public enum TimeSignature
{
	THREE_FOUR(3,4, 250),
	FOUR_FOUR(4,4, 250),
	SIX_EIGHT(6,8, 250),
	NINE_EIGHT(9,8, 250);

	public final int upperNum;
	public final int lowerNum;
	public final int probability;

	/**
	 *
	 * @param upperNum_loc
	 * @param lowerNum_loc
	 * @param probabiliy_loc Between 0 - 1000 inclusive
	 */
	TimeSignature(int upperNum_loc, int lowerNum_loc, int probabiliy_loc)
	{
		upperNum = upperNum_loc;
		lowerNum = lowerNum_loc;
		probability = probabiliy_loc;
	}

	/**
	 * @return True if the TimeSignature represents compound time, false if it represents simple time
	 */
	public boolean isCompound()
	{
		if (lowerNum == 8)
		{
			return true;
		}
		//else
		return false;
	}
}
