package drafts;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class hashFunction {

	static int rot(int x, int k){ return ((x)<<(k)) | ((x)>>(32-(k))); }
	
	static void mix(int[] tab){
		int a = tab[0];
		int b = tab[1];
		int c = tab[2];
		
		a -= c;  a ^= rot(c, 4);  c += b; 
		b -= a;  b ^= rot(a, 6);  a += c; 
		c -= b;  c ^= rot(b, 8);  b += a; 
		a -= c;  a ^= rot(c,16);  c += b; 
		b -= a;  b ^= rot(a,19);  a += c; 
		c -= b;  c ^= rot(b, 4);  b += a; 
		
		tab[0] = a;
		tab[1] = b;
		tab[2] = c;
	}
	
	static void finalMix(int[] tab){
		int a = tab[0];
		int b = tab[1];
		int c = tab[2];
		
		c ^= b; c -= rot(b,14); 
		a ^= c; a -= rot(c,11); 
		b ^= a; b -= rot(a,25); 
		c ^= b; c -= rot(b,16); 
		a ^= c; a -= rot(c,4);  
		b ^= a; b -= rot(a,14); 
		c ^= b; c -= rot(b,24); 
		
		tab[0] = a;
		tab[1] = b;
		tab[2] = c;
	}
	
	static int hashWord(int[] array){
		int a = 0xdeadbeef + ((array.length)<<2);
		int[] tab = new int[3];
		tab[0] = tab[1] = tab[2] = a;
		
		int pt = 0;
		int length = array.length;
		while(length>3){
			tab[0] += array[pt];
			tab[1] += array[pt+1];
			tab[2] += array[pt+2];
			mix(tab);
			length -= 3;
			pt += 3; 
		}
		
		switch(length){
		case 3:
			tab[2] += array[pt+2];
		case 2:
			tab[1] += array[pt+1];
		case 1:
		 	tab[0] += array[pt];
		 	finalMix(tab);
		case 0:
			break;
		}
		
		return tab[2];
	}
	
	static int hash(byte[] array){	
		int[] tab = new int[3];
		int a = 0xdeadbeef + ((array.length)<<2);
		tab[0] = tab[1] = tab[2] = a;
		
		int pt = 0;
		int length = array.length;
		while(length>12){
			tab[0] = (int) (array[pt]<<24) + (array[pt+1]<<16) + (array[pt+2]<<8) + array[pt+3];
			pt += 4;
			tab[1] = (int) (array[pt]<<24) + (array[pt+1]<<16) + (array[pt+2]<<8) + array[pt+3];
			pt += 4;
			tab[2] = (int) (array[pt]<<24) + (array[pt+1]<<16) + (array[pt+2]<<8) + array[pt+3];
			pt += 4;		
			length -= 12;
			mix(tab);	
		}
		
		switch(length){
		case 12:
			tab[0] = (int) (array[pt]<<24) + (array[pt+1]<<16) + (array[pt+2]<<8) + array[pt+3];
			pt += 4;
			tab[1] = (int) (array[pt]<<24) + (array[pt+1]<<16) + (array[pt+2]<<8) + array[pt+3];
			pt += 4;
			tab[2] = (int) (array[pt]<<24) + (array[pt+1]<<16) + (array[pt+2]<<8) + array[pt+3];
			break;
		case 11:
			tab[0] = (int) (array[pt]<<24) + (array[pt+1]<<16) + (array[pt+2]<<8) + array[pt+3];
			pt += 4;
			tab[1] = (int) (array[pt]<<24) + (array[pt+1]<<16) + (array[pt+2]<<8) + array[pt+3];
			pt += 4;
			tab[2] = (int) (array[pt]<<16) + (array[pt+1]<<8) + array[pt+2];
			break;
		case 10:
			tab[0] = (int) (array[pt]<<24) + (array[pt+1]<<16) + (array[pt+2]<<8) + array[pt+3];
			pt += 4;
			tab[1] = (int) (array[pt]<<24) + (array[pt+1]<<16) + (array[pt+2]<<8) + array[pt+3];
			pt += 4;
			tab[2] = (int) (array[pt]<<8) + array[pt+1];
			break;
		case 9:
			tab[0] = (int) (array[pt]<<24) + (array[pt+1]<<16) + (array[pt+2]<<8) + array[pt+3];
			pt += 4;
			tab[1] = (int) (array[pt]<<24) + (array[pt+1]<<16) + (array[pt+2]<<8) + array[pt+3];
			pt += 4;
			tab[2] = (int) array[pt];
			break;
		case 8:
			tab[0] = (int) (array[pt]<<24) + (array[pt+1]<<16) + (array[pt+2]<<8) + array[pt+3];
			pt += 4;
			tab[1] = (int) (array[pt]<<24) + (array[pt+1]<<16) + (array[pt+2]<<8) + array[pt+3];
			break;
		case 7:
			tab[0] = (int) (array[pt]<<24) + (array[pt+1]<<16) + (array[pt+2]<<8) + array[pt+3];
			pt += 4;
			tab[1] = (int) (array[pt]<<16) + (array[pt+1]<<8) + array[pt+2];
			break;
		case 6:
			tab[0] = (int) (array[pt]<<24) + (array[pt+1]<<16) + (array[pt+2]<<8) + array[pt+3];
			pt += 4;
			tab[1] = (int) (array[pt]<<8) + array[pt+1];
			break;
		case 5:
			tab[0] = (int) (array[pt]<<24) + (array[pt+1]<<16) + (array[pt+2]<<8) + array[pt+3];
			pt += 4;
			tab[1] = (int) array[pt];
			break;
		case 4:
			tab[0] = (int) (array[pt]<<24) + (array[pt+1]<<16) + (array[pt+2]<<8) + array[pt+3];
			break;
		case 3:
			tab[0] = (int) (array[pt]<<16) + (array[pt+1]<<8) + array[pt+2];
			break;
		case 2:
			tab[0] = (int) (array[pt]<<8) + array[pt+1];
			break;
		case 1:
			tab[0] = (int) array[pt];
			break;
		case 0:
			return tab[2];
		}
		
		finalMix(tab);
		
		return tab[2];
	}
	
	static int hashString(String s){
		return hash(s.getBytes());
	}
	
	static void prepare(String fileForReading, String fileForWritting){
		Path readFile = Paths.get(fileForReading);
		Path writeFile = Paths.get(fileForWritting);
		
		if(!Files.exists(writeFile))
			try {
				writeFile = Files.createFile(writeFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		
		Charset charset = Charset.forName("US-ASCII");	
		try {
		    BufferedReader reader = Files.newBufferedReader(readFile, charset);
		    BufferedWriter writer = Files.newBufferedWriter(writeFile, charset);
		    
		    String line = null;

		    while ((line = reader.readLine()) != null ) {
		        line = line.toLowerCase();
		        line = line.replaceAll("[^a-z ]|(?<=^)[ ]+", "");
		        line = line += "\n";
		        writer.write(line);		        
		    }
		    
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
	}
	
	
	
	
	public static void main(String[] args) {
		

		
	    long start = System.nanoTime();


		Charset charset = Charset.forName("US-ASCII");
		try {
			Path file = Paths.get("pg1002.txt");
		    BufferedReader reader = Files.newBufferedReader(file, charset);
		 
		    String line = null;
		    String[] strings = null;

		    while ((line = reader.readLine()) != null ) {
		        strings = line.split("[\\s]+");
		        for(String g: strings)
		        	hashString(g);
		     	
		        
		    }
		
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		
		System.out.println(((float)(System.nanoTime() - start))/1000000000.);

	
	}
}
