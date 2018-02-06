
/*
 * Description: Find the maximum profit with the bread left.
 * Mary is selling breads and she has bread left before closing the store.
 * She wants to make a set of breads to make the maximum profit. 
 * The price for each numbers of set is given. 
 * e.g. if 3 breads left, it costs $1 when you sell one bread each, it costs $5 when you sell a set of two breads, 
 * it costs $6 when you sell a set of three breads ...
 * Find the maximum amount of profit when the price for a set of bread number each.
 * 
// Input
 Line 1: # of breads leftover (1 ¡Â N ¡Â 1,000)
 Line 2...N+1: price for a set of bread number each

// Output
 Line 1: maximum profit ($)
 *
 */

import java.util.Scanner;
public class breadSold2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n  = sc.nextInt();	// # of bread
		int[] set = new int[n+1];	// cost depends on # of bread
		int[] dp = new int[n+1]; // profit for selling bread
		
		// get bread cost: eg. $2 for 1 bread sold, $3 for 2 bread sold
		for(int i =1; i<=n; i++) {
			set[i] = sc.nextInt();
		}
		
		// compare the current profit, dp[i] and price for i sold + rest profit, set[i] + dp[n-i]
		for (int i =1; i <=n; i++) {
			for (int j =1; j<=i; j++) {
				dp[i] = Math.max(dp[i], dp[i-j] + set[j]);
			}
		}
		
		System.out.print(dp[n]);
		
	}
}

/* A simple way without inputs. Only crucial part
 * 
 		public static void comb(int sum) {
			int[] dp = new int[sum+1];
			dp[0] = dp[1] = 1;
			if(sum > 1) dp[2] = 2;
			
			for(int j = 3; j <= sum; j++) {
				dp[j] += dp[j-1];
				dp[j] += dp[j-2];
				dp[j] += dp[j-3];
			}
			System.out.println(dp[sum]);
		}
*/