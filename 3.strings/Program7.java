public class Program7 {

	public static void main(String[] args) {
		
		int vowelCount = 0;
		int constantCount = 0;
		
		String s1 = "Hello World";
		s1 = s1.toLowerCase();
		for(int i=0;i<s1.length();i++) {
			
			char c = s1.charAt(i);
			if(c >= 'a' && c <= 'z') {
				if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
					vowelCount++;
				}
				else {
					constantCount++;
				}
			}
		}
		
		System.out.println("Total vowels are: "+vowelCount);
		System.out.println("Total constants are: "+constantCount);
	}
}
