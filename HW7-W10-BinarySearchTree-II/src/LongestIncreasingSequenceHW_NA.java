/**
 * @author: Nathan Ayele
 * @date: 04/05/2023
 * @class: CS302 - Design and Analysis of Algorithms
 * 
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/*
 * Returns the length of the longest increasing sequence
 */
public class LongestIncreasingSequenceHW_NA
{
	public static int lis1(int[] A)
	{
		int[] T = new int[A.length];

		for (int i = 0; i < A.length; i++)
		{
			T[i] = 1;
			for (int j = 0; j < i; j++)
				if (A[j] < A[i] && T[i] < T[j] + 1)
					T[i] = T[j] + 1;
		}
		
		ArrayList seq = new ArrayList<>();
		boolean done = false;
		while (!done){
			for (int x = T.length-1; x > 0; x--){
				if (T[x] == max(T)){
					seq.add(A[x]); 
					int pointer = max(T);
					for (int y = x; y > 0; y--) {
						if (T[y] == pointer-1 && A[y] < A[x]){
							seq.add(A[y]);
							pointer = T[y];
						}
						 
					}
					done = true;
					break;
				}
			}
		}
		System.out.println("Sequence: " + reverse(seq));

		return max(T);
	}

	// reverses an ArrayList
	public static ArrayList reverse(ArrayList al){
		ArrayList reversed = new ArrayList<>();
		for (int x = al.size()-1; x >= 0; x--){
			reversed.add(al.get(x));
		}
		return reversed;
	}

	public static int max(int[] T)
	{
		int maxSoFar = T[0];
		for (int val : T)
			if (val > maxSoFar)
				maxSoFar = val;

		return maxSoFar;
	}

	/*
	 * Returns the length of the longest increasing sequence and prints the actual
	 * sequence
	 */
	public static int lis2(int[] A)
	{
		int[] T = new int[A.length];
		int[] prev = new int[A.length];

		for (int i = 0; i < A.length; i++)
		{
			T[i] = 1;
			prev[i] = -1;

			for (int j = 0; j < i; j++)
				if (A[j] < A[i] && T[i] < T[j] + 1)
				{
					T[i] = T[j] + 1;
					prev[i] = j;
				}
		}

		ArrayList seq = new ArrayList<>();
		for (int x = T.length-1; x > 0; x--){
			if (T[x] == max(T)){
				seq.add(A[x]);

				while(prev[x] != -1){
					seq.add(A[prev[x]]);
					x = prev[x];
				}
				break;
			}
		}
		
		System.out.println("Sequence: " + reverse(seq));

		return max(T);
	}

	public static void main(String[] args)
	{
		// int[] a = { 7, 2, 1, 3, 8, 4, 9, 1, 2, 6, 5, 9, 3, 8, 1 };
		// System.out.println(Arrays.toString(a));
		// System.out.println("Length: " + lis1(a));
		// System.out.println("Length: " + lis2(a));
		
		// a = new	int[] { 7, 2, 0, 1, 3, 8, 4, 9, 1, 2, 6, 5, 9, 3, 8, 1, 11 };
		// System.out.println(Arrays.toString(a));
		// System.out.println("Length: " + lis1(a));
		// System.out.println("Length: " + lis2(a));

		
			int[] nums = {0,1,2,4,5,7};
			System.out.println(summaryRanges(nums));
			
	}
	public static List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        String s = "";
        s += nums[0];
        for(int i=1; i < nums.length; i++){
            if(nums[i] == nums[i-1] + 1){
                continue;
            } 
			if(i == nums.length-1){
				s += nums[i];
			}
                s +=  "->" + nums[i-1];
                list.add(s); 
                s = "" + nums[i];
				   
            
        }
        return list;
    }

}