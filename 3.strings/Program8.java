public class Program8 {

    public static void main(String[] args) {

        int specialCharactersCount = 0;
        String s1 = "Hello@World!";

        for (int i = 0; i < s1.length(); i++) {

            char c = s1.charAt(i);
            if ((c >= 32 && c <= 47) || (c >= 58 && c <= 64) || (c >= 91 && c <= 96) || (c >= 123 && c <= 126)) {
                specialCharactersCount++;
            }
        }
        System.out.println("Total special characters are: " + specialCharactersCount);
    }
}