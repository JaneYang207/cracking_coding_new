package leetCode;

public class ValidPalindrome_125 {

    public boolean isPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }

        int i = 0;
        int j = s.length() - 1;

        while (i < j) {
            // skip non-alphanumeric characters
            while (i < s.length() && !isAlphanumeric(s.charAt(i))) {
                i++;
            }

            while (j >= 0 && !isAlphanumeric(s.charAt(j))) {
                j--;
            }

            System.out.println("first char: " + s.charAt(i) + "    second char: " + s.charAt(j));

            if ( i >= s.length() || j < 0 || i > j || (s.charAt(i) != s.charAt(j) && Math.abs(s.charAt(i) - s.charAt(j)) != 'a' - 'A') ) {
                System.out.println("Not equal.");
                return false;
            }

            System.out.println("Equal.");
            i++;
            j--;
        }

        return true;
    }

    private boolean isAlphanumeric(char ch) {
        return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z');
    }

    public static void main(String[] args) {
        ValidPalindrome_125 myclass = new ValidPalindrome_125();
        String str = "A man, a plan, a canal: Panama";
        boolean ans = myclass.isPalindrome(str);
        System.out.println("ans = " + ans);
    }
}
