package producer;

import java.text.DateFormat;
//import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.I0Itec.zkclient.ZkClient;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import kafka.admin.AdminUtils;

import kafka.utils.ZKStringSerializer$;
import kafka.utils.ZkUtils;
import org.I0Itec.zkclient.ZkConnection;

public class SimpleProducer {

	// String[] nombres_empresas =
	// {"BBVA","Vodafone","Movistar","Apple","Dell","Fontbella","GSK","Adidas","Nike","Reebook"};
	// int[] valor = {1000,506,33,2,8,67,43,12,10,543};

	private String topic_name;

	private KafkaProducer<String, String> producer;

	// String zookeeperConnect = "localhost:2181";
	String zookeeperConnect = "10.0.2.15:2181,10.0.2.5:2181,10.0.2.4:2181";
	int sessionTimeoutMs = 10 * 1000;
	int connectionTimeoutMs = 8 * 1000;
	// Note: You must initialize the ZkClient with ZKStringSerializer. If you
	// don't, then
	// createTopic() will only seem to work (it will return without error). The
	// topic will exist in
	// only ZooKeeper and will be returned when listing topics, but Kafka itself
	// does not create the
	// topic.

	public SimpleProducer(String topic, int partitions, int replication) {
		// TODO Auto-generated constructor stub

		this.topic_name = topic;

		createTopic(topic, partitions, replication);

		Properties props = new Properties();
		// props.put("bootstrap.servers", "localhost:9092");
		props.put("bootstrap.servers", "10.0.2.15:9092,10.0.2.5:9092,10.0.2.4:9092");
		/*
		 * Serializer for conversion the key type to bytes
		 */
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		/*
		 * Serializer for conversion the value type to bytes
		 */
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		producer = new KafkaProducer<>(props);

	}

	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	// Date date = new Date();

	// public void produceAndPrint() {
	// for (int i = 0; i < 9; i++){
	// producer.send(new ProducerRecord<String,
	// String>(this.topic_name,String.valueOf(i),"30-03-2017
	// "+String.valueOf(valor[i])));
	// }
	// }

	private void createTopic(String topic, int partitions, int replication) {

		ZkClient zkClient = new ZkClient(zookeeperConnect, sessionTimeoutMs, connectionTimeoutMs,
				ZKStringSerializer$.MODULE$);

		// Security for Kafka was added in Kafka 0.9.0.0
		boolean isSecureKafkaCluster = false;
		ZkUtils zkUtils = new ZkUtils(zkClient, new ZkConnection(zookeeperConnect), isSecureKafkaCluster);

		if (!AdminUtils.topicExists(zkUtils, topic)) {

			Properties topicConfig = new Properties(); // add per-topic
														// configurations
														// settings here
			AdminUtils.createTopic(zkUtils, topic, partitions, replication, topicConfig, null);
		}

		zkClient.close();

	}

	public void stop() {
		producer.close();
	}

	public void send(String date, String val) {

		System.out.println("SEND " + this.topic_name);
		System.out.println("date:" + date);
		System.out.println("value:" + val + "\n");

		ProducerRecord<String, String> record = new ProducerRecord<String, String>(this.topic_name, date,
				this.topic_name + " - " + val + " - " + date);
		this.producer.send(record);

	}

}
