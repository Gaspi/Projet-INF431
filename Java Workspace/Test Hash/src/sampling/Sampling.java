package sampling;

import hash.HashFunction;
import hash.HashFunctionTests;
import hash.MurmurHash3;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.LinkedList;

import FileManager.WordReader;

public class Sampling {
	
	
    public static void main(String[] args) {
		// Implement here the command line interface for question 5
    	
    	MurmurHash3 f = new MurmurHash3();
    	Path path = FileManager.Files.hamlet;
    	
    	// Testing significant words
    	for (String s : SignificantWords.findSignificantWords(path, 5, f, 100)) {
    		System.out.println(s);
    		//System.out.println(Integer.toBinaryString(f.hashString(s)));
    	}
    	
    	
    	
    	
    	
    	// Testing number of mice
    	   	
//    	Path path = hashFunctionTests2.shakespeare;
//    	int k = 2;
//    	
//    	SignificantWordsArraySample ws = new SignificantWordsArraySample(1000, f);	
//    	for(String str: new WordReader(path)) {
//    		ws.addWord(str);
//    	}
//    	
//    	double a = benchmarkMiceNumber(path, k);
//    	double b = ws.estimateMiceNumber(k);
//    	    	
//    	
//    	System.out.println(a + " - " + b + " diff = " + Math.abs(a-b)/a*100.);
    	
    	
    	
    	
    	
    	// Testing icebergs
    	
//    	double threshold = 0.02;
//		System.out.println("Estimation");
//		for(String str: Sampling.findIcebergs(path, threshold))
//			System.out.println(str);
//		System.out.println("-----------------------------------");
//		System.out.println("Real");
//		for(String str: Sampling.benchmarkIcebergs(path, threshold))
//			System.out.println(str);
//		System.out.println("-----------------------------------");
//		
		
		// --> The algorithm from the subject gives many false positive
    	
    }
}
