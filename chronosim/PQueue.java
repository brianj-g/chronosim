package chronosim;

import java.util.Arrays;
import java.util.Comparator;

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
		//FIXME: This function uses 1-based index and needs to be converted
		// int parentIndex = i/2; // For reference temp
		int leftIndex = 2*i;
		int rightIndex = 2*i + 1;
		
		int leftCheck = Integer.min(A[i].getTime(), A[leftIndex].getTime());
		int rightCheck = Integer.min(leftCheck, A[rightIndex].getTime());
		
		
		if (A[i].scheduledTime != leftCheck) {
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
	
	public void bubbleUp(queueObj[] A, int i) {
		//FIXME: This function uses 1-based index and needs to be converted
		int parentIndex = i / 2;
		queueObj t;
		if (A[i].scheduledTime >= A[parentIndex].scheduledTime) return;
		
		t = A[parentIndex];
		A[parentIndex] = A[i];
		A[i] = t;
		
		bubbleUp(A, parentIndex);
	}
	
	
	class queueObj {
		Order o;
		int scheduledTime;
		Action action;
		
		public queueObj() {
			
		}
		
		public queueObj(Order o, int time, Action action) {
			this.o = o;
			this.scheduledTime = time;
			this.action = action;
		}
		
		public Order getOrder() {
			return o;
		}
		
		public int getTime() {
			return scheduledTime;
		}
		
		public Action getAction() {
			return action;
		}
	}
}
