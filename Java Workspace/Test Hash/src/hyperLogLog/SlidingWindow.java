package hyperLogLog;

import hash.HashFunction;

import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;

import drafts.Draft;
import drafts.GetInput;

import FileManager.WordReader;


/**
 * Question 4 :
 * Implements an algorithm to evaluate the number of different words in a sliding window of a text stream.
 */
public class SlidingWindow {
	
	
	 /**
     * Question 4
     * @param path
     * @param func
     * @param b
     * @param windowSize Positive width of the window
     * @return The array of the approximate number of different words seen among the last "windowSize".
     * The length of this array is 1 + the size of the sample - windowSize.
     */
    public static Double[] slidingWindow(Path path, HashFunction func, int b, int windowSize, int precision) {
    	
    	if (b <= 0 || b > 16)
    	    throw new AssertionError("hyperLogLog :  b <= 0 or b > 16");
    	
    	int m = 1 << b; // m = 2^b
    	int[] M = new int[m];
    	LinkedList<Double> result = new LinkedList<Double>();
    	
    	
    	double sum = m, eTimesSum = HyperLogLog.alpha[b] * ((double) m) * ((double) m),
    			n = Math.pow(2, 32);
    	
    	
    	// Invariant preserved :
    	// - timestamps.length == values.length
    	// - increasing values and decreasing timestamps
    	// - couple (timestamps, values) such that  currentTime - timestamps > windowSize,
    	// are removed as soon as possible (update function).
    	LinkedList<Integer>[] timestamps = new LinkedList[m], values = new LinkedList[m];
    	for (int i = 0; i < m; i++) {
    		timestamps[i] = new LinkedList<Integer>();
    		values[i] = new LinkedList<Integer>();
    	}
    	
    	
    	
    	int currentTime = 0;
    	
    	for (String s : new WordReader(path)) {
    	    long x = func.hashString(s);
    	    int j = (int) (x & (m - 1));
    	    int rho = Integer.numberOfTrailingZeros( (int) (x >>> b) ); // rho(w)
    	    
    	    // We insert rho in the queue preserving the invariants.
    	    insert( currentTime, rho, timestamps[j], values[j] );
    	    // Alternative : meilleure complexite en espace, pire en temps
    	    // update( currentTime - windowSize, timestamps[j], values[j] );
    	    
    		// We regularly evaluate and save the approximate number of words
    	    // corresponding to the current table M.
    		if (currentTime % precision == 0 && currentTime > windowSize) {
    			
    			sum = 0;
    			for (int k = 0; k < m; k++) {
    				update( currentTime - windowSize, timestamps[k], values[k] );
    				if ( values[k].isEmpty() )  M[k] = 0;
    				else M[k] = values[k].getLast();
    				sum += Math.pow(2, M[k]);
    			}
    			
    			// We evaluate the current estimation
    	    	double e = eTimesSum / sum;
    	    	if (e < 2.5 * m) {
    	    	    double v = 0;
    	    	    for (int i = 0; i < m; i++)
    	    	    	if (M[i] == 0) v++;
    	    	    
    	    	    if (v != 0)
    	    	    	e = m * Math.log(m / v);
    	    	} else if (e > n / 30)
    	    	    e = -n * Math.log(1 - e / n);
    	    	
    			// We save the current estimation
    			result.add( e );
    		}
    		
    		currentTime++;
    	}
    	
    	return result.toArray(new Double[0]);
    }
    
    
    /**
     * Insert the pair (rho, time) in the queues.
     * Delete the first elements when their values are less than the inserted rho
     * (they will never be used as maximum since the inserted value is greater and younger).
     */
    public static void insert( int timestamp, int rho, LinkedList<Integer> timestamps, LinkedList<Integer> values) {
    	while ( !values.isEmpty() && rho >= values.getFirst() ) {
    		values.remove();
    		timestamps.remove();
    	}
    	timestamps.addFirst(timestamp);
		values.addFirst(rho);
    }
    
    
    /**
     * Remove the end of the queues when their timeStamp is too old (< limit)
     */
    public static void update(int limit, LinkedList<Integer> timestamps, LinkedList<Integer> values) {
    	while ( !timestamps.isEmpty() && timestamps.getLast() <= limit ) {
    		timestamps.removeLast();
    		values.removeLast();
    	}
    }
    
    
    
    public static void displaySlidingWindows(Path path, HashFunction func, int b, int windowSize, int precision) {
    	Double[] tab = slidingWindow(path, func, b, windowSize, precision);
    	double[] doubleTab = new double[tab.length];
    	for (int i = 0; i < tab.length; i++)
    		doubleTab[i] = tab[i];
    	
    	// Do the plotting
    	Plot2DPanel plot = new Plot2DPanel();
    	plot.addLinePlot("test", doubleTab);
    	JFrame frame = new JFrame("Evolution of the estimate number of different words among the "
    			+ windowSize +" last words.");
    	frame.setSize(600, 600);
    	frame.setContentPane(plot);
    	frame.setVisible(true);
    	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    // This answers question 4
	public static void main(String[] args) throws NoSuchFileException, IllegalArgumentException {
		
		/*
		 * Here the command line from folder
		 *		/Projet-INF431/Java Workspace/Test Hash$ 
		 * is
		 * java -cp bin:./jmatharray.jar:./jmathplot.jar hyperLogLog.SlidingWindow LookUp3 11 1000 1000 ./files/processed/Shakespeare_Bible_concat.txt 
		 * to explain in the notice
		*/
		
    	if (args.length == 5) {
    		
        	Draft.checkHash( args[0] );
        	Draft.checkRange(args[1], 4, 15);
        	Draft.checkRange(args[2], 1, 1000000);
        	Draft.checkRange(args[3], 1, 1000000);
        	Draft.checkPath( args[4] );
        	
        	exec(args[4], args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
        	
    	} else if (args.length == 0) {
    		
        	String path = 	 GetInput.askPath("Path to the file");
        	String hash = 	 GetInput.askHash("Hash function");
        	int b = 		 GetInput.askParameterInRange("Parameter b", 4, 15);
        	int windowSize = GetInput.askParameterInRange("Window's size", 1, 1000000);
        	int precision =  GetInput.askParameterInRange("Precision", 1, 1000000);
        	exec(path, hash, b, windowSize, precision);
        	
    	} else
    		throw new IllegalArgumentException("Wrong number of arguments (5 expected)");
    	
	}
	
	
	
	public static void exec(String path, String func, int b, int windowSize, int precision){
    	System.out.println("Evolution of the estimate number of different words in the file");
    	System.out.println("	" + path + System.lineSeparator());
    	System.out.println("With parameters");
    	System.out.println("	b = " + b);
    	System.out.println("	Window's size = " + windowSize);
    	System.out.println("	Precision = " + precision);
    	displaySlidingWindows(Paths.get(path), HashFunction.getHashFunction(func), b, windowSize, precision);
    	System.out.println("----------------------------------------------------------------");
	}

}
