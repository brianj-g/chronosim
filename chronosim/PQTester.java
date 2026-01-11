package chronosim;

public class PQTester {
	public static void main(String args[]) {
		PQueue pq = new PQueue();
		
		for (int i = 1; i <= 100; ++i) {
			pq.insert((int)(Math.random()*101 + 1), new Order(i), Action.PAYMENT);
		}
		System.out.println("Next scheduled time: " + pq.minimum().scheduledTime);
		pq.insert(1,  new Order(127), Action.PAYMENT);
		System.out.println("Next scheduled time: " + pq.minimum().scheduledTime);
	}	
}
