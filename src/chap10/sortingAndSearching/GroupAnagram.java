package chap10.sortingAndSearching;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 10.2 Sort an array of Strings so that all anagrams are next to each other
 */
public class GroupAnagram implements Comparator<String> {

  /**
   * This method is from Comparator interface
   * Must implement this method, otherwise this class should be defined as a abstract class
   * @param s1
   * @param s2
   * @return
   */
  public int compare(String s1, String s2) {
//    return s1.compareTo(s2);

    // compare sorted string
    String s1_sorted = sortString(s1);
    String s2_sorted = sortString(s2);
    return s1_sorted.compareTo(s2_sorted);
  }

  public static void display(String[] arr) {
    for(String s : arr) {
      System.out.print(s+"   ");
    }
    System.out.println("");
  }

  public static void test1(String[] arr) {
    GroupAnagram anagramComparator = new GroupAnagram();
    Arrays.sort(arr, anagramComparator);

    System.out.println("========== sort String[] by self-defined comparator (Group anagrams together) ===============");
    GroupAnagram.display(arr);
  }

  public static void testSortByNaturalOrder(String[] arr) {
    Arrays.sort(arr);

    System.out.println("========== sort String[] by natural order ===============");
    GroupAnagram.display(arr);
  }


  public void groupAnagrams(String[] arr) {
    Map<String, List<String>> map = new HashMap<>();

    String key;
    for(String str : arr) {
      key = sortString(str);

      if (map.containsKey(key)) {
        map.get(key).add(str);
      } else {
        List<String> values = new ArrayList<>();
        values.add(str);
        map.put(key, values);
      }
    }

    // copy back
    int i = 0;
    for(String k : map.keySet()) {
      for(String val : map.get(k)) {
        arr[i] = val;
        i++;
      }
    }
  }

  /**
   * Sort a string
   * @param s1
   * @return
   */
  public String sortString(String s1) {
    char[] context = s1.toCharArray();
    Arrays.sort(context);
    return new String(context);
  }


  public static void test_groupAnagrams(String[] arr) {
    GroupAnagram myclass = new GroupAnagram();
    myclass.groupAnagrams(arr);

    System.out.println("========== group anagrams using hashmap ===============");
    GroupAnagram.display(arr);
  }

  public static void main(String[] args) {
    String[] arr = new String[] {"abc", "efgd", "ghih", "abdm", "cba", "dfeg", "bac"};

    GroupAnagram.testSortByNaturalOrder(arr);
    GroupAnagram.test1(arr);
    GroupAnagram.test_groupAnagrams(arr);

  }
}
