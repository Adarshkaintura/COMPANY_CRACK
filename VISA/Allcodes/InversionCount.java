import java.util.*;

public class InversionCount {
    static long inversion = 0;

    public static void merge(int[] arr, int l, int mid, int h) {
        int n1 = mid - l + 1;
        int n2 = h - mid;

        int[] left = new int[n1];
        int[] right = new int[n2];

        for (int i = 0; i < n1; i++) left[i] = arr[l + i];
        for (int j = 0; j < n2; j++) right[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = l;

        while (i < n1 && j < n2) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
                inversion += (n1 - i); // All remaining elements in left[] are inversions
            }
        }

        while (i < n1) arr[k++] = left[i++];
        while (j < n2) arr[k++] = right[j++];
    }

    public static void countInversions(int[] arr, int l, int h) {
        if (l < h) {
            int mid = (l + h) / 2;
            countInversions(arr, l, mid);
            countInversions(arr, mid + 1, h);
            merge(arr, l, mid, h);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        countInversions(arr, 0, n - 1);
        System.out.println(inversion);

        sc.close();
    }
}
