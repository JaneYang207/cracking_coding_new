package leetCode;

public class StringCompression_443 {
    public int compress(char[] chars) {
        if (chars == null) {
            return 0;
        }

        char curChar = chars[0];
        int curCount = 0;

        int i = 0, j = 0;
        while (i < chars.length) {

            if (chars[i] != curChar) {
                // modify chars in place
                chars[j] = curChar; // j is always smaller than i
                j++;

                if (curCount > 1) {
                    chars[j] = (char)(curCount + '0');
                    j++;
                }

                curChar = chars[i];
                curCount = 1;
            } else {
                curCount++;
            }

            i++;
        }

        // append the last one
        chars[j] = curChar;
        if (curCount > 1) {
            j++;
            chars[j] = (char)(curCount + '0');
        }

        return j + 1;
    }

    public static void main(String[] args) {
        StringCompression_443 myclass = new StringCompression_443();
        char[] chars = new char[]{'a'};
//        char[] chars = new char[]{'a', 'b', 'c'};
//        char[] chars = new char[]{'a','a', 'b', 'b', 'b', 'c', 'c'};
        myclass.compress(chars);
    }
}
