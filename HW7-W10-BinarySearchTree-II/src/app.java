public class app {
    public static long fibIterative(int n){
        if (n <= 1) return n;
        int fib1 = 0;
        int fib2 = 1;

        for (int i=2; i<n; i++){
            int sum = fib1 + fib2;
            fib1 = fib2;
            fib2 = sum;
        }
        return fib1 + fib2;
    }
    public static void main(String[] args) {
        System.out.println(fibIterative(30));
    }
}
