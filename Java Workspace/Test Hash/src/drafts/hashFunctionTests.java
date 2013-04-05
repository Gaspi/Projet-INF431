package drafts;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;

import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;
import static org.math.array.StatisticSample.*;



public class hashFunctionTests {
	static final Path shakespeare = Paths.get("Shakespeare_complete_processed.txt");
	static final Path numbers = Paths.get("Numbers_1000000");
	static final Path uuids = Paths.get("UUID_200000");
	static final Path englishWords = Paths.get("English_words.txt");
	
	private static String speedTestOnFile(Path path, hashFunction func){
		Charset charset = Charset.forName("US-ASCII");
		long start = System.nanoTime();
		try {
			BufferedReader reader = Files.newBufferedReader(path, charset); 
			String line = null;
			String[] strings = null;
			try{
				while ((line = reader.readLine()) != null ){
				    strings = line.split("[\\s]+");
				    for(String g: strings)
				    	func.hashString(g);    
				}		
			}finally {reader.close();}
		}catch (IOException e1) {e1.printStackTrace();}
		
		return ((float)(System.nanoTime() - start))/1000000000. + " s";
	}
		
	private static String collisionTestOnFile(Path path, hashFunction func){	
		Hashtable<Integer, String> tab = new Hashtable<Integer, String>();
		Charset charset = Charset.forName("US-ASCII");
		int comp = 0;
		try {
			BufferedReader reader = Files.newBufferedReader(path, charset);	 
			String line = null;
			String[] strings = null;
	
			try{
				while ((line = reader.readLine()) != null ){
					
				    strings = line.split("[\\s]+");
				    
				    for(String g: strings){
				    	int hash = func.hashString(g);
				    	
				    	if(tab.containsKey(hash))
				    		comp++;
				    	else
				    		tab.put(hash, g);
				    }
				}		
			}finally {reader.close();}	
		} catch (IOException e1) {e1.printStackTrace();}
		
		return Integer.toString(comp);

	}
	
	public static void speedTests(hashFunction func){
		System.out.println("---Speed test---");
		System.out.println("Using " + func.getClass().getSimpleName() + " hash function");
		
		//Shakespeare
		System.out.println("Shakespeare: " + System.lineSeparator() + speedTestOnFile(shakespeare, func));
		//Numbers
		System.out.println("Numbers from 0 to 1,000,000: " + System.lineSeparator()  + speedTestOnFile(numbers, func));
		//UUIDs
		System.out.println("200,000 random UUIDS: " + System.lineSeparator()  + speedTestOnFile(uuids, func));
	}
	
	public static void collisionTests(hashFunction func){
		System.out.println("---Collision test---");
		System.out.println("Using " + func.getClass().getSimpleName() + " hash function" + System.lineSeparator());
		
		//English words
		System.out.println("All english words: " + System.lineSeparator() + collisionTestOnFile(englishWords, func) + " collisions");
		//Numbers
		System.out.println("Numbers from 0 to 1,000,000: " + System.lineSeparator() + collisionTestOnFile(numbers, func) + " collisions");
		//UUIDS
		System.out.println("200,000 random UUIDS: " + System.lineSeparator() + collisionTestOnFile(uuids, func) + " collisions");
		
	}
	
	public static void uniformDistribTest(hashFunction func){
		
	}

	public static void testGraphicalTools(){
		// define your data
		double[] x = randomLogNormal(1000, 0, 0.5); // 1000 random numbers from a log normal statistical law

		// create your PlotPanel (you can use it as a JPanel)
		Plot2DPanel plot = new Plot2DPanel();

		// add the histogram (50 slices) of x to the PlotPanel
		plot.addHistogramPlot("Log Normal population", x, 50);

		// put the PlotPanel in a JFrame like a JPanel
		JFrame frame = new JFrame("a plot panel");
		frame.setSize(600, 600);
		frame.setContentPane(plot);
		frame.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		//collisionTests(new DJB2());
		testGraphicalTools();
	}

}
