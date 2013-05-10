package sampling;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 * 
 * Sample using the algorithm from the subject and a tree-based structure,
 * providing access and removal in O(log(n)). It results in many false positives
 * (see the Iceberg class)
 * 
 */
public class IcebergTreeSample extends IcebergSample {

	private final TreeMap<String, Integer> sample = new TreeMap<String, Integer>();

	public IcebergTreeSample(double frequency) {
		super(frequency);
	}

	@Override
	public void addWord(String word) {
		Integer i = sample.get(word);

		if (i == null)
			sample.put(word, 1);
		else
			sample.put(word, i.intValue() + 1);

		if (sample.size() == Math.ceil(2. / frequency)) {
			Iterator<String> it = sample.keySet().iterator();
			while (it.hasNext()) {
				String str = it.next();
				int j = sample.get(str);

				if (j == 1)
					it.remove();
				else
					sample.put(str, j - 1);
			}
		}
	}

	@Override
	public LinkedList<String> getIcebergs() {

		// To limit the number of false positive, we select only words with more
		// than 1 occurrence
		// (words that occurred at least 2 times within the last 1/frequency
		// words read).
		LinkedList<String> l = new LinkedList<String>();
		for (String s : sample.keySet())
			if (sample.get(s) > 1)
				l.add(s);

		return l;
	}

}
