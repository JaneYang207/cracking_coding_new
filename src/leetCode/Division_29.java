package leetCode;

public class Division_29 {
    public int divide(int dividend, int divisor) {
        long ans = recf(Math.abs((long)dividend), Math.abs((long)divisor));
        if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) {
            return ans < Integer.MIN_VALUE ? Integer.MAX_VALUE : (int)-ans;
        }
        return ans > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int)ans;
    }

    private long recf(long dividend, long divisor) {
        // base condition
        if(dividend < divisor)
            return 0;

        long ans = 0;
        long num = divisor;
        while (num <= dividend ) {
            ans = (ans == 0) ? 1 : (ans << 1);
            num = num << 1;
        }

        return ans + (int)recf(dividend - (num >> 1), divisor );
    }

    public static void main(String[] args) {
        Division_29 myclass = new Division_29();
//        int ans = myclass.divide(7, -3);
        int ans = myclass.divide(-2147483648, -1);
        System.out.println("ans = " + ans);

    }
}
