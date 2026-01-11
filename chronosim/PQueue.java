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
	
	public queueObj minimum() {
		if (heapArray.length < 1) {
			return null;
		}
		return heapArray[0];
	}
	
	private void minHeapify(queueObj[] A, int h, int i) {
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
	
	private void bubbleUp(queueObj[] A, int i) {
		//FIXME: This function uses 1-based index and needs to be converted
		int currentIndex = i;
		int parentIndex;
		if (currentIndex > 0) {
			parentIndex = (currentIndex - 1) / 2;
		} else {
			System.out.println("Skipping index zero");
			return;
		}
		System.out.println("Bubbling up index " + currentIndex + " with parent index " + parentIndex);
		System.out.println("Current scheduled time: " + A[currentIndex].scheduledTime);
		System.out.println("Parent scheduled time: " + A[parentIndex].scheduledTime);
		
		if (A[currentIndex].scheduledTime >= A[parentIndex].scheduledTime) {
			System.out.println("No more bubbling to do.");
			return;
		}
		
		queueObj t = A[parentIndex];
		A[parentIndex] = A[currentIndex];
		A[currentIndex] = t;
		
		bubbleUp(A, parentIndex);
	}
	
	public void insert(int scheduledTime, Order o, Action action) {
		heapSize++;
		int insertIndex = heapSize - 1;
		System.out.println("Heapsize: " + heapSize);
		
		//TODO: dynamic resizing.  For testing, it exits on overflow.
		assert heapSize < heapArray.length;
		
		heapArray[insertIndex] = new queueObj(o, scheduledTime, action);
		System.out.println("Item added at index " + (insertIndex));
		
		bubbleUp(heapArray, insertIndex);
		
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
