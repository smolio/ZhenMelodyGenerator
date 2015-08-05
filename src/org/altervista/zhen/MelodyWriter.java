package org.altervista.zhen;

import org.altervista.zhen.MusicalElements.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by zhendeveloper on 8/4/15.
 */
public class MelodyWriter
{
	//invisible constructor
	private MelodyWriter() {}

	/**
	 * @param ks Key signature of the melody to generate.
	 * @param ts Time signature of the melody to generate.
	 * @return The MusicXML file as a string.
	 */
	public static CharSequence generateMusicXMLMelody(KeySignature ks, TimeSignature ts)
	{
		Melody melody = new Melody(ks, ts);
		StringBuilder musicXMLString = new StringBuilder();
		musicXMLString.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n");
		musicXMLString.append("<!DOCTYPE score-partwise PUBLIC \n\t\"-//Recordare//DTD MusicXML 3.0 Partwise//EN\"\n\t\"http://www.musicxml.org/dtds/partwise.dtd\">\n");

		musicXMLString.append("<score-partwise version=\"3.0\">\n");
		musicXMLString.append("\t<part-list>\n");
		musicXMLString.append("\t\t<score-part id=\"P1\">\n");
		musicXMLString.append("\t\t\t<part-name>Music</part-name>\n");
		musicXMLString.append("\t\t</score-part>\n");
		musicXMLString.append("\t</part-list>\n");

		musicXMLString.append("\t<part id=\"P1\">\n");

		int harmonicNotesEnumerationIndex = 0;
		int numOfBeatsPerMeasure = ts.isCompound() ? ts.upperNum / 3 : ts.upperNum;
		for (int measureNum = 1; measureNum <= Melody.NUM_OF_BARS; measureNum++)
		{
			ArrayList<HarmonicNote> harmonicNotesInMeasure = new ArrayList<>();
			int numOfBeatsLeftToFill = numOfBeatsPerMeasure;
			while (numOfBeatsLeftToFill != 0)
			{
				HarmonicNote harmonicNoteToAppend = melody.getHarmonicNotes()[harmonicNotesEnumerationIndex];
				harmonicNotesInMeasure.add(harmonicNoteToAppend);
				harmonicNotesEnumerationIndex++;
				if (ts.isCompound())
				{
					numOfBeatsLeftToFill--;
				}
				else //simple time
				{
					if (harmonicNoteToAppend.getDuration() == Duration.HALF_NOTE)
					{
						numOfBeatsLeftToFill -= 2;
					}
					else //harmonicNoteToAppend.getDuration() == Duration.QUARTER_NOTE
					{
						numOfBeatsLeftToFill--;
					}
				}
			}

			musicXMLString.append("\t\t<measure number=\""+ measureNum +"\">\n");

			if (measureNum == 1)
			{
				musicXMLString.append("\t\t\t<attributes>\n");

				musicXMLString.append("\t\t\t\t<divisions>4</divisions>\n"); //all durations are expressed as multiples of 1/4 of a quarter note

				musicXMLString.append("\t\t\t\t<key>\n");
				musicXMLString.append("\t\t\t\t\t<fifths>" + ks.numOfAccidentals + "</fifths>\n");
				musicXMLString.append("\t\t\t\t</key>\n");

				musicXMLString.append("\t\t\t\t<time>\n");
				musicXMLString.append("\t\t\t\t\t<beats>" + ts.upperNum + "</beats>\n");
				musicXMLString.append("\t\t\t\t\t<beat-type>" + ts.lowerNum + "</beat-type>\n");
				musicXMLString.append("\t\t\t\t</time>\n");

				musicXMLString.append("\t\t\t\t<clef>\n");
				musicXMLString.append("\t\t\t\t\t<sign>G</sign>\n");//hardcoded to treble clef for now
				musicXMLString.append("\t\t\t\t\t<line>2</line>\n");
				musicXMLString.append("\t\t\t\t</clef>\n");

				musicXMLString.append("\t\t\t</attributes>\n");
			}

			for (int h = 0; h < harmonicNotesInMeasure.size(); h++)
			{
				MelodicNote[] melNotes = harmonicNotesInMeasure.get(h).getMelNotes();
				for (int i = 0; i < melNotes.length; i++)
				{
					musicXMLString.append("\t\t\t<note>\n");

					musicXMLString.append("\t\t\t\t<pitch>\n");
					musicXMLString.append("\t\t\t\t\t"+basePitchToMusicXMLPitchConverter(melNotes[i].getPitch().basePitch));
					musicXMLString.append("\t\t\t\t\t<octave>" + melNotes[i].getPitch().octave + "</octave>\n");
					musicXMLString.append("\t\t\t\t</pitch>\n");

					musicXMLString.append("\t\t\t\t<duration>" + melNotes[i].getDuration() + "</duration>\n");
					musicXMLString.append("\t\t\t\t"+durationIntToMusicXMLTypeAndDotConverter(melNotes[i].getDuration()));
					musicXMLString.append("\t\t\t</note>\n");
				}
			}

			musicXMLString.append("\t\t</measure>\n");
		}

		musicXMLString.append("\t</part>\n");
		musicXMLString.append("</score-partwise>\n");

		return musicXMLString;
	}

	private static String basePitchToMusicXMLPitchConverter(Pitch.BasePitch basePitch)
	{
		switch (basePitch)
		{
			case A_FLAT:
				return "<step>A</step>\n<alter>-1</alter>\n";
			case A:
				return "<step>A</step>\n";
			case A_SHARP:
				return "<step>A</step>\n<alter>1</alter>\n";
			case B_FLAT:
				return "<step>B</step>\n<alter>-1</alter>\n";
			case B:
				return "<step>B</step>\n";
			case B_SHARP:
				return "<step>B</step>\n<alter>1</alter>\n";
			case C_FLAT:
				return "<step>C</step>\n<alter>-1</alter>\n";
			case C:
				return "<step>C</step>\n";
			case C_SHARP:
				return "<step>C</step>\n<alter>1</alter>\n";
			case D_FLAT:
				return "<step>D</step>\n<alter>-1</alter>\n";
			case D:
				return "<step>D</step>\n";
			case D_SHARP:
				return "<step>D</step>\n<alter>1</alter>\n";
			case E_FLAT:
				return "<step>E</step>\n<alter>-1</alter>\n";
			case E:
				return "<step>E</step>\n";
			case E_SHARP:
				return "<step>E</step>\n<alter>1</alter>\n";
			case F_FLAT:
				return "<step>F</step>\n<alter>-1</alter>\n";
			case F:
				return "<step>F</step>\n";
			case F_SHARP:
				return "<step>F</step>\n<alter>1</alter>\n";
			case G_FLAT:
				return "<step>G</step>\n<alter>-1</alter>\n";
			case G:
				return "<step>G</step>\n";
			case G_SHARP:
				return "<step>G</step>\n<alter>1</alter>\n";
			default:
				throw new RuntimeException("Zhen, you forgot a BasePitch");
		}
	}

	private static String durationIntToMusicXMLTypeAndDotConverter(int duration)
	{
		switch (duration)
		{
			case Duration.SIXTEENTH_NOTE:
				return "<type>16th</type>\n";
			case Duration.EIGHTH_NOTE:
				return "<type>eighth</type>\n";
			case Duration.DOTTED_EIGHTH_NOTE:
				return "<type>eighth</type>\n<dot/>\n";
			case Duration.QUARTER_NOTE:
				return "<type>quarter</type>\n";
			case Duration.DOTTED_QUARTER_NOTE:
				return "<type>quarter</type>\n<dot/>\n";
			case Duration.HALF_NOTE:
				return "<type>half</type>\n";
			default:
				throw new RuntimeException("Invalid duration value");
		}
	}
}
