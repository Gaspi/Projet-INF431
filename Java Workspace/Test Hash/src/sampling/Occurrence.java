package sampling;

/**
 * 
 * Store a word and the number of times it appears in the text.
 *
 */
public class Occurrence {

    // The word
    String word;
    // Its number of occurrences
    int nbOcc;

    public Occurrence(String word, int nbOcc) {
    	this.word = word;
    	this.nbOcc = nbOcc;
    	
    }

    /**
     * Override the equals method so that occurrences are equals if they share the same word
     * (sharing the same number of occurrences is unnecessary)
     */
    @Override
    public boolean equals(Object obj) {
    	if (this == obj)
    		return true;
    	if (obj == null)
    		return false;
    	if (getClass() != obj.getClass())
    		return false;
    	Occurrence other = (Occurrence) obj;
    	if (word == null) {
    		if (other.word != null)
    			return false;
    	} else if (!word.equals(other.word))
    		return false;
    	return true;
    }
    
    

}