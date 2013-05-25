package hash;


/**
 * Found here :
 * http://en.wikipedia.org/wiki/MurmurHash
 */
public class MurmurHash3 extends HashFunction {
	
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
		final int c1 = 0xcc9e2d51;
	    final int c2 = 0x1b873593;
	    
	    int length = array.length;
	    int h1 = 0xdeadbeef ^ length;
	    
	    int pt = 0;
	    while(length >= 4){
	        int k1 = (array[pt] & 0xFF) << 24;
	        k1 |= (array[pt + 1] & 0xFF) << 16;
            k1 |= (array[pt + 2] & 0xFF) << 8;
            k1 |= (array[pt + 3] & 0xFF);
                
            
	        k1 *= c1;
	        k1 = (k1 << 15) | (k1 >>> 17); 
	        k1 *= c2;

	        h1 ^= k1;
	        h1 = (h1 << 13) | (h1 >>> 19); 
	        h1 = h1*5+0xe6546b64;
	        
	        pt += 4;
	        length -=4;
	      }
	    
	    int k1 = 0;
	    switch(length) {
	      case 3:
	        k1 = (array[pt + 2] & 0xFF) << 16;
	      case 2:
	        k1 |= (array[pt + 1] & 0xFF) << 8;
	      case 1:
	        k1 |= (array[pt] & 0xFF);
	        k1 *= c1;
	        k1 = (k1 << 15) | (k1 >>> 17);
	        k1 *= c2;
	        h1 ^= k1;
	    }
	    
	    h1 ^= length;

	    h1 ^= h1 >>> 16;
	    h1 *= 0x85ebca6b;
	    h1 ^= h1 >>> 13;
	    h1 *= 0xc2b2ae35;
	    h1 ^= h1 >>> 16;

	    return h1;
	}


}
