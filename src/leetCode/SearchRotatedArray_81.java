package leetCode;

public class SearchRotatedArray_81 {
    public boolean search(int[] nums, int target) {
        return recf(0, nums.length - 1, target, nums);
    }
    private boolean recf(int lower, int upper, int target, int[] nums) {
        if(lower > upper) {
            return false;
        }

        // compute middle
        int middle = (lower + upper) / 2;

        if (nums[middle] == target) {
            return true;
        }

        // when left side is normal
        if (nums[lower] < nums[middle]) { // can we tell left side is normal side based on this?
            // check if "target" is in the scope of normal half
            if(target < nums[middle] && target >= nums[lower]) {
                return recf(lower, middle-1, target, nums);
            } else {
                return recf(middle+1, upper, target, nums);
            }
        }
        // when only two elements left
        else if(nums[lower] == nums[middle]) {
            if(lower == middle || nums[upper] != nums[middle]) {
                return recf(middle+1, upper, target, nums); // go right
            } else {
                return recf(lower+1, upper-1, target, nums);
            }
        }
        // when right side is normal
        else {
            // check if "target" is in the scope of normal half
            if(target > nums[middle] && target <= nums[upper]) {
                return recf(middle+1, upper, target, nums);
            } else {
                return recf(lower, middle-1, target, nums);
            }
        }

    }

    public static void main(String[] args) {
        SearchRotatedArray_81 myclass = new SearchRotatedArray_81();
//        int[] A = new int[]{1, 1, 3, 1};
        int[] A = new int[]{1,3,5};
        boolean ans = myclass.search(A, 5);
        System.out.println(ans);
    }
}
