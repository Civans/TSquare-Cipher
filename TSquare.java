import java.security.*;
import java.util.*;
import java.io.*;
public class TSquare{
	
	

	//generateTable with argument as key
	public ArrayList<Character> generateTableList(String tblKey){
		ArrayList<Character> table = new ArrayList<Character>();
		char c = (char)1;
		
		tblKey = tblKey.toLowerCase();
		for(Character x: tblKey.toCharArray()){
			if(!table.contains(x))
				table.add(x);
		}
		
		int currentLetter =0;
		while(table.size()!=25){
			c =(char)(97+currentLetter++);
			 if(!table.contains(c) && c!='q')
				table.add(c);
			
		}
			return table;
	}	
	
	
	//generateTable with argument as key
	public static Character[][] generateTable(String tblKey){
		ArrayList<Character> table = new ArrayList<Character>();
		char c = (char)1;
		tblKey = tblKey.toLowerCase();
		for(Character x: tblKey.toCharArray()){
			if(!table.contains(x))
				table.add(x);
		}
		
		int currentLetter =0;
		while(table.size()!=25){
			c =(char)(97+currentLetter++);
			 if(!table.contains(c) && c!='q')
				table.add(c);
			
		}
				
		Character char2DArray[][] = new Character[5][5];
		for(int i =0; i<5; i++)
			for(int j =0;j<5;j++){
				char2DArray[i][j] = table.get((5*i)+j);
			}
			
			
		return char2DArray;
	}
	
	
	//encrypts message given in param
	public static String encryptMessage(String msg,String key1,String key2){
		/*
		BufferedReader r = new BufferedReader(new FileReader(file));
		String msg ="";
		while(r.ready()){
			msg += r.readLine();
		}
		*/

		
		
		Character charPair[];
		Character charTable1[][] = generateTable(key1);
		Character charTable2[][] = generateTable(key2);
		
		System.out.println("\n Table 1");
		for(int i =0; i<5; i++){
			for(int j =0;j<5;j++){
				System.out.print(charTable1[i][j]+" ");
			}
			System.out.println("");
		}	

		System.out.println("\n Table 2");
		for(int i =0; i<5; i++){
			for(int j =0;j<5;j++){
				System.out.print(charTable2[i][j]+" ");
			}
			System.out.println("");
		}
		
		String encryptedMessage="";
		if(msg.length()%2!=0){
			msg+='a';
		}
		msg = msg.toLowerCase();
		charPair = new Character[msg.length()];
		
		
		//seperates message into charPairs
		for(int i =0;i<msg.length();i+=2){
			charPair[i] =msg.charAt(i);
			charPair[i+1] =msg.charAt(i+1);
		}
		
		
		for(int i=0;i<charPair.length;i+=2){
			int pos1X=0,pos1Y=0;
			int pos2X=0,pos2Y=0;
			if(charPair[i] != ' ' || charPair[i+1] != ' '){
			for(int j=0;j<5;j++)
				for(int k=0;k<5;k++){
					if(charPair[i]== charTable1[j][k]){
						pos1X =j;
						pos1Y =k;
					}
					
					if(charPair[i+1] == charTable2[j][k]){
						pos2X =j;
						pos2Y =k;
					}
				}
				
			charPair[i]=charTable1[pos2X][pos1Y];
			charPair[i+1]=charTable2[pos1X][pos2Y];
			
			encryptedMessage+=charPair[i];
			encryptedMessage+=charPair[i+1];
			}
		}
		
		return encryptedMessage;
		
	}
	
	//encrypts message found in file
	public static String encryptMessage(File file,String key1,String key2){
		BufferedReader r;
		String msg ="";
		try{
			r = new BufferedReader(new FileReader(file));
			while(r.ready()){
				msg += r.readLine();
			}		
		} catch(Exception e){
			System.out.println(e);
			System.exit(-1);
		}
		
		//stores message pairs and tables respectively
		Character charPair[];
		Character charTable1[][] = generateTable(key1);
		Character charTable2[][] = generateTable(key2);
		
		System.out.println("\n Table 1");
		for(int i =0; i<5; i++){
			for(int j =0;j<5;j++){
				System.out.print(charTable1[i][j]+" ");
			}
			System.out.println("");
		}	

		System.out.println("\n Table 2");
		for(int i =0; i<5; i++){
			for(int j =0;j<5;j++){
				System.out.print(charTable2[i][j]+" ");
			}
			System.out.println("");
		}
		
		String encryptedMessage="";
		if(msg.length()%2!=0){
			msg+='x';
		}
		msg = msg.toLowerCase();
		charPair = new Character[msg.length()];
		
		
		//seperates message into charPairs
		for(int i =0;i<msg.length();i+=2){
			charPair[i] =msg.charAt(i);
			charPair[i+1] =msg.charAt(i+1);
		}
		
		
		for(int i=0;i<charPair.length;i+=2){
			int pos1X=0,pos1Y=0;
			int pos2X=0,pos2Y=0;
			if(charPair[i] != ' ' || charPair[i+1] != ' '){
			for(int j=0;j<5;j++)
				for(int k=0;k<5;k++){
					if(charPair[i]== charTable1[j][k]){
						pos1X =j;
						pos1Y =k;
					}
					
					if(charPair[i+1] == charTable2[j][k]){
						pos2X =j;
						pos2Y =k;
					}
				}
				
			charPair[i]=charTable1[pos2X][pos1Y];
			charPair[i+1]=charTable2[pos1X][pos2Y];
			
			encryptedMessage+=charPair[i];
			encryptedMessage+=charPair[i+1];
			}
		}
		PrintWriter w;
		try{
			w = new PrintWriter(new File("encrypted output"));
			w.write(encryptedMessage);
			w.flush();
			w.close();
		} catch(Exception e){
			System.out.println(e);
		}
		
		return encryptedMessage;
		
	}
	
	
	//requires three arguement java TSquare file.txt key1 key2
	public static void main(String args[]){
		File f = new File(args[0]);
		
		String s = encryptMessage(f,args[1],args[2]);
		
		System.out.println(s);
	}
}