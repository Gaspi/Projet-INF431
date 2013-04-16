package drafts;

public class UnsignedArithmetic {

	public static int unsignedAdd(int i1, int i2) {
		long l1 = i1 & 0xffffffffL, l2 = i2 & 0xffffffffL;
		return (int) (l1 + l2);
	}

	public static int unsignedMin(int i1, int i2) {
		long l1 = i1 & 0xffffffffL, l2 = i2 & 0xffffffffL;
		return (int) (l1 + l2);
	}

	public static void main(String[] args) {
		System.out.println(-4 >> 1);

	}

}
