/*
 * Name        : stacktest.java
 * Author      : Jisook Kim
 * Description : tests for the stack algorithm
 */


package stack;

public class stacktest {

	public static void main(String[] args) {
		stack<Integer> s = new stack<Integer>();
		s.pop();	// empty
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);
		s.push(6);	// full
		System.out.println(s.pop()); // pop the last number: 5

	}

}
