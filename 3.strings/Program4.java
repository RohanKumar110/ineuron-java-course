public class Program4 {

	public static void main(String[] args) {
		
		 boolean isPangram = true;
		 String s1 = "The quick brown fox jumps over the lazy dog";
	     boolean[] allAplphabets = new boolean[26];
	     
	     int index = 0;
	     s1 = s1.toLowerCase();
	     
	     for(int i=0;i<s1.length();i++) {
	    	 char c = s1.charAt(i); 
	    	 if( c >= 'a' && c <= 'z') {
	             index = c - 'a';
	             allAplphabets[index] = true;
	    	 }
	     }
	     
	     for (int i = 0; i < allAplphabets.length; i++) {
	         if (!allAplphabets[i]) { 
	        	 isPangram = false;
	        	 break;
	         }
	      }
	     if(isPangram) {
	    	 System.out.println(s1+" -> pangram");
	     }else {
	    	 System.out.println(s1+" -> not pangram");
	     }
	}
}
