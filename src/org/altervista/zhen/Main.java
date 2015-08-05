package org.altervista.zhen;

import org.altervista.zhen.MusicalElements.KeySignature;
import org.altervista.zhen.MusicalElements.Melody;
import org.altervista.zhen.MusicalElements.Pitch;
import org.altervista.zhen.MusicalElements.TimeSignature;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		KeySignature ks = null;
		Boolean proceedForwardKS = false;
		do
		{
			System.out.println("Enter the key signature of the melody to generate. Type help to get the list of acceptable inputs.");
			String ksRawInput = scanner.next();
			switch (ksRawInput)
			{
				case "help":
					proceedForwardKS = false;
					System.out.println("Acceptable inputs:");
					for (KeySignature possibleKS : KeySignature.values())
					{
						System.out.println(possibleKS.name()); //do not use toString()
					}
					break;
				default:
					for (KeySignature possibleKS : KeySignature.values())
					{
						if (ksRawInput.equals(possibleKS.name()))
						{
							ks = possibleKS;
							proceedForwardKS = true;
							break;
						}
					}

					if (!proceedForwardKS)
					{
						System.out.println("Invalid key signature input!");
					}
			}
		} while (!proceedForwardKS);

		TimeSignature ts = null;
		Boolean proceedForwardTS = false;
		do
		{
			System.out.println("Enter the time signature of the melody to generate. Type help to get the list of acceptable inputs.");
			String tsRawInput = scanner.next();
			switch (tsRawInput)
			{
				case "help":
					//implicitly: proceedForwardTS = false;
					System.out.println("Acceptable inputs:");
					for (TimeSignature possibleTS : TimeSignature.values())
					{
						System.out.println(possibleTS.name()); //do not use toString()
					}
					break;
				default:
					for (TimeSignature possibleTS : TimeSignature.values())
					{
						if (tsRawInput.equals(possibleTS.name()))
						{
							ts = possibleTS;
							proceedForwardTS = true;
							break;
						}
					}

					if (!proceedForwardTS)
					{
						System.out.println("Invalid time signature input!");
					}
			}
		} while (!proceedForwardTS);

		System.out.println("Enter the path of the folder to store the file in");
		String fileName = scanner.next() + "/" + LocalDateTime.now().toString() + "_MELODY.xml";

		CharSequence musicXML = MelodyWriter.generateMusicXMLMelody(ks, ts);

		try
		{
			PrintWriter printWriter = new PrintWriter(fileName);
			printWriter.append(musicXML);
			printWriter.close();
			System.out.println("Saved MusicXML file at: " + fileName);
		}
		catch (FileNotFoundException e)
		{
			System.err.println("Invalid path!");
		}
	}
}
