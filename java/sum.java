public class sum {
    public class ArraySumAndAverage {
        public static void main(String[] args) {
            int[] intArray = {5, 2, 8, 7, 1};
            int sum = 0;
            for (int i = 0; i < intArray.length; i++) {
                sum += intArray[i];
            }
            double average = (double) sum / intArray.length;
            System.out.println("Sum of Array Elements: " + sum);
            System.out.println("Average of Array Elements: " + average);
        }
    }
     
}
