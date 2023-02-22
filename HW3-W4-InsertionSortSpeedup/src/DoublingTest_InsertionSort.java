
import java.util.Arrays;
import java.util.Random;

public class DoublingTest_InsertionSort {
	static class Stopwatch {

		private final long start;

		/**
		 * Initializes a new stopwatch.
		 */
		public Stopwatch() {
			start = System.currentTimeMillis();
		}

		/**
		 * Returns the elapsed CPU time (in seconds) since the stopwatch was created.
		 *
		 * @return elapsed CPU time (in seconds) since the stopwatch was created
		 */
		public double elapsedTime() {
			long now = System.currentTimeMillis();
			return (now - start) / 1000.0;
		}
	}

	public static int[] fillWithRandomInts(int N) {
		Random rand = new Random();
		int[] a = new int[N];

		for (int i = 0; i < N; i++)
			a[i] = rand.nextInt();

		return a;
	}

	public static void main(String[] args)
	{
		Random rand = new Random();
		double previousTime = 0;
		int initialN = 4000;

		System.out.printf("%10s %8s  %8s\n", "N", "Time",  "Ratio");
		for (int N = initialN, trial = 1; trial < 15; N *= 2, trial++)
		{
			int[] a = fillWithRandomInts(N);

			Stopwatch s = new Stopwatch();
			
			// your call to insertion sort here --------↓
			// (@nathana) I had to wrap the int[] a array into Integer[] arr so that it matches with the input field for sort
				Integer[] arr = new Integer[a.length];
				Arrays.setAll(arr, i -> a[i]);
			// (@nathana) Calling the Insertion sort method here
			Insertion.sort(arr);

			double time = s.elapsedTime();

			System.out.printf("%,10d %8.2f ", N, time);

			if (previousTime == 0)
				System.out.println("        -");
			else
				System.out.printf(" %8.2f\n", time / previousTime);

			previousTime = time;
		}
	}
}