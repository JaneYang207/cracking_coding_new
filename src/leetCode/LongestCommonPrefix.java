package leetCode;

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {

        String ans = "";

        // if the array is empty, or the first string is null/empty
        String first = strs[0];
        if (strs == null || strs.length == 0 || strs[0] == null || strs[0].isEmpty()) {
            return ans;
        }


        char ch;

        for (int i = 0; i < strs[0].length(); i++) {
            ch = strs[0].charAt(i);

            // check every string in the array to see if they have the "ch" at "i"
            for(int index = 1; index < strs.length; index++) {
                if (strs[index] == null || i > strs[index].length() - 1 || strs[index].charAt(i) != ch) {
                    return ans;
                }
            }

            ans += ch;
        }

        return ans;
    }
}
