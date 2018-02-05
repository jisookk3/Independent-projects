import java.util.Scanner;

/* Description: Find the maximum score to take steps to climb the top of the stairs.
   You have a stair case with n steps. 
   You may take 1 or 2 steps at a time to climb the stairs.
   Each step has a score. 
    
// Input:
 Line 1: # of steps (n)
 Line 2...n+1: describes the score for each step.
 
// Output
 Line 1: one integer: the maximum of score you can step.
 
consider the two cases to get on the top: 
1. step onto [n-3] -> [n-1] -> [n]
2. step onto [n-2] -> [n]

dp[n] = sum of previous steps points
1. dp[n-3] + stairs[n-1] + stairs[n]
2. dp[n-2] + stairs[n]

set up base case - the first 3 steps 
dp[0], dp[1], dp[2]
dp[0] = stairs[0];
dp[1] = Math.max(stairs[0], 0) + stairs[1];
dp[2] = Math.max(stairs[0], stairs[1]) + stairs[2];

*/

public class steps2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// get # of steps : # of steps of a stair
		int n = sc.nextInt();
		// # of steps
		int[] stairs = new int[n];
		// get the point of each step
		for (int i =0; i<n; i++) {
			stairs[i] = sc.nextInt();
		}
		
		// dp[n] : stored total points that I walk on all steps
		long[] dp = new long[n];
		
		// set the base case
		dp[0] = stairs[0];
		
		dp[1] = Math.max(stairs[0], 0) + stairs[1];
		
		dp[2] = Math.max(stairs[0], stairs[1]) + stairs[2];
		
		// the maximum score for two cases
		for (int i =3; i<n; i++) {
			dp[i] = Math.max(dp[i-3]+stairs[i-1], dp[i-2]) + stairs[i];
		}
		
		System.out.println(dp[n-1]);  // start 0 for the stair so print n-1
	

	}

}
