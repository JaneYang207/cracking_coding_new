package leetCode;

public class MergeSortedArray_88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;

        int k = m + n - 1;

        while (i >= 0 && j >= 0) {
            System.out.println("i = " + i + ",   j = " + j + ",   k= "+ k);
            // move the larger one to right spot
            // decrease index of the larger one
            if (nums1[i] >= nums2[j]) {
                System.out.println("put value " + nums1[i] + " at the spot k");
                nums1[k] = nums1[i];
                i--;
            }
            else
            {
                System.out.println("put value " + nums2[j] + " at the spot k");
                nums1[k] = nums2[j];
                j--;
            }

            k--;
        }

        // if nums2 has reached the end, nums1 hasn't, do nothing;
        // if nums1 has reached the end, nums2 hasn't, move all remaining nums2 elements into nums1;
        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }

    }

    public static void main(String[] args) {
        MergeSortedArray_88 myclass = new MergeSortedArray_88();
        int[] nums1 = new int[]{1,2,3,0,0,0};
        int[] nums2 = new int[]{2,5,6};
        myclass.merge(nums1, 3, nums2, 3);
        System.out.println("");
    }
}
