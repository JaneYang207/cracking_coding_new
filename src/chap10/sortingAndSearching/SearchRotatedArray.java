package chap10.sortingAndSearching;

/**
 * 10.3
 */
public class SearchRotatedArray {

  /**
   * search in a rotated sorted array
   * Note: the array doesn't have duplicates
   * @param arr
   * @param lower
   * @param upper
   * @param tofind
   * @return
   */
  public int recSearchRotatedArray(int[] arr, int lower, int upper, int tofind) {
    if (lower > upper) return -1;

    int mid = (lower + upper) /2;

    if (tofind == arr[mid]) {
      return mid;
    }

    // if left is the normal half
    if (arr[lower] < arr[mid]) {
      // decide if in normal half
      if (tofind > arr[lower] && tofind < arr[mid]) {
        // normal bianry search
        return binarySearchIteration(arr, lower, mid-1, tofind);
      } else {
        return recSearchRotatedArray(arr, mid + 1, upper, tofind);
      }
    }

    // if right is the normal half
    else if (arr[mid] < arr[upper]) {
      if (tofind > arr[mid+1] && tofind < arr[upper]) {
        // normal bianry search
        return binarySearchIteration(arr, mid+1, upper, tofind);
      } else {
        return recSearchRotatedArray(arr, lower, mid-1, tofind);
      }

    }
    // only 2 elements
    else if(lower == mid) {
//      return binarySearchIteration(arr, lower, upper, tofind); // this is wrong, becasue the two elements could be not sorted correctly. eg. 40, 4
        if (tofind == arr[lower]) {
          return lower;
        } else if (tofind == arr[upper]){
          return upper;
        } else {
          return -1;
        }
    }
    // the array is not an rotated sorted array
    else {
      return -1;
    }
  }


  public int binarySearchIteration(int[] arr, int lower, int upper, int tofind) {
    int mid;
    while (lower <= upper) {
      mid = (lower + upper) /2;

      if (arr[mid] == tofind) {
        return mid;
      }
       else if (tofind < arr[mid] ) {
        upper = mid -1;
      } else {
        lower = mid + 1;
      }
    }

    return -1;
  }

  public int binarySearchRecursion(int[] arr, int lower, int upper, int tofind) {
    if (lower > upper) return -1;

    int  mid = (lower + upper) /2;

    if (arr[mid] == tofind) {
      return mid;
    }
    else if (tofind < arr[mid] ) {
      return binarySearchRecursion(arr, lower, mid - 1, tofind);
    } else {
      return binarySearchRecursion(arr, mid + 1, upper, tofind);
    }
  }

  public void testNormalCase() {
    int[] arr = new int[] {15, 16, 19, 20, 25, 30, 40, 4, 5, 7, 10, 14};
//    int index = recSearchRotatedArray(arr, 0, arr.length -1, 4 );
    int index = recSearchRotatedArrayWithDuplicates(arr, 0, arr.length -1, 4 );
    System.out.println("normal case: index = " + index);
  }

  public void testArrayWithDuplicates() {
    int[] arr = new int[] {2, 2, 2, 3, 4, 5};
//    int index = recSearchRotatedArray(arr, 0, arr.length -1, 3 );
    int index = recSearchRotatedArrayWithDuplicates(arr, 0, arr.length -1, 5 );
    System.out.println("array has duplicates: index = " + index);
  }


  public int recSearchRotatedArrayWithDuplicates(int[] arr, int lower, int upper, int tofind) {
    if (lower > upper) return -1;

    int mid = (lower + upper) /2;

    if (tofind == arr[mid]) {
      return mid;
    }

    // if left is the normal half
    if (arr[lower] < arr[mid]) {
      // decide if in normal half
      if (tofind >= arr[lower] && tofind <= arr[mid]) {
        // normal bianry search
        return binarySearchIteration(arr, lower, mid-1, tofind);
      } else {
        return recSearchRotatedArrayWithDuplicates(arr, mid + 1, upper, tofind);
      }
    }

    // if right is the normal half
    else if (arr[mid] < arr[upper]) {
      if (tofind >= arr[mid+1] && tofind <= arr[upper]) {
        // normal bianry search
        return binarySearchIteration(arr, mid+1, upper, tofind);
      } else {
        return recSearchRotatedArrayWithDuplicates(arr, lower, mid-1, tofind);
      }
    }
    // deal with duplicates (also deals with the case when only 2 elements left. eg. 40, 4)
    else if(arr[lower] == arr[mid]) {

      // the partition is o the left(all elements between lower ~ mid are the duplicates), eg. [2, 3, 2, 2, 2, 2]
      // go left, call recursion search
      if (arr[upper] == arr[lower]) {
        return recSearchRotatedArrayWithDuplicates(arr, lower, mid - 1, tofind);
      }
      // the partition is on the right (all elements between lower ~ mid are the duplicates), eg. [2, 2, 2, 3, 4, 1]:
      // go right, call recursion search
      else if (arr[upper] < arr[lower]) {
        return recSearchRotatedArrayWithDuplicates(arr, mid+1, upper, tofind);
      }
      // The following case will not happen, it's already covered in "else if (arr[mid] < arr[upper]) " (right half is normal sorted)
      // when arr[upper] > arr[lower], the whole arr is normally sorted eg. [2, 2, 2, 3, 4, 5]:
      // go right, and do a normal binary search
//      else {
//        return binarySearchIteration(arr, mid+1, upper, tofind);
//      }
    }

    return -1;
  }

  public static void main(String[] args) {
    SearchRotatedArray myclass = new SearchRotatedArray();
    myclass.testNormalCase();
    myclass.testArrayWithDuplicates();
  }
}


