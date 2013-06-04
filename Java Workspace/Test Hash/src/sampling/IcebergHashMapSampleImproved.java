package sampling;

import hash.HashFunction;
import hash.LookUp3;
import hash.LoseLose;
import hash.MurmurHash3;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeMap;
import org.apache.commons.math3.random.RandomDataGenerator;

public class IcebergHashMapSampleImproved extends IcebergSample {

	private final HashMap<MyString, Integer> sample = new HashMap<MyString, Integer>();
	private final double lowerBoundFrequency;
	private final double delta = 0.1;
	private final int b;
	private int h = 0;
	private int sumK = 0;

	public IcebergHashMapSampleImproved(double frequency,
			double lowerBoundFrequency) {
		super(frequency);
		
		this.lowerBoundFrequency = lowerBoundFrequency;
		b = (int)
			Math.ceil(
				Math.log(
					Math.pow((frequency * lowerBoundFrequency / 2), -2.)
				  * Math.log(3 / ((1 - lowerBoundFrequency / 2.)*frequency*delta))
				)  / Math.log(2)
			) + 3;
	}

	@Override
	public void addWord(String word) {

		if (Math.random() <= Math.pow(2., -h)) {
			MyString str = new MyString(word);
			Integer i = sample.get(str);
			
			if (i == null) sample.put(str, 1);
			else 		   sample.put(str, 1 + i.intValue());
			
			sumK++;
		}
		
		if(sumK == Math.pow(2., b)){
			h++;
			RandomDataGenerator rand = new RandomDataGenerator();
			Iterator<MyString> it = sample.keySet().iterator();
			while(it.hasNext()){
				MyString s = it.next();
				int ks = sample.get(s);
				sumK -= ks;
				int k = rand.nextBinomial(ks, 0.5);
				if(k != 0){
					sample.put(s, k);	
					sumK += k;
				}
				else
					it.remove();
			}
		}
		
	}
	
	
	@Override
	public LinkedList<String> getIcebergs() {
		
		LinkedList<String> l = new LinkedList<String>();
		
		for (MyString s : sample.keySet())
			if ((1 - lowerBoundFrequency / 2.) * frequency * sumK <= sample.get(s))
				l.add(s.str);
		
		return l;
	}
	
	
	private static class MyString{
		
		String str;
		
		public MyString(String str){
			this.str = str;
		}
		
		@Override
		public int hashCode(){
			HashFunction func = new LookUp3();
			return func.hashString(this.str);
		}
		
		@Override
		public boolean equals(Object obj){
			if (this == obj)
	    		return true;
	    	if (obj == null)
	    		return false;
	    	if (getClass() != obj.getClass())
	    		return false;
	    	MyString other = (MyString) obj;
	    	if (str == null) {
	    		if (other.str != null)
	    			return false;
	    	} else if (!str.equals(other.str))
	    		return false;
	    	return true;
		}
	}

}
