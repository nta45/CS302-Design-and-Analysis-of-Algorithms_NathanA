import java.util.Random;
import java.util.*;

public class Doubling
{
	static class Stopwatch
	{

		private final long start;

		/**
		 * Initializes a new stopwatch.
		 */
		public Stopwatch()
		{
			start = System.currentTimeMillis();
		}

		/**
		 * Returns the elapsed CPU time (in seconds) since the stopwatch was created.
		 *
		 * @return elapsed CPU time (in seconds) since the stopwatch was created
		 */
		public double elapsedTime()
		{
			long now = System.currentTimeMillis();
			return (now - start) / 1000.0;
		}
	}

	public static int[] fillWithInts(int N)
	{
		int[] a = new int[N];

		for (int i = 0; i < N; i++)
			a[i] = i;

		return a;
	}
	
	/*
	 * This method determines whether or not all of the elements in an integer array
	 * are distinct.  Return true if there are no duplicates
	 */

	public static boolean distinct(int[] a) {
		Set<Integer> set = new HashSet<>();

		for (int num : a) {
			if (set.contains(num)) {
				return false; // Found a duplicate element
			}
			set.add(num);
		}
		return true; // No duplicates found
	}

	public static void main(String[] args)
	{
		Random rand = new Random();
		double previousTime = 0;

		System.out.printf("%10s %8s %5s %7s\n", "N", "Time", "Match", "Ratio");
		for (int N = 4000, trial = 1; trial < 15; N *= 2, trial++)
		{
			int[] a = fillWithInts(N);

			Stopwatch s = new Stopwatch();
			distinct(a);

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