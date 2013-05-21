package drafts;

import hash.HashFunction;
import hash.hashFunctions.LookUp3;

import java.nio.file.Path;
import java.util.LinkedList;

import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;

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

    	// Calculation of the result
    	double sum = m, eTimesSum = HyperLogLog.alpha[b] * ((double) m) * ((double) m),
    			n = Math.pow(2, 32);
    	
    	
    	// Invariant preserved :
    	// - Same size
    	// - increasing values and decreasing timestamps
    	// When a couple (timestamps, values) as  currentTime - timestamps > windowSize,
    	// we update to remove it.
    	LinkedList<Integer>[] timestamps = new LinkedList[m], values = new LinkedList[m];
    	for (int i = 0; i < m; i++) {
    		timestamps[i] = new LinkedList<Integer>();
    		values[i] = new LinkedList<Integer>();
    	}
    	
    	
    	
    	int currentTime = 0;
    	
    	LinkedList<Integer> indexExpires = new LinkedList<Integer>();
    	for (int i = 0; i < m; i++)
    		indexExpires.add(-1);
    	
    	for (String s : new WordReader(path)) {
    	    long x = func.hashString(s);
    	    int j = (int) (x & (m - 1));
    	    int rho = Integer.numberOfTrailingZeros( (int) (x >>> b) ); // rho(w)
    	    
    	    // We insert rho in the queue preserving the invariants.
    	    update( currentTime - windowSize, timestamps[j], values[j] );
    	    insert( currentTime, rho, timestamps[j], values[j] );
    	    
    		
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
    	    	    
    	    	    if (v != 0) {
    	    	    	e = m * Math.log(m / v);
    	    	    	//System.out.println("ap");
    	    	    } else {
    	    	    	//System.out.println("a");
    	    	    }
    	    	} else if (e > n / 30) {
    	    	    e = -n * Math.log(1 - e / n);
    	    	   // System.out.println("b");
    	    	} else {
    	    	    //System.out.println("c");
    	    	}
    	    	
    			// We save the current estimation
    			result.add( e );
    		}
    		
    		currentTime++;
    	}
    	
    	return result.toArray(new Double[0]);
    }
    
    
    /**
     * Insert the pair (rho, time) in the queues.
     * Delete the first elements when their values are less than the inserted rho (they are outdated).
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
     * Remove the end of the queues when their timeStamp is to old (< limit)
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
    
    
	
	public static void main(String[] args) {
		int windowSize = 2000, precision = 2000; 
		Path file = FileManager.Files.shakespeare;
		displaySlidingWindows( file, new LookUp3() , 6, windowSize, precision);
		displaySlidingWindows( file, new LookUp3() , 8, windowSize, precision);
		displaySlidingWindows( file, new LookUp3() , 10, windowSize, precision);
    	displaySlidingWindows( file, new LookUp3() , 12, windowSize, precision);
		
		
	}

}
