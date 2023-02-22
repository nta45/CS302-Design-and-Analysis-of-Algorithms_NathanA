
public class Insertion {
    public static void sort(Comparable[] x) {
        for (int i = 1; i < x.length; i++)
            for (int j = i; j > 0; j--)
                if (x[j - 1].compareTo(x[j]) > 0)
                    swap(x, j - 1, j);
                else
                    break;
    }

    private static void swap(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}