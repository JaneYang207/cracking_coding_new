package leetCode;

public class MaximumSubarray_53 {
    // 变形题： 求这个有最大sum的subarray的起止位置
    public int maxSubArray(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }

        int max = nums[0];
        int maxStartIndex = 0;
        int maxEndIndex = 0;

        int runningSum = nums[0];

        for(int i = 1; i < nums.length; i++) {
            runningSum += nums[i];

            if(runningSum <= nums[i]) {
                runningSum = nums[i];
                if(runningSum > max) {
                    maxStartIndex = i;
                }
            }

            if (runningSum > max) {
                maxEndIndex = i;
                max = runningSum;
            }
        }

        System.out.println("Start Index = " + maxStartIndex);
        System.out.println("End Index = " + maxEndIndex);

        return max;
    }

    public static void main(String[] args) {
        MaximumSubarray_53 myclass = new MaximumSubarray_53();
        int[] arr = new int[] {-2, 5, 1, -3, -4, 2, 1};
        myclass.maxSubArray(arr);
    }
}


