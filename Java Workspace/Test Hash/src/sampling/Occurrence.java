package sampling;

public class Occurrence {

    String word;
    int nbOcc;

    public Occurrence(String word, int nbOcc) {
	this.word = word;
	this.nbOcc = nbOcc;
    }

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