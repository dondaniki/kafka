package producer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import scala.util.Random;

public class Producer_thread implements Runnable {

	private String threadName;
	private Thread t;

	private SimpleProducer prod;
	
	private Random rand;

	public Producer_thread(String topic) {
		// TODO Auto-generated constructor stub
		threadName = topic;
		System.out.println("creating " + threadName);

		int partitions = 6;
		int replication= 2;
		prod = new SimpleProducer(topic, partitions, replication);
		rand = new Random();
	}

	@Override
	public void run() {

		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			
			
			while (true) {
				
			Date cal = new Date();
			System.out.println("LA FECHA! "  + dateFormat.format(cal)); //2016/11/16 12:08:43

			String date = dateFormat.format(cal);
			int value = rand.nextInt(50);
			prod.send(date, Integer.toString(value));

			Thread.sleep(1000);
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

}
