package stack;

public class stacktest {

	public static void main(String[] args) {
		stack<Integer> s = new stack<Integer>();
		s.pop();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);
		s.push(6);
		System.out.println(s.pop());

	}

}
