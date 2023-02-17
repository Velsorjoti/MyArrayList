import java.util.Arrays;

public class Main {

    public static int[] generateRandomArray() {
        java.util.Random random = new java.util.Random();
        int[] arr = new int[100000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(1) + 100_000;
        }
        return arr;
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
        int [] masive = generateRandomArray();
        int [] masiveBubble = Arrays.copyOf(masive, 100000);
        int [] massiveSelection = Arrays.copyOf(masiveBubble, 100000);
        int [] massiveInsertion = Arrays.copyOf(massiveSelection, 100000);
        long start = System.currentTimeMillis();
//        sortBubble(masiveBubble);                                // 1781
//        sortSelection(massiveSelection);                         // 1270
//        sortInsertion(massiveInsertion);                         // 2491
        System.out.println(System.currentTimeMillis() - start);
    }

    private static void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    public static void sortBubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    public static void sortSelection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    public static void sortInsertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }
}