package chronosim;
import java.util.HashSet;

// Priority queue for scheduling orders
public class PQueue {
	private queueObj[] heapArray;
	private int heapSize;
	
	public PQueue() {
		this.heapSize = 0;
	}
	
	public queueObj minimum() {
		if (heapArray.length < 1) {
			return null;
		}
		return heapArray[0];
	}
	
	public void minHeapify(queueObj[] A, int h, int i) {
		// int parentIndex = i/2; // For reference temp
		int leftIndex = 2*i;
		int rightIndex = 2*i + 1;
		
		int leftCheck = Integer.min(A[i].getTime(), A[leftIndex].getTime());
		int rightCheck = Integer.min(leftCheck, A[rightIndex].getTime());
		
		
		if (A[i].time != leftCheck) {
			queueObj tempOrder = A[i];
			if (leftCheck != rightCheck) {
				A[i] = A[rightIndex];
				A[rightIndex] = tempOrder;
				minHeapify(A, h, rightIndex);
			} else {
				A[i] = A[leftIndex];
				A[leftIndex] = tempOrder;
				minHeapify(A, h, leftIndex);
			}
		}
		
		return;
	}
	
	
	class queueObj {
		Order o;
		int time;
		Action action;
		
		public queueObj() {
			
		}
		
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
