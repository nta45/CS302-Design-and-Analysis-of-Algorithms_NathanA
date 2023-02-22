
public class Insertion_binarysearch {

    public static void sort(Comparable[] x) {
        for (int i = 1; i < x.length; i++) {
            Comparable pointer = x[i];
            int left = 0;
            int right = i - 1;
            int j = i;

            while (left <= right){
                int mid = left + (right-left)/2;
                if (pointer.compareTo(x[mid])<0){
                    right = mid - 1;
                    j = mid;
                }
                else{
                    left = mid + 1;
                }
            }
            
            for (int n = i; n > j; n --) {
                x[n] = x[n - 1];
            }
            x[j] = pointer;

        }
    }

}