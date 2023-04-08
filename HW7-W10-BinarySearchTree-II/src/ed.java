public class ed {
    public static void main(String[] args) {
        
    String[] A = {"n", "a", "t", "h", "a", "n"};
    String[] B = {"p", "r", "a", "l", "o", "s", "h"};

    int i = A.length;
    int j = B.length;

    System.out.println(ED(i, j, A, B));

    }
    
    public static int ED(int i, int j, String[] A, String[] B){
        if(i==0){return j;}
        if(j==0){return i;}

        int insertion = 1 + ED(i, j-1, A, B);
        int deletion = 1 + ED(i-1, j, A, B);
        int mismatch = diff(A[i-1], B[j-1]) + ED(i-1, j-1, A, B);

        return min(insertion, deletion, mismatch);
    }

    private static int min(int i, int j, int k) {
        return Math.min(Math.min(i, j), k);
    }

    private static int diff(String a, String b) {
        return (a.equals(b)) ? 0 : 1;
        
    }
}
