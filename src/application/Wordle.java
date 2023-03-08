package application;


import java.sql.SQLException;

import utilities.DataBase;

public class Wordle {

	DataBase d1;
	static String palabra;

	public Wordle() { 
		d1 = new DataBase();
	}


	/**
	 * GENERATES A RANDOM WORD BETWEEN +600000 POSIBILITIES. IT EXECUTES THE QUERY TILL IT FINDS A WORD
	 * @param length: the max length of the searched word 
	 * @return boolean that means if the word has been properly selected
	 */

	public boolean dailyWord(int length) { 
		boolean selected = false;
		int randomId;
		int numVeces;
		try {

			do {
				randomId = (int)(Math.random()*646615);
				palabra = d1.consultaStr("DICCIONARIO", "PALABRA", "ID_PAL = " +randomId+" AND LENGTH(PALABRA) = "+length);

			}while(palabra==""); 
			numVeces = d1.consultaNum("DICCIONARIO", "NUM_VECES", "ID_PAL = " +randomId) + 1;
			//d1.updateTabla("DICCIONARIO", "NUM_VECES", numVeces, "ID_PAL = " +randomId);
			selected = true;
			System.out.println("Palabra = "+palabra+"\nID_PAL = "+randomId+"\nNum_Veces = "+numVeces);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return selected;
	}
	
	public void checkWord(String s) {
		char [] dailyWord = palabra.toCharArray();
		char [] actualWord = s.toCharArray();
		
		
		
		
	}
	

	public static void main(String[] args) {
		Wordle w1 = new Wordle();
		try {
			DataBase.connect();
			w1.dailyWord(6);
			DataBase.close();

		} catch (Exception e) {
			e.printStackTrace();
		
		}
		
	}


}


