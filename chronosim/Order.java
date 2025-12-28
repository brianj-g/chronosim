package chronosim;

public class Order {
	private int id;
	private OrderStatus status;
	private int retryCount;
	
	Order(int id){
		this.id = id;
		this.retryCount = 0;
	}
	
	public int getId() {
		return id;
	}
	
	public int getRetryCount() {
		return retryCount;
	}
	
	public void incrementRetryCount() {
		retryCount += 1;
	}
	
	public OrderStatus getStatus() {
		return status;
	}
	
	public void markPaid() {
		status = OrderStatus.PAID;
	}
	
	public void markRetrying() {
		status = OrderStatus.RETRYING;
	}
	
	public void markFailed() {
		status = OrderStatus.FAILED;
	}
	
	public void markFulfilled() {
		status = OrderStatus.FULFILLED;
	}
	
	public void markShipped() {
		status = OrderStatus.SHIPPED;
	}
	
}
