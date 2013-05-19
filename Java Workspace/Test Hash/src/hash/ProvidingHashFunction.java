package hash;


public class ProvidingHashFunction {
	public static HashFunction newHashFunction(String name){
		
	    Class c = null;
	    HashFunction func = null;
		try {
			c = Class.forName(name);
			func = (HashFunction) c.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	    return  func;
	}
}
