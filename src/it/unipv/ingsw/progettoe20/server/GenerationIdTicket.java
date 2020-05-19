package it.unipv.ingsw.progettoe20.server;

import java.util.Random;
public class GenerationIdTicket {
	private final int lungId=8;
	private String[] alfaCharacters = { "a", "b", "c", "d", "e", "f", "g", "h", "i",
			"l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "z", "y",
			"j", "k", "x", "w", "A", "B", "C", "D", "E", "F", "G", "H", "I",
			"L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "Z", "Y",
			"J", "K", "X", "W", "à", "è", "é", "ì", "ò", "ù" };
	private String[] numCharacters = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };
	private String[] specialCharacters = { "!", "£", "$", "%", "&", "@", "*"};
	// creazione oggetto per la generazione casuale
	private Random rand = new Random();

	public String GenerateId() {

		// ottengo la lunghezza di ogni array
		int lungCaratteri = alfaCharacters.length;
		int lungNumeri = numCharacters.length;
		int lungSpeciali= specialCharacters.length;
		String randomString = "A";				
		
		while (randomString.length() < lungId) {

			// ottengo un elemento casuale per ogni array
			int c = rand.nextInt(lungCaratteri);
			int n = rand.nextInt(lungNumeri);
			int s = rand.nextInt(lungSpeciali);
			// aggiungo una lettera casuale
			randomString += alfaCharacters[c];
			randomString += numCharacters[n];
			randomString += specialCharacters[s];			
		}
		// se la stringa generata dovesse superare il numero di caratteri
		// richiesto, la taglio.
		
		if (randomString.length() > lungId) {
			randomString = randomString.substring(0, lungId);
		}
		return randomString;	
			
		}
}
