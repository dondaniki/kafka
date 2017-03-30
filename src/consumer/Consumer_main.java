package consumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import producer.Producer_thread;

public class Consumer_main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		String groupId = "consumer-tutorial-group";
		
		//String[] nombres_empresas = {"BBVA"};
//		String[] nombres_empresas = {"BBVA","Vodafone","Movistar","Apple","Dell","Fontbella","GSK","Adidas","Nike","Reebook"};
		
//		List<String> topics_C1 = Arrays.asList("BBVA","Vodafone","Movistar","Apple");
//		List<String> topics_C2 = Arrays.asList("Movistar","Apple","Dell","Fontbella");
//		List<String> topics_C3 = Arrays.asList("Dell","Fontbella","GSK","Adidas");
//		List<String> topics_C4 = Arrays.asList("GSK","Adidas","Nike","Reebook");
		List<String> topics_C1 = Arrays.asList("BBVA","VODAFONE","MOVISTAR","APPLE");
		List<String> topics_C2 = Arrays.asList("MOVISTAR","APPLE","DELL","FONTBELLA");
		List<String> topics_C3 = Arrays.asList("DELL","FONTBELLA","GSK","ADIDAS");
		List<String> topics_C4 = Arrays.asList("GSK","ADIDAS","NIKE","REEBOOK");		
		
//		topics_C2.add("Movistar");
//		topics_C2.add("Apple");
//		topics_C2.add("Dell");
//		topics_C2.add("Fontbella");
		
		
		
		
		Consumer_thread tc1 = new Consumer_thread("tc1",  groupId, topics_C1);
		tc1.start();
		
		
		Consumer_thread tc2 = new Consumer_thread("tc2",  groupId, topics_C2);
		tc2.start();
		
		
		Consumer_thread tc3 = new Consumer_thread("tc3",  groupId, topics_C3);
		tc3.start();
		
			
		Consumer_thread tc4 = new Consumer_thread("tc4",  groupId, topics_C4);
		tc4.start();
		

	}

}
