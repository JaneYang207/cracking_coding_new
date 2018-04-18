package leetCode;

public class LongestPalindrome {
    private int maxStartIndex = 0;
    private int maxEndIndex = 0;

    public String longestPalindrome(String s) {
        // use each char as the middle one, check the longest palindrome who take charAt(i) as middle point
        for (int i = 0; i < s.length(); i++) {
            System.out.println("============= i = " + i + "  ===================");

            // case 1. s.charAt(i) is the only middle point
            extend(false, i, s);

            // case 2: s.charAt(i) and s.charAt(i+1) are middle point together.
            // note: only consider this case under certain conditions
            if (i+1 < s.length() && s.charAt(i) == s.charAt(i+1)) {
                extend(true, i, s);
            }
        }

        return s.substring(maxStartIndex, maxEndIndex + 1);
    }

    // if isEven = true, middle including two elements; otherwise, middle is the only middle element
    private void extend(boolean isEven, int middle, String s) {
        int i = middle - 1;
        int j = isEven ? middle + 2 : middle + 1;

//        System.out.println("left = " + i + "    right = " + j);

        // compare s.charAt(i), s.charAt(j); if not equal, break; if equal, continue
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }

        System.out.println("left index = " + i + "    right index = " + j);

        if (j - i - 1 > maxEndIndex - maxStartIndex + 1) {
            maxStartIndex = i + 1;
            maxEndIndex = j - 1;
            System.out.println("start = " + maxStartIndex + "     end = " + maxEndIndex);
        }
    }

    // dp
    public String longestPalindrome_dp(String s) {
        if (s == null) return null;

        int N = s.length();

        boolean[][] dp = new boolean[N][N];  // row: the start index of substring; col: the end index of substring

        int maxLength = 0;
        int ansStart = 0;
        int ansEnd = 0;

        for (int end = 0; end < N; end++) {

            for(int start = 0; start <= end; start++) {

                dp[start][end] = s.charAt(start) == s.charAt(end) && (end - start <= 2 || dp[start+1][end-1]);

                 System.out.println("start = " + start + "  end = " + end + "   isPalindrome: " + dp[start][end]);

                if (dp[start][end] && maxLength < end - start + 1) {
                    maxLength = end - start + 1;
                     System.out.println("max length = " + maxLength);
                    ansStart = start;
                    ansEnd = end;
                }
            }
        }

        return s.substring(ansStart, ansEnd + 1);
    }

    public Result recf(String s, int start, int end) {
        System.out.println("start = " + start + "    end = " + end);
        if (start == end) {
            return new Result(true, 1, start, end);
        }

        if (s.charAt(start) == s.charAt(end)) {



            Result child = recf(s, start + 1, end -1);
            if (child.isPalindrome) {
                int max = child.val + 2;
                return new Result(true, child.val + 2, start, end);
            } else {
                // return new Result(false, child.val, start + 1, end - 1);
                return new Result(false, child.val, child.start, child.end);
            }
        }
        else {
            Result leftChild = recf(s, start, end - 1);
            Result rightChild = recf(s, start + 1, end);

            if (leftChild.val > rightChild.val) {

                // return new Result(false, leftChild.val, start, end -1);
                return new Result(false, leftChild.val, leftChild.start, leftChild.end);
            } else {

                // return new Result(false, rightChild.val, start + 1, end);
                return new Result(false, rightChild.val, rightChild.start, rightChild.end);
            }
        }
    }

    class Result {

        boolean isPalindrome; // is the string itself a palindrome
        int val; // maximum length of palindrome in substring the string;
        int start; // start index of palindrome substring
        int end; // end index of palindrome string

        public Result(boolean isPalindrome, int val, int start, int end) {
            this.start = start;
            this.end = end;
            this.isPalindrome = isPalindrome;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        LongestPalindrome myclass = new LongestPalindrome();
//        myclass.longestPalindrome("cbbd");
        String ans = myclass.longestPalindrome("cbbd");
        System.out.println(ans);
    }
}



