public class Program5 {

	public static void main(String[] args) {

		int position = -1;
		String s1 = "Java";

		for (int i = 0; i < s1.length(); i++) {
			for (int j = i + 1; j < s1.length(); j++) {
				if (s1.charAt(i) == s1.charAt(j)) {
					position = i;
					break;
				}
			}
			if (position != -1)
				break;
		}

		if (position == -1)
			System.out.println("Not found");
		else
			System.out.println(s1.charAt(position));
	}
}
