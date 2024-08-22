import java.util.Arrays;

public class SortArrays {
    public static void main(String[] args) {
       
        int[] intArray = {5, 2, 8, 7, 1};
        String[] strArray = {"banana", "apple", "cherry", "date", "elderberry"};
        Arrays.sort(intArray);
        System.out.println("Sorted Integer Array: " + Arrays.toString(intArray));
      
        Arrays.sort(strArray);
        System.out.println("Sorted String Array: " + Arrays.toString(strArray));
    }
}
