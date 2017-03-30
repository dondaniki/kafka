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
		
		List<String> topics_C1 = Arrays.asList("BBVA","Vodafone","Movistar","Apple");
		List<String> topics_C2 = Arrays.asList("Movistar","Apple","Dell","Fontbella");
		List<String> topics_C3 = Arrays.asList("Dell","Fontbella","GSK","Adidas");
		List<String> topics_C4 = Arrays.asList("GSK","Adidas","Nike","Reebook");
		
		
		
			
		Consumer_thread tc1 = new Consumer_thread("tc1",  groupId, topics_C1);
//		tc1.run();
		tc1.start();
		

	}

}
