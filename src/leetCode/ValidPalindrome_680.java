package leetCode;

public class ValidPalindrome_680 {
    public boolean validPalindrome(String s) {
        if(s == null || s.isEmpty()) {
            return true;
        }

        int i = 0;
        int j = s.length() - 1;

        boolean removedOne = false; // is true if already removed one character from the string

        while (i < j) { // todo: how aabout i==j?
            // compare charAt(i) and charAt(j)
            // if equal, update i, j, then continue; else, remove one
            System.out.println("i = " + i + "     j = " + j);
            if (s.charAt(i) == s.charAt(j)) {
                System.out.println("Is equal. ");
                i++;
                j--;
            } else {
                if(removedOne) {
                    return false;
                } else if(s.charAt(i) == s.charAt(j-1)) {
                    System.out.println("Not equal. Remove char at " + j + " which is " + s.charAt(j));
                    j--;
                    removedOne = true;
                } else if (s.charAt(j) == s.charAt(i+1)) {
                    System.out.println("Not equal. Remove char at " + i + " which is " + s.charAt(i));
                    i++;
                    removedOne = true;
                } else {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        ValidPalindrome_680 myclass = new ValidPalindrome_680();
        String s = "ebcbbececabbacecbbcbe";
        boolean ans = myclass.validPalindrome(s);
        System.out.println("ans is " + ans);
    }
}
