public class Test {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		String s = "a;a;a;a;a;a;a;a;a";
		for (String s2 : s.split(";"))
			System.out.println(s2);
	}

}
