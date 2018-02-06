
/* Description: Hide and seek game (find the minimum time)
 * Jake is at N position ((0 ¡Â N ¡Â 100,000) and  Mike is at K position (0 ¡Â K ¡Â 100,000).
 * Jake can walk or do a quick move. If Jake walks at X position, he will be moved to X-1 or X+1 after ONE second.
 * If he do a quick move, he will be at 2*X position after ONE second.
 * When positions of two people are given, find the minimum amount of time for Jake to reach to Mike.
 * 
// Input
 Line 1: Jake position (N), Mike position (K)

// Output
 Line 1: minimum time (second)
 */

import java.util.LinkedList;
import java.util.Queue;	// Queue interface in Java
import java.util.Scanner;

public class hide_seek2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();  // start
		int K = sc.nextInt();  // end
		
		Queue<Integer> q = new LinkedList<>();
		// dist[]: minimum time for distance between N and K
		int[] dist = new int[100001];
		boolean isEnd = false;
		
	// fill dist[] with -1
		for(int i =0; i<dist.length; i++) {
			dist[i] = -1;
		}
		
		dist[N] = 0; // initialize the start point(N)
		q.offer(N);  // insert the value to the queue
		
		while(!q.isEmpty()) {
			// poll(): Removes and returns the head of the queue. Returns null if queue is empty.
			int nowPos = q.poll();
			int[] nextPos = new int[]{nowPos-1, nowPos+1, nowPos*2};
			
			for(int i =0; i<nextPos.length; i++) {
				if(0<=nextPos[i] && nextPos[i]<=100000 && dist[nextPos[i]] == -1) {
					dist[nextPos[i]] = dist[nowPos] +1; // add 1 second for one movement
					q.offer(nextPos[i]);
					
					// stop if arrive the destination, K
					if(nextPos[i] == K) {
						isEnd = true;
						break;
					}
				}
			}
			if(isEnd) break;
		}
			
		System.out.print(dist[K]);
		
	}

}
