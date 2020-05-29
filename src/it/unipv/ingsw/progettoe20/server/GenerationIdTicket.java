package it.unipv.ingsw.progettoe20.server;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;
import it.unipv.ingsw.progettoe20.server.model.Level;
public class GenerationIdTicket {
	private DatabaseFacade dbFacade;
	private final int lungId=8;
	private List<Level>levelList;
	private int contLevel;
	private Boolean check;
	private String levelName;
	private String[] alfaCharacters = { "a", "b", "c", "d", "e", "f", "g", "h", "i",
			"l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "z", "y",
			"j", "k", "x", "w", "A", "B", "C", "D", "E", "F", "G", "H", "I",
			"L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "Z", "Y",
			"J", "K", "X", "W"};
	private String[] numCharacters = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };
	
	private Random rand = new Random();
	GenerationIdTicket(DatabaseFacade dbFacade) {
		this.dbFacade=dbFacade;
	}
	public String getAvailableLevel() {
		levelList= new ArrayList<Level>();
		levelList= dbFacade.getLevelList();
		contLevel=levelList.size();
		check=false;
		int i=0;
		levelName="";
		do {
			if(!check) {
				if(levelList.get(i).getAvailable()!=0) {
				levelName=levelList.get(i).getName();
				check=true;
				}
			}
			i++;
		}while(i<contLevel);
		if(check==false) {
			levelName="?";
		}
		return levelName;
	}
	public String GenerateId() {

		// ottengo la lunghezza di ogni array
		int lungCaratteri = alfaCharacters.length;
		int lungNumeri = numCharacters.length;
		String randomString = getAvailableLevel();				
		
		while (randomString.length() < lungId) {

			// ottengo un elemento casuale per ogni array
			int c = rand.nextInt(lungCaratteri);
			int n = rand.nextInt(lungNumeri);
			// aggiungo una lettera casuale
			randomString += alfaCharacters[c];
			randomString += numCharacters[n];
					
		}
		// se la stringa generata dovesse superare il numero di caratteri
		// richiesto, la taglio.
		
		if (randomString.length() > lungId) {
			randomString = randomString.substring(0, lungId);
		}
		return randomString;	
			
		}
}
