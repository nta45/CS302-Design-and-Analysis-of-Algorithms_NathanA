
public class Insertion_halfswap {

    public static void sort(Comparable[] x) {
        for (int i = 1; i < x.length; i++) {
            Comparable pointer = x[i];
            int j = i;
            while (j > 0 && pointer.compareTo(x[j-1])<0) {
                x[j] = x[j - 1];
                j--;
            }
            x[j] = pointer;
        }
    }
}