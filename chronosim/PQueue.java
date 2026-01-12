package chronosim;

import java.util.Arrays;
import java.util.Comparator;

// Priority queue for scheduling orders
public class PQueue {
	private queueObj[] heapArray = new queueObj[128]; //Initial array size 128
	private int heapSize;
	
	public PQueue() {
		this.heapSize = 0;
	}
	
	private void minHeapify(queueObj[] A, int i) {
		//FIXME: Fix broken logic that produces inaccurate outcome
		
		int leftIndex = 2 * i + 1;
		int rightIndex = 2 * i + 2;
		
		if (leftIndex >= heapSize) {
			return;
		}
		
		int currentTime = A[i].getTime();
		int leftTime = A[leftIndex].getTime();
		int rightTime = A[rightIndex].getTime();
			
		int min = Integer.min(Integer.min(leftTime, rightTime), currentTime);
		
		if (A[i].scheduledTime != min) {
			queueObj tempOrder = A[i];
			if (leftTime > rightTime) {
				A[i] = A[rightIndex];
				A[rightIndex] = tempOrder;
				minHeapify(A, rightIndex);
			} else if (rightTime > leftTime) {
				A[i] = A[leftIndex];
				A[leftIndex] = tempOrder;
				minHeapify(A, leftIndex);
			}
		}
		
		return;
	}
	
	private void bubbleUp(queueObj[] A, int i) {
		int currentIndex = i;
		int parentIndex;
		if (currentIndex > 0) {
			parentIndex = (currentIndex - 1) / 2;
		} else {
			System.out.println("Order inserted at index " + currentIndex + " with scheduled time " + A[currentIndex].getTime());
			return;
		}
		
		if (A[currentIndex].scheduledTime >= A[parentIndex].scheduledTime) {
			System.out.println("Order inserted at index " + currentIndex + " with scheduled time " + A[currentIndex].getTime());
			return;
		}
		
		queueObj t = A[parentIndex];
		A[parentIndex] = A[currentIndex];
		A[currentIndex] = t;
		
		bubbleUp(A, parentIndex);
	}
	
	public Order minimum() {
		if (heapArray.length < 1) {
			return null;
		}
		return heapArray[0].getOrder();
	}
	
	public int nextScheduledTime() {
		if (heapSize < 1) {
			return -1;
		}
		
		return heapArray[0].getTime();
	}
	
	public void insert(int scheduledTime, Order o, Action action) {
		heapSize++;
		int insertIndex = heapSize - 1;
		System.out.println("Heapsize: " + heapSize);
		
		//TODO: dynamic resizing.  For testing, it exits on overflow.
		assert heapSize < heapArray.length : "Queue size exceeds array length";
		
		heapArray[insertIndex] = new queueObj(o, scheduledTime, action);
		System.out.println("Item added with id " + o.getId());
		
		bubbleUp(heapArray, insertIndex);	
	}
	
	public Order extractMin() {
		if (heapSize < 1) {
			return null;
		}
		
		queueObj t = heapArray[0];
		heapArray[0] = heapArray[heapSize - 1];
		heapSize -= 1;
		minHeapify(heapArray, 0);
		
		return t.getOrder();
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
