package leetCode;

public class CountAndSay_38 {
    public String countAndSay(int n) {
        return recf(n);
    }

    public String recf(int n) {
        if (n == 1) {
            return "1";
        }


        String ans =  read(recf(n-1));
        System.out.println("==== when n = " + n);
        System.out.println(ans);
        return ans;
    }

    private String read(String input) {
        StringBuilder builder = new StringBuilder();

        char curChar = input.charAt(0);
        int curCount = 0;

        for(int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == curChar) {
                curCount++;
            } else {
                builder.append(curCount);
                builder.append(curChar);

                curChar = input.charAt(i);
                curCount = 1;
            }
        }

        // append the last one
        builder.append(curCount);
        builder.append(curChar);

        String str =  builder.toString();
        return str;
    }

    public static void main(String[] args) {
        CountAndSay_38 myclass = new CountAndSay_38();
        myclass.countAndSay(4);
    }
}
