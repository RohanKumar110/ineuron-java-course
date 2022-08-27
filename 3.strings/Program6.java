public class Program6 {

	public static void main(String[] args) {
		
		String s1 = "telusko ineuron";
		
		char[] arr = s1.toCharArray();
		
		for(int i=0;i<arr.length;i++) {
			
			for(int j=0;j<arr.length-1;j++) {
				
				if(arr[j] > arr[j+1]) {
					char temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		
		String sortedString = new String(arr);
		
		System.out.println("Actual String -> "+s1);
		System.out.println("Sorted String -> "+sortedString);
	}
}
