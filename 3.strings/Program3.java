public class Program3 {

	public static void main(String[] args) {
		
		boolean isAnagram = true;
		String firstString = "Earth";
		String secondString = "Heart";
		
		String s1 = firstString.toLowerCase();
		String s2 = secondString.toLowerCase();
		
		if(s1.length() != s2.length()) {
			  System.out.println(firstString + " and " + secondString + " are not anagram.");
		}else {
			
			s1 = sort(s1);
			s2 = sort(s2);
			
			for(int i = 0; i< s1.length(); i++) {
				
				if(s1.charAt(i) != s2.charAt(i)) {
					isAnagram = false;
					break;
				}
			}
			
			if(isAnagram) {
				  System.out.println(firstString + " and " + secondString+ " are anagram.");			
		    } else {
		    	  System.out.println(firstString + " and " + secondString + " are not anagram.");
		    }
		}
	}
	
	static String sort(String s) {
		
		char[] array = s.toCharArray();
		
		for(int i=0; i<array.length; i++) {
			
			for(int j=0 ; j< array.length - 1; j++) {
				
				if(array[j] > array[j +1]) {
					char temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
			}
		}
		
		return new String(array);
	}
}
