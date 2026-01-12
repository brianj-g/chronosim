package chronosim;

public class PQTester {
	public static void main(String args[]) {
		PQueue pq = new PQueue();
		
		for (int i = 1; i <= 100; ++i) {
			pq.insert((int)(Math.random()*101 +1), new Order(i), Action.PAYMENT);
		}
		

		for (int i = 0; i < 20; ++i) {
			System.out.println("Next order: " + pq.minimum().getId() + " at time " + pq.nextScheduledTime());
			Order t = pq.extractMin();
			System.out.println("\nRetrieved order: " + t.getId());
		}
		
	}	
}
