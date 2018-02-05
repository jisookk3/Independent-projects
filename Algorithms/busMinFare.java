
/* 
 * Description: Find the minimum bus fare
 * Question: Find the minimum bus fare between one city to another city.
 * Input: 
 *  - line 1: number of cities n(1¡Ân¡Â1,000)
 *  - line 2: number of buses m(1¡Âm¡Â100,000)
 *  - line 3 to m+2: departure city number, arrival city number, bus fare
 *  - line m+3: departure city # and arrival city # to find the minimum bus fare
 * 
// Sample input:
5
8
1 2 2
1 3 3
1 4 1
1 5 10
2 4 2
3 4 1
3 5 1
4 5 3
1 5

// output:
4

 */

import java.util.PriorityQueue;
import java.util.Scanner;

public class busMinFare {
	static int[][] ad;
	static int[] dist;
	static int Vn;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Vn = sc.nextInt();	// how many cities 1-1,000
		int En = sc.nextInt();	// how many buses 1-100,000
		ad = new int[Vn+1][Vn+1];	// why +1 ? the city number starts 1 for the array boundary 
		
		for (int i = 0; i < ad.length; i++) {
			for (int j = 0; j < ad[i].length; j++) {
				ad[i][j] = 100001;	// To check if the route has the bus fare.
				}
		}
		
		// get inputs for bus route and fare
		for (int i = 0; i < En; i++) {
			int u = sc.nextInt();	// departure city
			int v = sc.nextInt();	// arrival city
			int w = sc.nextInt();	// bus fare
			if (ad[u][v] > w) {	// set the smaller fare
				ad[u][v] = w;	// make the max cost to entered number
//				System.out.println("ad["+u + "][" + v +"] = " + w);
			}
		}
	
		// get start/end city
		int start = sc.nextInt();	// city start #
		int end = sc.nextInt();	// city end #
		// dist[city# + 1] : save each bus fare
		dist = new int[Vn +1];	// distance 
		
		for (int i = 0; i < dist.length; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		
		System.out.print(dijkstra(start,end));
	}
	

	// Algorithm: find the minimum cost / compare each distance
	public static int dijkstra(int start, int end) {
		PriorityQueue<Vertex> q = new PriorityQueue<Vertex>();
		dist[start] = 0;
		// Inserts the specified element into this priority queue.
		q.offer(new Vertex(start, dist[start]));
		
		while(!q.isEmpty()) {
			// peek: Retrieves, but does not remove, the head of this queue,
			int nowVertex = q.peek().getIndex();
			int cost = q.peek().getWeight();
			q.poll();	// poll: Retrieves and removes the head of this queue
			
			// repeat until the fare is less than Integer MAX.
			
			// if there is a round to check, then go to the next line
			if(cost > dist[nowVertex]) continue;
			
			// Important part!  Vn: city #
			// dist[i]: save the minimum fare depending on the bus route
			for (int i = 1; i <= Vn; i++) {
				if (ad[nowVertex][i] != 100001 && dist[i] > dist[nowVertex] +ad[nowVertex][i]) {
					dist[i] = dist[nowVertex] + ad[nowVertex][i];
					q.offer(new Vertex(i, dist[i]));
				}
			}
		}
		return dist[end];		
	}
	
	
	static class Vertex implements Comparable<Vertex> {
		private int index;
		private int weight;
		
		public Vertex(int index, int weight) {
			this.index = index;
			this.weight = weight;
		}
		
		public int getIndex() {
			return index;
		}
		
		public int getWeight() {
			return weight;
		}
		
		// Compares this object with the specified object for order. 
		// Returns a negative integer, zero, or a positive integer 
		// as this object is less than, equal to, or greater than the specified object. 
		@Override
		public int compareTo(Vertex another) {
			return this.weight - another.weight;
		}
		
	}

}
