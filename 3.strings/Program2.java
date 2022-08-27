public class Program2 {

	public static void main(String[] args) {
		
		String s1 = "Think Twice";
		String s2 = "";
		
		String[] strings = s1.split(" ");
		
		for(String s : strings) {
			
			for(int i=s.length()-1;i>=0;i--) {
				
				s2 = s2 + s1.charAt(i);
			}
			
			s2 = s2 + " ";
		}
		
		System.out.println("S1 -> "+s1);
		System.out.println("S2 -> "+s2);
	}
}
