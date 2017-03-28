package producer;

public class producer_thread implements Runnable {

	private String threadName;
	private Thread t;

	public producer_thread(String name) {
		// TODO Auto-generated constructor stub
		threadName = name;
		System.out.println("creating " + threadName);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
			for (int i = 4; i > 0; i--) {
				System.out.println("Thread: " + threadName + ", " + i);
				// Let the thread sleep for a while.
				Thread.sleep(50);
			}
		} catch (InterruptedException e) {
			System.out.println("Thread " + threadName + " interrupted.");
		}
		System.out.println("Thread " + threadName + " exiting.");
	}

	public void start() {
		System.out.println("Starting " + threadName);
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();    
		}
	} 

	// }

}
