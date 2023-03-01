/**
 * Student: Nathan Ayele
 * Class: CS302 - Design and Analysis of Algorithms | CaldwellSpring'23
 * Date: March 1st, 2023
 */
import java.util.ArrayList;
import java.util.Arrays;

public class RadixSortHW {
	public static int width(int[] a) {
		int highest = 0;
		for (int i : a) {
			if (i > highest) {
				highest = i;
			}
		}
		return String.valueOf(highest).length();
	}

	public static int getDigit(int value, int digit) {
		// to do
		if (digit < 1) {
			throw new ArithmeticException("digit should be greater than 0");
		} else {
			for (int i = 0; i < digit; i++) {
				value /= 10;
			}
			// as counted from behind (right to left)
			return value % 10;
		}
	}

	public static void sort(int[] a) {
		ArrayList<ArrayList<Integer>> buckets = new ArrayList<>();
		// to do
		for (int i = 0; i < 10; i++) {
			buckets.add(new ArrayList<>());
		}
		for (int i = 1; i <= width(a); i++) {
			fillBucket(buckets, a, i);
			emptyBuckets(a, buckets);
		}
	}

	public static void emptyBuckets(int[] a, ArrayList<ArrayList<Integer>> buckets) {
		// to do
		int k = 0;
		for (int i = 0; i < buckets.size(); i++) {
			for (int j = 0; j < buckets.get(i).size(); j++) {
				a[k++] = buckets.get(i).get(j);
			}
			buckets.get(i).clear();
		}
	}

	public static void fillBucket(ArrayList<ArrayList<Integer>> buckets, int[] a, int position) {
		// todo
		for (int i : a) {
			int digit = getDigit(i, position);
			buckets.get(digit).add(i);
		}
	}

	public static void main(String[] args) {
		int[] a = new int[] { 333, 22, 0, 1, 666666, 5475, 99, 4444, 2, 77 };

		System.out.println(Arrays.toString(a));
		sort(a);
		System.out.println(Arrays.toString(a));

	}
}