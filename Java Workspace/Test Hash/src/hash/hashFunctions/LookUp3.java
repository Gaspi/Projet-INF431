package hash.hashFunctions;

import hash.hashFunction;
import drafts.UnsignedArithmetic;


/**
 * Effective hash function suggested at the following link.
 * http://www.burtleburtle.net/bob/c/lookup3.c
 */
public class LookUp3 implements hashFunction{

	static int rot(int x, int k){ return ((x)<<(k)) | ((x)>>>(32-(k))); }
	
	static void mix(int[] tab){
		int a = tab[0];
		int b = tab[1];
		int c = tab[2];
		
		a = UnsignedArithmetic.unsignedMin(a, c);  a ^= rot(c, 4);  c = UnsignedArithmetic.unsignedAdd(c, b); 
		b = UnsignedArithmetic.unsignedMin(b, a);  b ^= rot(a, 6);  a = UnsignedArithmetic.unsignedAdd(a, c); 
		c = UnsignedArithmetic.unsignedMin(c, b);  c ^= rot(b, 8);  b = UnsignedArithmetic.unsignedAdd(b, a); 
		a = UnsignedArithmetic.unsignedMin(a, c);  a ^= rot(c,16);  c = UnsignedArithmetic.unsignedAdd(c, b); 
		b = UnsignedArithmetic.unsignedMin(b, a);  b ^= rot(a,19);  a = UnsignedArithmetic.unsignedAdd(a, c); 
		c = UnsignedArithmetic.unsignedMin(c, b);  c ^= rot(b, 4);  b = UnsignedArithmetic.unsignedAdd(b, a); 
		
		tab[0] = a;
		tab[1] = b;
		tab[2] = c;
	}
	
	static void finalMix(int[] tab){
		int a = tab[0];
		int b = tab[1];
		int c = tab[2];
		
		c ^= b; c = UnsignedArithmetic.unsignedMin(c, rot(b,14)); 
		a ^= c; a = UnsignedArithmetic.unsignedMin(a, rot(c,11)); 
		b ^= a; b = UnsignedArithmetic.unsignedMin(b, rot(a,25)); 
		c ^= b; c = UnsignedArithmetic.unsignedMin(c, rot(b,16)); 
		a ^= c; a = UnsignedArithmetic.unsignedMin(a, rot(c,4));  
		b ^= a; b = UnsignedArithmetic.unsignedMin(b, rot(a,14)); 
		c ^= b; c = UnsignedArithmetic.unsignedMin(c,  rot(b,24)); 
		
		tab[0] = a;
		tab[1] = b;
		tab[2] = c;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public int hashString(String s) {
		byte[] b = new byte[s.length()];
		// Increase speed.
		// Safe if char is less than 8 bits long, for exemple for ASCII caracters.
		s.getBytes(0, s.length(), b, 0);
		return hashByteArray(b);
	}

	@Override
	public int hashByteArray(byte[] array) {
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
		
		// To-do: shortening the switch-case assertion by removing the break statements and redundant instructions
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

	public int hashIntArray(int[] array) {
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
	
	
	public static void main(String[] args) {

	}
}
