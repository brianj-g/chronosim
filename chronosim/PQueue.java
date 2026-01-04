package chronosim;
import java.util.HashSet;

// Priority queue for scheduling orders
public class PQueue {
	
	
	class queueObj {
		Order o;
		int time;
		Action action;
		
		public queueObj(Order o, int time, Action action) {
			this.o = o;
			this.time = time;
			this.action = action;
		}
		
		public Order getOrder() {
			return o;
		}
		
		public int getTime() {
			return time;
		}
		
		public Action getAction() {
			return action;
		}
	}
}
