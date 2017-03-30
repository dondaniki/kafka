package consumer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;

public class Consumer_thread implements Runnable {

	private final KafkaConsumer<String, String> consumer;
	private final List<String> topics;
	//private final int id;

	private String threadName;
	private Thread t;

	//public Consumer_thread(String t_name, String groupId, List<String> topics) {
	public Consumer_thread(String t_name,String groupId,  List<String> topics) {
		
		this.threadName = t_name;
		this.topics = topics;
		Properties props = new Properties();
//		props.put("bootstrap.servers", "localhost:9092");
		props.put("bootstrap.servers", "10.0.2.15:9092,10.0.2.4:9092,10.0.2.5:9092");
		props.put("group.id", groupId);
	     props.put("enable.auto.commit", "true");
	     props.put("auto.commit.interval.ms", "1000");
		props.put("key.deserializer", StringDeserializer.class.getName());
		props.put("value.deserializer", StringDeserializer.class.getName());
		this.consumer = new KafkaConsumer<>(props);
	}

	@Override
	public void run() {
		try {
			consumer.subscribe(topics);

			while (true) {
				ConsumerRecords<String, String> records = consumer.poll(Long.MAX_VALUE);
				for (ConsumerRecord<String, String> record : records) {
					Map<String, Object> data = new HashMap<>();
					data.put("partition", record.partition());
					data.put("offset", record.offset());
					data.put("value", record.value());
					System.out.println(this.threadName + ": " + data);
				}
			}
		} catch (WakeupException e) {
			// ignore for shutdown
		} finally {
			consumer.close();
		}
	}

	public void shutdown() {
		consumer.wakeup();
	}
	
	public void start() {
		System.out.println("Starting " + threadName);
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}
}
